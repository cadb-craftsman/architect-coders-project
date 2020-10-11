package com.woowrale.openlibrary.ui.base

import androidx.fragment.app.Fragment
import com.woowrale.openlibrary.di.injector.Injectable
import com.woowrale.openlibrary.ui.dialogs.AlertMessageDialog
import com.woowrale.openlibrary.ui.dialogs.ConfirmMessageDialog

abstract class BaseFragment : Fragment(), Injectable {

    fun showMessageDialog() {
        ConfirmMessageDialog.newInstance()
            .show(requireActivity().supportFragmentManager, "Alert Message Dialog")
    }

    fun showAlertMessageDialog() {
        AlertMessageDialog.newInstance()
            .show(requireActivity().supportFragmentManager, "Alert Message Dialog")
    }

}
