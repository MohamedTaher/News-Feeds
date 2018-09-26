package com.link.newsfeed.NewsFeed.MainScreen.DrawerMenu

import android.widget.ImageView
import android.widget.TextView
import com.link.newsfeed.Data.Model.User
import com.link.newsfeed.R
import com.mindorks.placeholderview.annotations.*


@NonReusable
@Layout(R.layout.drawer_header)
class DrawerHeader(val user: User) {

    private var mCallBack: DrawerHeader.DrawerCallBack? = null

    @View(R.id.profile_image_view)
    lateinit var profileImageView: ImageView

    @View(R.id.name_text_view)
    lateinit var nameTextView: TextView

    @Resolve
    fun onResolved() {
        nameTextView.text = user.name
        profileImageView.setImageResource(user.imageDrawableId)
    }

    @Click(R.id.drawer_header_layout)
    fun onMenuItemClick(){
        mCallBack?.onDrawerHeaderClick()
    }

    fun setDrawerCallBack(callBack: DrawerHeader.DrawerCallBack): DrawerHeader {
        mCallBack = callBack
        return this
    }

    interface DrawerCallBack {
        fun onDrawerHeaderClick()
    }
}