import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.somativaandroid.recyclerviewpackage.Planet
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlanetsViewModel : ViewModel() {

    // Backing property to store planet list
    private val _planets = MutableLiveData<List<Planet>>()
    val planets: LiveData<List<Planet>> get() = _planets

    // Error message in case of API failure
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    // Function to fetch exoplanets from the API
    fun fetchExoplanets() {
        val apiService = RetrofitClient.exoplanetApiService

        apiService.getExoplanets().enqueue(object : Callback<List<Planet>> {
            override fun onResponse(call: Call<List<Planet>>, response: Response<List<Planet>>) {
                if (response.isSuccessful && response.body() != null) {
                    _planets.postValue(response.body())
                } else {
                    _errorMessage.postValue("Failed to retrieve exoplanets")
                }
            }

            override fun onFailure(call: Call<List<Planet>>, t: Throwable) {
                _errorMessage.postValue(t.message ?: "Unknown error occurred")
            }
        })
    }
}
