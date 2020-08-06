package com.woowrale.openlibrary.ui.global.local

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.woowrale.openlibrary.R
import com.woowrale.openlibrary.ui.base.BaseFragment
import javax.inject.Inject

class GlobalLocalFragment : BaseFragment(){

    @Inject
    lateinit var globalLocalViewModel: GlobalLocalViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        globalLocalViewModel =
                ViewModelProviders.of(this).get(GlobalLocalViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_global_local, container, false)

        return root
    }
}