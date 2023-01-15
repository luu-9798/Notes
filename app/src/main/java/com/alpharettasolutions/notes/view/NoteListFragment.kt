package com.alpharettasolutions.notes.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.alpharettasolutions.notes.R
import com.alpharettasolutions.notes.databinding.FragmentNoteListBinding
import com.alpharettasolutions.notes.viewmodel.MainViewModel

class NoteListFragment : Fragment() {
    private lateinit var binding: FragmentNoteListBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        initObservers()
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

    private fun initObservers() {
        viewModel.createNoteLiveData.observe(this) {
            if (it) {
                val options = NavOptions.Builder()
                    .setEnterAnim(R.anim.enter_from_left)
                    .build()
                findNavController().navigate(R.id.NoteListFragment_to_NoteDetailFragment, null, options)
            }
        }
    }
}
