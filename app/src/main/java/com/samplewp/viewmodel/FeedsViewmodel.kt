package com.samplewp.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.samplewp.api.ApiFactory
import com.samplewp.base.BaseViewModel
import com.samplewp.model.SampleResponse
import com.samplewp.repo.FeedRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FeedsViewmodel(app: Application) : BaseViewModel(app) {

    private var response: MutableLiveData<SampleResponse> = MutableLiveData()
    private val feedRepo = FeedRepository(ApiFactory.makeRetrofitService())

    init {
        refreshFeed()
    }

    fun refreshFeed() {
        //response.value = null
        uiScope.launch {
            response.value = getAllFeed()
        }
    }

    private suspend fun getAllFeed(): SampleResponse? =
        withContext(Dispatchers.IO) {
            return@withContext feedRepo.getFeedDeta()
        }


    class FeedsViewModelFactory(private var app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(Application::class.java).newInstance(app)
        }
    }
}