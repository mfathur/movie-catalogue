package com.mfathurz.moviecatalogue.ui.movie

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.mfathurz.moviecatalogue.R
import com.mfathurz.moviecatalogue.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_movie.*


class MovieFragment : Fragment(),View.OnClickListener {

    private lateinit var viewModel : MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]

        btnToDetail.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btnToDetail-> {
                val intent = Intent(context,DetailActivity::class.java)
                startActivity(intent)
            }
        }
    }

}