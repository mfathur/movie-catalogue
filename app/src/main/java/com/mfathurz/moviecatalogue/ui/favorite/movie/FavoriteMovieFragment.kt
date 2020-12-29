package com.mfathurz.moviecatalogue.ui.favorite.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mfathurz.moviecatalogue.R
import kotlinx.android.synthetic.main.fragment_favorite_movie.*
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteMovieFragment : Fragment() {

    private val viewModel: FavoriteMovieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val favoriteMovieAdapter = FavoriteMovieAdapter(requireActivity())

        rvFavoriteMovie.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = favoriteMovieAdapter
        }

        emptyIndicator.visibility = View.VISIBLE

        viewModel.favoriteMovies.observe(viewLifecycleOwner) { list ->
            favoriteMovieAdapter.submitList(list)
            if (list.isNotEmpty()) {
                emptyIndicator.visibility = View.GONE
            } else {
                emptyIndicator.visibility = View.VISIBLE
            }
        }
    }

}