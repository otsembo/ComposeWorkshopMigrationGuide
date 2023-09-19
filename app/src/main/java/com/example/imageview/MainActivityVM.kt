package com.example.imageview

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.update

class MainActivityVM : ViewModel() {

    private val _numState: MutableStateFlow<Int> = MutableStateFlow(0)
    val numState: StateFlow<Int> = _numState

    fun increaseCount(){
        _numState.update {
            it + 1
        }
    }

    fun decreaseCount(){
        _numState.update {
            if(it == 0 ) 0 else  it - 1
        }
    }

}