package com.mfathurz.moviecatalogue.ui.tv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mfathurz.moviecatalogue.R
import com.mfathurz.moviecatalogue.model.MovieEntity
import com.mfathurz.moviecatalogue.model.TVShowEntity
import kotlinx.android.synthetic.main.item_movie_tv_show_recycler.view.*

class TVShowRecyclerAdapter : RecyclerView.Adapter<TVShowRecyclerAdapter.MovieViewHolder>() {

    private val listTVShow = ArrayList<TVShowEntity>()

    fun setTVShow(movies: List<TVShowEntity>?) {
        movies?.let {
            listTVShow.clear()
            listTVShow.addAll(it)
        }
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie_tv_show_recycler, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val tvShow = listTVShow[position]
        holder.itemView.apply {
            item_txt_title.text = tvShow.title
            item_txt_category.text = tvShow.category
            item_txt_status.text = tvShow.status
        }
    }

    override fun getItemCount(): Int = listTVShow.size

}