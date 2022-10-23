package com.afrinaldi.beber.core.utils

import com.afrinaldi.beber.core.data.source.local.entity.NewsEntity
import com.afrinaldi.beber.core.data.source.remote.response.ArticlesItem
import com.afrinaldi.beber.core.domain.model.News

object DataMapper {
    fun mapResponsesToEntities(input: List<ArticlesItem>): List<NewsEntity> {
        val newsList = ArrayList<NewsEntity>()
        input.map {
            val news = NewsEntity(
                id = 0,
                name = it.source.name,
                title = it.title,
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
                id = it.id,
                name = it.name,
                title = it.title,
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
            id = input.id,
            name = input.name,
            title = input.title,
            date = input.date,
            image = input.image,
            content = input.content,
            author = input.author,
            url = input.url,
            isBookmark = input.isBookmark
        )
}