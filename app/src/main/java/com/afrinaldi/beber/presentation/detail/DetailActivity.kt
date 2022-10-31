package com.afrinaldi.beber.presentation.detail

import android.os.Build.VERSION
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.afrinaldi.beber.databinding.ActivityDetailBinding
import com.afrinaldi.core.domain.model.News
import com.afrinaldi.core.utils.DATA
import com.afrinaldi.core.utils.Helper
import com.bumptech.glide.Glide
import eightbitlab.com.blurview.RenderScriptBlur
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private var _binding : ActivityDetailBinding? = null
    private val binding get() = _binding!!

    private val detailViewModel: DetailViewModel by viewModel()

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
                tvDate.text = Helper.formatDate(detailNews.date)
                tvDetail.text = detailNews.content
                tvSource.text = detailNews.name

                Log.e("cek image", detailNews.image)

                var statusBookmark = detailNews.isBookmark
                ivBookmark.setImageDrawable(ContextCompat.getDrawable(this@DetailActivity, Helper.setStatusBookmark(statusBookmark)))
                binding.ivBookmark.setOnClickListener {
                    statusBookmark = !statusBookmark
                    detailViewModel.setBookmarkTourism(detailNews, statusBookmark)
                    ivBookmark.setImageDrawable(ContextCompat.getDrawable(this@DetailActivity, Helper.setStatusBookmark(statusBookmark)))
                }
            }
        }
    }
}