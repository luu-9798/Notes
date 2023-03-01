package com.alpharettasolutions.notes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alpharettasolutions.notes.view.ViewState

class MainViewModel(application: Application) : AndroidViewModel(application){

    private val repository: NoteRepository = NoteRepository(application)

    private val _viewStateLiveData: MutableLiveData<ViewState> = MutableLiveData<ViewState>()
    val viewStateLiveData: LiveData<ViewState> = _viewStateLiveData

    /** VIEW INTERACTING SECTION **/

    var currentTitle: String = ""
    var currentContent: String = ""

    fun clickCreateNoteButton() {
        _viewStateLiveData.value = ViewState.OPEN_NOTE_DETAIL_FRAGMENT
    }

    fun clickSaveChangesButton() {
        if (currentTitle.isEmpty() || currentContent.isEmpty()) {
            _viewStateLiveData.value = ViewState.NOTIFY_EMPTY_TITLE_OR_CONTENT
        } else {
            _viewStateLiveData.value = ViewState.CLOSE_NOTE_DETAIL_FRAGMENT
        }

    }

    fun clickDiscardChangesButton() {
        _viewStateLiveData.value = ViewState.CLOSE_NOTE_DETAIL_FRAGMENT
    }
    /** END SECTION **/
}

class MainViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(application) as T
        }
        throw java.lang.IllegalArgumentException("Unknown ViewModel class")
    }
}
