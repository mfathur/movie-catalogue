package com.mfathurz.moviecatalogue.favorite.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mfathurz.moviecatalogue.favorite.databinding.FragmentFavoriteTvShowBinding
import com.mfathurz.moviecatalogue.favorite.di.favoriteModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteTVShowFragment : Fragment() {

    private val viewModel: FavoriteTVShowViewModel by viewModel()

    private var _binding: FragmentFavoriteTvShowBinding? = null
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
        _binding = FragmentFavoriteTvShowBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val favoriteTVShowAdapter = FavoriteTVShowAdapter(requireActivity())

        binding.rvFavoriteTvShow.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = favoriteTVShowAdapter
        }

        showEmptyState(true)

        viewModel.favoriteTVShows.observe(viewLifecycleOwner, { list ->
            favoriteTVShowAdapter.submitList(list)
            if (list.isNotEmpty()) {
                showEmptyState(false)
            } else {
                showEmptyState(true)
            }
        })
    }

    private fun showEmptyState(state: Boolean) {
        if (state) {
            binding.emptyImageIndicator.visibility = View.VISIBLE
            binding.emptyTextIndicator.visibility = View.VISIBLE
        } else {
            binding.emptyImageIndicator.visibility = View.GONE
            binding.emptyTextIndicator.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}