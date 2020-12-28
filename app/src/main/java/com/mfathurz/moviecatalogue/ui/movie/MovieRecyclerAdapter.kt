package com.mfathurz.moviecatalogue.ui.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.mfathurz.moviecatalogue.R
import com.mfathurz.moviecatalogue.core.domain.model.Movie
import com.mfathurz.moviecatalogue.core.utils.Constants
import com.mfathurz.moviecatalogue.core.utils.UtilsHelper
import kotlinx.android.synthetic.main.item_movie_tv_show_recycler.view.*

class MovieRecyclerAdapter :
    ListAdapter<Movie, MovieRecyclerAdapter.MovieViewHolder>(MOVIE_DIFF_UTIL_CALLBACK) {

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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
                    onItemClickCallback.onItemClicked(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie_tv_show_recycler, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    interface OnItemClickCallback {
        fun onItemClicked(movie: Movie)
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickedCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    companion object {
        private val MOVIE_DIFF_UTIL_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(
                oldItem: Movie,
                newItem: Movie
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Movie,
                newItem: Movie
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}