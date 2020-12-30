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
import com.mfathurz.moviecatalogue.core.databinding.ItemMovieTvShowRecyclerBinding
import com.mfathurz.moviecatalogue.core.domain.model.TVShow
import com.mfathurz.moviecatalogue.core.utils.Constants
import com.mfathurz.moviecatalogue.core.utils.UtilsHelper
import com.mfathurz.moviecatalogue.ui.detail.DetailActivity


class FavoriteTVShowAdapter(private val activity: Activity) :
    PagedListAdapter<TVShow, FavoriteTVShowAdapter.FavoriteTVShowViewHolder>(
        TV_SHOW_COMPARATOR
    ) {

    inner class FavoriteTVShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemMovieTvShowRecyclerBinding.bind(itemView)
        fun bind(tvShow: TVShow) {
            with(binding) {
                itemTxtTitle.text = tvShow.name
                itemTxtDate.text = UtilsHelper.changeDateFormat(tvShow.firstAirDate)
                itemTxtOverview.text = tvShow.overview

                itemImgPoster.load(Constants.POSTER_PATH_BASE_URL + tvShow.posterPath) {
                    crossfade(true)
                    placeholder(R.drawable.image_placeholder)
                    error(R.drawable.ic_broken_image)
                }

                root.setOnClickListener {
                    val intent = Intent(activity, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_DATA, tvShow)
                    intent.putExtra(DetailActivity.DATA_TYPE, DetailActivity.DATA_TV_SHOW)
                    activity.startActivity(intent)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: FavoriteTVShowViewHolder, position: Int) {
        holder.bind(getItem(position) as TVShow)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteTVShowViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_movie_tv_show_recycler,
                parent,
                false
            )
        return FavoriteTVShowViewHolder(view)
    }

    companion object {
        private val TV_SHOW_COMPARATOR = object : DiffUtil.ItemCallback<TVShow>() {
            override fun areItemsTheSame(oldItem: TVShow, newItem: TVShow): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TVShow, newItem: TVShow): Boolean {
                return oldItem == newItem
            }
        }
    }
}