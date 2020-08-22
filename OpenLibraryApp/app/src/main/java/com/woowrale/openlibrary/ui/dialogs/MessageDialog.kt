package com.woowrale.openlibrary.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.woowrale.openlibrary.R

class MessageDialog : DialogFragment() {

    lateinit var builder: AlertDialog.Builder

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        builder = AlertDialog.Builder(activity)
        //builder.setTitle("App Title")
        //builder.setMessage("This is an alert with no consequence")
        builder.setPositiveButton(
            getString(R.string.button_ok),
            DialogInterface.OnClickListener{ dialog, id ->

            })

        return builder.create()
    }

    fun showMessage(title: String, message: String){
        builder.setTitle(title)
        builder.setMessage(message)
        builder.show()
    }
}