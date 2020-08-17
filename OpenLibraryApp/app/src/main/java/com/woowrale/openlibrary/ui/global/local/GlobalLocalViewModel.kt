package com.woowrale.openlibrary.ui.global.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.woowrale.openlibrary.domain.model.Seed
import com.woowrale.openlibrary.utils.DataWrapper
import javax.inject.Inject

class GlobalLocalViewModel  @Inject constructor(): ViewModel() {

    private var seedList: MutableLiveData<List<Seed>> = MutableLiveData()

    fun getSeedList(): LiveData<List<Seed>>{
        seedList.value = DataWrapper.seedList
        return seedList
    }
}