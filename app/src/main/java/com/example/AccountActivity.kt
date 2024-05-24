package com.example

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.example.databinding.AccountActivityBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class AccountActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: AccountActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AccountActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnAccountInfo : Button = findViewById(R.id.btnAccountInfo)
        btnAccountInfo.setOnClickListener{
            Toast.makeText(this, "Edit your information", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, PersonalSystemActivity::class.java)
            startActivity(intent)
        }

        val btnLogout : Button = findViewById(R.id.btnLogout)
        btnLogout.setOnClickListener {
            Toast.makeText(this, "Successfully Logout", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.selectedItemId = R.id.BottomAccount


        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.BottomAccount -> true
                R.id.BottomOrder -> {
                    startActivity(Intent(applicationContext, OrdersActivity::class.java))
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    finish()
                    true
                }
                R.id.BottomNotification -> {
                    startActivity(Intent(applicationContext, DeliveryActivity::class.java))
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    finish()
                    true
                }
                R.id.BottomHome -> {
                    startActivity(Intent(applicationContext, HomeActivity::class.java))
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    finish()
                    true
                }
                else -> false
            }
        }

    }
}