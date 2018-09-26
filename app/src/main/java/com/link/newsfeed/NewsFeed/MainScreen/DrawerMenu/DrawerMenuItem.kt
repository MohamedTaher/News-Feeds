package com.link.newsfeed.NewsFeed.MainScreen.DrawerMenu

import android.content.Context
import android.widget.ImageView
import com.link.newsfeed.R
import android.widget.TextView
import com.link.newsfeed.Data.Constant
import com.mindorks.placeholderview.annotations.*


@Layout(R.layout.drawer_item)
class DrawerMenuItem(private val mContext: Context, private var position: Int) {

    private var mCallBack: DrawerCallBack? = null

    @View(R.id.drawer_selected_layout)
    lateinit var selectedLayout: ImageView

    @View(R.id.drawer_item_image_view)
    lateinit var drawerImage: ImageView

    @View(R.id.drawer_item_title_text_view)
    lateinit var titleTextView: TextView

    @Resolve
    fun onResolved() {
        when (position) {

            Constant.MenuItem.EXPLORE_MENU_ITEM -> {
                drawerImage.setImageResource(R.drawable.explore)
                titleTextView.text = mContext.getString(R.string.explore)
            }

            Constant.MenuItem.LIVE_CHAT_MENU_ITEM -> {
                drawerImage.setImageResource(R.drawable.live)
                titleTextView.text = mContext.getString(R.string.live_chat)
            }

            Constant.MenuItem.GALLERY_MENU_ITEM -> {
                drawerImage.setImageResource(R.drawable.gallery)
                titleTextView.text = mContext.getString(R.string.gallery)
            }

            Constant.MenuItem.WISH_LIST_MENU_ITEM -> {
                drawerImage.setImageResource(R.drawable.wishlist)
                titleTextView.text = mContext.getString(R.string.wish_list)
            }

            Constant.MenuItem.E_MAGAZINE_MENU_ITEM -> {
                drawerImage.setImageResource(R.drawable.e_magazine)
                titleTextView.text = mContext.getString(R.string.e_magazine)
            }

        }
    }

    @Click(R.id.main_drawer_item_layout)
    fun onMenuItemClick(){
        mCallBack?.onMenuItemClick(position)
        selectedLayout.visibility = android.view.View.VISIBLE
    }

    fun setDrawerCallBack(callBack: DrawerCallBack): DrawerMenuItem {
        mCallBack = callBack
        return this
    }

    interface DrawerCallBack {
        fun onMenuItemClick(position: Int)
    }

}