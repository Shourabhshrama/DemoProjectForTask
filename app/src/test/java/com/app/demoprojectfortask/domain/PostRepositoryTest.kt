import com.app.demoprojectfortask.data.remote.api.ApiService
import com.app.demoprojectfortask.data.remote.models.PostResponseItem
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import retrofit2.Response

class PostRepositoryTest {

    private lateinit var apiService: ApiService
    private lateinit var postRepository: PostRepository

    @Before
    fun setUp() {
        apiService = Mockito.mock(ApiService::class.java)
        postRepository = PostRepository(apiService)
    }

    @Test
    fun testGetPost() = runBlocking {
        val mockResponse = Response.success(listOf(PostResponseItem()))
        `when`(apiService.getPost()).thenReturn(mockResponse)

        val result = postRepository.getPost()
        assertEquals(mockResponse, result)
    }
}
