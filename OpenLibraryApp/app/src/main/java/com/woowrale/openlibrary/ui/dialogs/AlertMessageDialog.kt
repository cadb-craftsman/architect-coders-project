package com.woowrale.openlibrary.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.woowrale.openlibrary.R

class AlertMessageDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var title: String = "Titulo"//savedInstanceState!!.getString("title", "")
        var message: String = "Message" //savedInstanceState!!.getString("message", "")
        var builder = AlertDialog.Builder(requireActivity())

        builder.setTitle(getString(R.string.title_message))
        builder.setMessage(getString(R.string.body_message))
        builder.setPositiveButton(getString(R.string.button_ok), DialogInterface.OnClickListener { dialog, which ->
            dialog.dismiss()
        })
        /*
        builder.setNegativeButton(getString(R.string.button_cancel), DialogInterface.OnClickListener { dialog, which ->
            dialog.dismiss()
        })
         */
        return builder.create()
    }

    companion object {

        fun newInstance(): AlertMessageDialog {
            return AlertMessageDialog()
        }
        /*
        fun newInstance(title: String, message: String): AlertMessageDialog {
            val fragment = AlertMessageDialog()
            val args = Bundle()
            args.putString("title", title)
            args.putString("message", message)
            fragment.arguments = args
            return fragment
        }
         */
    }
}