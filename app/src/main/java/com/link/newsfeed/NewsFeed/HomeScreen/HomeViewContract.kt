package com.link.newsfeed.NewsFeed.HomeScreen

import com.link.newsfeed.Data.Model.Article

interface HomeViewContract {

    fun setProgressBar(isVisible: Boolean)

    fun popErrorMassage(msg: String? = null)

    fun setLoadedArticlesToView(articles: ArrayList<Article>)

    fun openArticleDetails(article: Article)
}