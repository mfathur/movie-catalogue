package com.mfathurz.moviecatalogue.ui.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.mfathurz.moviecatalogue.R
import kotlinx.android.synthetic.main.activity_favorite.*

class FavoriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        favoriteToolbar.title = "Favorite"
        setSupportActionBar(favoriteToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        viewPager.adapter = FavoriteViewPagerAdapter(this)

        TabLayoutMediator(tabLayout, viewPager) { currentTab, currentPosition ->
            currentTab.text = when (currentPosition) {
                FavoriteViewPagerAdapter.FAVORITE_MOVIE_FRAGMENT -> getString(R.string.movie_fragment_title)
                FavoriteViewPagerAdapter.FAVORITE_TV_SHOW_FRAGMENT -> getString(R.string.tv_show_fragment_title)
                else -> getString(R.string.viewpager_error)
            }
        }.attach()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}