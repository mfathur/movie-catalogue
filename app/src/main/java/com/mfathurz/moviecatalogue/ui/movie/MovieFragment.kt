package com.mfathurz.moviecatalogue.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mfathurz.moviecatalogue.R
import com.mfathurz.moviecatalogue.core.Resource
import com.mfathurz.moviecatalogue.core.domain.model.Movie
import com.mfathurz.moviecatalogue.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_movie.*
import org.koin.android.viewmodel.ext.android.viewModel


class MovieFragment : Fragment() {

    private val viewModel: MovieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val factory = ViewModelFactory.getInstance(requireActivity())
//        viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

        val recyclerAdapter = MovieRecyclerAdapter()

        viewModel.popularMovies.observe(viewLifecycleOwner, { listMovies ->
            when (listMovies) {
                is Resource.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    progressBar.visibility = View.GONE
                    recyclerAdapter.submitList(listMovies.data)
                }
                is Resource.Error -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), listMovies.message, Toast.LENGTH_SHORT).show()
                }
            }

        })

        rvMovies.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recyclerAdapter
        }

        recyclerAdapter.setOnItemClickedCallback(object : MovieRecyclerAdapter.OnItemClickCallback {
            override fun onItemClicked(movie: Movie) {
                val intent = Intent(context, DetailActivity::class.java)
                intent.apply {
                    putExtra(DetailActivity.EXTRA_DATA, movie)
                    putExtra(DetailActivity.DATA_TYPE, DetailActivity.DATA_MOVIE)
                }
                startActivity(intent)
            }
        })
    }

}