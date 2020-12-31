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
    ): View? {
        _binding = FragmentFavoriteMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val favoriteMovieAdapter = FavoriteMovieAdapter()

        binding.rvFavoriteMovie.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = favoriteMovieAdapter
        }

        binding.emptyIndicator.visibility = View.VISIBLE

        viewModel.favoriteMovies.observe(viewLifecycleOwner) { list ->
            favoriteMovieAdapter.submitList(list)
            if (list.isNotEmpty()) {
                binding.emptyIndicator.visibility = View.GONE
            } else {
                binding.emptyIndicator.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}