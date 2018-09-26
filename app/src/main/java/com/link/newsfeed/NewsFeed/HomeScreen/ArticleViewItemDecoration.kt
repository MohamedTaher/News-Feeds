package com.link.newsfeed.NewsFeed.HomeScreen

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

class ArticleViewItemDecoration(private val spacing: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view) // item position
        val count = parent.adapter?.itemCount // items count

        outRect.left = spacing
        outRect.right = spacing
        outRect.top = spacing

        if (position == count?.minus(1)) {
            outRect.bottom = spacing
        }
    }
}