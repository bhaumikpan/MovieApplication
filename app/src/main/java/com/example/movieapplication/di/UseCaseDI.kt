package com.example.movieapplication.di

import com.example.movieapplication.domain.MovieRepo
import com.example.movieapplication.domain.UseCaseRepo
import com.example.movieapplication.domain.UseCaseRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseDI {
    @Provides
    fun providesUseCaseApi(
        api: MovieRepo
    ): UseCaseRepo = UseCaseRepoImpl(api)
}