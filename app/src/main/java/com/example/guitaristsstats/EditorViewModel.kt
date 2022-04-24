package com.example.guitaristsstats

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.guitaristsstats.data.AppDatabase
import com.example.guitaristsstats.data.GuitarEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditorViewModel(app: Application) : AndroidViewModel(app) {

    private val database = AppDatabase.getInstance(app)
    //when ever the guitar entity changes it broadcasts any changes to a class that is viewing it
    val currentGuitar = MutableLiveData<GuitarEntity>()

    fun getGuitarById(guitarId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val guitar =
                    //checks to see if guitarId is not the same as NEW_GUITAR_ID
                    if (guitarId != NEW_GUITAR_ID) {
                        database?.guitarDao()?.getGuitarById(guitarId)
                    } else {
                       GuitarEntity()
                    }
                currentGuitar.postValue(guitar)
            }
        }
    }

    //updates text field entity
    fun updateGuitar() {
        currentGuitar.value?.let {
            it.name = it.name.trim()
            it.band = it.band.trim()
            it.dob = it.dob.trim()
            it.genre = it.genre.trim()
            it.guitarType = it.guitarType.trim()
            it.status = it.status.trim()
            it.guitaristImage = it.guitaristImage.trim()
            //prevents creating a guitarist object with blank text
            if (it.id == NEW_GUITAR_ID && it.name.isEmpty() && it.band.isEmpty() && it.dob.isEmpty() && it.genre.isEmpty() && it.guitarType.isEmpty() && it.status.isEmpty() && it.guitaristImage.isEmpty()){
                return
            }
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    //if the name object is empty it is deleted from the database
                    if (it.name.isEmpty() && it.band.isEmpty() && it.dob.isEmpty() && it.genre.isEmpty() && it.guitarType.isEmpty() && it.status.isEmpty() && it.guitaristImage.isEmpty()) {
                        database?.guitarDao()?.deleteGuitar(it)
                    } else {
                        //if it is not empty it inserts into the database
                        database?.guitarDao()?.insertGuitar(it)
                    }
                }
            }
        }
    }
}