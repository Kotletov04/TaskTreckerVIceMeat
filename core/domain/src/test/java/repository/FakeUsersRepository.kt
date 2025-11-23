package repository

import com.example.domain.model.UserModel
import com.example.domain.repository.UserRepository

class FakeUsersRepository: UserRepository {

    val fakeDb = mutableListOf<UserModel>(
        UserModel(
            id = "1",
            username = "KotelTapunk",
            email = "test@mail.com",
            hubs = emptyList(),
            role = "User"
        ),
        UserModel(
            id = "2",
            username = "PolinaVedeneeva",
            email = "test2@mail.com",
            hubs = emptyList(),
            role = "User"
        )
    )

    override suspend fun getAllUsers(): List<UserModel> {
        return fakeDb
    }

    override suspend fun getCurrentUser(): UserModel? {
        return UserModel(
            id = "1",
            username = "KotelTapunk",
            email = "test@mail.com",
            hubs = emptyList(),
            role = "User"
        )
    }

    override suspend fun getUserById(id: String): UserModel? {
        return fakeDb.find { it.id == id }
    }

    override suspend fun createUser(user: UserModel): Boolean {
        fakeDb.addLast(user)
        return true
    }
}