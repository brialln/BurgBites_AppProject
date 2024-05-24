package com.example

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.databinding.SignupActivityBinding
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: SignupActivityBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(3000)
        installSplashScreen()
        binding = SignupActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // password view hide
        val cbShowPass = binding.cbShowPass
        val etSignPassword = binding.etSignPassword
        cbShowPass.setOnClickListener {
            if(cbShowPass.isChecked){
                etSignPassword.inputType = 1
            } else {
                etSignPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
        }

        // confirm password view hide
        val cbShowConfirmPass = binding.cbShowConfirmPass
        val etConfirmPassword = binding.etConfirmPassword
        cbShowConfirmPass.setOnClickListener {
            if(cbShowConfirmPass.isChecked){
                etConfirmPassword.inputType = 1
            } else {
                etConfirmPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
        }

        // firebase db sign up
        firebaseAuth = FirebaseAuth.getInstance()
        binding.btnSignUp.setOnClickListener {
            val email = binding.etEmailAddress.text.toString()
            val username = binding.etSignUsername.text.toString()
            val password = binding.etSignPassword.text.toString()
            val confirmPassword = binding.etConfirmPassword.text.toString()

            if (email.isNotEmpty() && username.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()) {
                if (password == confirmPassword) {
                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val intent = Intent(this, LoginActivity::class.java)
                            Toast.makeText(this, "Account Successfully Created.", Toast.LENGTH_LONG).show()
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this, "Password doesn't match.", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "Please fill in all the fields.", Toast.LENGTH_LONG).show()
            }
        }

        // direct to login activity
        val btnContinueLogin = findViewById<Button>(R.id.btnContinueLogin)
        btnContinueLogin.setOnClickListener{
            val Intent = Intent(this,LoginActivity::class.java)
            startActivity(Intent)
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}