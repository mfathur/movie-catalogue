package com.mfathurz.moviecatalogue.ui.favorite.tv

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.mfathurz.moviecatalogue.R
import com.mfathurz.moviecatalogue.core.data.source.local.entity.TVShowEntity
import com.mfathurz.moviecatalogue.ui.detail.DetailActivity
import com.mfathurz.moviecatalogue.core.utils.Constants
import com.mfathurz.moviecatalogue.core.utils.UtilsHelper
import kotlinx.android.synthetic.main.item_movie_tv_show_recycler.view.*

class FavoriteTVShowAdapter(private val activity: Activity) :
    PagedListAdapter<TVShowEntity, FavoriteTVShowAdapter.FavoriteTVShowViewHolder>(
        TV_SHOW_COMPARATOR
    ) {

    inner class FavoriteTVShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tvShow: TVShowEntity) {
            with(itemView) {
                item_txt_title.text = tvShow.name
                item_txt_date.text = UtilsHelper.changeDateFormat(tvShow.firstAirDate)
                item_txt_overview.text = tvShow.overview

                item_img_poster.load(Constants.POSTER_PATH_BASE_URL + tvShow.posterPath) {
                    crossfade(true)
                    placeholder(R.drawable.image_placeholder)
                    error(R.drawable.ic_broken_image)
                }

                setOnClickListener {
                    val intent = Intent(activity, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_DATA, tvShow)
                    intent.putExtra(DetailActivity.DATA_TYPE, DetailActivity.LOCAL_DATA_TV_SHOW)
                    activity.startActivity(intent)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: FavoriteTVShowViewHolder, position: Int) {
        holder.bind(getItem(position) as TVShowEntity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteTVShowViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie_tv_show_recycler, parent, false)
        return FavoriteTVShowViewHolder(view)
    }

    companion object {
        private val TV_SHOW_COMPARATOR = object : DiffUtil.ItemCallback<TVShowEntity>() {
            override fun areItemsTheSame(oldItem: TVShowEntity, newItem: TVShowEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TVShowEntity, newItem: TVShowEntity): Boolean {
                return oldItem == newItem
            }

        }
    }
}