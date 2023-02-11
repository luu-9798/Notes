package com.alpharettasolutions.notes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alpharettasolutions.notes.view.ViewState

class MainViewModel : ViewModel() {

    var currentId: String = ""
    var currentTitle: String = ""
    var currentContent: String = ""

    private val _viewStateLiveData: MutableLiveData<ViewState> = MutableLiveData<ViewState>()
    val viewStateLiveData: LiveData<ViewState> = _viewStateLiveData

    fun clickCreateNoteButton() {
        _viewStateLiveData.value = ViewState.OPEN_NOTE_DETAIL_FRAGMENT
    }

    fun clickSaveChangesButton() {
        _viewStateLiveData.value = ViewState.CLOSE_NOTE_DETAIL_FRAGMENT
    }

    fun clickDiscardChangesButton() {
        _viewStateLiveData.value = ViewState.CLOSE_NOTE_DETAIL_FRAGMENT
    }
}
