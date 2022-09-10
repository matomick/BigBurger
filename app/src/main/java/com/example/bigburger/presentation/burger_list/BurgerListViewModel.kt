package com.example.bigburger.presentation.burger_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.bigburger.common.Resource
import com.example.bigburger.domain.model.Burger
import com.example.bigburger.domain.use_case.GetBigBurgersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BurgerListViewModel @Inject constructor(
    private val getBurgersUseCase: GetBigBurgersUseCase
) : ViewModel() {

    fun getBurgers(): LiveData<Resource<List<Burger>>> {
        return getBurgersUseCase().asLiveData(viewModelScope.coroutineContext)
    }

}