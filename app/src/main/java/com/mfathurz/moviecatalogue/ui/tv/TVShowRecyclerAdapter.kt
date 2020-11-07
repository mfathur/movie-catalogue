package com.mfathurz.moviecatalogue.ui.tv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.mfathurz.moviecatalogue.R
import com.mfathurz.moviecatalogue.data.remote.model.TVResultsItem
import com.mfathurz.moviecatalogue.util.Constants
import com.mfathurz.moviecatalogue.util.UtilsHelper
import kotlinx.android.synthetic.main.item_movie_tv_show_recycler.view.*

class TVShowRecyclerAdapter :
    ListAdapter<TVResultsItem, TVShowRecyclerAdapter.TVShowViewHolder>(TVShowDiffUtilCallback()) {

    inner class TVShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: TVResultsItem) {
            with(itemView) {
                item_txt_title.text = item.name
                item_txt_date.text = UtilsHelper.changeDateFormat(item.firstAirDate)
                item_txt_overview.text = item.overview

                item_img_poster.load(Constants.POSTER_PATH_BASE_URL + item.posterPath) {
                    placeholder(R.drawable.image_placeholder)
                    crossfade(true)
                    error(R.drawable.ic_broken_image)
                }

                setOnClickListener {
                    onItemClickCallback.onItemClicked(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVShowViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie_tv_show_recycler, parent, false)
        return TVShowViewHolder(view)
    }

    override fun onBindViewHolder(holder: TVShowViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    interface OnItemClickCallback {
        fun onItemClicked(tvResultsItem: TVResultsItem)
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickedCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
}

private class TVShowDiffUtilCallback : DiffUtil.ItemCallback<TVResultsItem>() {
    override fun areItemsTheSame(oldItem: TVResultsItem, newItem: TVResultsItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TVResultsItem, newItem: TVResultsItem): Boolean {
        return oldItem == newItem
    }
}