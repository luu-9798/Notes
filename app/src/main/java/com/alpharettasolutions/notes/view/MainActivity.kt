package com.alpharettasolutions.notes.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.Navigation.findNavController
import com.alpharettasolutions.notes.R
import com.alpharettasolutions.notes.viewmodel.MainViewModel
import com.alpharettasolutions.notes.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(this.application)
        )[MainViewModel::class.java]

        initObservers()
    }

    private fun initObservers() {
        viewModel.viewStateLiveData.observe(this) { viewState ->
            when (viewState) {
                ViewState.OPEN_NOTE_DETAIL_FRAGMENT -> {
                    navigateToAnotherFragment(R.id.NoteListFragment_to_NoteDetailFragment)
                }
                ViewState.CLOSE_NOTE_DETAIL_FRAGMENT -> {
                    findNavController(this, R.id.navigation_host_fragment).popBackStack()
                }
                ViewState.NOTIFY_EMPTY_TITLE_OR_CONTENT -> {
                    displayAlertDialog("TITLE OR CONTENT IS EMPTY", "Title and content must not be empty")
                }
            }
        }
    }

    private fun navigateToAnotherFragment(actionId: Int) {
        val options = NavOptions.Builder()
            //.setEnterAnim(R.anim.enter_from_left)
            .build()
        findNavController(this, R.id.navigation_host_fragment)
            .navigate(actionId, null, options)
    }

    private fun displayAlertDialog(title: String, message: String) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle(title)
        alertDialogBuilder.setMessage(message)

        alertDialogBuilder.setPositiveButton("OK") { _, _ -> }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}
