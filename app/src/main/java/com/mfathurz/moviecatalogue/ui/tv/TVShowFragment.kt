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
import com.mfathurz.moviecatalogue.model.TVShowEntity
import com.mfathurz.moviecatalogue.ui.detail.DetailActivity
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
        viewModel = ViewModelProvider(this)[TVShowViewModel::class.java]

        val listTVShow = viewModel.getAllTVShows()
        val recyclerAdapter = TVShowRecyclerAdapter()
        recyclerAdapter.submitList(listTVShow)

        rvTVShow.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = recyclerAdapter
        }

        recyclerAdapter.setOnItemClickedCallback(object :
            TVShowRecyclerAdapter.OnItemClickCallback {
            override fun onItemClicked(tvShowEntity: TVShowEntity) {
                val intent = Intent(context, DetailActivity::class.java)
                intent.apply {
                    putExtra(DetailActivity.EXTRA_DATA, tvShowEntity)
                    putExtra(DetailActivity.DATA_TYPE, DATA_TV_SHOW)
                }
                startActivity(intent)
            }
        })
    }

}