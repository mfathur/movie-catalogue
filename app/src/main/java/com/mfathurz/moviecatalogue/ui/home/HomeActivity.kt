package com.mfathurz.moviecatalogue.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.mfathurz.moviecatalogue.R
import com.mfathurz.moviecatalogue.ui.home.HomeViewPagerAdapter.Companion.MOVIE_FRAGMENT
import com.mfathurz.moviecatalogue.ui.home.HomeViewPagerAdapter.Companion.TV_SHOW_FRAGMENT
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        toolbar.title = ""
        setSupportActionBar(toolbar)

        viewPager.adapter = HomeViewPagerAdapter(this)

        TabLayoutMediator(tabLayout, viewPager) { currentTab, currentPosition ->
            currentTab.text = when (currentPosition){
                MOVIE_FRAGMENT -> getString(R.string.movie_fragment_title)
                TV_SHOW_FRAGMENT -> getString(R.string.tv_show_fragment_title)
                else -> getString(R.string.viewpager_error)
            }
        }.attach()
    }
}