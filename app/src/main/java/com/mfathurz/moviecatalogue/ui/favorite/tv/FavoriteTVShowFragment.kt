package com.mfathurz.moviecatalogue.ui.favorite.tv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.mfathurz.moviecatalogue.R
import com.mfathurz.moviecatalogue.viewmodel.ViewModelFactory


class FavoriteTVShowFragment : Fragment() {

    private lateinit var viewModel: FavoriteTVShowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_tv_show, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this,factory)[FavoriteTVShowViewModel::class.java]
    }

}