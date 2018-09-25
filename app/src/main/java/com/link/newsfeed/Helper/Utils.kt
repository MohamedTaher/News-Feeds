package com.link.newsfeed.Helper

import android.content.Context
import android.widget.Toast

object Utils {

    var mToast: Toast? = null

    fun makeToast(context: Context?, name: Int) {
        if (context != null) {
            val msg = context.resources?.getString(name) ?: ""
            Utils.makeToast(context, msg)
        }
    }

    fun makeToast(context: Context?, msg: String) {
        if (context != null) {
            mToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT)
            mToast!!.show()
        }
    }




}