package com.link.newsfeed.Helper

import android.content.Context
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*
import android.content.ActivityNotFoundException
import android.support.v4.content.ContextCompat.startActivity
import android.content.Intent
import android.net.Uri
import com.link.newsfeed.R


object Utils {

    fun makeToast(context: Context?, name: Int) {
        if (context != null) {
            val msg = context.resources?.getString(name) ?: ""
            Utils.makeToast(context, msg)
        }
    }

    fun makeToast(context: Context?, msg: String) {
        if (context != null) {
            val mToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT)
            mToast!!.show()
        }
    }

    fun toFormattedString(date: Date?, formate: String): String {

        if (date == null) {
            return ""
        }

        val simpleDateFormat = SimpleDateFormat(formate)
        return simpleDateFormat.format(date)
    }

    fun openWebPage(context: Context?, url: String) {

        try {
            val webpage = Uri.parse(url)
            val myIntent = Intent(Intent.ACTION_VIEW, webpage)
            context?.startActivity(myIntent)

        } catch (e: ActivityNotFoundException) {
            Utils.makeToast(context, R.string.no_application_to_open_web_link)
            e.printStackTrace()
        }

    }


}