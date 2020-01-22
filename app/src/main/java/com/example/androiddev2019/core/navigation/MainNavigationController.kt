//package com.example.androiddev2019.core.navigation
//
//import android.content.Context
//import androidx.core.os.bundleOf
//import androidx.navigation.fragment.NavHostFragment
//import com.example.androiddev2019.R
//import com.example.androiddev2019.features.menu.MenuActivity
//
//class MainNavigationController (
//    mainActivity: MenuActivity
//) {
//
//    private val host: NavHostFragment = mainActivity.supportFragmentManager
//        .findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment
//    private val navController = host.navController
//
//    fun navigateSearch() {
//        navController.navigate(R.id.searchFragment)
//    }
//
//    fun navigateProfile() {
//        navController.navigate(R.id.profileFragment)
//    }
//
//    fun navigateHome() {
//        navController.navigate(R.id.homeFragment)
//    }
//
//}