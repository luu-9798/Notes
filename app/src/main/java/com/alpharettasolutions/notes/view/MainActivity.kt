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
        viewModel.getAllNotes()
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
                    displayAlertDialog(
                        title = "Empty Title or Content ",
                        message = "Title and content must not be empty"
                    )
                }
                ViewState.NOTIFY_DISCARD_CHANGES -> {
                    displayAlertDialog(
                        title = "Discard Changes",
                        message = "Are you sure you want to discard your changes?",
                        cancellable = true
                    ) {
                        viewModel.closeNoteDetailFragment()
                    }
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

    /**
     * displayAlertDialog - Function to display an alert dialog
     *
     * @param title      : Title of the alert dialog
     * @param message    : Message to be displayed in the alert dialog
     * @param cancellable: Boolean value to determine whether the alert dialog can be cancelled or not
     * @param callback   : Callback function to be executed when "OK" button is clicked
     */
    private fun displayAlertDialog(
        title: String,
        message: String,
        cancellable: Boolean = false,
        callback: (() -> Unit)? = null
    ) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle(title)
        alertDialogBuilder.setMessage(message)

        alertDialogBuilder.setPositiveButton("OK") { _, _ -> callback?.invoke() }

        if (cancellable) {
            alertDialogBuilder.setNegativeButton("Cancel") { _, _ -> }
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}
