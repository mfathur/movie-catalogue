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
import com.mfathurz.moviecatalogue.core.Resource
import com.mfathurz.moviecatalogue.core.domain.model.TVShow
import com.mfathurz.moviecatalogue.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_tv_show.*
import org.koin.android.viewmodel.ext.android.viewModel


class TVShowFragment : Fragment() {

    private val viewModel: TVShowViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val factory = ViewModelFactory.getInstance(requireActivity())
//        viewModel = ViewModelProvider(this, factory)[TVShowViewModel::class.java]

        val recyclerAdapter = TVShowRecyclerAdapter()
        progressBar.visibility = View.VISIBLE

        viewModel.popularTVShows.observe(viewLifecycleOwner, { listTVShows ->
            when (listTVShows) {
                is Resource.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    progressBar.visibility = View.GONE
                    recyclerAdapter.submitList(listTVShows.data)
                }
                is Resource.Error -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), listTVShows.message, Toast.LENGTH_SHORT).show()
                }
            }

        })


        rvTVShow.apply {
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

}