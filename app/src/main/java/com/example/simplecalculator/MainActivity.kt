package com.example.simplecalculator

import android.annotation.SuppressLint
import com.example.simplecalculator.databinding.MainBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainBinding
    private var oper = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener { calculateResult("+") }
        binding.btnSub.setOnClickListener { calculateResult("-") }
        binding.btnMult.setOnClickListener { calculateResult("*") }
        binding.btnDiv.setOnClickListener { calculateResult("/") }
    }

    @SuppressLint("SetTextI18n")
    private fun calculateResult(operation: String) {
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
                        binding.tvResult.text = "Нельзя делить на 0"
                        return
                    }
                    num1 / num2
                }
                else -> 0f
            }

            oper = operation
            binding.tvResult.text = "$num1 $operation $num2 = $result"
        } catch (e: NumberFormatException) {
            binding.tvResult.text = "Неверный ввод"
        }
    }
}

