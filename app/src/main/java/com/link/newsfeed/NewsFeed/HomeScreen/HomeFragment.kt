package com.link.newsfeed.NewsFeed.HomeScreen

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.link.newsfeed.Api.Api
import com.link.newsfeed.Data.Constant
import com.link.newsfeed.Data.Model.Article
import com.link.newsfeed.Helper.Utils
import com.link.newsfeed.NewsFeed.ArticleDetailsScreen.ArticleDetailsActivity
import com.link.newsfeed.NewsFeed.HomeScreen.Adapter.ArticleRecyclerViewAdapter
import com.link.newsfeed.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment: Fragment(), HomeViewContract {

    private val TAG = Constant.FragmentsTags.HOME_TAG
    private val presenter: HomePresenter
    private val RECYCLER_VIEW_SPACING = 20

    private var articlesAdapter: ArticleRecyclerViewAdapter? = null

    init {
        val api = Api.getNewsFeedApi()
        presenter = HomePresenter(viewContract = this, api = api)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initViews()
        handleOptions()
        presenter.getArticles(Constant.SOURCE)
    }

    private fun initViews() {
        initArticlesRecyclerView()
    }

    private fun initArticlesRecyclerView() {
        val layoutManger = LinearLayoutManager(context)
        articles_recycler_view?.layoutManager = layoutManger

        val spacing = (RECYCLER_VIEW_SPACING * resources.displayMetrics.density).toInt()
        val itemDecoration = ArticleViewItemDecoration(spacing)
        articles_recycler_view?.addItemDecoration(itemDecoration)

        articlesAdapter = ArticleRecyclerViewAdapter(context)
        articles_recycler_view?.adapter = articlesAdapter

        articlesAdapter?.setOnItemClickListener(object : ArticleRecyclerViewAdapter.OnItemClickListener{
            override fun onItemClick(item: Article) {
                openArticleDetails(item)
            }
        })
    }

    private fun handleOptions() {
        swipe_articles_recycler_view?.setOnRefreshListener {
            presenter.getArticles(Constant.SOURCE)
        }
    }

    override fun setProgressBar(isVisible: Boolean) {
        swipe_articles_recycler_view?.isRefreshing = isVisible
    }

    override fun popErrorMassage(msg: String?) {
        val errorMassage = msg ?: getString(R.string.connection_error)
        Utils.makeToast(context, errorMassage)
    }

    override fun setLoadedArticlesToView(articles: ArrayList<Article>) {
        articlesAdapter?.setArticles(articles)
    }

    override fun openArticleDetails(article: Article) {
        val intent = ArticleDetailsActivity.getIntent(context, article)
        context?.startActivity(intent)
    }
}