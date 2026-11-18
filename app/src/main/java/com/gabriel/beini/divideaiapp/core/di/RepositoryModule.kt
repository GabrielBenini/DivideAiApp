package com.gabriel.beini.divideaiapp.core.di

import com.gabriel.beini.divideaiapp.feature.user.data.repository.UserRepositoryImpl
import com.gabriel.beini.divideaiapp.feature.user.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository


}