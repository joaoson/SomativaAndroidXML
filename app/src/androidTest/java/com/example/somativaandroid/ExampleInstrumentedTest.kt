import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.somativaandroid.recyclerviewpackage.User
import com.example.somativaandroid.recyclerviewpackage.UserSingleton
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SignUpActivityInstrumentedTest {

    @Before
    fun setUp() {
        //Verifica se o UserSingleton inicializa corretamente
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        UserSingleton.setContext(context)
        UserSingleton.userList = emptyList()
    }

    @Test
    fun testAddUserWithValidInputs() {
        // Adiciona um usuário na lista
        val user = User(email = "test@example.com", senha = "password123", username = "testuser")
        UserSingleton.addUser(user)

        val addedUser = UserSingleton.userList.find { it.email == "test@example.com" }
        assertNotNull(addedUser)
        assertEquals("testuser", addedUser?.username)
    }
}
