package com.alpharettasolutions.notes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _createNoteLiveData: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val createNoteLiveData: LiveData<Boolean> = _createNoteLiveData

    fun createNewNote() {
        _createNoteLiveData.postValue(true)
    }
}