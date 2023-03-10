package com.example.android.marsrealestate.screens.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.android.marsrealestate.network.MarsProperty

/**
 * The [ViewModel] that is associated with the [DetailFragment].
 */
class DetailViewModel(
    @Suppress("UNUSED_PARAMETER") marsProperty: MarsProperty, app: Application
) : AndroidViewModel(app) {}
