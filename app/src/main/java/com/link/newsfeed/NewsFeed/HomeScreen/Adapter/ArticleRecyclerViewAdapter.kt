package com.link.newsfeed.NewsFeed.HomeScreen.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.link.newsfeed.Data.Model.Article
import com.link.newsfeed.Helper.Utils
import com.link.newsfeed.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.article_item_layout.view.*

class ArticleRecyclerViewAdapter(private val mContext: Context?): RecyclerView.Adapter<ArticleRecyclerViewAdapter.ViewHolder>() {

    private var articles = ArrayList<Article>()
    private var onItemClickListener: OnItemClickListener?= null

    fun setArticles(articles: ArrayList<Article>) {
        this.articles.clear()
        this.articles.addAll(articles)
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.article_item_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return articles.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = articles.get(position)
        holder.bindData(item)
        holder.handleOptions(item)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val dateFormate = "MMM dd, yyyy"

        fun bindData(article: Article) {

            Picasso.get()
                    .load(article.urlToImage)
                    .placeholder(R.drawable.placeholder)
                    .into(itemView.article_image_view)

            itemView.article_title_text_view?.text = article.title

            val authorBy = "${mContext?.getString(R.string.by)} ${article.author}"
            itemView.article_author_text_view?.text = authorBy

            val formattedDateStr = Utils.toFormattedString(article.publishedAt, dateFormate)
            itemView.article_date_text_view?.text = formattedDateStr
        }

        fun handleOptions(article: Article) {
            itemView.setOnClickListener {
                onItemClickListener?.onItemClick(article)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(item: Article)
    }
}