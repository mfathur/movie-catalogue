package com.mfathurz.moviecatalogue.favorite.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mfathurz.moviecatalogue.favorite.databinding.FragmentFavoriteMovieBinding
import com.mfathurz.moviecatalogue.favorite.di.favoriteModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteMovieFragment : Fragment() {

    private val viewModel: FavoriteMovieViewModel by viewModel()

    private var _binding: FragmentFavoriteMovieBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(favoriteModule)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val favoriteMovieAdapter = FavoriteMovieAdapter(requireActivity())

        binding.rvFavoriteMovie.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = favoriteMovieAdapter
        }

        showEmptyState(true)
        viewModel.favoriteMovies.observe(viewLifecycleOwner) { list ->
            favoriteMovieAdapter.submitList(list)
            if (list.isNotEmpty()) {
                showEmptyState(false)
            } else {
                showEmptyState(true)
            }
        }
    }

    private fun showEmptyState(state:Boolean){
        if (state){
            binding.emptyImageIndicator.visibility = View.VISIBLE
            binding.emptyTextIndicator.visibility = View.VISIBLE
        } else {
            binding.emptyImageIndicator.visibility = View.GONE
            binding.emptyTextIndicator.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}