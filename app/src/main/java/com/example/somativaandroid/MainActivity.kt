package com.example.somativaandroid

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.example.somativaandroid.databinding.ActivityMainBinding
import com.example.somativaandroid.recyclerviewpackage.User
import com.example.somativaandroid.recyclerviewpackage.UserSingleton


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        UserSingleton.setContext(this)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.button2.setOnClickListener {
            val email = binding.editTextText2.text.toString()
            val senha = binding.editTextText.text.toString()
            val user = User(email = email, senha = senha)
            UserSingleton.addUser(user)
        }




    }
}