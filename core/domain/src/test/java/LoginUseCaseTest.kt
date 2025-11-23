import com.example.domain.usecase.auth.LoginUseCase
import com.example.domain.usecase.auth.RegisterUseCase
import com.example.domain.util.Errors
import com.example.domain.util.Resource
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import repository.FakeAuthRepository





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

        assertEquals(result.data, true)
    }

    @Test
    fun `If fields are not specified it will not authorize`() = runBlocking {
        val fakeEmail = ""
        val fakePassword = ""

        val result = loginUseCaseTest.invoke(email = fakeEmail, password = fakePassword).last()

        assertEquals(result.data, null)
        assertEquals(result.message, Errors.NoInputData.error)
    }

    @Test
    fun `Only mail is indicated`() = runBlocking {
        val fakeEmail = "test@gmail.com"
        val fakePassword = ""

        val result = loginUseCaseTest.invoke(email = fakeEmail, password = fakePassword).last()

        assertEquals(result.data, null)
        assertEquals(result.message, Errors.InvalidData.error)
    }



}