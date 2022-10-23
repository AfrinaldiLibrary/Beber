package com.afrinaldi.beber.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    val id: Int,
    val name: String,
    val title: String,
    val date: String,
    val image: String,
    val content: String,
    val author: String,
    val url: String,
    val isBookmark: Boolean,
): Parcelable
