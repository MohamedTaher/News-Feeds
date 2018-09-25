package com.link.newsfeed.NewsFeed.MainScreen

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.view.Menu
import android.view.MenuItem
import com.link.newsfeed.Data.Constant
import com.link.newsfeed.Helper.Utils
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


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_meun, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()
        if (id == R.id.action_search) {
            Utils.makeToast(this, "SEARCH")
        }

        return super.onOptionsItemSelected(item)

    }
}
