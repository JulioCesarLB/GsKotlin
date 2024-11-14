package br.com.analuisa936_juliocesar94543.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DicasViewModelFactory(private val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(DicasViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DicasViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}