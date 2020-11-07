package com.mfathurz.moviecatalogue.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import com.mfathurz.moviecatalogue.R
import com.mfathurz.moviecatalogue.data.remote.model.MovieResultsItem
import com.mfathurz.moviecatalogue.data.remote.model.TVResultsItem
import com.mfathurz.moviecatalogue.ui.movie.MovieFragment
import com.mfathurz.moviecatalogue.ui.tv.TVShowFragment
import com.mfathurz.moviecatalogue.util.Constants
import com.mfathurz.moviecatalogue.util.UtilsHelper
import com.mfathurz.moviecatalogue.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val DATA_TYPE = "data_type"
    }

    private lateinit var viewModel: DetailViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        btnBackToHome.setOnClickListener(this)
        btnShare.setOnClickListener(this)

        val bundle = intent.extras
        bundle?.let {
            val type = it.getInt(DATA_TYPE)
            viewModel.setDataType(type)
            when (type) {
                MovieFragment.DATA_MOVIE -> {
                    val data = intent.getParcelableExtra<MovieResultsItem>(EXTRA_DATA)
                    viewModel.setMovieData(data as MovieResultsItem)
                }

                TVShowFragment.DATA_TV_SHOW -> {
                    val data = intent.getParcelableExtra<TVResultsItem>(EXTRA_DATA)
                    viewModel.setTVShowData(data as TVResultsItem)
                }
            }
        }
        populateData()
    }

    private fun populateData() {
        when (viewModel.getDataType()) {
            MovieFragment.DATA_MOVIE -> {
                val movie = viewModel.getMovieData()
                imgPoster.load(Constants.POSTER_PATH_BASE_URL + movie.posterPath) {
                    crossfade(true)
                    placeholder(R.drawable.image_placeholder)
                    error(R.drawable.ic_broken_image)
                }
                val genres = viewModel.getMovieGenres()
                CoroutineScope(IO).launch {
                    var genre = ""
                    movie.genreIds?.forEach { genreId ->
                        for (item in genres) {
                            if (item.id == genreId) {
                                genre += item.name + " "
                            }
                        }
                    }
                    withContext(Main) {
                        txtCategory.text = genre
                    }
                }

                txtTitle.text = movie.title
                txtLanguage.text = movie.originalLanguage
                txtPopularity.text = movie.popularity.toString()
                txtRating.text = movie.voteAverage.toString()
                txtOverview.text = movie.overview
                txtReleasedDate.text = UtilsHelper.changeDateFormat(movie.releaseDate)
            }

            TVShowFragment.DATA_TV_SHOW -> {
                val tvShow = viewModel.getTVShowData()
                imgPoster.load(Constants.POSTER_PATH_BASE_URL + tvShow.posterPath) {
                    crossfade(true)
                    placeholder(R.drawable.image_placeholder)
                    error(R.drawable.ic_broken_image)
                }
                val genres = viewModel.getTVShowGenres()
                CoroutineScope(IO).launch {
                    var genre = ""
                    tvShow.genreIds?.forEach { genreId ->
                        for (item in genres) {
                            if (item.id == genreId) {
                                genre += item.name + " "
                            }
                        }
                    }
                    withContext(Main) {
                        txtCategory.text = genre
                    }
                }

                txtTitle.text = tvShow.name
                txtCategory.text = tvShow.genreIds.toString()
                txtLanguage.text = tvShow.originalLanguage
                txtPopularity.text = tvShow.popularity.toString()
                txtRating.text = tvShow.voteAverage.toString()
                txtOverview.text = tvShow.overview
                txtReleasedDate.text = UtilsHelper.changeDateFormat(tvShow.firstAirDate)
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
                            setText(getString(R.string.share_movie_message, movie.title))
                            startChooser()
                        }
                    }

                    TVShowFragment.DATA_TV_SHOW -> {
                        val tvShow = viewModel.getTVShowData()
                        ShareCompat.IntentBuilder.from(this).apply {
                            setType(mimeType)
                            setChooserTitle(getString(R.string.share_tv_show_title))
                            setText(getString(R.string.share_tv_show_message, tvShow.name))
                            startChooser()
                        }
                    }
                }
            }
        }
    }
}