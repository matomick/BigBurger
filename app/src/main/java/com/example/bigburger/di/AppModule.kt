package com.example.bigburger.di

import com.example.bigburger.common.Constants
import com.example.bigburger.data.remote.BigBurgerApi
import com.example.bigburger.data.repository.BigBurgerRepositoryImpl
import com.example.bigburger.domain.repository.BigBurgerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBigBurgerApi(): BigBurgerApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BigBurgerApi::class.java)
    }

    @Provides
    @Singleton
    fun provideBigBurgerRepository(api: BigBurgerApi): BigBurgerRepository {
        return BigBurgerRepositoryImpl(api)
    }
}