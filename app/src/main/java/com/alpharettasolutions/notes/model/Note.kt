package com.alpharettasolutions.notes.model

import androidx.room.PrimaryKey

data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val content: String
)
