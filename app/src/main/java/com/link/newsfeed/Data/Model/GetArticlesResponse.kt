package com.link.newsfeed.Data.Model

class GetArticlesResponse: BaseResponse() {
    var source: String? = null
    var sortBy: String? = null
    var articles: ArrayList<Article>? = null

}