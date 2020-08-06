package com.woowrale.openlibrary.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.woowrale.openlibrary.R
import com.woowrale.openlibrary.ui.base.BaseFragment
import javax.inject.Inject

class DetailsFragment : BaseFragment() {

    @Inject
    lateinit var detailsViewModel: DetailsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        detailsViewModel =
                ViewModelProviders.of(this).get(DetailsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_details, container, false)
        return root
    }
}