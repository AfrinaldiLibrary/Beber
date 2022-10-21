package com.afrinaldi.beber.core.utils

import com.afrinaldi.beber.core.data.source.local.entity.NewsEntity
import com.afrinaldi.beber.core.data.source.remote.response.ArticlesItem
import com.afrinaldi.beber.core.domain.model.News

object DataMapper {
    fun mapResponsesToEntities(input: List<ArticlesItem?>): List<NewsEntity> {
        val newsList = ArrayList<NewsEntity>()
        input.map {
            val news = NewsEntity(
                name = it?.source!!.name.toString(),
                title = it.title.toString(),
                date = it.publishedAt.toString(),
                image = it.urlToImage.toString(),
                content = it.content ?: "No content created",
                author = it.author ?: "Anonymous",
                url = it.url.toString(),
                isFavorite = false,
            )
            newsList.add(news)
        }
        return newsList
    }

    fun mapEntityToDomain(input: List<NewsEntity>): List<News> =
        input.map {
            News(
                name = it.name,
                title = it.title,
                date = it.date,
                image = it.image,
                content = it.content,
                author = it.author,
                url = it.url,
                isFavorite = false,
            )
        }

    fun mapDomainToEntity(input: News) =
        NewsEntity(
            name = input.name,
            title = input.title,
            date = input.date,
            image = input.image,
            content = input.content,
            author = input.author,
            url = input.url,
            isFavorite = input.isFavorite,
        )
}