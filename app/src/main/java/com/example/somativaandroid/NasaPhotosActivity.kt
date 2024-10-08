package com.example.somativaandroid
import NASAApiService
import NasaImage
import NasaImageAdapter
import NasaImageResponse
import retrofit2.Call
import retrofit2.Callback

import PlanetAdapter
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.somativaandroid.recyclerviewpackage.Planet
import com.example.somativaandroid.recyclerviewpackage.PlanetSingleton
import com.example.somativaandroid.databinding.HomepageBinding
import com.example.somativaandroid.databinding.NasaPhotosActivityBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Response


class NasaPhotosActivity : AppCompatActivity() {
    lateinit var binding: NasaPhotosActivityBinding
    var exoplanetApiService = RetrofitClient.exoplanetApiService
    lateinit var planetAdapter: PlanetAdapter
    private lateinit var adapter: NasaImageAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = NasaPhotosActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize RecyclerView
        binding.recyclerView.layoutManager = GridLayoutManager(this,1)
        binding.recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        binding.recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL))

        adapter = NasaImageAdapter(emptyList()) // Initialize with an empty list
        binding.recyclerView.adapter = adapter
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        val navbarActivity = NavbarActivity(this)
        navbarActivity.setupNavbar(bottomNavigationView)

        // Call the NASA API
        fetchNasaImages("galaxy") // Example search query "galaxy"
    }

    private fun fetchNasaImages(query: String) {
        val nasaApi = RetrofitClient2.retrofit.create(NASAApiService::class.java)
        val call = nasaApi.searchImages(query)



        call.enqueue(object : Callback<NasaImageResponse> {
            override fun onResponse(call: Call<NasaImageResponse>, response: Response<NasaImageResponse>) {
                if (response.isSuccessful) {
                    Log.d("HomePageActivity", "Full Response: ${response.body()}")

                    // Log the collection info
                    Log.d("HomePageActivity", "Collection href: ${response.body()?.collection?.href}")
                    Log.d("HomePageActivity", "Collection version: ${response.body()?.collection?.version}")
                    val nasaImages = response.body()?.collection?.items ?: emptyList()
                    Log.d("HomePageActivity", "Received ${nasaImages.size} items")

                    // Update the adapter with the new data
                    adapter = NasaImageAdapter(nasaImages)
                    binding.recyclerView.adapter = adapter
                } else {
                    Log.e("HomePageActivity", "Failed to retrieve images")
                }
            }

            override fun onFailure(call: Call<NasaImageResponse>, t: Throwable) {
                Log.e("HomePageActivity", "Error: ${t.message}")
            }
        })
    }

}