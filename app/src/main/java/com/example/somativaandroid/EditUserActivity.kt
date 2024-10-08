package com.example.somativaandroid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.somativaandroid.recyclerviewpackage.User
import com.example.somativaandroid.recyclerviewpackage.UserDAO
import com.example.somativaandroid.recyclerviewpackage.userDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditUserActivity : AppCompatActivity() {

    private lateinit var editTextUsername: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var buttonSave: Button
    private lateinit var buttonCancel: Button
    private lateinit var userDao: UserDAO
    private var userId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_user)

        // Initialize views
        editTextUsername = findViewById(R.id.editTextUsername)
        editTextEmail = findViewById(R.id.editTextEmail)
        buttonSave = findViewById(R.id.buttonSave)
        buttonCancel = findViewById(R.id.buttonCancel)

        // Get the user DAO instance
        userDao = userDatabase.getInstance(this)?.UserDAO()!!

        // Retrieve the logged-in user info from SharedPreferences
        val sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE)
        val loggedInUserEmail = sharedPreferences.getString("loggedInUserEmail", null)
        userId = sharedPreferences.getInt("loggedInUserId", -1)

        // Load the current user details into the fields
        val user = userDao.getUserByEmail(loggedInUserEmail ?: "")
        user?.let {
            editTextUsername.setText(it.username)
            editTextEmail.setText(it.email)
        }

        // Save button logic
        buttonSave.setOnClickListener {
            val newUsername = editTextUsername.text.toString()
            val newEmail = editTextEmail.text.toString()

            if (!isUsernameValid(newUsername)) {
                Toast.makeText(this, "Username should not contain numbers!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!isEmailValid(newEmail)) {
                Toast.makeText(this, "Please enter a valid email address!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Update the user information
            user?.let { user ->
                user.username = newUsername
                user.email = newEmail

                // Perform update operation on a background thread
                lifecycleScope.launch {
                    userDao.update(user)

                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@EditUserActivity, "User information updated successfully!", Toast.LENGTH_SHORT).show()

                        // Update SharedPreferences
                        with(sharedPreferences.edit()) {
                            putString("loggedInUserEmail", newEmail)
                            putString("loggedInUser", newUsername)

                            apply()
                        }

                        finish()
                    }
                }
            }
        }


        // Cancel button logic
        buttonCancel.setOnClickListener {
            // Discard changes and return to the previous activity
            finish()
        }
    }

    private fun isUsernameValid(username: String): Boolean {
        // Check if the username contains any numbers
        return username.matches(Regex("^[a-zA-Z]+$"))
    }

    private fun isEmailValid(email: String): Boolean {
        // Check if the email matches a valid email format
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
