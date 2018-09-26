package com.link.newsfeed.Api

import com.link.newsfeed.Data.Model.GetArticlesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsFeedApi {

    @GET(Config.GetArticles.API)
    fun getArticles(
            @Query(Config.GetArticles.SOURCE) source: String
            , @Query(Config.GetArticles.API_KEY) apiKey: String
    ): Call<GetArticlesResponse>

}