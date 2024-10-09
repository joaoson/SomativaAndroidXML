import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Path

interface NASAApiService {
    @GET("search")
    fun searchImages(@Query("q") query: String): Call<NasaImageResponse>
}
