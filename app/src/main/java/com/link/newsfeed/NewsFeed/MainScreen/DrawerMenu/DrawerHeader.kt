package com.link.newsfeed.NewsFeed.MainScreen.DrawerMenu

import android.widget.ImageView
import com.mindorks.placeholderview.annotations.Resolve
import android.widget.TextView
import com.link.newsfeed.Data.Model.User
import com.link.newsfeed.R
import com.mindorks.placeholderview.annotations.Layout
import com.mindorks.placeholderview.annotations.NonReusable
import com.mindorks.placeholderview.annotations.View


@NonReusable
@Layout(R.layout.drawer_header)
class DrawerHeader(val user: User) {

    @View(R.id.profile_image_view)
    lateinit var profileImageView: ImageView

    @View(R.id.name_text_view)
    lateinit var nameTextView: TextView

    @Resolve
    fun onResolved() {
        nameTextView.text = user.name
        profileImageView.setImageResource(user.imageDrawableId)
    }
}