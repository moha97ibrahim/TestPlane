package com.flatplay.testplane.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.flatplay.testplane.viewModel.MainViewModel
import com.flatplay.testplane.R
import com.flatplay.testplane.adapter.MainAdapter
import com.flatplay.testplane.model.MainModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class MainActivity : AppCompatActivity() {

    private lateinit var _spaceOccupiedText: TextView
    private lateinit var _availableStorage: TextView
    private lateinit var mainViewModel: MainViewModel
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var _appNameRecyclerView: RecyclerView
    private var firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    private var mainAdapter:MainAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        _spaceOccupiedText = findViewById(R.id.spaceOccupied)
        _availableStorage = findViewById(R.id.availableStorage)
        _appNameRecyclerView = findViewById(R.id.appNameRecyclerView)


        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        val space: LiveData<String> = mainViewModel.getInitialCount()
        space.observe(this, Observer {
            _spaceOccupiedText.text = it
        })

        val availableStorage: LiveData<String> = mainViewModel.getAvailabeStorage()
        availableStorage.observe(this, Observer {
            _availableStorage.text = it
        })

        val getAppNamelist: LiveData<FirestoreRecyclerOptions<MainModel>> =
            mainViewModel.getAppList()
        getAppNamelist.observe(this, Observer {
            mainAdapter = MainAdapter(it)
        })

        linearLayoutManager = LinearLayoutManager(this)
        _appNameRecyclerView.layoutManager = linearLayoutManager
        _appNameRecyclerView.setHasFixedSize(true)
        _appNameRecyclerView.adapter = mainAdapter


    }


    override fun onStart() {
        super.onStart()
        mainAdapter!!.startListening()
    }

    override fun onDestroy() {
        super.onDestroy()
        mainAdapter!!.stopListening()
    }
}
