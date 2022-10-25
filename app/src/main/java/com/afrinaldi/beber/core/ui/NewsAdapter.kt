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
import com.afrinaldi.beber.databinding.ItemNewsBinding
import com.bumptech.glide.Glide

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    private var listData = emptyList<News>()
    var onIntentClicked: ((News) -> Unit)? = null
    var onBookmarkClicked: ((News, ItemNewsBinding) -> Unit)? = null

    fun setData(newListData: List<News>?) {
        if (newListData == null) return
        val diffUtil = MyDiffUtil(listData, newListData)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        listData = newListData
        diffResults.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false))

    override fun getItemCount() : Int = if (listData.size > 10) 10 else listData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemNewsBinding.bind(itemView)
        fun bind(data: News) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.image)
                    .into(ivMain)
                tvTitle.text = data.title
                tvContent.text = data.content
                tvDate.text = Helper.formatDate(data.date)

                ivBookmark.background.setTint(ContextCompat.getColor(itemView.context, Helper.setStatusBookmarkItem(data.isBookmark)))
            }
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