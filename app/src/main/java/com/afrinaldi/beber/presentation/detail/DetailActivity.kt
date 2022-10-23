package com.afrinaldi.beber.presentation.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.afrinaldi.beber.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private var _binding : ActivityDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}