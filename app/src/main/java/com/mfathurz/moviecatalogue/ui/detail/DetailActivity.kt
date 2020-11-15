package com.mfathurz.moviecatalogue.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import com.mfathurz.moviecatalogue.R
import com.mfathurz.moviecatalogue.data.local.room.entity.MovieEntity
import com.mfathurz.moviecatalogue.data.local.room.entity.TVShowEntity
import com.mfathurz.moviecatalogue.data.remote.model.MovieResultsItem
import com.mfathurz.moviecatalogue.data.remote.model.TVResultsItem
import com.mfathurz.moviecatalogue.util.Constants
import com.mfathurz.moviecatalogue.util.UtilsHelper
import com.mfathurz.moviecatalogue.util.showToast
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

        const val DATA_MOVIE = 1
        const val DATA_TV_SHOW = 2
        const val LOCAL_DATA_MOVIE = 3
        const val LOCAL_DATA_TV_SHOW = 4
    }

    private lateinit var viewModel: DetailViewModel
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        btnBackToHome.setOnClickListener(this)
        btnShare.setOnClickListener(this)
        btnFavorite.setOnClickListener(this)

        val bundle = intent.extras
        bundle?.let {
            val type = it.getInt(DATA_TYPE)
            viewModel.setDataType(type)
            when (type) {
                DATA_MOVIE -> {
                    val data = intent.getParcelableExtra<MovieResultsItem>(EXTRA_DATA)
                    viewModel.changeMovieResultItemToEntity(data as MovieResultsItem)
                    viewModel.isAlreadyFavorite(DATA_MOVIE)
                }

                DATA_TV_SHOW -> {
                    val data = intent.getParcelableExtra<TVResultsItem>(EXTRA_DATA)
                    viewModel.changeTVShowResultItemToEntity(data as TVResultsItem)
                    viewModel.isAlreadyFavorite(DATA_TV_SHOW)
                }

                LOCAL_DATA_MOVIE -> {
                    val data = intent.getParcelableExtra<MovieEntity>(EXTRA_DATA)
                    viewModel.setFavorite(true)
                    viewModel.setMovieData(data as MovieEntity)
                }

                LOCAL_DATA_TV_SHOW -> {
                    val data = intent.getParcelableExtra<TVShowEntity>(EXTRA_DATA)
                    viewModel.setFavorite(true)
                    viewModel.setTVShowData(data as TVShowEntity)
                }
                else -> {
                }
            }
        }
        populateData()

        viewModel.isFavorite.observe(this, {
            isFavorite = it
            setButtonFavoriteIcon(it)
        })
    }

    private fun populateData() {
        val type = viewModel.getDataType()

        if (type == DATA_MOVIE || type == LOCAL_DATA_MOVIE) {
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

        } else if (type == LOCAL_DATA_TV_SHOW || type == DATA_TV_SHOW) {
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

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnBackToHome -> {
                onBackPressed()
            }

            R.id.btnShare -> {
                val mimeType = "text/plain"
                when (viewModel.getDataType()) {
                    DATA_MOVIE or LOCAL_DATA_MOVIE -> {
                        val movie = viewModel.getMovieData()
                        ShareCompat.IntentBuilder.from(this).apply {
                            setType(mimeType)
                            setChooserTitle(getString(R.string.share_movie_title))
                            setText(getString(R.string.share_movie_message, movie.title))
                            startChooser()
                        }
                    }

                    DATA_TV_SHOW -> {
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

            R.id.btnFavorite -> {
                val type = viewModel.getDataType()
                if (isFavorite) {
                    showDeleteAlertDialog()
                } else {
                    if (type == DATA_MOVIE || type == LOCAL_DATA_MOVIE) {
                        viewModel.insertFavoriteMovie(viewModel.getMovieData())
                        showToast(getString(R.string.add_to_favorite_succeed))
                    } else {
                        viewModel.insertFavoriteTVShow(viewModel.getTVShowData())
                        showToast(getString(R.string.add_to_favorite_succeed))
                    }
                    viewModel.setFavorite(!isFavorite)
                }

            }
        }
    }

    private fun showDeleteAlertDialog() {
        val type = viewModel.getDataType()
        val message: String =
            if (type == DATA_MOVIE || type == LOCAL_DATA_MOVIE) {
                getString(R.string.alert_delete_message, "Movie")
            } else {
                getString(R.string.alert_delete_message, "TV Show")
            }

        val alertDialogBuilder = AlertDialog.Builder(this)
            .setTitle(getString(R.string.alert_dialog_title))
            .setMessage(message)
            .setNegativeButton(getString(R.string.delete)) { _, _ ->
                if (type == DATA_MOVIE || type == LOCAL_DATA_MOVIE) {
                    viewModel.deleteFavoriteMovie(viewModel.getMovieData())
                } else {
                    viewModel.deleteFavoriteTVShow(viewModel.getTVShowData())
                }
                viewModel.setFavorite(!isFavorite)
            }
            .setPositiveButton(getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun setButtonFavoriteIcon(state: Boolean) {
        if (state) {
            btnFavorite.setImageResource(R.drawable.ic_favorite)
        } else {
            btnFavorite.setImageResource(R.drawable.ic_not_favorite)
        }
    }
}