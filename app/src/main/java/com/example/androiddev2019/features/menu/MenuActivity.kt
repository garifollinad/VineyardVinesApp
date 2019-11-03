package com.example.androiddev2019.features.menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.androiddev2019.R
import com.example.androiddev2019.features.home.presentation.home.HomeFragment
import com.example.androiddev2019.features.profile.ProfileFragment
import com.example.androiddev2019.features.search.SearchFragment
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {

    var listFragment: List<Fragment> = listOf(HomeFragment(), SearchFragment(), ProfileFragment())
    var selectedFragment = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(R.id.container, listFragment.get(0), HOME_FRAGMENT)
                .add(R.id.container, listFragment.get(1), SEARCH_FRAGMENT)
                .add(R.id.container, listFragment.get(2), PROFILE_FRAGMENT)
                .show(listFragment.get(0))
                .hide(listFragment.get(1))
                .hide(listFragment.get(2))
                .commit()
            selectedFragment = 0
        }
        bottomNavigationView.setOnNavigationItemSelectedListener {
            val transaction = supportFragmentManager.beginTransaction()
            when(it.itemId) {
                R.id.navigation_home -> {
                    if (selectedFragment != 0) {
                        transaction.hide(listFragment.get(selectedFragment))
                            .show(listFragment.get(0))
                        selectedFragment = 0
                    }
                }
                R.id.navigation_search -> {
                    if (selectedFragment != 1) {
                        transaction.hide(listFragment.get(selectedFragment))
                            .show(listFragment.get(1))
                        selectedFragment = 1
                    }

                }
                else -> {
                    if (selectedFragment != 2) {
                        transaction.hide(listFragment.get(selectedFragment))
                            .show(listFragment.get(2))
                        selectedFragment = 2
                    }

                }
            }
            transaction.commit()
            true
        }
    }


    companion object TAGS {
        const val HOME_FRAGMENT = "HOME_FRAGMENT"
        const val SEARCH_FRAGMENT = "SEARCH_FRAGMENT"
        const val PROFILE_FRAGMENT = "PROFILE_FRAGMENT"
        const val SELECTED_POSITION = "SELECTED_POSITION"
    }

}
