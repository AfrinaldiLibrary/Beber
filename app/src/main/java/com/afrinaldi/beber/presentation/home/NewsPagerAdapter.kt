package com.afrinaldi.beber.presentation.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.afrinaldi.beber.presentation.detail.DetailActivity
import com.afrinaldi.beber.presentation.detail.DetailViewModel
import com.afrinaldi.core.R
import com.afrinaldi.core.domain.model.News
import com.afrinaldi.core.ui.NewsAdapter
import com.afrinaldi.core.utils.DATA
import com.afrinaldi.core.utils.Helper

class NewsPagerAdapter(private val detailViewModel: DetailViewModel) : RecyclerView.Adapter<NewsPagerAdapter.ViewHolder>() {
    private val categoryMap = LinkedHashMap<Category, List<News>>()

    fun setData(key: Category, newListData: List<News>?) {
        if (newListData == null) return
        categoryMap[key] = newListData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_pager_item, parent, false)
        )

    override fun getItemCount(): Int = categoryMap.size

    private fun getIndexKey(position: Int) = categoryMap.keys.toTypedArray().getOrNull(position)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val key = getIndexKey(position) ?: return
        val category = categoryMap[key] ?: return

        when(key) {
            Category.SPORT -> {
                val adapter = NewsAdapter()
                adapter.setData(category)
                adapter.onIntentClicked = { news ->
                    Intent(holder.itemView.context, DetailActivity::class.java).also {
                        it.putExtra(DATA, news)
                        holder.itemView.context.startActivity(it)
                    }
                }

                adapter.onBookmarkClicked = { news, item ->
                    var statusBookmark = news.isBookmark
                    statusBookmark = !statusBookmark
                    detailViewModel.setBookmarkTourism(news, statusBookmark)
                    item.ivBookmark.setImageDrawable(ContextCompat.getDrawable(holder.itemView.context, Helper.setStatusBookmark(statusBookmark)))
                }

                holder.rvNews.adapter = adapter
            }

            Category.TECH -> {
                val adapter = NewsAdapter()
                adapter.setData(category)
                adapter.onIntentClicked = { news ->
                    Intent(holder.itemView.context, DetailActivity::class.java).also {
                        it.putExtra(DATA, news)
                        holder.itemView.context.startActivity(it)
                    }
                }

                adapter.onBookmarkClicked = { news, item ->
                    var statusBookmark = news.isBookmark
                    statusBookmark = !statusBookmark
                    detailViewModel.setBookmarkTourism(news, statusBookmark)
                    item.ivBookmark.setImageDrawable(ContextCompat.getDrawable(holder.itemView.context, Helper.setStatusBookmark(statusBookmark)))
                }

                holder.rvNews.adapter = adapter
            }

            Category.BUSINESS -> {
                val adapter = NewsAdapter()
                adapter.setData(category)
                adapter.onIntentClicked = { news ->
                    Intent(holder.itemView.context, DetailActivity::class.java).also {
                        it.putExtra(DATA, news)
                        holder.itemView.context.startActivity(it)
                    }
                }

                adapter.onBookmarkClicked = { news, item ->
                    var statusBookmark = news.isBookmark
                    statusBookmark = !statusBookmark
                    detailViewModel.setBookmarkTourism(news, statusBookmark)
                    item.ivBookmark.setImageDrawable(ContextCompat.getDrawable(holder.itemView.context, Helper.setStatusBookmark(statusBookmark)))
                }

                holder.rvNews.adapter = adapter
            }

            Category.HEALTH -> {
                val adapter = NewsAdapter()
                adapter.setData(category)

                adapter.onIntentClicked = { news ->
                    Intent(holder.itemView.context, DetailActivity::class.java).also {
                        it.putExtra(DATA, news)
                        holder.itemView.context.startActivity(it)
                    }
                }

                adapter.onBookmarkClicked = { news, item ->
                    var statusBookmark = news.isBookmark
                    statusBookmark = !statusBookmark
                    detailViewModel.setBookmarkTourism(news, statusBookmark)
                    item.ivBookmark.setImageDrawable(ContextCompat.getDrawable(holder.itemView.context, Helper.setStatusBookmark(statusBookmark)))
                }

                holder.rvNews.adapter = adapter
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var rvNews: RecyclerView = itemView.findViewById(R.id.rv_news)
    }

    enum class Category {
        SPORT, TECH, BUSINESS, HEALTH
    }

}