package `fake-repository`

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

    var userIsNull = false

    override suspend fun getAllUsers(): List<UserModel> {
        return fakeDb
    }

    override suspend fun getCurrentUser(): UserModel? {
        return if (!userIsNull) {
            UserModel(
                id = "1",
                username = "KotelTapunk",
                email = "test@mail.com",
                hubs = emptyList(),
                role = "User"
            )
        } else {
            null
        }
    }

    override suspend fun getUserById(id: String): UserModel? {
        return if (!userIsNull) {
            fakeDb.find { it.id == id }
        } else {
            null
        }


    }

    override suspend fun createUser(user: UserModel): Boolean {
        fakeDb.addLast(user)
        return true
    }
}