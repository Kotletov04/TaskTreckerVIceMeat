package users

import com.example.domain.model.UserModel
import com.example.domain.usecase.users.CreateUserUseCase
import com.example.domain.util.Errors
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import repository.FakeUsersRepository

class CreateUserUseCaseTest {

    private lateinit var createUserUseCaseTest: CreateUserUseCase
    private lateinit var fakeUsersRepository: FakeUsersRepository


    @BeforeEach
    fun setUp() {
        fakeUsersRepository = FakeUsersRepository()
        createUserUseCaseTest = CreateUserUseCase(fakeUsersRepository)
    }

    @Test
    fun `Default launch`() = runBlocking {
        val user = UserModel(email = "reutov@gmail.com", username = "TV", role = "User")

        val result =
            createUserUseCaseTest.invoke(email = user.email, username = user.username).last()

        Assertions.assertEquals(result.data, true)
        Assertions.assertEquals(user, fakeUsersRepository.fakeDb.last())

    }


    @Test
    fun `Error creating user without data`() = runBlocking {
        val userFakeEmail = ""
        val userFakeUsername = ""

        val result =
            createUserUseCaseTest.invoke(username = userFakeUsername, email = userFakeEmail).last()

        Assertions.assertEquals(result.message, Errors.CreateUserError.error)
        Assertions.assertEquals(result.data, null)
    }


}