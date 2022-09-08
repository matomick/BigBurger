package com.example.bigburger.domain.use_case

import com.example.bigburger.domain.repository.BigBurgerRepository
import javax.inject.Inject

class GetBigBurgersUseCase @Inject constructor(
    private val repository: BigBurgerRepository
){


}