package com.example.tasktreckervicemeat.di

import android.content.Context
import com.example.data.remote.NetworkManager
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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Data {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun provideAuthRepository(firebaseAuth: FirebaseAuth): AuthRepository {
        return AuthRepositoryImpl(firebaseAuth = firebaseAuth)
    }

    @Provides
    @Singleton
    fun provideProfileRepository(firebaseDb: FirebaseFirestore): ProfileRepository {
        return ProfileRepositoryImpl(firebaseDb = firebaseDb)
    }

    @Provides
    @Singleton
    fun provideMediaRepository(@ApplicationContext context: Context): MediaRepository {
        return MediaRepositoryImpl(context = context)
    }

    @Provides
    @Singleton
    fun provideUserRepository(firebaseDb: FirebaseFirestore, firebaseAuth: FirebaseAuth): UserRepository {
        return UserRepositoryImpl(firebaseDb = firebaseDb, firebaseAuth = firebaseAuth)
    }

    @Provides
    @Singleton
    fun provideHubRepository(firebaseDb: FirebaseFirestore, firebaseAuth: FirebaseAuth): HubRepository {
        return HubRepositoryImpl(firebaseDb = firebaseDb, firebaseAuth = firebaseAuth)
    }

    @Provides
    @Singleton
    fun provideNetworkManager(@ApplicationContext context: Context): NetworkManager {
        return NetworkManager(context = context)
    }
}