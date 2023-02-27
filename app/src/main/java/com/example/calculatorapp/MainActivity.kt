package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.calculatorapp.databinding.ActivityMainBinding
import java.lang.ref.Cleaner

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var resultText: TextView
    private var operand1: Double = 0.0
    private var operation: String = ""
    private var isDot: Boolean = false
    private var isOp: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        resultText = binding.resultTextView

        val buttonC: Button = binding.buttonClear
        val buttonPlusMinus: Button = binding.buttonPlusMinus
        val buttonDivide: Button = binding.buttonDivide
        val button7: Button = binding.button7
        val button8: Button = binding.button8
        val button9: Button = binding.button9
        val buttonMultiply: Button = binding.buttonMultiply
        val button4: Button = binding.button4
        val button5: Button = binding.button5
        val button6: Button = binding.button6
        val buttonMinus: Button = binding.buttonMinus
        val button1: Button = binding.button1
        val button2: Button = binding.button2
        val button3: Button = binding.button3
        val buttonPlus: Button = binding.buttonPlus
        val button0: Button = binding.button0
        val buttonDot: Button = binding.buttonDot
        val buttonEquals: Button = binding.buttonEquals

        button1.setOnClickListener { appendValue("1") }
        button2.setOnClickListener { appendValue("2") }
        button3.setOnClickListener { appendValue("3") }
        button4.setOnClickListener { appendValue("4") }
        button5.setOnClickListener { appendValue("5") }
        button6.setOnClickListener { appendValue("6") }
        button7.setOnClickListener { appendValue("7") }
        button8.setOnClickListener { appendValue("8") }
        button9.setOnClickListener { appendValue("9") }
        button0.setOnClickListener { appendValue("0") }
        buttonDot.setOnClickListener { appendValue(".") }

        buttonPlus.setOnClickListener { setOperation("+") }
        buttonMinus.setOnClickListener { setOperation("-") }
        buttonMultiply.setOnClickListener { setOperation("*") }
        buttonDivide.setOnClickListener { setOperation("/") }
        buttonEquals.setOnClickListener { calculateResult() }

        buttonC.setOnClickListener { clearAll() }
        buttonPlusMinus.setOnClickListener { toggleSign() }
    }

    private fun appendValue(digit: String) {
        try {
            val currentValue = resultText.text.toString()
            if (digit == ".") {
                if (isDot) {
                    return
                } else {
                    resultText.text = "$currentValue$digit"
                    isDot = true
                }
            } else {
                resultText.text = "$currentValue$digit"
            }
            isOp = false
        }
        catch (e: java.lang.Exception){
            clearAll()
        }

    }

    private fun setOperation(op: String) {
        try {
            if (!isOp) {
                operand1 = resultText.text.toString().toDouble()
                operation = op
                resultText.text = ""
            } else if (operation != op) {
                operation = op
            }
            isDot = false
            isOp = true
        }
        catch (e: java.lang.Exception){
            clearAll()
        }
    }

    private fun calculateResult() {
        if(!isOp) {
            val operand2 = resultText.text.toString().toDouble()
            var result = 0.0

            when (operation) {
                "+" -> result = operand1 + operand2
                "-" -> result = operand1 - operand2
                "*" -> result = operand1 * operand2
                "/" -> result = operand1 / operand2
            }
            if(isInteger(result.toString())){
                resultText.text = result.toInt().toString()
            }else{
                resultText.text = result.toString()
                isDot = true
            }
        }
    }

    private fun clearAll() {
        resultText.text = ""
        operand1 = 0.0
        operation = ""
        isDot = false
    }

    private fun toggleSign() {
        try {
            if (!isOp) {
                val currentValue = resultText.text.toString().toDouble()
                resultText.text = (-currentValue).toString()
            }
        }
        catch (e: java.lang.Exception){
            clearAll()
        }
    }

    fun isInteger(str: String): Boolean {
        val d = str.toDouble()
        return d % 1 == 0.0
    }
}
