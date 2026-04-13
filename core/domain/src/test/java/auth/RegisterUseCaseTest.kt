package auth

import com.example.domain.usecase.auth.RegisterUseCase
import com.example.domain.util.Errors
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import `fake-repository`.FakeAuthRepository

class RegisterUseCaseTest {

    private lateinit var registerUseCaseTest: RegisterUseCase
    private lateinit var fakeAuthRepository: FakeAuthRepository


    @BeforeEach
    fun setUp() {
        fakeAuthRepository = FakeAuthRepository()
        registerUseCaseTest = RegisterUseCase(authRepository = fakeAuthRepository)
    }



    @Test
    fun `Default test useCase`() = runBlocking {
        val fakeUserPassword = "12345678"
        val fakeUserEmail = "example@gmail.com"

        val result =
            registerUseCaseTest.invoke(email = fakeUserEmail, password = fakeUserPassword).last()

        Assertions.assertEquals(result.data, true)

    }

    @Test
    fun `Error if password less 8`() = runBlocking {
        val fakeUserPassword = "1234567"
        val fakeUserEmail = "example@gmail.com"

        val result =
            registerUseCaseTest.invoke(email = fakeUserEmail, password = fakeUserPassword).last()

        Assertions.assertEquals(result.message, Errors.FirebaseAuthWeakPasswordException.error)
        Assertions.assertEquals(null, result.data)
    }

    @Test
    fun `Network error`() = runBlocking {
        val fakeUserPassword = "12345678"
        val fakeUserEmail = "example@gmail.com"
        fakeAuthRepository.networkIsConnected = false

        val result =
            registerUseCaseTest.invoke(email = fakeUserEmail, password = fakeUserPassword).last()

        Assertions.assertEquals(result.message, Errors.IOException.error)
        Assertions.assertEquals(null, result.data)
    }

}