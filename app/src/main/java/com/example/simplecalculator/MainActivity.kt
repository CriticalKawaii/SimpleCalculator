package com.example.simplecalculator

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var oper = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize binding and set the content view to binding's root
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set click listeners using lambdas and direct view access through binding
        binding.btnAdd.setOnClickListener { calculateResult("+") }
        binding.btnSub.setOnClickListener { calculateResult("-") }
        binding.btnMult.setOnClickListener { calculateResult("*") }
        binding.btnDiv.setOnClickListener { calculateResult("/") }
    }

    private fun calculateResult(operation: String) {
        // Check for empty fields using binding to access views
        if (binding.etNum1.text.isNullOrEmpty() || binding.etNum2.text.isNullOrEmpty()) {
            return
        }

        try {
            val num1 = binding.etNum1.text.toString().toFloat()
            val num2 = binding.etNum2.text.toString().toFloat()
            val result = when (operation) {
                "+" -> num1 + num2
                "-" -> num1 - num2
                "*" -> num1 * num2
                "/" -> {
                    if (num2 == 0f) {
                        binding.tvResult.text = "Cannot divide by zero"
                        return
                    }
                    num1 / num2
                }
                else -> 0f
            }

            oper = operation
            binding.tvResult.text = "$num1 $operation $num2 = $result"
        } catch (e: NumberFormatException) {
            binding.tvResult.text = "Invalid input"
        }
    }
}

