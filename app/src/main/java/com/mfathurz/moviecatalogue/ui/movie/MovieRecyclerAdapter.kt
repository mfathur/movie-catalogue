package com.mfathurz.moviecatalogue.ui.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mfathurz.moviecatalogue.R
import com.mfathurz.moviecatalogue.model.MovieEntity
import kotlinx.android.synthetic.main.item_movie_tv_show_recycler.view.*

class MovieRecyclerAdapter : RecyclerView.Adapter<MovieRecyclerAdapter.MovieViewHolder>() {

    private val listMovies = ArrayList<MovieEntity>()

    fun setMovies(movies: List<MovieEntity>?) {
        movies?.let {
            listMovies.clear()
            listMovies.addAll(it)
        }
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie_tv_show_recycler, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listMovies[position]
        holder.itemView.apply {
            item_txt_title.text = movie.title
            item_txt_category.text = movie.category
            item_txt_status.text = movie.status
        }
    }

    override fun getItemCount(): Int = listMovies.size

}