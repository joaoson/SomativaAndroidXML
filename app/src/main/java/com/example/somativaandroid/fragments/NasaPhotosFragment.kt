package com.example.somativaandroid.fragments
import NASAApiService
import NasaImage
import NasaImageAdapter
import NasaImageResponse
import retrofit2.Call
import retrofit2.Callback

import PlanetAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.somativaandroid.recyclerviewpackage.Planet
import com.example.somativaandroid.recyclerviewpackage.PlanetSingleton
import com.example.somativaandroid.databinding.HomepageBinding
import com.example.somativaandroid.databinding.NasaPhotosActivityBinding
import com.example.somativaandroid.databinding.NasaPhotosActivitySeachBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Response

class NasaPhotosFragment : Fragment() {

    private lateinit var binding: NasaPhotosActivitySeachBinding
    private lateinit var adapter: NasaImageAdapter
    private var isSingleColumn: Boolean = false // Track if single column or not

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = NasaPhotosActivitySeachBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize RecyclerView with 2 columns by default
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.layoutManager = gridLayoutManager
        binding.recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        binding.recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.HORIZONTAL))

        adapter = NasaImageAdapter(emptyList()) // Initialize with an empty list
        binding.recyclerView.adapter = adapter

        // Fetch NASA images
        binding.searchButton.setOnClickListener {
            val query = binding.searchQuery.text.toString().trim()
            if (query.isNotEmpty()) {
                fetchNasaImages(query)
            } else {
                Toast.makeText(requireContext(), "Please enter a search query", Toast.LENGTH_SHORT).show()
            }
        }
        fetchNasaImages("galaxy") // Example search query "galaxy"

        // Toggle column span on button click
        binding.toggleSpanButton.setOnClickListener {
            isSingleColumn = !isSingleColumn
            val newSpanCount = if (isSingleColumn) 1 else 2
            gridLayoutManager.spanCount = newSpanCount
            adapter.notifyDataSetChanged() // Notify adapter that the layout has changed
        }
    }

    private fun fetchNasaImages(query: String) {
        val nasaApi = RetrofitClient2.retrofit.create(NASAApiService::class.java)
        val call = nasaApi.searchImages(query)

        call.enqueue(object : Callback<NasaImageResponse> {
            override fun onResponse(call: Call<NasaImageResponse>, response: Response<NasaImageResponse>) {
                if (response.isSuccessful) {
                    Log.d("NasaPhotosFragment", "Full Response: ${response.body()}")

                    val nasaImages = response.body()?.collection?.items ?: emptyList()
                    Log.d("NasaPhotosFragment", "Received ${nasaImages.size} items")

                    // Update the adapter with the new data
                    adapter = NasaImageAdapter(nasaImages)
                    binding.recyclerView.adapter = adapter
                } else {
                    Log.e("NasaPhotosFragment", "Failed to retrieve images")
                }
            }

            override fun onFailure(call: Call<NasaImageResponse>, t: Throwable) {
                Log.e("NasaPhotosFragment", "Error: ${t.message}")
            }
        })
    }
}
