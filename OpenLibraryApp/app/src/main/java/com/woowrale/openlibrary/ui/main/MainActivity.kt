package com.woowrale.openlibrary.ui.main

import android.graphics.drawable.Icon
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.woowrale.openlibrary.R
import com.woowrale.openlibrary.data.remote.model.response.BookEntriesResponse
import com.woowrale.openlibrary.data.remote.model.response.BookResponse
import com.woowrale.openlibrary.data.remote.model.response.MenuEntriesResponse
import com.woowrale.openlibrary.data.remote.model.response.SeedEntriesResponse
import com.woowrale.openlibrary.domain.model.Book
import com.woowrale.openlibrary.domain.model.MenuEntries
import com.woowrale.openlibrary.domain.model.SeedEntries
import com.woowrale.openlibrary.ui.base.BaseActivity
import com.woowrale.openlibrary.utils.DataWrapper

class MainActivity : BaseActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var menuEntries: MenuEntriesResponse
    private lateinit var seedEntries: SeedEntriesResponse
    private lateinit var book: BookResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDagger()
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_remote_global,
                R.id.nav_local_global,
                R.id.nav_details
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        /*
        menuEntries = DataWrapper.getMenuFromJson("menu-list.json", this)
        seedEntries = DataWrapper.getSeedFromJson("seed-list.json", this)
        book = DataWrapper.getBookFromJson("book-olid.json", "OLID:OL23662890M", this)

        val menu = navView.menu
        var counter = 0
        menu.clear()
        for (m in menuEntries.entries) {
            menu.add(counter, R.drawable.ic_menu_gallery, counter, m.name)
            counter += 1
        }
         */
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun initDagger() {
        getDaggerMainComponent().inject(this)
    }

    fun createMenu() {

    }
}