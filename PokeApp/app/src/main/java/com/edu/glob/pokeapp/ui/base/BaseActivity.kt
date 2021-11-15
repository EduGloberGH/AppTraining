package com.edu.glob.pokeapp.ui.base

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.edu.glob.pokeapp.ui.interactor.UIState

abstract class BaseActivity<B : ViewBinding, VM : ViewModelBase<*>> : AppCompatActivity() {

    protected lateinit var binding: B
    protected lateinit var viewModel: VM

    abstract fun getViewBinding(): B
    abstract fun getMainViewModel(): VM
    abstract fun processData(any: Any)
    abstract fun processError(message: String)
    abstract fun showLoading(show: Boolean)
    abstract fun processNoData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewBinding()
        setupUIObserver()
    }

    private fun setupViewBinding() {
        binding = getViewBinding()
        setContentView(binding.root)
    }

    private fun setupUIObserver() {
        viewModel = getMainViewModel()
        viewModel.uiState.observe(this, { uiState ->
            Log.v("LALALALALA", "entrando al post $uiState")
            when (uiState) {
                is UIState.Data -> processData(uiState.data)
                is UIState.Error -> processError(uiState.errorMessage)
                is UIState.Loading -> showLoading(uiState.showLoading)
                is UIState.NoData -> processNoData()
            }
        })
    }

}