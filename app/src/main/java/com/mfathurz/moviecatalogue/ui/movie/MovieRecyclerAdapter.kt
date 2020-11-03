package com.mfathurz.moviecatalogue.ui.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.mfathurz.moviecatalogue.R
import com.mfathurz.moviecatalogue.db.remote.model.MovieResultsItem
import kotlinx.android.synthetic.main.item_movie_tv_show_recycler.view.*

class MovieRecyclerAdapter :
    ListAdapter<MovieResultsItem, MovieRecyclerAdapter.MovieViewHolder>(MovieDiffUtilCallback()) {

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: MovieResultsItem) {
            with(itemView) {
                item_txt_title.text = item.title
//                item_txt_category.text = item.category
//                item_txt_status.text = movieEntity.status
                item_img_poster.load(item.posterPath) {
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
        fun onItemClicked(movieResultsItem: MovieResultsItem)
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickedCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
}

private class MovieDiffUtilCallback : DiffUtil.ItemCallback<MovieResultsItem>() {
    override fun areItemsTheSame(oldItem: MovieResultsItem, newItem: MovieResultsItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieResultsItem, newItem: MovieResultsItem): Boolean {
        return oldItem == newItem
    }
}