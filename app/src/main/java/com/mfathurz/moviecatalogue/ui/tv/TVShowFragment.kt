package com.mfathurz.moviecatalogue.ui.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mfathurz.moviecatalogue.R
import kotlinx.android.synthetic.main.fragment_tv_show.*


class TVShowFragment : Fragment() {

    private lateinit var viewModel: TVShowViewModel

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
        recyclerAdapter.setTVShow(listTVShow)

        rvTVShow.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = recyclerAdapter
        }
    }

}