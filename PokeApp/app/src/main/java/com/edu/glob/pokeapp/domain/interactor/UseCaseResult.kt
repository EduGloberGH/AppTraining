package com.edu.glob.pokeapp.domain.interactor

sealed class UseCaseResult<out T : Any> {

    class Success<out T : Any>(val data: T): UseCaseResult<T>()
    class Error(val exception: Throwable): UseCaseResult<Nothing>()
    object NoData : UseCaseResult<Nothing>()

}