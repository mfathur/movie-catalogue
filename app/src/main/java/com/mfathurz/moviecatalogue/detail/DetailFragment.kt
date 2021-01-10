package com.mfathurz.moviecatalogue.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import coil.api.load
import com.mfathurz.moviecatalogue.R
import com.mfathurz.moviecatalogue.core.domain.model.Movie
import com.mfathurz.moviecatalogue.core.domain.model.TVShow
import com.mfathurz.moviecatalogue.core.utils.Constants
import com.mfathurz.moviecatalogue.core.utils.Helpers
import com.mfathurz.moviecatalogue.core.utils.showToast
import com.mfathurz.moviecatalogue.databinding.FragmentDetailBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.viewmodel.ext.android.viewModel

class DetailFragment : Fragment(), View.OnClickListener {

    private val viewModel: DetailViewModel by viewModel()

    private var _binding: FragmentDetailBinding? = null
    private val binding
        get() = _binding!!

    private var isFavorite: Boolean = false

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val DATA_TYPE = "data_type"

        const val DATA_MOVIE = 1
        const val DATA_TV_SHOW = 2
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBackToHome.setOnClickListener(this)
        binding.btnShare.setOnClickListener(this)
        binding.btnFavorite.setOnClickListener(this)

        arguments?.let {
            val type = it.getInt(DATA_TYPE)
            viewModel.setDataType(type)
            when (type) {
                DATA_MOVIE -> {
                    val data = it.getParcelable<Movie>(EXTRA_DATA)
                    viewModel.setMovieData(data as Movie)
                }

                DATA_TV_SHOW -> {
                    val data = it.getParcelable<TVShow>(EXTRA_DATA)
                    viewModel.setTVShowData(data as TVShow)
                }
                else -> {
                }
            }
            populateData()
        }

        viewModel.isFavorite.observe(viewLifecycleOwner, {
            isFavorite = it
            setButtonFavoriteIcon(it)
        })
    }

    private fun populateData() {
        val type = viewModel.getDataType()

        if (type == DATA_MOVIE) {
            val movie = viewModel.getMovieData()

            binding.imgPoster.load(Constants.POSTER_PATH_BASE_URL + movie.posterPath) {
                crossfade(true)
                placeholder(R.drawable.image_placeholder)
                error(R.drawable.ic_broken_image)
            }

            CoroutineScope(Dispatchers.IO).launch {
                val genres = viewModel.getMovieGenres()
                var count = 0
                val genre = StringBuilder()
                movie.genreIds?.forEach { genreId ->
                    for (item in genres) {
                        if (item.id == genreId) {
                            count++
                            if (count > 1) {
                                genre.append(" | " + item.name)
                            } else {
                                genre.append(item.name)
                            }
                        }
                    }
                }
                withContext(Dispatchers.Main) {
                    binding.txtCategory.text = genre
                }

            }

            binding.txtTitle.text = movie.title
            binding.txtLanguage.text = movie.originalLanguage
            binding.txtPopularity.text = movie.popularity.toString()
            binding.txtRating.text = movie.voteAverage.toString()
            binding.txtOverview.text = movie.overview
            binding.txtReleasedDate.text = Helpers.changeDateFormat(movie.releaseDate)

        } else if (type == DATA_TV_SHOW) {
            val tvShow = viewModel.getTVShowData()
            binding.imgPoster.load(Constants.POSTER_PATH_BASE_URL + tvShow.posterPath) {
                crossfade(true)
                placeholder(R.drawable.image_placeholder)
                error(R.drawable.ic_broken_image)
            }

            CoroutineScope(Dispatchers.IO).launch {
                val genres = viewModel.getTVShowGenres()
                val genre = StringBuilder()
                var count = 0
                tvShow.genreIds?.forEach { genreId ->
                    for (item in genres) {
                        if (item.id == genreId) {
                            count++
                            if (count > 1) {
                                genre.append(" | " + item.name)
                            } else {
                                genre.append(item.name)
                            }
                        }
                    }
                }
                withContext(Dispatchers.Main) {
                    binding.txtCategory.text = genre
                }
            }

            binding.txtTitle.text = tvShow.name
            binding.txtLanguage.text = tvShow.originalLanguage
            binding.txtPopularity.text = tvShow.popularity.toString()
            binding.txtRating.text = tvShow.voteAverage.toString()
            binding.txtOverview.text = tvShow.overview
            binding.txtReleasedDate.text = Helpers.changeDateFormat(tvShow.firstAirDate)
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_back_to_home -> {
                activity?.onBackPressed()
            }
            R.id.btn_share -> {
                val mimeType = "text/plain"
                when (viewModel.getDataType()) {
                    DATA_MOVIE -> {
                        val movie = viewModel.getMovieData()
                        ShareCompat.IntentBuilder.from(requireActivity()).apply {
                            setType(mimeType)
                            setChooserTitle(getString(R.string.share_movie_title))
                            setText(getString(R.string.share_movie_message, movie.title))
                            startChooser()
                        }
                    }

                    DATA_TV_SHOW -> {
                        val tvShow = viewModel.getTVShowData()
                        ShareCompat.IntentBuilder.from(requireActivity()).apply {
                            setType(mimeType)
                            setChooserTitle(getString(R.string.share_tv_show_title))
                            setText(getString(R.string.share_tv_show_message, tvShow.name))
                            startChooser()
                        }
                    }
                }
            }
            R.id.btn_favorite -> {
                val type = viewModel.getDataType()
                if (isFavorite) {
                    showDeleteAlertDialog()
                } else {
                    if (type == DATA_MOVIE) {
                        viewModel.insertFavoriteMovie(viewModel.getMovieData())
                        requireContext().showToast(getString(R.string.add_to_favorite_succeed))
                    } else {
                        viewModel.insertFavoriteTVShow(viewModel.getTVShowData())
                        requireContext().showToast(getString(R.string.add_to_favorite_succeed))
                    }
                    viewModel.setFavorite(!isFavorite)
                }
            }
        }
    }

    private fun showDeleteAlertDialog() {
        val type = viewModel.getDataType()
        val message: String =
            if (type == DATA_MOVIE) {
                getString(R.string.alert_delete_message, "Movie")
            } else {
                getString(R.string.alert_delete_message, "TV Show")
            }

        val alertDialogBuilder = AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.alert_dialog_title))
            .setMessage(message)
            .setNegativeButton(getString(R.string.delete)) { _, _ ->
                if (type == DATA_MOVIE) {
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
            binding.btnFavorite.setImageResource(R.drawable.ic_favorite)
        } else {
            binding.btnFavorite.setImageResource(R.drawable.ic_not_favorite)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}