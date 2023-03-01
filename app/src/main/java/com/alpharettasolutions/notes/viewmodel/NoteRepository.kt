package com.alpharettasolutions.notes.viewmodel

import android.content.Context
import com.alpharettasolutions.notes.database.NoteDao
import com.alpharettasolutions.notes.database.NoteDatabaseManager
import com.alpharettasolutions.notes.database.NoteEntity
import kotlinx.coroutines.flow.Flow

class NoteRepository(context: Context) {

    // Declare a private variable to hold the NoteDao instance.
    private val noteDao: NoteDao = NoteDatabaseManager.getInstance(context = context).noteDao()

    // Define a function to get all notes from the database.
    // The function returns a flow of List<NoteEntity> objects.
    fun getAll(): Flow<List<NoteEntity>> {
        return noteDao.getAll()
    }

    // Define a function to insert a new note into the database.
    // The function takes a NoteEntity object as a parameter.
    fun insert(noteEntity: NoteEntity) {
        noteDao.insert(noteEntity)
    }
}
