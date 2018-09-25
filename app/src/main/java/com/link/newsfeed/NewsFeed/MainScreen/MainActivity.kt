package com.link.newsfeed.NewsFeed.MainScreen

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import com.link.newsfeed.Data.Constant
import com.link.newsfeed.NewsFeed.HomeScreen.HomeFragment
import com.link.newsfeed.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = getString(R.string.link_development)
        
        initViews()
    }

    private fun initViews() {
        setHomeFragment()
    }

    private fun setHomeFragment() {
        val homeFragment = HomeFragment()
        val transaction = supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.main_frame_layout, homeFragment, Constant.FragmentsTags.HOME_TAG)
        transaction?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction?.commit()
    }
}
