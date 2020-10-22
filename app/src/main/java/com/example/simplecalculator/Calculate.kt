package com.example.simplecalculator

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_calculate.*

/**
 * Gets operator number1 and number2 from MainPage
 * evaluates expression and sets result to textView
 * supported operators: - + * /
 */
class Calculate : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculate)

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.customGreen));
        }

        val operator = intent.getSerializableExtra("operator") as Operators
        val number1 = intent.getLongExtra("number1", 0)
        val number2 = intent.getLongExtra("number2", 0)

        calculateExpression(operator, number1, number2)
        tapToClose()
    }

    /**
     * evaluates expression and sets result to textView
     * @operator operator [+ - * /]
     * @num1 first number
     * @num2 second number
     */
    private fun calculateExpression(operator: Operators, num1: Long, num2: Long) {
        if(num2 == 0L) {
            show_result.text = "ERR(ZERO_DIVISION)"
            show_result.textSize = 20f
            return
        }
        var result = when(operator) {
            Operators.ADD -> (num1 + num2).toString()
            Operators.SUBTRACT -> (num1 - num2).toString()
            Operators.MULTIPLY -> (num1 * num2).toString()
            Operators.DIVIDE -> {
                String.format("%.1f", num1.toDouble() / num2.toDouble())
            }
        }
        result = result.replace(Regex("\\.0"), "")
        show_result.text = "$num1 ${operator.char} $num2 = $result"
        if(show_result.text.length >= 18)
            show_result.textSize = 20f
    }

    /**
     * closes activity by pressing anywhere
     */
    private fun tapToClose() {
        full_page.setOnClickListener {
            super.finish()
        }
    }

}