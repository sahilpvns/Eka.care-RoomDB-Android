package com.sahilpvns.ekacare

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.sahilpvns.ekacare.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding?.viewModel = userViewModel
        binding?.lifecycleOwner = this

        binding?.etDOB?.setOnClickListener {
            showDatePickerDialog(binding?.etDOB)
        }

        binding?.btnSave?.setOnClickListener {
            saveUser()
        }

    }
    @SuppressLint("SetTextI18n")
    private fun showDatePickerDialog(etDOB: EditText?) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            etDOB?.setText("$selectedDay/${selectedMonth + 1}/$selectedYear")
        }, year, month, day)
        datePickerDialog.show()

    }

    private fun saveUser() {
        val name = binding?.etName?.text.toString()
        val age = binding?.etAge?.text.toString().toIntOrNull()
        val dob = binding?.etDOB?.text.toString()
        val address = binding?.etAddress?.text.toString()

        if (name.isNotEmpty() && age != null && dob.isNotEmpty() && address.isNotEmpty()) {
            val user = UserInfo(name = name, age = age, dob = dob, address = address)
            userViewModel.insert(user)
            Toast.makeText(this, "User saved!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_SHORT).show()
        }
    }

}