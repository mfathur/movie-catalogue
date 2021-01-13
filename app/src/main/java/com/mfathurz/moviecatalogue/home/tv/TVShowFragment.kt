package com.mfathurz.moviecatalogue.home.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mfathurz.moviecatalogue.core.data.Resource
import com.mfathurz.moviecatalogue.databinding.FragmentTvShowBinding
import org.koin.android.viewmodel.ext.android.viewModel


class TVShowFragment : Fragment() {

    private val viewModel: TVShowViewModel by viewModel()

    private var _binding: FragmentTvShowBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerAdapter = TVShowRecyclerAdapter(requireActivity())
        binding.progressBar.visibility = View.VISIBLE

        viewModel.popularTVShows.observe(viewLifecycleOwner, { listTVShows ->
            when (listTVShows) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    recyclerAdapter.submitList(listTVShows.data)
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), listTVShows.message, Toast.LENGTH_SHORT).show()
                }
            }

        })

        binding.rvTvShow.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recyclerAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}