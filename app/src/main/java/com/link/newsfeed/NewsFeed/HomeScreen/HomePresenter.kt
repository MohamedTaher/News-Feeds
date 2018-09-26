package com.link.newsfeed.NewsFeed.HomeScreen

import android.util.Log
import com.link.newsfeed.Api.NewsFeedApi
import com.link.newsfeed.Api.StatusEnum
import com.link.newsfeed.Data.Constant
import com.link.newsfeed.Data.Model.GetArticlesResponse
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import ru.gildor.coroutines.retrofit.Result
import ru.gildor.coroutines.retrofit.awaitResult

class HomePresenter(private val viewContract: HomeViewContract, private val api: NewsFeedApi): HomePresenterContract {

    private val TAG = "HomePresenter"

    override fun getArticles(source: String) = launch(UI)  {
        viewContract.setProgressBar(isVisible = true)
        val result = api.getArticles(source = source, apiKey = Constant.API_KEY).awaitResult()

        when(result) {
            is Result.Ok -> {
                val getArticlesResponse = result.value
                handleGetArticlesResponse(getArticlesResponse)

                Log.e(TAG, "fetchArticles OK")
            }

            is Result.Error -> {
                viewContract.popErrorMassage()
                Log.e(TAG, "fetchArticles Error ${result.exception}")
            }

            is Result.Exception -> {
                viewContract.popErrorMassage()
                Log.e(TAG, "fetchArticles Exception ${result.exception}")
            }
        }

        viewContract.setProgressBar(isVisible = false)
    }

    private fun handleGetArticlesResponse(response: GetArticlesResponse) {
        val status = response.status

        when (status) {
            StatusEnum.OK.value -> {
                val articles = response.articles
                if (articles == null) {
                    viewContract.popErrorMassage()
                } else {
                    viewContract.setLoadedArticlesToView(articles)
                }
            }

            StatusEnum.OK.value -> {
                val massage = response.massage
                viewContract.popErrorMassage(massage)
            }

            else -> {
                viewContract.popErrorMassage()
            }
        }

    }
}