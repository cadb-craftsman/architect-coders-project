package com.woowrale.openlibrary.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.woowrale.openlibrary.R

class AlertMessageDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var builder = AlertDialog.Builder(requireActivity(), R.style.CustomAlertDialog)

        builder.setTitle(getString(R.string.title_alert_message))
        builder.setMessage(getString(R.string.body_alert_message))
        builder.setPositiveButton(getString(R.string.button_ok), DialogInterface.OnClickListener { dialog, which ->
            dialog.cancel()
        })
        return builder.create()
    }

    companion object {
        @JvmStatic
        fun newInstance(): AlertMessageDialog {
            return AlertMessageDialog()
        }
    }
}