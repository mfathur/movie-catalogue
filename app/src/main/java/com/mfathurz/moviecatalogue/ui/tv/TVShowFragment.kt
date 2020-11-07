package com.mfathurz.moviecatalogue.ui.tv

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mfathurz.moviecatalogue.R
import com.mfathurz.moviecatalogue.data.remote.model.TVResultsItem
import com.mfathurz.moviecatalogue.ui.detail.DetailActivity
import com.mfathurz.moviecatalogue.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_tv_show.*


class TVShowFragment : Fragment() {

    private lateinit var viewModel: TVShowViewModel

    companion object {
        const val DATA_TV_SHOW = 2
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[TVShowViewModel::class.java]
        viewModel.getPopularTVShows()
        viewModel.isLoading.observe(viewLifecycleOwner, { state ->
            progressBar.visibility = if (state) View.VISIBLE else View.GONE
        })

        val recyclerAdapter = TVShowRecyclerAdapter()

        viewModel.popularTVShows.observe(viewLifecycleOwner, { listTVShows ->
            recyclerAdapter.submitList(listTVShows)
        })


        rvTVShow.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recyclerAdapter
        }

        recyclerAdapter.setOnItemClickedCallback(object :
            TVShowRecyclerAdapter.OnItemClickCallback {
            override fun onItemClicked(tvResultsItem: TVResultsItem) {
                val intent = Intent(context, DetailActivity::class.java)
                intent.apply {
                    putExtra(DetailActivity.EXTRA_DATA, tvResultsItem)
                    putExtra(DetailActivity.DATA_TYPE, DATA_TV_SHOW)
                }
                startActivity(intent)
            }
        })
    }

}