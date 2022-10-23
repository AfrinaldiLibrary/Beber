package com.afrinaldi.beber.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.afrinaldi.beber.R
import com.afrinaldi.beber.core.domain.model.News
import com.afrinaldi.beber.core.utils.Helper
import com.afrinaldi.beber.core.utils.MyDiffUtil
import com.afrinaldi.beber.databinding.ItemRecommendationBinding
import com.bumptech.glide.Glide
import eightbitlab.com.blurview.RenderScriptBlur

class BreakingNewsAdapter : RecyclerView.Adapter<BreakingNewsAdapter.ViewHolder>() {
    private var listData = emptyList<News>()
    var onIntentClicked: ((News) -> Unit)? = null
    var onBookmarkClicked: ((News, ItemRecommendationBinding) -> Unit)? = null

    fun setData(newListData: List<News>?) {
        if (newListData == null) return
        val diffUtil = MyDiffUtil(listData, newListData)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        listData = newListData
        diffResults.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_recommendation, parent, false))

    override fun getItemCount() : Int = if (listData.size > 5) 5 else listData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemRecommendationBinding.bind(itemView)
        fun bind(data: News) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.image)
                    .into(ivMain)
                tvTitle.text = data.title
                tvDate.text = Helper.formatDate(data.date)

                ivBookmark.setImageDrawable(ContextCompat.getDrawable(itemView.context, Helper.setStatusBookmark(data.isBookmark)))
            }

            binding.blurLayoutBg.setupWith(binding.root, RenderScriptBlur(itemView.context))
                .setBlurRadius(8f)
        }

        init {
            binding.root.setOnClickListener {
                onIntentClicked?.invoke(listData[adapterPosition])
            }
            binding.ivBookmark.setOnClickListener {
                onBookmarkClicked?.invoke(listData[adapterPosition], binding)
            }
        }
    }

}