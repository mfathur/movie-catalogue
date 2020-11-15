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
import com.mfathurz.moviecatalogue.data.remote.model.MovieResultsItem
import com.mfathurz.moviecatalogue.ui.detail.DetailActivity
import com.mfathurz.moviecatalogue.viewmodel.ViewModelFactory
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
        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
        viewModel.getPopularMovies()

        viewModel.isLoading.observe(viewLifecycleOwner, { state ->
            progressBar.visibility = if (state) View.VISIBLE else View.GONE
        })
        val recyclerAdapter = MovieRecyclerAdapter()

        viewModel.popularMovies.observe(viewLifecycleOwner, { listMovies ->
            recyclerAdapter.submitList(listMovies)
        })

        rvMovies.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recyclerAdapter
        }

        recyclerAdapter.setOnItemClickedCallback(object : MovieRecyclerAdapter.OnItemClickCallback {
            override fun onItemClicked(movieResultsItem: MovieResultsItem) {
                val intent = Intent(context, DetailActivity::class.java)
                intent.apply {
                    putExtra(DetailActivity.EXTRA_DATA, movieResultsItem)
                    putExtra(DetailActivity.DATA_TYPE, DetailActivity.DATA_MOVIE)
                }
                startActivity(intent)
            }
        })
    }

}