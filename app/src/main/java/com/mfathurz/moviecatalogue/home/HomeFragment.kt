package com.mfathurz.moviecatalogue.home

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.mfathurz.moviecatalogue.R
import com.mfathurz.moviecatalogue.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity).setSupportActionBar(binding.homeToolbar)
        }

        binding.viewPager.adapter = HomeViewPagerAdapter(requireActivity())

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { currentTab, currentPosition ->
            currentTab.text = when (currentPosition) {
                HomeViewPagerAdapter.MOVIE_FRAGMENT -> getString(R.string.movie_fragment_title)
                HomeViewPagerAdapter.TV_SHOW_FRAGMENT -> getString(R.string.tv_show_fragment_title)
                else -> getString(R.string.viewpager_error)
            }
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.home_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_favorite -> {
                val action = HomeFragmentDirections.actionHomeFragmentToFavoriteFragment()
                findNavController().navigate(action)
                true
            }
            R.id.nav_setting -> {
                val intent = Intent(Settings.ACTION_SETTINGS)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}