package com.example.data.di

import android.content.Context
import com.amazonaws.ClientConfiguration
import com.amazonaws.Protocol
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.S3ClientOptions
import com.example.data.remote.NetworkManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideNetworkManager(@ApplicationContext context: Context): NetworkManager {
        return NetworkManager(context = context)
    }

    @Named("MinioUrl")
    @Provides
    @Singleton
    fun provideRenderMinioUrl(): String {
        return "http://192.168.1.171:9000"
    }

    @Provides
    @Singleton
    fun provideAmazonS3Client(@Named("MinioUrl") endpoint: String): AmazonS3Client {
        val client = ClientConfiguration().apply { this.protocol = Protocol.HTTP }
        val credentials = BasicAWSCredentials("minioadmin", "minioadmin")

        return AmazonS3Client(credentials, client).apply {
            this.endpoint = endpoint
            setS3ClientOptions(S3ClientOptions.builder().setPathStyleAccess(true).build())

        }

    }

}