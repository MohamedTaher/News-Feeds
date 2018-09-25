package com.link.newsfeed.NewsFeed.HomeScreen

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.link.newsfeed.Api.Api
import com.link.newsfeed.Data.Constant
import com.link.newsfeed.R
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import ru.gildor.coroutines.retrofit.Result
import ru.gildor.coroutines.retrofit.awaitResult


class HomeFragment: Fragment() {

    private val TAG = Constant.FragmentsTags.HOME_TAG

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        fetchArticles()
    }

    private fun fetchArticles() = launch(UI) {
        val api = Api.getNewsFeedApi()
        val result = api.getArticles(source = Constant.SOURCE, apiKey = Constant.API_KEY).awaitResult()

        when(result) {
            is Result.Ok -> {
                val response = result.value
                Log.e(TAG, response.articles?.get(0)?.publishedAt)
            }

            is Result.Error -> {
                Log.e(TAG, "fetchArticles ${result.exception}")
            }

            is Result.Exception -> {
                Log.e(TAG, "fetchArticles ${result.exception}")
            }
        }
    }


}