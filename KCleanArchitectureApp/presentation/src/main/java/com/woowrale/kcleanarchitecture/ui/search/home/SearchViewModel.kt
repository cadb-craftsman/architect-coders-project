package com.woowrale.kcleanarchitecture.ui.search.home

import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class SearchViewModel @Inject constructor() : ViewModel() {
    private var navigation: MutableLiveData<Intent> = MutableLiveData<Intent>()

    fun navigationTo(context: Context, navigationClass: Class<*>): LiveData<Intent> {
        val intent: Intent = Intent(context, navigationClass)
        navigation.setValue(intent)
        return navigation
    }
}