package com.example.somativaandroid.nasaimages

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.somativaandroid.recyclerviewpackage.Planet
import com.example.somativaandroid.recyclerviewpackage.PlanetSingleton
import com.example.somativaandroid.databinding.PlanetItemBinding

class PlanetAdapter() :
    RecyclerView.Adapter<PlanetAdapter.ViewHolder>() {
    private val planetList = PlanetSingleton.planetList

    inner class ViewHolder(val binding: PlanetItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(planet: Planet){
            binding.planetNameTextView.text = planet.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PlanetItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        planetList[position].let { holder.bind(it) }
    }

    override fun getItemCount() = planetList.size

}