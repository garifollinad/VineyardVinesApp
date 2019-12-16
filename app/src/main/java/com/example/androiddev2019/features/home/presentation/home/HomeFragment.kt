package com.example.androiddev2019.features.home.presentation.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.androiddev2019.R
import androidx.viewpager.widget.ViewPager
import com.example.androiddev2019.core.FragmentAdapter
import com.google.android.material.tabs.TabLayout
import android.widget.TextView
import android.widget.RelativeLayout



class HomeFragment: Fragment() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private var fragmentAdapter: FragmentAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setRetainInstance(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_new, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            bindView(view)
            setAdapter()
        }
    }

    private fun bindView(view: View) {
        tabLayout = view.findViewById(R.id.tabs)
        viewPager = view.findViewById(R.id.viewpager)


        tabLayout.apply {
            addTab(newTab().setText("Women"))
            addTab(newTab().setText("Men"))
            addTab(newTab().setText("Kids"))
            addTab(newTab().setText("Sale"))

            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabReselected(p0: TabLayout.Tab?) {
                }

                override fun onTabUnselected(p0: TabLayout.Tab?) {
                }

                override fun onTabSelected(tab: TabLayout.Tab?) {
                    if (tab?.position != null) {
                        Log.d("tab_selected", tab.position.toString())
                        viewPager.currentItem = tab.position
                    } else {
                        Log.d("tab_selected", "null")
                    }
                }
            })
        }
    }

    private fun setAdapter() {
        val list: List<Fragment> = arrayListOf(
            WomenClothFragment.newInstance(arguments),
            WomenClothFragment.newInstance(arguments),
            WomenClothFragment.newInstance(arguments),
            WomenClothFragment.newInstance(arguments)
        )
        fragmentAdapter = FragmentAdapter(childFragmentManager, list)
        viewPager.apply {
            adapter = fragmentAdapter
            offscreenPageLimit = 3
            addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) {

                }

                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    //tabLayout.changeTabStyle(selectedTab = tabLayout.getTabAt(position))
                    tabLayout.setScrollPosition(position, positionOffset, true)

                }

                override fun onPageSelected(position: Int) {

                }
            })
        }
    }
}