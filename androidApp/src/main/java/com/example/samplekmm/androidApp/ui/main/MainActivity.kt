package com.example.samplekmm.androidApp.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.samplekmm.androidApp.R
import com.example.samplekmm.androidApp.databinding.ActivityMainBinding
import com.example.samplekmm.androidApp.remote.State

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()
        setupListeners()

        viewModel.fetchComic()
    }

    private fun setupObservers() {
        viewModel.comic.observe(this) {
            when (it) {
                is State.Loading -> {
                    disableButton()
                    binding.titleLabel.text = "Loading"
                }
                is State.Success -> {
                    enableButton()
                    binding.titleLabel.text = it.result.title
                    Glide.with(this)
                        .load(it.result.imageUrl)
                        .into(findViewById(R.id.image))
                }
                is State.Error -> {
                    enableButton()
                    binding.titleLabel.text = "Error"
                }
            }
        }
    }

    private fun setupListeners() {
        binding.btnRefresh.setOnClickListener {
            viewModel.fetchComic()
        }
    }

    private fun enableButton() {
        binding.btnRefresh.isEnabled = true
    }

    private fun disableButton() {
        binding.btnRefresh.isEnabled = false
    }
}