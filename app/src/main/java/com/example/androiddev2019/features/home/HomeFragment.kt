package com.example.androiddev2019.features.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.androiddev2019.R
import com.example.androiddev2019.core.model.Cloth
import com.example.androiddev2019.features.home_detail.HomeDetailActivity
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import java.util.*
import java.text.SimpleDateFormat


class HomeFragment: Fragment() {

    private var listCloth = ArrayList<Cloth>()
    private lateinit var adapter: HomeAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var appBar: AppBarLayout
    private lateinit var collapsingToolbar: CollapsingToolbarLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setRetainInstance(true)
        val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm")
        val currentDateTimeString = formatter.format(Date())
        for (i in 0..100) {
            listCloth.add(Cloth("Long-Sleeve Vintage Whale Graphic Pocket T-Shirt", "\$48.00",
                "In our world, vintage means “been there, done that, can’t wait to do it again!” " +
                        "This cotton men’s long-sleeve t-shirt is so Old School comfortable, " +
                        "you’ll always feel right at home in it.", currentDateTimeString,
                "https://n.nordstrommedia.com/ImageGallery/store/product/Zoom/10/_105978370.jpg?h=365&w=240&dpr=2&quality=45&fit=fill&fm=jpg"))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            bindView(view)
            setAdapter()
        }
    }

    private fun bindView(view: View) {
        recyclerView = view.findViewById(R.id.recyclerView)
        appBar = view.findViewById(R.id.appBar)
        collapsingToolbar = view.findViewById(R.id.collapsingToolbar)

        appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (Math.abs(verticalOffset) - appBarLayout.totalScrollRange == 0) {
                collapsingToolbar.contentScrim =
                    ResourcesCompat.getDrawable(resources, R.drawable.bg_collapsing_white, null)

            } else {
                collapsingToolbar.contentScrim = ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.bg_collapsing_transparent,
                    null
                )
            }
        })
    }

    private fun setAdapter(){
        val listener = object: HomeListener {
            override fun onClick(item: Cloth) {
                val intent = Intent(context, HomeDetailActivity::class.java)
                intent.putExtra(HOME_DETAIL, item)
                Log.d("put_detail", item.toString())
                startActivity(intent)
            }

        }
        adapter = HomeAdapter(listener, listCloth)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(context, 3)
    }

    companion object {
        const val HOME_DETAIL = "HOME_DETAIL"
    }
}