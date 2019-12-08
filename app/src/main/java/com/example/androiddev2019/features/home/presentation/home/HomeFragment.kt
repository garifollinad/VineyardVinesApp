package com.example.androiddev2019.features.home.presentation.home

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
import com.example.androiddev2019.features.home.data.model.Cloth
import com.example.androiddev2019.features.home.presentation.home_detail.HomeDetailActivity
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import org.koin.android.viewmodel.ext.android.viewModel
import androidx.lifecycle.Observer
import kotlin.collections.ArrayList


class HomeFragment: Fragment() {

    private var listCloth = ArrayList<Cloth>()
    private lateinit var adapter: HomeAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var appBar: AppBarLayout
    private lateinit var collapsingToolbar: CollapsingToolbarLayout
    val homeViewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setRetainInstance(true)
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
            setData()
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

    private fun setData() {
        homeViewModel.getClothes()
        homeViewModel.liveData.observe(this, Observer { result ->
            when (result) {
                is HomeViewModel.Result.ShowLoading -> {
                }
                is HomeViewModel.Result.HideLoading -> {
                }
                is HomeViewModel.Result.Clothes -> {
                    Log.d("my_dinara_result", result.toString())
                    adapter.initCloths(result.clothList as java.util.ArrayList<Cloth>)

                }
                is HomeViewModel.Result.Error -> {
                }
            }
        })
    }

    private fun setAdapter(){
        val listener = object:
            HomeListener {
            override fun onClick(item: Cloth) {
                val intent = Intent(context, HomeDetailActivity::class.java)
                intent.putExtra(HOME_DETAIL, item)
                Log.d("put_detail", item.toString())
                startActivity(intent)
            }

        }
        adapter =
            HomeAdapter(
                listener
            )
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(context, 2) as RecyclerView.LayoutManager?
    }

    companion object {
        const val HOME_DETAIL = "HOME_DETAIL"
    }
}