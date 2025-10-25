package com.example.mylibrary

import com.example.domain.repository.AuthRepository
import com.example.domain.repository.HubRepository
import com.example.domain.repository.MediaRepository
import com.example.domain.repository.UserRepository
import com.example.domain.usecase.auth.CheckAuthUseCase
import com.example.domain.usecase.auth.LoginUseCase
import com.example.domain.usecase.auth.LogoutUseCase
import com.example.domain.usecase.auth.RegisterUseCase
import com.example.domain.usecase.auth.VerifyCheckEmailUseCase
import com.example.domain.usecase.hubs.CreateNewHubUseCase
import com.example.domain.usecase.hubs.GetAllHubsUseCase
import com.example.domain.usecase.hubs.GetImagesUseCase
import com.example.domain.usecase.media.PutAvatarImageUseCase
import com.example.domain.usecase.media.RemoteGetAvatarImageUseCase
import com.example.domain.usecase.users.CreateUserUseCase
import com.example.domain.usecase.users.GetCurrentAuthUserUseCase
import com.example.domain.usecase.users.GetUserByIdUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

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
    fun provideCreateUserUseCase(userRepository: UserRepository): CreateUserUseCase {
        return CreateUserUseCase(userRepository = userRepository)
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

    @Provides
    @Singleton
    fun provideGetAllHubsUseCase(hubRepository: HubRepository): GetAllHubsUseCase {
        return GetAllHubsUseCase(hubRepository = hubRepository)
    }

    @Provides
    @Singleton
    fun provideGetCurrentAuthUserUseCase(userRepository: UserRepository): GetCurrentAuthUserUseCase {
        return GetCurrentAuthUserUseCase(userRepository = userRepository)
    }

    @Provides
    @Singleton
    fun provideCreateNewHubUseCase(hubRepository: HubRepository): CreateNewHubUseCase {
        return CreateNewHubUseCase(hubRepository = hubRepository)
    }

    @Provides
    @Singleton
    fun provideGetUserByUidUseCase(userRepository: UserRepository): GetUserByIdUseCase {
        return GetUserByIdUseCase(userRepository = userRepository)
    }

    @Provides
    @Singleton
    fun provideRemoteGetAvatarImageUseCase(mediaRepository: MediaRepository): RemoteGetAvatarImageUseCase {
        return RemoteGetAvatarImageUseCase(mediaRepository = mediaRepository)
    }



    @Provides
    @Singleton
    fun providePutAvatarImageUseCase(mediaRepository: MediaRepository): PutAvatarImageUseCase {
        return PutAvatarImageUseCase(mediaRepository = mediaRepository)
    }



}