package com.example.somativaandroid.fragments
import MarginItemDecoration

import retrofit2.Call
import retrofit2.Callback

import PlanetAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.somativaandroid.R
import com.example.somativaandroid.recyclerviewpackage.Planet
import com.example.somativaandroid.recyclerviewpackage.PlanetSingleton
import com.example.somativaandroid.databinding.HomepageBinding
import retrofit2.Response
class PlanetsFragment : Fragment() {

    private lateinit var binding: HomepageBinding
    private lateinit var planetAdapter: PlanetAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment using DataBindingUtil
        binding = DataBindingUtil.inflate(inflater, R.layout.homepage, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the RecyclerView and the PlanetAdapter
        planetAdapter = PlanetAdapter()

        binding.recyclerView.adapter = planetAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.addItemDecoration(MarginItemDecoration(22))

        // Fetch exoplanets from API
        fetchExoplanets()
    }

    private fun fetchExoplanets() {
        // Make the API call asynchronously
        val apiService = RetrofitClient.exoplanetApiService

        apiService.getExoplanets().enqueue(object : Callback<List<Planet>> {
            override fun onResponse(call: Call<List<Planet>>, response: Response<List<Planet>>) {
                if (response.isSuccessful && response.body() != null) {
                    // Clear the previous list (if any) and add the new planets
                    PlanetSingleton.planetList.clear()
                    val exoplanets = response.body()

                    exoplanets?.forEach { planet ->
                        println(planet.name) // Adjust this based on your Planet data class
                        PlanetSingleton.planetList.add(planet)
                    }

                    // Notify the adapter that the data has changed
                    planetAdapter.notifyDataSetChanged()
                } else {
                    // Handle errors in the response
                    Toast.makeText(requireContext(), "Failed to retrieve exoplanets", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Planet>>, t: Throwable) {
                // Handle network failures or other errors
                println(t.message)
                Toast.makeText(requireContext(), "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
