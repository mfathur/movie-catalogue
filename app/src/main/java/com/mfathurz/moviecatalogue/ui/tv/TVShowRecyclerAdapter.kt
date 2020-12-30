package com.mfathurz.moviecatalogue.ui.tv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.mfathurz.moviecatalogue.R
import com.mfathurz.moviecatalogue.core.databinding.ItemMovieTvShowRecyclerBinding
import com.mfathurz.moviecatalogue.core.domain.model.TVShow
import com.mfathurz.moviecatalogue.core.utils.Constants
import com.mfathurz.moviecatalogue.core.utils.UtilsHelper

class TVShowRecyclerAdapter :
    ListAdapter<TVShow, TVShowRecyclerAdapter.TVShowViewHolder>(TV_SHOW_DIFF_CALLBACK) {

    inner class TVShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemMovieTvShowRecyclerBinding.bind(itemView)
        fun bind(item: TVShow) {
            with(binding) {
                itemTxtTitle.text = item.name
                itemTxtDate.text = UtilsHelper.changeDateFormat(item.firstAirDate)
                itemTxtOverview.text = item.overview

                itemImgPoster.load(Constants.POSTER_PATH_BASE_URL + item.posterPath) {
                    placeholder(R.drawable.image_placeholder)
                    crossfade(true)
                    error(R.drawable.ic_broken_image)
                }

                root.setOnClickListener {
                    onItemClickCallback.onItemClicked(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVShowViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_movie_tv_show_recycler,
                parent,
                false
            )
        return TVShowViewHolder(view)
    }

    override fun onBindViewHolder(holder: TVShowViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    interface OnItemClickCallback {
        fun onItemClicked(tvShow: TVShow)
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickedCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    companion object {
        private val TV_SHOW_DIFF_CALLBACK = object : DiffUtil.ItemCallback<TVShow>() {
            override fun areItemsTheSame(oldItem: TVShow, newItem: TVShow): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: TVShow,
                newItem: TVShow
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
