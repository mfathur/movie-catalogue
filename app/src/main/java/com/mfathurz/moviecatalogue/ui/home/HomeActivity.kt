package com.mfathurz.moviecatalogue.ui.home

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
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
            currentTab.text = when (currentPosition) {
                MOVIE_FRAGMENT -> getString(R.string.movie_fragment_title)
                TV_SHOW_FRAGMENT -> getString(R.string.tv_show_fragment_title)
                else -> getString(R.string.viewpager_error)
            }
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.nav_setting -> {
                val intent = Intent(Settings.ACTION_SETTINGS)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}