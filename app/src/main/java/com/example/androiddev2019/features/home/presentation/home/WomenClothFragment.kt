package com.example.androiddev2019.features.home.presentation.home


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.androiddev2019.R
import com.example.androiddev2019.features.home.data.model.Cloth
import com.example.androiddev2019.features.home.presentation.home_detail.HomeDetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class WomenClothFragment : Fragment() {

    private var listCloth = ArrayList<Cloth>()
    private lateinit var adapter: HomeAdapter
    private lateinit var recyclerView: RecyclerView
    val homeViewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_women_cloth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            bindView(view)
            setAdapter()
            setData()
        }
    }

    private fun bindView(view: View) {
        recyclerView = view.findViewById(R.id.recyclerView)
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
                intent.putExtra(WomenClothFragment.HOME_DETAIL, item)
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
        fun newInstance(data: Bundle? = null): WomenClothFragment {
            val fragment = WomenClothFragment()
            fragment.arguments = data
            return fragment
        }
    }
}
