package com.example.apimarvel.di

import com.example.apimarvel.remoteDataSource.MarvelServices
import com.example.apimarvel.utils.Utils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofitInit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Utils.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(): MarvelServices {
        return provideRetrofitInit().create(MarvelServices::class.java)
    }
}