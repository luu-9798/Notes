package com.alpharettasolutions.notes.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alpharettasolutions.notes.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}
