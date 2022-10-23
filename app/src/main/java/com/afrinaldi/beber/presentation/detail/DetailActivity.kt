package com.afrinaldi.beber.presentation.detail

import android.os.Build.VERSION
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.afrinaldi.beber.core.domain.model.News
import com.afrinaldi.beber.core.utils.DATA
import com.afrinaldi.beber.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide
import eightbitlab.com.blurview.RenderScriptBlur

class DetailActivity : AppCompatActivity() {

    private var _binding : ActivityDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val detailNews = if (VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(DATA, News::class.java)
        } else {
            intent.getParcelableExtra<News>(DATA)
        }

        showDetailNews(detailNews)
    }

    private fun showDetailNews(detailNews: News?) {
        detailNews?.let {
            with(binding) {
                Glide.with(this@DetailActivity)
                    .load(detailNews.image)
                    .into(ivBlurBg)

                blurLayout.setupWith(binding.root, RenderScriptBlur(this@DetailActivity))
                    .setBlurRadius(20f)

                Glide.with(this@DetailActivity)
                    .load(detailNews.image)
                    .into(ivMain)

                tvTitle.text = detailNews.title
                tvAuthor.text = detailNews.author
                tvDate.text = detailNews.date
                tvDetail.text = detailNews.content
                tvSource.text = detailNews.name
            }
        }
    }
}