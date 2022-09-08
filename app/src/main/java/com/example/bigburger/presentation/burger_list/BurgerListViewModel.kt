package com.example.bigburger.presentation.burger_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bigburger.common.Resource
import com.example.bigburger.domain.model.Burger
import com.example.bigburger.domain.use_case.GetBigBurgersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BurgerListViewModel @Inject constructor(
    private val getBurgersUseCase: GetBigBurgersUseCase
) : ViewModel() {

    fun getBurgers(callback: GetBigBurgersCallback) {
        getBurgersUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    callback.onGetBurgersLoaded(result.data ?: emptyList())
                }
                is Resource.Error -> {
                    callback.onError(result.message ?: "An unexpected error occured")
                }
                is Resource.Loading -> {
                    callback.onLoading()
                }
            }
        }.launchIn(viewModelScope)
    }
}

interface GetBigBurgersCallback {
    fun onGetBurgersLoaded(burgers: List<Burger>)
    fun onError(errorMessage: String)
    fun onLoading()
}