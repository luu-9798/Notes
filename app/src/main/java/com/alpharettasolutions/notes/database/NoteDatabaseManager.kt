package com.alpharettasolutions.notes.database

import android.content.Context
import androidx.room.Room

object NoteDatabaseManager {
    @Volatile
    // Declare a private volatile variable to hold the singleton instance of the NoteDatabase class.
    private var INSTANCE: NoteDatabase? = null

    // Define a public function to return the singleton instance of the NoteDatabase class.
    // The function takes a Context object as a parameter to create the database instance.
    fun getInstance(context: Context): NoteDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                NoteDatabase::class.java,
                "NoteDatabase"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}
