package com.afrinaldi.beber.core.utils

import com.afrinaldi.beber.core.data.source.local.entity.NewsEntity
import com.afrinaldi.beber.core.data.source.remote.response.ArticlesItem
import com.afrinaldi.beber.core.domain.model.News
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

object DataMapper {
    fun mapResponsesToEntities(input: List<ArticlesItem>, category: String): List<NewsEntity> {
        val newsList = ArrayList<NewsEntity>()
        input.map {
            val news = NewsEntity(
                title = it.title,
                category = category,
                name = it.source.name,
                date = it.publishedAt,
                image = it.urlToImage ?: "https://picsum.photos/id/1/200/300",
                content = it.content ?: "No content created",
                author = it.author ?: "Anonymous",
                url = it.url,
                isBookmark = false
            )
            newsList.add(news)
        }
        return newsList
    }

    fun mapEntitiesToDomain(input: List<NewsEntity>): List<News> =
        input.map {
            News(
                title = it.title,
                category = it.category,
                name = it.name,
                date = it.date,
                image = it.image,
                content = it.content,
                author = it.author,
                url = it.url,
                isBookmark = it.isBookmark,
            )
        }

    fun mapDomainToEntity(input: News) =
        NewsEntity(
            title = input.title,
            category = input.category,
            name = input.name,
            date = input.date,
            image = input.image,
            content = input.content,
            author = input.author,
            url = input.url,
            isBookmark = input.isBookmark
        )
}