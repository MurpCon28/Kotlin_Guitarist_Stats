package com.example.guitaristsstats

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.guitaristsstats.data.AppDatabase
import com.example.guitaristsstats.data.GuitarEntity
import com.example.guitaristsstats.data.SampleDataProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val database = AppDatabase.getInstance(app)

    val guitarsList = database?.guitarDao()?.getAll()

    //code gets the sample data and inserts it into the database
    fun addSampleData() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val sampleGuitars = SampleDataProvider.getGuitars()
                database?.guitarDao()?.insertAll(sampleGuitars)
            }
        }
    }

    //code calls function in GuitarDao to remove data from the database
    fun deleteGuitars(selectedGuitars: List<GuitarEntity>) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                database?.guitarDao()?.deleteGuitars(selectedGuitars)
            }
        }
    }

    //code calls the delete all function and removes all data from the database
    fun deleteAllGuitars() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                database?.guitarDao()?.deleteAll()
            }
        }
    }
}