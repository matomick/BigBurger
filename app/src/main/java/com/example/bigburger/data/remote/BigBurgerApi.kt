package com.example.bigburger.data.remote

import com.example.bigburger.data.remote.dto.BurgerDto
import retrofit2.http.GET

interface BigBurgerApi {

    @GET("/bigburger")
    suspend fun getBurgers(): List<BurgerDto>

}