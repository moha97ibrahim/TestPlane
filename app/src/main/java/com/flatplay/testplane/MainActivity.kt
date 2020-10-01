package com.flatplay.testplane

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

class MainActivity : AppCompatActivity() {

    private lateinit var _spaceOccupiedText :TextView
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        _spaceOccupiedText = findViewById(R.id.spaceOccupied)
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        val space:LiveData<String> = mainViewModel.getInitialCount()
        space.observe(this, Observer {
            _spaceOccupiedText.text = it
        })

    }
}
