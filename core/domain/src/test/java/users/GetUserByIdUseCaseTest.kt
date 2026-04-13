package users

import com.example.domain.model.UserModel
import com.example.domain.usecase.users.GetUserByIdUseCase
import com.example.domain.util.Errors
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import `fake-repository`.FakeUsersRepository


class GetUserByIdUseCaseTest {

    private lateinit var getUserByIdUseCaseTest: GetUserByIdUseCase
    private lateinit var fakeUsersRepository: FakeUsersRepository

    @BeforeEach
    fun setUp() {
        fakeUsersRepository = FakeUsersRepository()
        getUserByIdUseCaseTest = GetUserByIdUseCase(userRepository = fakeUsersRepository)

    }

    @Test
    fun `Default launch`() = runBlocking {
        val id = "1"
        val user = fakeUsersRepository.fakeDb.find {it.id == id}

        val result = getUserByIdUseCaseTest.invoke(id = id).last()

        Assertions.assertEquals(result.data, user)
        Assertions.assertEquals(result.message, null)
    }

    @Test
    fun `If id is empty`() = runBlocking {
        val userId = ""

        val result = getUserByIdUseCaseTest.invoke(id = userId).last()

        Assertions.assertEquals(result.data, null)
        Assertions.assertEquals(result.message, Errors.FirebaseAuthInvalidUserException.error)
    }


}