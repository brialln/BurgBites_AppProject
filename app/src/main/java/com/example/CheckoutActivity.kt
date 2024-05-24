package com.example

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.ui.AppBarConfiguration
import com.example.databinding.CheckoutActivityBinding
import com.example.databinding.OrdersActivityBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CheckoutActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: CheckoutActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CheckoutActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // order now
        val btnCheckoutNow = findViewById<Button>(R.id.btnCheckoutNow)
        btnCheckoutNow.setOnClickListener {
            Toast.makeText(this, "Items Checked Out.", Toast.LENGTH_SHORT).show()
        }

        // done
        val btnDone = findViewById<Button>(R.id.btnDone)
        btnDone.setOnClickListener{
            val Intent = Intent(this, OrdersActivity::class.java)
            startActivity(Intent)
            finish()
        }

    }
}