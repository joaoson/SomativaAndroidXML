package com.example.somativaandroid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.somativaandroid.databinding.ActivityMainBinding
import com.example.somativaandroid.recyclerviewpackage.User
import com.example.somativaandroid.recyclerviewpackage.UserSingleton

class SignUpActivity : AppCompatActivity() {
    lateinit var binding: SignUpActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signuppage)
        enableEdgeToEdge()

        // Layout
        val Username: EditText = findViewById(R.id.Username)
        val Email: EditText = findViewById(R.id.Email)
        val Password: EditText = findViewById(R.id.Password)
        val cbAgeConfirmation: CheckBox = findViewById(R.id.cbAgeConfirmation)

        val btnLaunchJourney: Button = findViewById(R.id.btnLaunchJourney)
        val btnLoginBack: Button = findViewById(R.id.btnLaunchJourney2)



        // Botão "Launch Your Journey"
        btnLaunchJourney.setOnClickListener {
            val email = Email.text.toString()
            val username = Username.text.toString()
            val password = Password.text.toString()
            if (cbAgeConfirmation.isChecked) {
                if (email.isNotEmpty() && username.isNotEmpty() && password.isNotEmpty()) {
                    val intent = Intent(this, HomepageActivity::class.java) //Adicionar a pagina seguinte
                    val user = User(email = email, senha = password, username = username)
                    UserSingleton.addUser(user)

                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please confirm that you are 16 years or older.", Toast.LENGTH_SHORT).show()
            }




        }
        btnLoginBack.setOnClickListener {
            // Intent para navegar para a SecondActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)

        }


    }
}