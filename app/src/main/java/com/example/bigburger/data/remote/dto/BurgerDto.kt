package com.example.bigburger.data.remote.dto

import com.example.bigburger.domain.model.Burger

data class BurgerDto(
    val description: String,
    val price: Int,
    val ref: String,
    val thumbnail: String,
    val title: String
)

fun BurgerDto.toBurger(): Burger {
    return Burger(
        description, price, ref, thumbnail, title
    )
}