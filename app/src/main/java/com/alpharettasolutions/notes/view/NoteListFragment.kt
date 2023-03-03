package com.alpharettasolutions.notes.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.alpharettasolutions.notes.R
import com.alpharettasolutions.notes.databinding.FragmentNoteListBinding
import com.alpharettasolutions.notes.view.adapter.NoteAdapter
import com.alpharettasolutions.notes.viewmodel.MainViewModel

class NoteListFragment : Fragment() {
    private lateinit var binding: FragmentNoteListBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        viewModel.notesLiveData.observe(this) {
            val adapter = NoteAdapter(noteList = it)
            binding.recyclerViewNotes.adapter = adapter
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_note_list, container, false)
        binding.viewModel = viewModel
        return binding.root
    }
}
