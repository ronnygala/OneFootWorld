package com.example.myapplication.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.Adapters.BroadcastAdapter
import com.example.myapplication.Adapters.ScoreAdapter
import com.example.myapplication.Adapters.ScreeningAdapter
import com.example.myapplication.R
import com.example.myapplication.model.Broadcast
import com.example.myapplication.model.Score
import com.example.myapplication.model.Screening
import com.google.gson.Gson
import com.google.gson.JsonArray
import kotlinx.android.synthetic.main.fragment_broadcast.view.*
import kotlinx.android.synthetic.main.fragment_score.view.*
import kotlinx.android.synthetic.main.fragment_screening.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL

class ScreeningFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        val root = inflater.inflate(R.layout.fragment_screening, container, false)



        doAsync {
            val json = URL("http://192.168.0.105/football/screening.json").readText()
           // Log.d("ronak",json)
            uiThread {
                val broadcastChannel = Gson().fromJson(json, Array<Screening>::class.java).toList()

                root.recycler_view_screening.apply {
                    layoutManager = GridLayoutManager(activity, 1) as RecyclerView.LayoutManager?
                    adapter = ScreeningAdapter(broadcastChannel)
                    root.progressBar_screening.visibility = View.GONE
                }
            }

        }

        return root
    }
}