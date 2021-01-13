package com.mfathurz.moviecatalogue.home

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import com.mfathurz.moviecatalogue.R
import com.mfathurz.moviecatalogue.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), LifecycleOwner {

    private var _binding: FragmentHomeBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity).setSupportActionBar(binding.homeToolbar)
        }

        binding.viewPager.adapter = HomeViewPagerAdapter(requireContext(), childFragmentManager)
        binding.tabLayout.setupWithViewPager(binding.viewPager)
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