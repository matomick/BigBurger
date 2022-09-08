package com.example.bigburger.presentation.burger_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.bigburger.common.Resource
import com.example.bigburger.domain.use_case.GetBigBurgersUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import com.example.bigburger.domain.model.Burger

class BurgerListViewModel @Inject constructor(
    private val getBurgersUseCase: GetBigBurgersUseCase
) : ViewModel() {

    fun getBurgers(callback: GetBigBurgersCallback) {
        getBurgersUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    callback.onPostsLoaded(result.data ?: emptyList())
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
    fun onPostsLoaded(posts: List<Burger>)
    fun onError(errorMessage: String)
    fun onLoading()
}