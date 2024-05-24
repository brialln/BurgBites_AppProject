package com.example

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import android.view.Menu
import android.view.MenuItem
import android.view.Window
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import com.example.databinding.PersonalSystemActivityBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class PersonalSystemActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: PersonalSystemActivityBinding
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PersonalSystemActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val deleteBtn : Button = findViewById(R.id.deleteBtn)
        deleteBtn.setOnClickListener() {
            binding.etFullName.text.clear()
            binding.etHomeAddress.text.clear()
            binding.etEmailAddress.text.clear()
            binding.etContactNumber.text.clear()
            binding.etUserAge.text.clear()

            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
        }

        val addBtn : Button = findViewById(R.id.addBtn)
        addBtn.setOnClickListener() {
            binding.etFullName.text.clear()
            binding.etHomeAddress.text.clear()
            binding.etEmailAddress.text.clear()
            binding.etContactNumber.text.clear()
            binding.etUserAge.text.clear()

            Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show()
        }

        val submitBtn : Button = findViewById(R.id.submitBtn)
        submitBtn.setOnClickListener() {
            val message : String? = "Are you sure you want to submit?"
            showCustomDialogBox(message)
        }


/*
        val confirmBtn : Button = findViewById(R.id.btnConfirm)
        confirmBtn.setOnClickListener(){
            val fullName = binding.etFullName.text.toString()
            val studentNumber = binding.etStudentNumber.text.toString()
            val emailAddress = binding.etEmailAddress.text.toString()
            val contactNumber = binding.etContactNumber.text.toString()
            val birthDate = binding.etBirthdate.text.toString()

            database = FirebaseDatabase.getInstance().getReference("Users")
            val userinfo = userinfo(fullName, studentNumber, emailAddress, contactNumber, birthDate)
            database.child(studentNumber).setValue(userinfo).addOnSuccessListener {

                binding.etFullName.text.clear()
                binding.etStudentNumber.text.clear()
                binding.etEmailAddress.text.clear()
                binding.etContactNumber.text.clear()
                binding.etBirthdate.text.clear()

                Toast.makeText(this, "Submitted", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "Failed to Submit", Toast.LENGTH_SHORT).show()
            }

        }
 */
    }
    private fun showCustomDialogBox(message: String?) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.layout_dialog_box)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val tvMessage : TextView = dialog.findViewById(R.id.tvMessage)
        val btnConfirm : Button = dialog.findViewById(R.id.btnConfirm)
        val btnCancel : Button = dialog.findViewById(R.id.btnCancel)

        val termsCheckBox = findViewById<CheckBox>(R.id.cbCorrect)

        tvMessage.text = message

            if (!termsCheckBox.isChecked) {
                Toast.makeText(
                    this,
                    "Please certify to the provided information.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                btnConfirm.setOnClickListener() {
                    val fullName = binding.etFullName.text.toString()
                    val homeAddress = binding.etHomeAddress.text.toString()
                    val emailAddress = binding.etEmailAddress.text.toString()
                    val contactNumber = binding.etContactNumber.text.toString()
                    val userAge = binding.etUserAge.text.toString()

                    database = FirebaseDatabase.getInstance().getReference("Users")
                    val User = UserData(fullName, homeAddress, emailAddress, contactNumber, userAge)
                    database.child(contactNumber).setValue(User).addOnSuccessListener {

                        binding.etFullName.text.clear()
                        binding.etHomeAddress.text.clear()
                        binding.etEmailAddress.text.clear()
                        binding.etContactNumber.text.clear()
                        binding.etUserAge.text.clear()

                        dialog.dismiss()
                        Toast.makeText(this, "Submitted", Toast.LENGTH_SHORT).show()
                    }.addOnFailureListener {
                        dialog.dismiss()
                        Toast.makeText(this, "Failed to Submit", Toast.LENGTH_SHORT).show()
                    }
                }

                btnCancel.setOnClickListener() {
                    dialog.dismiss()
                }

                dialog.show()
            }
    }



    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}