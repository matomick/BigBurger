package com.example.bigburger.domain.use_case

import com.example.bigburger.data.remote.dto.toBurger
import com.example.bigburger.common.Resource
import com.example.bigburger.domain.model.Burger
import com.example.bigburger.domain.repository.BigBurgerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetBigBurgersUseCase @Inject constructor(
    private val repository: BigBurgerRepository
) {

    operator fun invoke(): Flow<Resource<List<Burger>>> = flow {
        try {
            emit(Resource.Loading<List<Burger>>())
            val burgers = repository.getBurgers().map { it.toBurger() }
            if (burgers.size > 0) {
                emit(Resource.Success<List<Burger>>(burgers))
            } else {
                emit(Resource.Error<List<Burger>>("Empty Data"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error<List<Burger>>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<List<Burger>>("Couldn't reach server. Check your internet connection."))
        }
    }

}