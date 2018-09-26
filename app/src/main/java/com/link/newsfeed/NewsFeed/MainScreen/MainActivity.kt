package com.link.newsfeed.NewsFeed.MainScreen

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.link.newsfeed.Data.Constant
import com.link.newsfeed.Data.Model.User
import com.link.newsfeed.Helper.Utils
import com.link.newsfeed.NewsFeed.HomeScreen.HomeFragment
import com.link.newsfeed.NewsFeed.MainScreen.DrawerMenu.DrawerHeader
import com.link.newsfeed.NewsFeed.MainScreen.DrawerMenu.DrawerMenuItem
import com.link.newsfeed.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity(), DrawerHeader.DrawerCallBack, DrawerMenuItem.DrawerCallBack {

    private var selectedItemPosition: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(main_toolbar)

        initViews()
    }

    private fun initViews() {
        initDrawer()
        setHomeFragment()
    }

    private fun initDrawer() {

        val user = User("Tony Roshdy", R.drawable.profile)

        drawer_place_holder_view?.addView(DrawerHeader(user).setDrawerCallBack(this))
                ?.addView(DrawerMenuItem(this, Constant.MenuItem.EXPLORE_MENU_ITEM).setDrawerCallBack(this))
                ?.addView(DrawerMenuItem(this, Constant.MenuItem.LIVE_CHAT_MENU_ITEM).setDrawerCallBack(this))
                ?.addView(DrawerMenuItem(this, Constant.MenuItem.GALLERY_MENU_ITEM).setDrawerCallBack(this))
                ?.addView(DrawerMenuItem(this, Constant.MenuItem.WISH_LIST_MENU_ITEM).setDrawerCallBack(this))
                ?.addView(DrawerMenuItem(this, Constant.MenuItem.E_MAGAZINE_MENU_ITEM).setDrawerCallBack(this))

        val drawerToggle = ActionBarDrawerToggle(this, main_drawer_layout, main_toolbar, R.string.open_drawer, R.string.close_drawer)
        main_drawer_layout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
    }

    private fun setHomeFragment() {
        val homeFragment = HomeFragment()
        val transaction = supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.main_frame_layout, homeFragment, Constant.FragmentsTags.HOME_TAG)
        transaction?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction?.commit()
    }

    override fun onMenuItemClick(position: Int) {

        if (selectedItemPosition != null) {
            val preSelectedItem = drawer_place_holder_view?.getViewResolverAtPosition(selectedItemPosition!!) as DrawerMenuItem
            preSelectedItem.selectedLayout.visibility = View.INVISIBLE
        }

        selectedItemPosition = position

        when (position) {
            Constant.MenuItem.EXPLORE_MENU_ITEM -> {
                Utils.makeToast(this, R.string.explore)
            }

            Constant.MenuItem.LIVE_CHAT_MENU_ITEM -> {
                Utils.makeToast(this, R.string.live_chat)
            }

            Constant.MenuItem.GALLERY_MENU_ITEM -> {
                Utils.makeToast(this, R.string.gallery)
            }

            Constant.MenuItem.WISH_LIST_MENU_ITEM -> {
                Utils.makeToast(this, R.string.wish_list)
            }

            Constant.MenuItem.E_MAGAZINE_MENU_ITEM -> {
                Utils.makeToast(this, R.string.e_magazine)
            }

        }
    }

    override fun onDrawerHeaderClick() {
        Utils.makeToast(this, "PROFILE")
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
