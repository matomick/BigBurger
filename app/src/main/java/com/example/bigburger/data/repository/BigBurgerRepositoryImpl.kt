package com.example.bigburger.data.repository

import com.example.bigburger.data.remote.BigBurgerApi
import com.example.bigburger.data.remote.dto.BurgerDto
import com.example.bigburger.domain.model.Burger
import com.example.bigburger.domain.repository.BigBurgerRepository
import javax.inject.Inject

class BigBurgerRepositoryImpl  @Inject constructor(
    private val api: BigBurgerApi
) : BigBurgerRepository {

    override suspend fun getBurgers(): List<BurgerDto> {
        return api.getBurgers()
    }

}