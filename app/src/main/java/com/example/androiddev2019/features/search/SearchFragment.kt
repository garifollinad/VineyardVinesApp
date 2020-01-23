package com.example.androiddev2019.features.search


import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController

import com.example.androiddev2019.R
import com.example.androiddev2019.features.menuHamburger.HamburgerMenuActivity


class SearchFragment : Fragment() {

    private lateinit var hamburgerMenu: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)
        setData()
    }

    private fun bindViews(view: View) {
        view.apply {
            hamburgerMenu = findViewById(R.id.goToHamburgerMenu)
        }
    }

    private fun setData() {
        hamburgerMenu.setOnClickListener {
            val intent = Intent(activity, HamburgerMenuActivity::class.java)
            startActivity(intent)
//            findNavController().navigate(R.id.action_navigation_search_to_hamburgerMenuFragment)
        }
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        if (savedInstanceState == null) {
//        }
//    }
}
