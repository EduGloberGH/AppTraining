package com.edu.glob.pokeapp.ui.interactor

sealed class UIState<out T : Any> {
    data class Data<D : Any>(val data: D) : UIState<D>()
    data class Loading(val showLoading: Boolean) : UIState<Nothing>()
    data class Error(val errorMessage: String) : UIState<Nothing>()
    object NoData : UIState<Nothing>()
}
