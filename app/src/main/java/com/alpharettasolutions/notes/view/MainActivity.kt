package com.alpharettasolutions.notes.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.Navigation.findNavController
import com.alpharettasolutions.notes.R
import com.alpharettasolutions.notes.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        initObservers()
    }

    private fun initObservers() {
        viewModel.viewStateLiveData.observe(this) { viewState ->
            when (viewState) {
                ViewState.OPEN_NOTE_DETAIL_FRAGMENT -> {
                    navigateFromListFragmentToDetailFragment()
                }
                ViewState.CLOSE_NOTE_DETAIL_FRAGMENT -> {
                    findNavController(this, R.id.navigation_host_fragment).popBackStack()
                }
            }
        }
    }

    private fun navigateFromListFragmentToDetailFragment() {
        val options = NavOptions.Builder()
            //.setEnterAnim(R.anim.enter_from_left)
            .build()
        findNavController(this, R.id.navigation_host_fragment)
            .navigate(R.id.NoteListFragment_to_NoteDetailFragment, null, options)
    }
}