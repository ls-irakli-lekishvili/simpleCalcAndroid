package com.example.simplecalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author Irakli Lekishvili
 * @version 1.0.0
 * @name simple calculator
 *
 * Main page of application gets user Input
 * Starts new activity Calculate
 */
class MainPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addClickListener(add, Operators.ADD)
        addClickListener(subtract, Operators.SUBTRACT)
        addClickListener(multiply, Operators.MULTIPLY)
        addClickListener(divide, Operators.DIVIDE)
    }

    /**
     * Adds event on button
     * Gets user input and opens new Activity
     * @button Buttons TextView
     * @operator Enum of operator
     */
    private fun addClickListener(button: Button, operator: Operators) {
        button.setOnClickListener {
            val numbers: LongArray? = getNumbers()
            if(numbers != null) {
                val calculate = Intent(this, Calculate::class.java).apply {
                    putExtra("operator", operator)
                    putExtra("number1", numbers[0])
                    putExtra("number2", numbers[1])
                }
                startActivity(calculate)
            }
        }
    }

    /**
     * @return LongArray if both number fields are filled, null otherwise
     */
    private fun getNumbers(): LongArray? {
        val num1 = number1.text.toString()
        val num2 = number2.text.toString()
        if(num1.isEmpty() || num2.isEmpty()) {
            val toast = Toast.makeText(applicationContext, "Please enter both numbers", Toast.LENGTH_LONG)
            toast.show()
            return null;
        }
        return longArrayOf(num1.toLong(), num2.toLong())
    }
}