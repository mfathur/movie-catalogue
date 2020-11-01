package com.mfathurz.moviecatalogue.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import com.mfathurz.moviecatalogue.R
import com.mfathurz.moviecatalogue.model.MovieEntity
import com.mfathurz.moviecatalogue.model.TVShowEntity
import com.mfathurz.moviecatalogue.ui.movie.MovieFragment
import com.mfathurz.moviecatalogue.ui.tv.TVShowFragment
import com.mfathurz.moviecatalogue.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val DATA_TYPE = "data_type"
    }

    private lateinit var viewModel: DetailViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val factory = ViewModelFactory.getInstance()
        viewModel = ViewModelProvider(this,factory)[DetailViewModel::class.java]

        btnBackToHome.setOnClickListener(this)
        btnShare.setOnClickListener(this)

        val bundle = intent.extras
        bundle?.let {
            val type = it.getInt(DATA_TYPE)
            viewModel.setDataType(type)
            when (type) {
                MovieFragment.DATA_MOVIE -> {
                    val data = intent.getParcelableExtra<MovieEntity>(EXTRA_DATA)
                    viewModel.setMovieData(data)
                }

                TVShowFragment.DATA_TV_SHOW -> {
                    val data = intent.getParcelableExtra<TVShowEntity>(EXTRA_DATA)
                    viewModel.setTVShowData(data)
                }
            }
        }
        populateData()
    }

    private fun populateData() {
        when (viewModel.getDataType()) {
            MovieFragment.DATA_MOVIE -> {
                val movie = viewModel.getMovieData()
                imgPoster.load(movie.imageUrl) {
                    crossfade(true)
                    placeholder(R.drawable.image_placeholder)
                }
                txtTitle.text = movie.title
                txtCategory.text = movie.category
                txtCreator.text = movie.director
                txtLanguage.text = movie.language
                txtStatus.text = movie.status
                txtTime.text = movie.time
                txtOverview.text = movie.overview
                txtCast.text = movie.casters
                txtReleasedDate.text = movie.releaseDate
            }

            TVShowFragment.DATA_TV_SHOW -> {
                val tvShow = viewModel.getTVShowData()
                imgPoster.load(tvShow.imageUrl) {
                    crossfade(true)
                    placeholder(R.drawable.image_placeholder)
                }
                txtTitle.text = tvShow.title
                txtCategory.text = tvShow.category
                txtCreator.text = tvShow.creator
                txtLanguage.text = tvShow.language
                txtStatus.text = tvShow.status
                txtTime.text = tvShow.time
                txtOverview.text = tvShow.overview
                txtCast.text = tvShow.casters
                txtReleasedDate.text = tvShow.releaseDate
            }
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnBackToHome -> {
                onBackPressed()
            }

            R.id.btnShare -> {
                val mimeType = "text/plain"
                when (viewModel.getDataType()) {
                    MovieFragment.DATA_MOVIE -> {
                        val movie = viewModel.getMovieData()
                        ShareCompat.IntentBuilder.from(this).apply {
                            setType(mimeType)
                            setChooserTitle(getString(R.string.share_movie_title))
                            setText(getString(R.string.share_movie_message,movie.title))
                            startChooser()
                        }
                    }

                    TVShowFragment.DATA_TV_SHOW -> {
                        val tvShow = viewModel.getTVShowData()
                        ShareCompat.IntentBuilder.from(this).apply {
                            setType(mimeType)
                            setChooserTitle(getString(R.string.share_tv_show_title))
                            setText(getString(R.string.share_tv_show_message,tvShow.title))
                            startChooser()
                        }
                    }
                }
            }
        }
    }
}