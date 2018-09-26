package com.link.newsfeed.NewsFeed.ArticleDetailsScreen

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.link.newsfeed.Data.Model.Article
import com.link.newsfeed.Helper.Utils
import com.link.newsfeed.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_article_details.*

class ArticleDetailsFragment: Fragment(), ArticleDetailsViewContract {

    private val dateFormate = "MMM dd, yyyy"

    private var article: Article? = null

    fun setArticle(article: Article) {
        this.article = article
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_article_details, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initView()
        handleOptions()
    }

    private fun initView() {
        Picasso.get()
                .load(article?.urlToImage)
                .placeholder(R.drawable.placeholder)
                .into(article_image_view)

        article_title_text_view?.text = article?.title

        val authorBy = "${getString(R.string.by)} ${article?.author}"
        article_author_text_view?.text = authorBy

        val formattedDateStr = Utils.toFormattedString(article?.publishedAt, dateFormate)
        article_date_text_view?.text = formattedDateStr

        article_description_text_view?.text = article?.description
    }

    private fun handleOptions() {
        open_website_button?.setOnClickListener {
            openArticleWebsite(article?.url)
        }
    }

    override fun openArticleWebsite(url: String?) {
        if (url == null) {
            Utils.makeToast(context, R.string.no_website_to_this_article)

        } else {
            Utils.openWebPage(context, article?.url!!)
        }
    }


}