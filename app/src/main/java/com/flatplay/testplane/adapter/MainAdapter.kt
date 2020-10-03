package com.flatplay.testplane.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.flatplay.testplane.R
import com.flatplay.testplane.model.MainModel


class MainAdapter(options: FirestoreRecyclerOptions<MainModel>) : FirestoreRecyclerAdapter<MainModel, MainAdapter.ViewHolder>(
    options
)  {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.app_list_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, model: MainModel) {
        Log.e("recyclerView",""+model.appName)
        holder._appNameText.text = model.appName
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var _appNameText: TextView = itemView.findViewById(R.id.appName)
    }


}