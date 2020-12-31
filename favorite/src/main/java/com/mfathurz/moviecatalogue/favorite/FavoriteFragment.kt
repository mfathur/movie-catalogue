package com.mfathurz.moviecatalogue.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.mfathurz.moviecatalogue.R
import com.mfathurz.moviecatalogue.favorite.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.favoriteToolbar.title = "Favorite"
        binding.favoriteToolbar.setNavigationIcon(R.drawable.ic_arrow_back)

        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity).setSupportActionBar(binding.favoriteToolbar)
        }

        binding.favoriteToolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }

        binding.viewPager.adapter = FavoriteViewPagerAdapter(requireActivity())

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { currentTab, currentPosition ->
            currentTab.text = when (currentPosition) {
                FavoriteViewPagerAdapter.FAVORITE_MOVIE_FRAGMENT -> getString(R.string.movie_fragment_title)
                FavoriteViewPagerAdapter.FAVORITE_TV_SHOW_FRAGMENT -> getString(R.string.tv_show_fragment_title)
                else -> getString(R.string.viewpager_error)
            }
        }.attach()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}