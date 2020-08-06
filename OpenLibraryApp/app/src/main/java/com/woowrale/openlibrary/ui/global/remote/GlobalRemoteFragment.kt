package com.woowrale.openlibrary.ui.global.remote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.woowrale.openlibrary.R
import com.woowrale.openlibrary.ui.base.BaseFragment
import javax.inject.Inject

class GlobalRemoteFragment : BaseFragment() {

    @Inject
    lateinit var globalRemoteViewModel: GlobalRemoteViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        globalRemoteViewModel =
                ViewModelProviders.of(this).get(GlobalRemoteViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_global_remote, container, false)

        return root
    }
}