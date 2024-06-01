package com.niko.filmlist.presentation.activitys

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.niko.filmlist.R
import com.niko.filmlist.databinding.ActivityFilmDetailBinding
import com.niko.filmlist.domain.models.Film
import com.niko.filmlist.presentation.adapters.ReviewAdapter
import com.niko.filmlist.presentation.adapters.TrailerAdapter
import com.niko.filmlist.presentation.viewModels.FilmDetailViewModel
import com.squareup.picasso.Picasso

class FilmDetailActivity : AppCompatActivity() {
    private var _binding: ActivityFilmDetailBinding? = null
    private val binding: ActivityFilmDetailBinding
        get() = _binding ?: throw RuntimeException("Film Detail Activity == null")
    private val film: Film by lazy {
        intent.getParcelableExtra(FILM) as Film? ?: throw RuntimeException("")
    }
    private val viewModel by lazy {
        ViewModelProvider(
            this
        )[FilmDetailViewModel::class.java]
    }
    private val trailerAdapter = TrailerAdapter()
    private val reviewAdapter = ReviewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFilmDetailBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        getInfo()
        setDetail()
    }

    private fun getInfo() {
        viewModel.getMovieTrailers(filmId = film.id)
        viewModel.getReviews(film.id)
    }

    private fun setDetail() {
        Picasso.get().load(film.poster?.url).resize(1024, 1024).centerInside()
            .into(binding.imgPoster)
        binding.tvTitle.text = film.name
        binding.tvYear.text = film.year.toString()
        binding.tvDesc.text = film.description
        setTrailers()
        setReviews()
    }

    private fun setReviews() {
        binding.recViewReview.adapter = reviewAdapter
        viewModel.reviewList.observe(this) {
            reviewAdapter.submitList(it)
        }
        reviewAdapter.uploadReview = {
            viewModel.getReviews(film.id)
        }
    }

    private fun setTrailers() {
        binding.recViewTrailes.adapter = trailerAdapter
        trailerAdapter.itemClick = {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(it.url)
            startActivity(intent)
        }
        viewModel.trailersList.observe(this) {
            trailerAdapter.submitList(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val FILM = "film"
        fun newIntent(context: Context, film: Film): Intent {
            return Intent(context, FilmDetailActivity::class.java).apply {
                this.putExtra(FILM, film)
            }
        }
    }
}