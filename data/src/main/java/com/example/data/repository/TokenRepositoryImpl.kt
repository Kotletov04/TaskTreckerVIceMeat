package com.example.data.repository

import android.content.Context
import androidx.core.content.edit
import com.example.domain.repository.TokenRepository
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val context: Context
): TokenRepository {

    override suspend fun getToken(): String? {
        return context.getSharedPreferences("token", Context.MODE_PRIVATE).getString("token", null)

    }

    override suspend fun saveToken(token: String) {
        context.getSharedPreferences("token", Context.MODE_PRIVATE).edit { putString("token", token) }
    }

    override suspend fun cleanToken() {
        context.getSharedPreferences("token", Context.MODE_PRIVATE).edit { remove("token") }
    }
}