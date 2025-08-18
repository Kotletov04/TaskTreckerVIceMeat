package com.example.tasktreckervicemeat.di

import com.example.domain.repository.AuthRepository
import com.example.domain.repository.MediaRepository
import com.example.domain.usecase.auth.CheckAuthUseCase
import com.example.domain.usecase.auth.LoginUseCase
import com.example.domain.usecase.auth.LogoutUseCase
import com.example.domain.usecase.auth.RegisterUseCase
import com.example.domain.usecase.auth.VerifyCheckEmailUseCase
import com.example.domain.usecase.hubs.GetImagesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Domain {

    @Provides
    @Singleton
    fun provideLoginUseCase(authRepository: AuthRepository): LoginUseCase {
        return LoginUseCase(authRepository = authRepository)
    }

    @Provides
    @Singleton
    fun provideRegisterUseCase(authRepository: AuthRepository): RegisterUseCase {
        return RegisterUseCase(authRepository = authRepository)
    }

    @Provides
    @Singleton
    fun provideVerifyEmailUseCase(authRepository: AuthRepository): VerifyCheckEmailUseCase {
        return VerifyCheckEmailUseCase(authRepository = authRepository)
    }

    @Provides
    @Singleton
    fun provideCheckAuthUseCase(authRepository: AuthRepository): CheckAuthUseCase {
        return CheckAuthUseCase(authRepository = authRepository)
    }

    @Provides
    @Singleton
    fun provideLogoutUseCase(authRepository: AuthRepository): LogoutUseCase {
        return LogoutUseCase(authRepository = authRepository)
    }

    @Provides
    @Singleton
    fun provideGetImagesUseCase(mediaRepository: MediaRepository): GetImagesUseCase {
        return GetImagesUseCase(mediaRepository = mediaRepository)
    }

}