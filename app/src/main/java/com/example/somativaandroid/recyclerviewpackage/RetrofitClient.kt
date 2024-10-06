import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://exoplanetarchive.ipac.caltech.edu/" // Altere conforme necessário

    val exoplanetApiService: ExoplanetApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // Configura o Retrofit para usar Gson
            .build()
            .create(ExoplanetApiService::class.java)
    }
}