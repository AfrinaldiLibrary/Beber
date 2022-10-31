package com.afrinaldi.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    val title: String,
    val category: String,
    val name: String,
    val date: String,
    val image: String,
    val content: String,
    val author: String,
    val url: String,
    val isBookmark: Boolean,
): Parcelable