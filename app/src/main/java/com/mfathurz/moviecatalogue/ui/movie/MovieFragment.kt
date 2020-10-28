package com.mfathurz.moviecatalogue.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mfathurz.moviecatalogue.R
import kotlinx.android.synthetic.main.fragment_movie.*


class MovieFragment : Fragment() {

    private lateinit var viewModel: MovieViewModel

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
        recyclerAdapter.setMovies(listMovies)

        rvMovies.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = recyclerAdapter
        }
    }
}