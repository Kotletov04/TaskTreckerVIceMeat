package `fake-repository`

import com.example.domain.repository.AuthRepository
import java.io.IOException

class FakeAuthRepository: AuthRepository {


    val fakeUserIsAuthorized = false
    var fakeUserEmail = ""
    var fakeUserPassword = ""
    var networkIsConnected = true

    override suspend fun login(email: String, password: String): Boolean {
        return true
    }

    override suspend fun checkAuth(): Boolean {
        return true
    }

    override suspend fun logout() {

    }

    override suspend fun deleteAccount(email: String): Boolean {
        return true
    }

    override suspend fun register(email: String, password: String): Boolean {
        if (networkIsConnected && fakeUserEmail != email) {
            return true
        }
        if (!networkIsConnected) {
            throw IOException()
        }
        if (fakeUserEmail == email) {
            return false
        } else {
            return false
        }

    }

    override suspend fun resetPassword(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun verifyCheckEmail(): Boolean {
        TODO("Not yet implemented")
    }
}