package com.mfathurz.moviecatalogue.ui.favorite.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mfathurz.moviecatalogue.databinding.FragmentFavoriteTvShowBinding
import com.mfathurz.moviecatalogue.databinding.FragmentTvShowBinding
import kotlinx.android.synthetic.main.fragment_favorite_tv_show.*
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteTVShowFragment : Fragment() {

    private val viewModel: FavoriteTVShowViewModel by viewModel()

    private var _binding: FragmentFavoriteTvShowBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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

        binding.emptyIndicator.visibility = View.VISIBLE

        viewModel.favoriteTVShows.observe(viewLifecycleOwner, { list ->
            favoriteTVShowAdapter.submitList(list)
            if (list.isNotEmpty()) {
                binding.emptyIndicator.visibility = View.GONE
            } else {
                binding.emptyIndicator.visibility = View.VISIBLE
            }

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}