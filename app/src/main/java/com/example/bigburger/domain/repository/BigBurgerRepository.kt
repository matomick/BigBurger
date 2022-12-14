package com.example.bigburger.domain.repository

import com.example.bigburger.data.remote.dto.BurgerDto
import com.example.bigburger.domain.model.Burger

interface BigBurgerRepository {

    suspend fun getBurgers(): List<BurgerDto>

}