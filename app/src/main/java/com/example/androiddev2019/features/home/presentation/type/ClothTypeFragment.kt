package com.example.androiddev2019.features.home.presentation.type


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androiddev2019.core.navigation.ContainerActivity

import com.example.androiddev2019.R
import com.example.androiddev2019.core.navigation.Screen
import com.example.androiddev2019.features.home.data.model.Type
import com.example.androiddev2019.features.home.presentation.home.HomeViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class ClothTypeFragment : Fragment() {

    private var  adapter: HomeClothTypeAdapter? = null
    private lateinit var recyclerView: RecyclerView
    val homeViewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cloth_type, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            bindView(view)
            setData()
        }
    }

    private fun setData() {
        homeViewModel.getTypes()
        homeViewModel.liveData.observe(this, Observer { result ->
            when (result) {
                is HomeViewModel.Result.ShowLoading -> {
                }
                is HomeViewModel.Result.HideLoading -> {
                }
                is HomeViewModel.Result.Types -> {
                    setAdapter(result.typeList)
                }
                is HomeViewModel.Result.Error -> {
                }
            }
        })
    }

    private fun bindView(view: View) {
        recyclerView = view.findViewById(R.id.recyclerView)
        adapter?.clearAll()
    }

    private fun setAdapter(typeList: List<Type>){
        adapter =
            HomeClothTypeAdapter(typeList)
        Log.d("my_dinara_result", typeList.toString())
        recyclerView.adapter = adapter
        adapter?.apply {
            setOnItemClickListener{ adapter, view, position ->
                val data = Bundle()
                data.apply {
                    putString(Screen.SCREEN, Screen.WOMEN_CLOTH)
                }
                ContainerActivity.start(context, data)
                Log.d("cllickeeeeed", "oook")
            }
        }
        recyclerView.layoutManager = LinearLayoutManager(context) as RecyclerView.LayoutManager?
    }

    companion object {
        fun newInstance(data: Bundle? = null): ClothTypeFragment {
            val fragment =
                ClothTypeFragment()
            fragment.arguments = data
            return fragment
        }
    }

}
