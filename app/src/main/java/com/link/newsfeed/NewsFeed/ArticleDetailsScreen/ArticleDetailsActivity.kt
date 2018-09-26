package com.link.newsfeed.NewsFeed.ArticleDetailsScreen

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.view.Menu
import android.view.MenuItem
import com.link.newsfeed.Data.Constant
import com.link.newsfeed.Data.Model.Article
import com.link.newsfeed.Helper.Utils
import com.link.newsfeed.R
import kotlinx.android.synthetic.main.toolbar.*

class ArticleDetailsActivity : AppCompatActivity() {

    private var article: Article? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_details)

        article = intent.getParcelableExtra(ARTICLE_KEY)
        if (article == null) {
            finish()
        }

        setSupportActionBar(main_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        initViews()
    }

    private fun initViews() {
        setFragment()
    }

    private fun setFragment() {
        if (article == null) {
            return
        }

        val articleDetailsFragment = ArticleDetailsFragment()
        articleDetailsFragment.setArticle(article!!)

        val transaction = supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.article_details_frame_layout, articleDetailsFragment, Constant.FragmentsTags.ARTICLE_DETAILS_TAG)
        transaction?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction?.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_meun, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()

        if (item.itemId == android.R.id.home) {
            onBackPressed()
        } else if (id == R.id.action_search) {
            Utils.makeToast(this, "SEARCH")
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
