package com.alpharettasolutions.notes.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alpharettasolutions.notes.R
import com.alpharettasolutions.notes.databinding.ViewHolderNoteListBinding
import com.alpharettasolutions.notes.model.NoteEntity

class NoteAdapter(private val noteList: List<NoteEntity>): RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    class ViewHolder(private val binding: ViewHolderNoteListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(noteEntity: NoteEntity) {
            binding.tvNoteTitle.text = noteEntity.title
            binding.tvNoteContent.text = noteEntity.content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ViewHolderNoteListBinding.inflate(
                LayoutInflater.from(parent.context),
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
