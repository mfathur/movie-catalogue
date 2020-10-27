package com.mfathurz.moviecatalogue.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mfathurz.moviecatalogue.ui.movie.MovieFragment
import com.mfathurz.moviecatalogue.ui.tv.TVShowFragment


class HomeViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            MOVIE_FRAGMENT -> MovieFragment()
            TV_SHOW_FRAGMENT -> TVShowFragment()
            else -> throw IllegalStateException("invalid adapter position")
        }
    }

    companion object {
       const val MOVIE_FRAGMENT = 0
       const val TV_SHOW_FRAGMENT = 1
    }

}