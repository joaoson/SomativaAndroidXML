import com.example.somativaandroid.recyclerviewpackage.Planet
import retrofit2.Call
import retrofit2.http.GET

interface ExoplanetApiService {
    @GET("TAP/sync?query=SELECT+TOP+30+DISTINCT+pl_name,gaia_id,disc_year,discoverymethod,disc_locale,disc_facility,disc_telescope+FROM+ps&format=json")
    fun getExoplanets(): Call<List<Planet>>
}