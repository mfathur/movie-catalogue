package com.mfathurz.moviecatalogue.ui.tv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.mfathurz.moviecatalogue.R
import com.mfathurz.moviecatalogue.model.TVShowEntity
import kotlinx.android.synthetic.main.item_movie_tv_show_recycler.view.*

class TVShowRecyclerAdapter :
    ListAdapter<TVShowEntity, TVShowRecyclerAdapter.TVShowViewHolder>(TVShowDiffUtilCallback()) {

    inner class TVShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tvShowEntity: TVShowEntity) {
            with(itemView) {
                item_txt_title.text = tvShowEntity.title
                item_txt_category.text = tvShowEntity.category
                item_txt_status.text = tvShowEntity.status
                item_img_poster.load(tvShowEntity.imageUrl) {
                    placeholder(R.drawable.image_placeholder)
                    crossfade(true)
                }

                setOnClickListener {
                    onItemClickCallback.onItemClicked(tvShowEntity)
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
        fun onItemClicked(tvShowEntity: TVShowEntity)
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickedCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
}

private class TVShowDiffUtilCallback : DiffUtil.ItemCallback<TVShowEntity>() {
    override fun areItemsTheSame(oldItem: TVShowEntity, newItem: TVShowEntity): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: TVShowEntity, newItem: TVShowEntity): Boolean {
        return oldItem == newItem
    }
}