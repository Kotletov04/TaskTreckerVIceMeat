package users

import com.example.domain.model.UserModel
import com.example.domain.usecase.users.GetCurrentAuthUserUseCase
import com.example.domain.util.Errors
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import `fake-repository`.FakeUsersRepository

class GetCurrentAuthUserUseCaseTest {

    private lateinit var getCurrentAuthUserUseCaseTest: GetCurrentAuthUserUseCase
    private lateinit var fakeUsersRepository: FakeUsersRepository


    @BeforeEach
    fun setUp() {
        fakeUsersRepository = FakeUsersRepository()
        getCurrentAuthUserUseCaseTest = GetCurrentAuthUserUseCase(userRepository = fakeUsersRepository)
    }

    @Test
    fun `Default launch`() = runBlocking {
        val user = UserModel(
            id = "1",
            username = "KotelTapunk",
            email = "test@mail.com",
            hubs = emptyList(),
            role = "User"
        )

        val result = getCurrentAuthUserUseCaseTest.invoke().last()

        Assertions.assertEquals(result.data, user)
        Assertions.assertEquals(result.message, null)
    }


    @Test
    fun `If the user is absent`() = runBlocking {
        fakeUsersRepository.userIsNull = true

        val result = getCurrentAuthUserUseCaseTest.invoke().last()

        Assertions.assertEquals(result.data, null)
        Assertions.assertEquals(result.message, Errors.FirebaseAuthInvalidUserException.error)
    }

}