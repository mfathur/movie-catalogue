package com.mfathurz.moviecatalogue.favorite

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mfathurz.moviecatalogue.favorite.movie.FavoriteMovieFragment
import com.mfathurz.moviecatalogue.favorite.tv.FavoriteTVShowFragment

class FavoriteViewPagerAdapter(fa: Fragment) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            FAVORITE_MOVIE_FRAGMENT -> FavoriteMovieFragment()
            FAVORITE_TV_SHOW_FRAGMENT -> FavoriteTVShowFragment()
            else -> throw IllegalStateException("invalid adapter position")
        }
    }

    companion object {
        const val FAVORITE_MOVIE_FRAGMENT = 0
        const val FAVORITE_TV_SHOW_FRAGMENT = 1
    }
}