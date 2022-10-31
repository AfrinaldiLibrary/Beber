package com.afrinaldi.core.utils

import androidx.recyclerview.widget.DiffUtil
import com.afrinaldi.core.domain.model.News

class MyDiffUtil(
    private val oldList: List<News>,
    private val newList: List<News>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].title == newList[newItemPosition].title
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].title != newList[newItemPosition].title -> {
                false
            }
            oldList[oldItemPosition].isBookmark != newList[newItemPosition].isBookmark -> {
                false
            }
            else -> true
        }
    }
}