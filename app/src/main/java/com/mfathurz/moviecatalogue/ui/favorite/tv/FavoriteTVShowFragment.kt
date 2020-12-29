package com.mfathurz.moviecatalogue.ui.favorite.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mfathurz.moviecatalogue.R
import kotlinx.android.synthetic.main.fragment_favorite_tv_show.*
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteTVShowFragment : Fragment() {

    private val viewModel: FavoriteTVShowViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_tv_show, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//
//        val factory = ViewModelFactory.getInstance(requireActivity())
//        viewModel = ViewModelProvider(this, factory)[FavoriteTVShowViewModel::class.java]

        val favoriteTVShowAdapter = FavoriteTVShowAdapter(requireActivity())

        rvFavoriteTVShow.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = favoriteTVShowAdapter
        }

        emptyIndicator.visibility = View.VISIBLE

        viewModel.favoriteTVShows.observe(viewLifecycleOwner, { list ->
            favoriteTVShowAdapter.submitList(list)
            if (list.isNotEmpty()) {
                emptyIndicator.visibility = View.GONE
            } else {
                emptyIndicator.visibility = View.VISIBLE
            }

        })
    }

}