package com.woowrale.kcleanarchitecture.ui.search.home

import android.os.Bundle
import com.woowrale.kcleanarchitecture.R
import com.woowrale.kcleanarchitecture.ui.base.BaseActivity
import com.woowrale.kcleanarchitecture.ui.search.local.LocalSearchActivity
import com.woowrale.kcleanarchitecture.ui.search.remote.RemoteSearchActivity
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.content_search.*
import javax.inject.Inject

class SearchActivity : BaseActivity() {

    @Inject
    lateinit var model: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDagger()

        setContentView(R.layout.activity_search)
        setSupportActionBar(toolbar)

        btnLocalSearch.setOnClickListener { view ->
            startActivity(model.navigationTo(this, LocalSearchActivity::class.java).value)
        }

        btnRemoteSearch.setOnClickListener { view ->
            startActivity(model.navigationTo(this, RemoteSearchActivity::class.java).value)
        }
    }

    override fun initDagger() {
        getDaggerMainComponent().inject(this)
    }

}