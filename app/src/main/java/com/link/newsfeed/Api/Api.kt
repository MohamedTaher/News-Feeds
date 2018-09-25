package com.link.newsfeed.Api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Api {

    private var newsFeedApi: NewsFeedApi? = null

    fun getNewsFeedApi(): NewsFeedApi {

        if (newsFeedApi == null) {
            val gson = GsonBuilder()
                    .setDateFormat(Config.DATE_FORMAT)
                    .create()

            val client = OkHttpClient.Builder().addInterceptor(MyRetrofitInterceptor())
                    .connectTimeout(Config.TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(Config.TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(Config.TIMEOUT, TimeUnit.SECONDS)
                    .build()

            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .baseUrl(Config.BASE_URL)
                    .build()

            newsFeedApi = retrofit.create(NewsFeedApi::class.java)
        }

        return newsFeedApi!!
    }

}