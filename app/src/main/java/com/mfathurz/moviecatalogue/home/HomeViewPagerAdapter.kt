package com.mfathurz.moviecatalogue.home

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.mfathurz.moviecatalogue.R
import com.mfathurz.moviecatalogue.home.movie.MovieFragment
import com.mfathurz.moviecatalogue.home.tv.TVShowFragment


class HomeViewPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentStatePagerAdapter(
        fm,
        BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ) {

    @StringRes
    private val tabTitles =
        intArrayOf(R.string.movie_fragment_title, R.string.tv_show_fragment_title)

    companion object {
        const val MOVIE_FRAGMENT = 0
        const val TV_SHOW_FRAGMENT = 1
    }

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        return when (position) {
            MOVIE_FRAGMENT -> MovieFragment()
            TV_SHOW_FRAGMENT -> TVShowFragment()
            else -> throw IllegalStateException("invalid adapter position")
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return context.resources.getString(tabTitles[position])
    }

}