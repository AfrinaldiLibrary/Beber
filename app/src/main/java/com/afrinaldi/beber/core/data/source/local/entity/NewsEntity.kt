package com.afrinaldi.beber.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class NewsEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "category")
    var category: String,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "date")
    var date: String,

    @ColumnInfo(name = "image")
    var image: String,

    @ColumnInfo(name = "content")
    var content: String,

    @ColumnInfo(name = "author")
    var author: String,

    @ColumnInfo(name = "url")
    var url: String,

    @ColumnInfo(name = "isBookmark")
    var isBookmark: Boolean = false
)
