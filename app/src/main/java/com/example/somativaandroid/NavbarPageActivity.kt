package com.example.somativaandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.somativaandroid.fragments.PlanetsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class NavbarPageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navbar)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        val navbarActivity = NavbarActivity(this)
        navbarActivity.setupNavbar(bottomNavigationView)

        // Set default fragment when activity starts
        if (savedInstanceState == null) {
            navbarActivity.setupNavbar(bottomNavigationView)
            navbarActivity.replaceFragment(PlanetsFragment()) // Set initial fragment
        }
    }
}
