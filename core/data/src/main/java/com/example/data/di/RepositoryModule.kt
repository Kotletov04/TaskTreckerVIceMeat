package com.example.data.di

import dagger.Module
import com.example.data.repository.AuthRepositoryImpl
import com.example.data.repository.HubRepositoryImpl
import com.example.data.repository.MediaRepositoryImpl
import com.example.data.repository.ProfileRepositoryImpl
import com.example.data.repository.UserRepositoryImpl
import com.example.domain.repository.AuthRepository
import com.example.domain.repository.HubRepository
import com.example.domain.repository.MediaRepository
import com.example.domain.repository.ProfileRepository
import com.example.domain.repository.UserRepository
import dagger.Binds
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    internal abstract fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    internal abstract fun bindProfileRepository(impl: ProfileRepositoryImpl): ProfileRepository

    @Binds
    @Singleton
    internal abstract fun bindMediaRepository(impl: MediaRepositoryImpl): MediaRepository

    @Binds
    @Singleton
    internal abstract fun bindUserRepository(impl: UserRepositoryImpl): UserRepository

    @Binds
    @Singleton
    internal abstract fun bindHubRepository(impl: HubRepositoryImpl): HubRepository



}