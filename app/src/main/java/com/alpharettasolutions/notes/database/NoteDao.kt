package com.alpharettasolutions.notes.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.alpharettasolutions.notes.model.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert
    fun insert(noteEntity: NoteEntity)

    @Update
    fun update(noteEntity: NoteEntity)

    @Delete
    fun delete(noteEntity: NoteEntity)

    @Query("SELECT * FROM NoteEntity")
    fun getAll(): List<NoteEntity>

    @Query("SELECT * FROM NoteEntity WHERE id = :id")
    fun getById(id: Int): NoteEntity
}
