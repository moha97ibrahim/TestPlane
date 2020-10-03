package com.flatplay.testplane.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.flatplay.testplane.model.MainModel
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query


class MainViewModel : ViewModel() {
    private var _spaceOccupied: String = "50"
    private var _spaceOcupiedLiveData = MutableLiveData<String>()
    private var _availabeStorageLiveData = MutableLiveData<String>()
    private var _appNameList = MutableLiveData<FirestoreRecyclerOptions<MainModel>>()
    private var firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private lateinit var documentReference: DocumentReference



    fun getInitialCount(): MutableLiveData<String> {
        _spaceOcupiedLiveData.value = _spaceOccupied
        return _spaceOcupiedLiveData
    }

    fun getAvailabeStorage(): MutableLiveData<String> {
        documentReference = firebaseFirestore.collection("SETTING").document("STORAGE")
        if(_availabeStorageLiveData.value == null){
            documentReference.get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        Log.d("", "DocumentSnapshot data: ${document.get("availableStorage")}")
                        _availabeStorageLiveData.postValue(document.get("availableStorage").toString())
                    } else {
                        Log.d("", "No such document")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d("", "get failed with ", exception)
                }
        }
        return _availabeStorageLiveData
    }

    fun getAppList(): MutableLiveData<FirestoreRecyclerOptions<MainModel>> {
        val query: Query = firebaseFirestore.collection("APPS")
        val appData: FirestoreRecyclerOptions<MainModel> = FirestoreRecyclerOptions.Builder<MainModel>()
                .setQuery(query, MainModel::class.java)
                .build()
        _appNameList.value = appData
        Log.e("recyclerView",""+appData.snapshots.size)
        return _appNameList
    }

}