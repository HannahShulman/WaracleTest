package com.hanna.waracle.test.presenter.alerts

import android.app.AlertDialog
import android.content.Context
import androidx.fragment.app.FragmentManager


fun showErrorAlert(context: Context, message: String, onAction: () -> Unit) {
    val builder = AlertDialog.Builder(context)
    builder.setTitle("An Error!")
    builder.setCancelable(false)//to avoid user from dismissing dialog, and remaining with an empty page.
    builder.setMessage((message))//TODO("Error messages should be mapped.")
    builder.setNegativeButton("RETRY") { dialogInterface, which ->
        dialogInterface.dismiss()
        onAction()
    }
    builder.create()
    builder.show()
}

fun showSimpleAlert(fragmentManager: FragmentManager, message: String) =
    CakeDescriptionDialog.newInstance(message).show(fragmentManager, "")

