import com.example.domain.usecase.auth.CheckAuthUseCase

import org.junit.jupiter.api.BeforeEach

class CheckAuthUseCaseTest {


    private lateinit var checkAuthUseCaseTest: CheckAuthUseCase

    @BeforeEach
    fun setUp() {
        checkAuthUseCaseTest = CheckAuthUseCase()
    }


}