package com.example.somativaandroid

import android.app.Activity
import com.google.android.material.bottomnavigation.BottomNavigationView

class NavbarActivity(private val activity: Activity) {

    fun setupNavbar(bottomNavigationView: BottomNavigationView) {
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.planetsButton -> {
                    // inserir mudar de tela ao clicar em planetas
                    true
                }
                R.id.nasaPhotosButton -> {
                    // inserir mudar de tela ao clicar em fotos
                    true
                }
                R.id.profileButton -> {
                    // inserir mudar de tela ao clicar em profile
                    true
                }
                else -> false
            }
        }
    }
}



