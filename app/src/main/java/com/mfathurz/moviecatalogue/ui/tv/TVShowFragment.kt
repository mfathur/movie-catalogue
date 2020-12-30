package com.mfathurz.moviecatalogue.ui.tv

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mfathurz.moviecatalogue.R
import com.mfathurz.moviecatalogue.core.data.Resource
import com.mfathurz.moviecatalogue.core.domain.model.TVShow
import com.mfathurz.moviecatalogue.databinding.FragmentTvShowBinding
import com.mfathurz.moviecatalogue.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_tv_show.*
import org.koin.android.viewmodel.ext.android.viewModel


class TVShowFragment : Fragment() {

    private val viewModel: TVShowViewModel by viewModel()

    private var _binding : FragmentTvShowBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTvShowBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerAdapter = TVShowRecyclerAdapter()
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

        recyclerAdapter.setOnItemClickedCallback(object :
            TVShowRecyclerAdapter.OnItemClickCallback {
            override fun onItemClicked(tvShow: TVShow) {
                val intent = Intent(context, DetailActivity::class.java)
                intent.apply {
                    putExtra(DetailActivity.EXTRA_DATA, tvShow)
                    putExtra(DetailActivity.DATA_TYPE, DetailActivity.DATA_TV_SHOW)
                }
                startActivity(intent)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}