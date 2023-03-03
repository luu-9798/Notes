package com.alpharettasolutions.notes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.alpharettasolutions.notes.model.NoteEntity
import com.alpharettasolutions.notes.view.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(application: Application) : AndroidViewModel(application){

    private val repository: NoteRepository = NoteRepository(application)

    private val _viewStateLiveData: MutableLiveData<ViewState> = MutableLiveData<ViewState>()
    val viewStateLiveData: LiveData<ViewState> = _viewStateLiveData

    /** VIEW INTERACTIon SECTION **/

    val notesLiveData = MutableLiveData<List<NoteEntity>>()

    var currentTitle: String = ""
    var currentContent: String = ""
    var isNewNote: Boolean = false

    fun clickCreateNoteButton() {
        isNewNote = true
        _viewStateLiveData.value = ViewState.OPEN_NOTE_DETAIL_FRAGMENT
    }

    fun clickSaveChangesButton() {
        if (currentTitle.isEmpty() || currentContent.isEmpty()) {
            _viewStateLiveData.value = ViewState.NOTIFY_EMPTY_TITLE_OR_CONTENT
        } else {
            if (isNewNote) {
                insertNoteToDatabase()
            }

            closeNoteDetailFragment()
        }
    }

    fun clickDiscardChangesButton() {
        _viewStateLiveData.value = ViewState.NOTIFY_DISCARD_CHANGES
    }

    fun closeNoteDetailFragment() {
        _viewStateLiveData.value = ViewState.CLOSE_NOTE_DETAIL_FRAGMENT
    }

    fun getAllNotes() {
        viewModelScope.launch {
            val notesList = withContext(Dispatchers.IO) {
                repository.getAll()
            }
            notesLiveData.postValue(notesList)
        }
    }
    /** END SECTION **/

    /** DATABASE SECTION**/
    private fun insertNoteToDatabase() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.insert(
                    NoteEntity(
                        id = 0,
                        title = currentTitle,
                        content = currentContent
                    )
                )

                getAllNotes()
            }

            currentTitle = ""
            currentContent = ""
        }
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
