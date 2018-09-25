package com.link.newsfeed.NewsFeed.ArticleDetailsScreen

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.link.newsfeed.Data.Model.Article
import com.link.newsfeed.Helper.Utils
import com.link.newsfeed.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_article_details.*

class ArticleDetailsActivity : AppCompatActivity(), ArticleDetailsViewContract {

    private val dateFormate = "MMM dd, yyyy"

    private var article: Article? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_details)

        article = intent.getParcelableExtra(ARTICLE_KEY)
        if (article == null) {
            finish()
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        title = getString(R.string.link_development)

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
            Utils.makeToast(this, R.string.no_website_to_this_article)

        } else {
            Utils.openWebPage(this, article?.url!!)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // handle arrow click here
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }

    companion object {

        private val ARTICLE_KEY = "article"

        fun getIntent(context: Context?, article: Article): Intent {
            val intent = Intent(context, ArticleDetailsActivity::class.java)
            intent.putExtra(ARTICLE_KEY, article)

            return intent
        }
    }
}
