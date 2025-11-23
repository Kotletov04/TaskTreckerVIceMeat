import com.example.domain.usecase.auth.CheckAuthUseCase
import com.example.domain.usecase.auth.RegisterUseCase
import com.example.domain.util.Errors
import com.example.domain.util.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import repository.FakeAuthRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals

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

        val result = registerUseCaseTest.invoke(email = fakeUserEmail, password = fakeUserPassword).last()

        assertEquals(result.data, true)

    }

    @Test
    fun `Error if password less 8`() = runBlocking {
        val fakeUserPassword = "1234567"
        val fakeUserEmail = "example@gmail.com"

        val result = registerUseCaseTest.invoke(email = fakeUserEmail, password = fakeUserPassword).last()

        assertEquals(result.message, Errors.FirebaseAuthWeakPasswordException.error)
        assertEquals(null, result.data)
    }

    @Test
    fun `Network error`() = runBlocking {
        val fakeUserPassword = "12345678"
        val fakeUserEmail = "example@gmail.com"
        fakeAuthRepository.networkIsConnected = false

        val result = registerUseCaseTest.invoke(email = fakeUserEmail, password = fakeUserPassword).last()

        assertEquals(result.message, Errors.IOException.error)
        assertEquals(null, result.data)
    }

    @Test
    fun `User already registered`() = runBlocking {
        val fakeUserPassword = "12345678"
        val fakeUserEmail = "example@gmail.com"
        fakeAuthRepository.fakeUserPassword = "12345678"
        fakeAuthRepository.fakeUserEmail = "example@gmail.com"

        val result = registerUseCaseTest.invoke(email = fakeUserEmail, password = fakeUserPassword).last()

        assertEquals(result.message, Errors.FirebaseAuthUserCollisionException.error)
        assertEquals(null, result.data)
    }


}