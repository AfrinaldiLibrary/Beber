package com.afrinaldi.beber.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.afrinaldi.beber.R
import com.afrinaldi.beber.core.domain.model.News

class NewsPagerAdapter : RecyclerView.Adapter<NewsPagerAdapter.ViewHolder>() {
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
                holder.rvNews.adapter = adapter
            }

            Category.TECH -> {
                val adapter = NewsAdapter()
                adapter.setData(category)
                holder.rvNews.adapter = adapter
            }

            Category.BUSINESS -> {
                val adapter = NewsAdapter()
                adapter.setData(category)
                holder.rvNews.adapter = adapter
            }

            Category.HEALTH -> {
                val adapter = NewsAdapter()
                adapter.setData(category)
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