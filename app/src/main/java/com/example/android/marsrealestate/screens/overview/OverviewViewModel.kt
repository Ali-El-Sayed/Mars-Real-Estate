package com.example.android.marsrealestate.screens.overview

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.marsrealestate.network.MarsApiService
import com.example.android.marsrealestate.network.MarsProperty
import com.example.android.marsrealestate.util.IConnected
import com.example.android.marsrealestate.util.NetworkMonitor
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*

enum class MarsApiStatus { LOADING, ERROR, DONE }

/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */
class OverviewViewModel(context: Application, val view: View?) : AndroidViewModel(context),
    IConnected {

    // The internal MutableLiveData String that stores the most recent response
    private val _status = MutableLiveData<MarsApiStatus>()
    val status: LiveData<MarsApiStatus>
        get() = _status

    private val _properties = MutableLiveData<List<MarsProperty>>()
    val properties: LiveData<List<MarsProperty>>
        get() = _properties

    private val networkMonitor = NetworkMonitor(context, this)

    init {
//        doRequest()
        networkMonitor.register()
    }

    override fun onCleared() {
        super.onCleared()
        networkMonitor.unRegister()
    }

    override fun doRequest() {
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                _status.value = MarsApiStatus.LOADING

            }
        }
        viewModelScope.launch {
            try {
                _properties.value = MarsApiService().getInstance().getProperties()
                _status.value = MarsApiStatus.DONE
            } catch (e: Exception) {
                _status.value = MarsApiStatus.ERROR
                _properties.value = listOf()
            }
        }
    }

    override fun showError() {
        if (view != null) {
            Snackbar.make(view, "No Internet connection", Snackbar.LENGTH_LONG).show()
        }
    }


}
