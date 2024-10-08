package com.example.somativaandroid

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.bumptech.glide.Glide
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.example.somativaandroid.databinding.ActivityMainBinding
import com.example.somativaandroid.recyclerviewpackage.User
import com.example.somativaandroid.recyclerviewpackage.UserSingleton
import com.example.somativaandroid.recyclerviewpackage.userDatabase


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        UserSingleton.setContext(this)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val imageCosmic = findViewById<ImageView>(R.id.cosmic)
        Glide.with(this).load("https://filedn.com/lvGfwT6oV64Y1r7lDsc9I60/cosmic.png").into(imageCosmic)


        binding.button.setOnClickListener {
            val email = binding.editTextText2.text.toString()
            val senha = binding.editTextText.text.toString()
            val userDao = userDatabase.getInstance(this)?.UserDAO()

            // Check if user with the given email and password exists
            val user = userDao?.getUserByEmailAndPassword(email, senha)

            if (user != null) {
                // Email and password match
                Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomepageActivity::class.java)
                startActivity(intent)
            } else {
                // Email or password incorrect
                Toast.makeText(this, "Invalid email or password!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.goToLogin.setOnClickListener {
            // Intent para navegar para a SecondActivity
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }




    }
}