package com.example.androiddev2019.features.menuHamburger

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.navigateUp
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.androiddev2019.R
import com.google.android.material.navigation.NavigationView

class HamburgerMenuActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration : AppBarConfiguration
    private lateinit var navView: NavigationView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hamburger_menu)
        bindViews()

        navController.addOnDestinationChangedListener { _, destination, _ ->
            val dest: String = try {
                resources.getResourceName(destination.id)
            } catch (e: Resources.NotFoundException) {
                Integer.toString(destination.id)
            }
        }

        NavigationUI.setupActionBarWithNavController(this,navController, drawerLayout)
        NavigationUI.setupWithNavController(navView, navController)
    }

    private fun bindViews(){
        navView = findViewById(R.id.navView)
        toolbar = findViewById(R.id.toolbar)
        drawerLayout = findViewById(R.id.drawer_layout)

        setSupportActionBar(toolbar)
        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment? ?: return
        navController = host.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)

        setupActionBar(navController, appBarConfiguration)
    }

    private fun setupActionBar(navController: NavController,
                               appBarConfig : AppBarConfiguration
    ) {

        setupActionBarWithNavController(navController, appBarConfig)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navigateUp(navController, drawerLayout)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.navigation_home) {
            Navigation.findNavController(this, R.id.main_nav_host_fragment)
                .navigate(R.id.navigation_home)
            return true
        }
        if (item.itemId == R.id.navigation_search) {
            Navigation.findNavController(this, R.id.main_nav_host_fragment)
                .navigate(R.id.navigation_search)
            return true
        }
        if (item.itemId == R.id.navigation_profile) {
            Navigation.findNavController(this, R.id.main_nav_host_fragment)
                .navigate(R.id.navigation_profile)
            return true
        }
        if (item.itemId == R.id.navigation_hamburgerMenuFragment) {
            Navigation.findNavController(this, R.id.main_nav_host_fragment)
                .navigate(R.id.navigation_hamburgerMenuFragment)
            return true
        } else {
            Toast.makeText(
                this@HamburgerMenuActivity, "diko else",
                Toast.LENGTH_SHORT
            ).show()
            Log.d("diko else", "diko else")

        }
        return item.onNavDestinationSelected(findNavController(R.id.main_nav_host_fragment))
                || super.onOptionsItemSelected(item)
    }
}
