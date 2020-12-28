package com.mfathurz.moviecatalogue.ui.favorite.movie

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
import com.mfathurz.moviecatalogue.core.data.source.local.entity.MovieEntity
import com.mfathurz.moviecatalogue.core.domain.model.Movie
import com.mfathurz.moviecatalogue.core.utils.Constants
import com.mfathurz.moviecatalogue.core.utils.UtilsHelper
import com.mfathurz.moviecatalogue.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.item_movie_tv_show_recycler.view.*

class FavoriteMovieAdapter(private val activity: Activity) :
    PagedListAdapter<Movie, FavoriteMovieAdapter.FavoriteMovieViewHolder>(
        FAVORITE_MOVIE_COMPARATOR
    ) {

    inner class FavoriteMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Movie) {
            with(itemView) {
                item_txt_title.text = item.title
                item_txt_date.text = UtilsHelper.changeDateFormat(item.releaseDate)
                item_txt_overview.text = item.overview

                item_img_poster.load(Constants.POSTER_PATH_BASE_URL + item.posterPath) {
                    crossfade(true)
                    placeholder(R.drawable.image_placeholder)
                    error(R.drawable.ic_broken_image)
                }

                setOnClickListener {
                    val intent = Intent(activity, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_DATA, item)
                    intent.putExtra(DetailActivity.DATA_TYPE, DetailActivity.DATA_MOVIE)
                    activity.startActivity(intent)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: FavoriteMovieViewHolder, position: Int) {
        holder.bind(getItem(position) as Movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie_tv_show_recycler, parent, false)
        return FavoriteMovieViewHolder(view)
    }

    companion object {
        private val FAVORITE_MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }
}