package com.example.somativaandroid

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.somativaandroid.fragments.NasaPhotosFragment
import com.example.somativaandroid.fragments.PlanetsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class NavbarActivity(private val activity: FragmentActivity) {

    fun setupNavbar(bottomNavigationView: BottomNavigationView) {
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.planetsButton -> {
                    // Switch to PlanetsFragment
                    replaceFragment(PlanetsFragment())
                    true
                }
                R.id.nasaPhotosButton -> {
                    // Switch to NasaPhotosFragment
                    replaceFragment(NasaPhotosFragment())
                    true
                }
                R.id.profileButton -> {
                    // Switch to ProfileFragment
                    replaceFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }
    }

    fun replaceFragment(fragment: Fragment) {
        val fragmentManager = activity.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }
}
