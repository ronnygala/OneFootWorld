package com.example.myapplication.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.Adapters.ProductsAdapter
import com.example.myapplication.R
import com.example.myapplication.model.Product
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_main.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL

class MainFragment:Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val  root = inflater.inflate(R.layout.fragment_main, container,false)




        doAsync {
            val json = URL((getString(R.string.ip_add)+"/football/results.json")).readText()

            uiThread {
                val products = Gson().fromJson(json, Array<Product>::class.java).toList()
                root.recycler_view.apply {
                    layoutManager = GridLayoutManager(activity, 2) as RecyclerView.LayoutManager?
                    adapter = ProductsAdapter(products)
                    root.progressBar.visibility = View.GONE
                }
            }

        }

        return root

    }
}

