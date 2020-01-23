package com.example.androiddev2019.features.menuHamburger


import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController

import com.example.androiddev2019.R
import com.google.android.material.navigation.NavigationView



class HamburgerMenuFragment : Fragment() {

    private lateinit var appBarConfiguration : AppBarConfiguration
    private lateinit var navView: NavigationView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hamburger_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)
        setController()
    }

    private fun bindViews(view: View) {
        view.apply {
            navView = findViewById(R.id.navViewFragment)
            toolbar = findViewById(R.id.toolbarFragment)
            drawerLayout = findViewById(R.id.drawerLayout)
            (activity as AppCompatActivity).setSupportActionBar(toolbar)
        }
        setHasOptionsMenu(true)
    }

    private fun setController() {
        val host: NavHostFragment = (activity as AppCompatActivity).supportFragmentManager
            .findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment? ?: return
        navController = host.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBar(navController, appBarConfiguration)
        onSupportNavigateUp()

        NavigationUI.setupActionBarWithNavController((activity as AppCompatActivity), navController, drawerLayout)
        NavigationUI.setupWithNavController(navView, navController)
    }

    private fun setupActionBar(navController: NavController, appBarConfig : AppBarConfiguration) {

        (activity as AppCompatActivity).setupActionBarWithNavController(navController, appBarConfig)
    }

    fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, drawerLayout)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.navigation, menu)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.navigation_home) {
            Navigation.findNavController((activity as AppCompatActivity), R.id.main_nav_host_fragment)
                .navigate(R.id.navigation_home)
            return true
        }
        if (item.itemId == R.id.navigation_search) {
            Navigation.findNavController((activity as AppCompatActivity), R.id.main_nav_host_fragment)
                .navigate(R.id.navigation_search)
            return true
        }
        if (item.itemId == R.id.navigation_profile) {
            Navigation.findNavController((activity as AppCompatActivity), R.id.main_nav_host_fragment)
                .navigate(R.id.navigation_profile)
            return true
        } else {
            Toast.makeText(
                context, "diko else",
                Toast.LENGTH_SHORT
            ).show()
            Log.d("diko else", "diko else")

        }
        return item.onNavDestinationSelected((activity as AppCompatActivity).findNavController(R.id.main_nav_host_fragment))
                || super.onOptionsItemSelected(item)
    }
}
