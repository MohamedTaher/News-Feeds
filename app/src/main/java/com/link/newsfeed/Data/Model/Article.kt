package com.link.newsfeed.Data.Model

import android.os.Parcel
import android.os.Parcelable
import paperparcel.PaperParcel
import java.util.*

@PaperParcel
data class Article(
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: Date

) : Parcelable{

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        PaperParcelArticle.writeToParcel(this, dest, flags)
    }

    companion object {
        @JvmField val CREATOR = PaperParcelArticle.CREATOR
    }
}