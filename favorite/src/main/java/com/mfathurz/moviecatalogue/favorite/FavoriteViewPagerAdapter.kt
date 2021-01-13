package com.mfathurz.moviecatalogue.favorite

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.mfathurz.moviecatalogue.R
import com.mfathurz.moviecatalogue.favorite.movie.FavoriteMovieFragment
import com.mfathurz.moviecatalogue.favorite.tv.FavoriteTVShowFragment

class FavoriteViewPagerAdapter(private val context: Context, fa: FragmentManager) :
    FragmentStatePagerAdapter(fa, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        const val FAVORITE_MOVIE_FRAGMENT = 0
        const val FAVORITE_TV_SHOW_FRAGMENT = 1
    }

    @StringRes
    private val tabTitles =
        intArrayOf(R.string.movie_fragment_title, R.string.tv_show_fragment_title)

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        return when (position) {
            FAVORITE_MOVIE_FRAGMENT -> FavoriteMovieFragment()
            FAVORITE_TV_SHOW_FRAGMENT -> FavoriteTVShowFragment()
            else -> throw IllegalStateException("invalid adapter position")
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return context.resources.getString(tabTitles[position])
    }
}