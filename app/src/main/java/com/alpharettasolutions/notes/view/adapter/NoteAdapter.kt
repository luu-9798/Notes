package com.alpharettasolutions.notes.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alpharettasolutions.notes.R
import com.alpharettasolutions.notes.model.Note

class NoteAdapter(private val noteList: List<Note>): RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView = itemView.findViewById<TextView>(R.id.tv_note_title)
        private val contentTextView = itemView.findViewById<TextView>(R.id.tv_note_content)

        fun bind(note: Note) {
            titleTextView.text = note.title
            contentTextView.text = note.content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(
                    R.layout.view_holder_note_list,
                    parent,
                    false
                )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(noteList[position])
    }

    override fun getItemCount(): Int {
        return noteList.size
    }
}