package com.niko.filmlist.presentation.activitys

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.niko.filmlist.R
import com.niko.filmlist.databinding.ActivityMainBinding
import com.niko.filmlist.presentation.adapters.FilmsAdapter
import com.niko.filmlist.presentation.viewModels.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding ?: throw RuntimeException("Activity Main == null")
    private val viewModel by lazy {
        ViewModelProvider(
            this
        )[MainActivityViewModel::class.java]
    }
    private val adapter = FilmsAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        if (savedInstanceState == null)
            viewModel.getFilmsList()
        observeLoading()
        initRecView()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(IS_LOADING, true)
    }


    private fun initRecView() {
        binding.recViewMovies.adapter = adapter
        adapter.onReachEndListener = {
            viewModel.getFilmsList()
        }
        viewModel.filmList.observe(this) {
            adapter.submitList(it)
        }
        initTapOnMovie()
    }

    private fun initTapOnMovie() {
        adapter.click = {
            startActivity(FilmDetailActivity.newIntent(this, it))
        }
    }

    private fun observeLoading() {
        viewModel.isLoading.observe(this) {
            if (it)
                binding.progressBar.visibility = View.VISIBLE
            else
                binding.progressBar.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val IS_LOADING = "isLoading"
    }
}