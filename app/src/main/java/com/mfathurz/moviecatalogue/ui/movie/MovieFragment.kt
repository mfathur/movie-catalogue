package com.mfathurz.moviecatalogue.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mfathurz.moviecatalogue.R
import com.mfathurz.moviecatalogue.model.MovieEntity
import com.mfathurz.moviecatalogue.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_movie.*


class MovieFragment : Fragment() {

    private lateinit var viewModel: MovieViewModel

    companion object {
        const val DATA_MOVIE = 1
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]
        val listMovies = viewModel.getAllMovies()
        val recyclerAdapter = MovieRecyclerAdapter()
        recyclerAdapter.submitList(listMovies)

        rvMovies.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = recyclerAdapter
        }

        recyclerAdapter.setOnItemClickedCallback(object : MovieRecyclerAdapter.OnItemClickCallback {
            override fun onItemClicked(movieEntity: MovieEntity) {
                val intent = Intent(context, DetailActivity::class.java)
                intent.apply {
                    putExtra(DetailActivity.EXTRA_DATA, movieEntity)
                    putExtra(DetailActivity.DATA_TYPE, DATA_MOVIE)
                }
                startActivity(intent)
            }
        })
    }
}