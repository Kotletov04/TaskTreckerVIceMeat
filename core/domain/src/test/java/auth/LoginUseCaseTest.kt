package auth

import com.example.domain.usecase.auth.LoginUseCase
import com.example.domain.util.Errors
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import `fake-repository`.FakeAuthRepository

class LoginUseCaseTest {

    private lateinit var loginUseCaseTest: LoginUseCase
    private lateinit var fakeAuthRepository: FakeAuthRepository


    @BeforeEach
    fun setUp() {
        fakeAuthRepository = FakeAuthRepository()
        loginUseCaseTest = LoginUseCase(authRepository = fakeAuthRepository)
    }

    @Test
    fun `Default launch`() = runBlocking {
        val fakeEmail = "test@gmail.com"
        val fakePassword = "12345678"

        val result = loginUseCaseTest.invoke(email = fakeEmail, password = fakePassword).last()

        Assertions.assertEquals(result.data, true)
    }

    @Test
    fun `If fields are not specified it will not authorize`() = runBlocking {
        val fakeEmail = ""
        val fakePassword = ""

        val result = loginUseCaseTest.invoke(email = fakeEmail, password = fakePassword).last()

        Assertions.assertEquals(result.data, null)
        Assertions.assertEquals(result.message, Errors.NoInputData.error)
    }

    @Test
    fun `Only mail is indicated`() = runBlocking {
        val fakeEmail = "test@gmail.com"
        val fakePassword = ""

        val result = loginUseCaseTest.invoke(email = fakeEmail, password = fakePassword).last()

        Assertions.assertEquals(result.data, null)
        Assertions.assertEquals(result.message, Errors.InvalidData.error)
    }



}