package com.link.newsfeed.NewsFeed.HomeScreen

import kotlinx.coroutines.experimental.Job

interface HomePresenterContract {

    fun getArticles(source: String): Job

}