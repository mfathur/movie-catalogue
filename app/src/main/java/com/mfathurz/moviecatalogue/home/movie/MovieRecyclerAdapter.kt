package com.mfathurz.moviecatalogue.home.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.mfathurz.moviecatalogue.R
import com.mfathurz.moviecatalogue.core.databinding.ItemMovieTvShowRecyclerBinding
import com.mfathurz.moviecatalogue.core.domain.model.Movie
import com.mfathurz.moviecatalogue.core.utils.Constants
import com.mfathurz.moviecatalogue.core.utils.Helpers
import com.mfathurz.moviecatalogue.detail.DetailFragment

class MovieRecyclerAdapter :
    ListAdapter<Movie, MovieRecyclerAdapter.MovieViewHolder>(MOVIE_DIFF_UTIL_CALLBACK) {

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemMovieTvShowRecyclerBinding.bind(itemView)
        fun bind(item: Movie) {
            with(binding) {
                itemTxtTitle.text = item.title
                itemTxtDate.text = Helpers.changeDateFormat(item.releaseDate)
                itemTxtOverview.text = item.overview

                itemImgPoster.load(Constants.POSTER_PATH_BASE_URL + item.posterPath) {
                    crossfade(true)
                    placeholder(R.drawable.image_placeholder)
                    error(R.drawable.ic_broken_image)
                }
                root.setOnClickListener {
                    val arg = bundleOf(
                        DetailFragment.EXTRA_DATA to item,
                        DetailFragment.DATA_TYPE to DetailFragment.DATA_MOVIE
                    )
                    val pendingIntent = root.findNavController()
                        .createDeepLink()
                        .setDestination(R.id.detailFragment)
                        .setArguments(arg)
                        .createPendingIntent()

                    pendingIntent.send()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_movie_tv_show_recycler,
                parent,
                false
            )
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
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