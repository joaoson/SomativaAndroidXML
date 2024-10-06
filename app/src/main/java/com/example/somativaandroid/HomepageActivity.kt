package com.example.somativaandroid
import retrofit2.Call
import retrofit2.Callback

import PlanetAdapter
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.somativaandroid.recyclerviewpackage.Planet
import com.example.somativaandroid.recyclerviewpackage.PlanetSingleton
import com.example.somativaandroid.databinding.HomepageBinding
import retrofit2.Response


class HomepageActivity : AppCompatActivity() {
    lateinit var binding: HomepageBinding
    var exoplanetApiService = RetrofitClient.exoplanetApiService
    lateinit var planetAdapter: PlanetAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.homepage)

        planetAdapter = PlanetAdapter()

        binding = DataBindingUtil.setContentView(this,R.layout.homepage)
        binding.recyclerView.adapter = planetAdapter
        binding.recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        fetchExoplanets()
    }

    private fun fetchExoplanets() {
        // Faz a chamada à API de maneira assíncrona
        val apiService = RetrofitClient.exoplanetApiService

        apiService.getExoplanets().enqueue(object : Callback<List<Planet>> {
            override fun onResponse(call: Call<List<Planet>>, response: Response<List<Planet>>) {
                if (response.isSuccessful && response.body() != null) {
                    // Adiciona os planetas recebidos à lista do Singleton
                    PlanetSingleton.planetList.clear() // Limpa a lista anterior, se houver
                    val exoplanets = response.body()
                    exoplanets?.forEach { planet ->
                        println(planet.name) // Adjust this based on your Planet data class
                        PlanetSingleton.planetList.add(planet)
                    }

                    // Notifica o adaptador que os dados mudaram
                    planetAdapter.notifyDataSetChanged()
                } else {
                    // Lida com erros na resposta
                    Toast.makeText(this@HomepageActivity, "Failed to retrieve exoplanets", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Planet>>, t: Throwable) {
                // Lida com falhas de rede ou outros erros
                Toast.makeText(this@HomepageActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

}