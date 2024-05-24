package com.example

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.example.databinding.OrdersActivityBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class OrdersActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: OrdersActivityBinding
    private lateinit var itemsRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = OrdersActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Initialize Firebase Database instance
        val database = FirebaseDatabase.getInstance()

        // Get reference to the "items" node in the database
        itemsRef = database.getReference("items")


        // accessing the add to cart button
        val btnAddtoCartOne = findViewById<Button>(R.id.btnAddtoCartOne)
        val btnAddtoCartTwo = findViewById<Button>(R.id.btnAddtoCartTwo)

        // accessing the checkout button
        val btnCheckout = findViewById<Button>(R.id.btnCheckout)

        val ItemPriceOne = findViewById<TextView>(R.id.tvItemPriceOne)
        val ItemPriceTwo = findViewById<TextView>(R.id.tvItemPriceTwo)

        val ItemNameOne = findViewById<TextView>(R.id.tvItemNameOne)
        val ItemNameTwo = findViewById<TextView>(R.id.tvItemNameTwo)

        btnAddtoCartOne.setOnClickListener{
            // Save item to Firebase Realtime Database
            val itemName = ItemNameOne.text.toString()
            val itemPrice = ItemPriceOne.text.toString()
            saveItemToDatabase(itemName, itemPrice)
        }

        btnAddtoCartTwo.setOnClickListener{
            // Save item to Firebase Realtime Database
            val itemName = ItemNameTwo.text.toString()
            val itemPrice = ItemPriceTwo.text.toString()
            saveItemToDatabase(itemName, itemPrice)
        }

        btnCheckout.setOnClickListener{
            val intent = Intent(this,CheckoutActivity::class.java)
            startActivity(intent)
        }


        // bottom navigation bar
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.selectedItemId = R.id.BottomOrder

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.BottomOrder -> true
                R.id.BottomAccount -> {
                    startActivity(Intent(applicationContext, AccountActivity::class.java))
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

    private fun saveItemToDatabase(itemName: String, itemPrice: String) {
        // Generate a unique key for the item
        val itemId = itemsRef.push().key ?: return

        // Create the OrdersData object
        val item = OrdersData(itemName, itemPrice)

        // Save the item to the database
        itemsRef.child(itemId).setValue(item)
            .addOnSuccessListener {
                Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show()
            }
    }

}