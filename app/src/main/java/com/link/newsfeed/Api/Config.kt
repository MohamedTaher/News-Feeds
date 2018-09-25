package com.link.newsfeed.Api

object Config {

    const val BASE_URL = "https://newsapi.org/v1/"
    const val TIMEOUT = 60L //in seconds
    const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ"

    object GetArticles {
        const val API = "articles"

        const val SOURCE = "source"
        const val API_KEY = "apiKey"
    }
}