package com.edu.glob.pokeapp.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.edu.glob.pokeapp.ui.interactor.UIState
import com.edu.glob.pokeapp.util.notNull
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

open class ViewModelBase<T: Any>: ViewModel(), CoroutineScope {

    private val job = Job()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        uiState.postValue(UIState.Error(throwable.localizedMessage.notNull()))
        uiState.postValue(UIState.Loading(false))
    }

    override val coroutineContext: CoroutineContext = Dispatchers.Main + coroutineExceptionHandler + job

    val uiState = MutableLiveData<UIState<T>>()

}