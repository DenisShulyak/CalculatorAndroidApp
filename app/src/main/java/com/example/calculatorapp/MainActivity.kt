package com.example.calculatorapp

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.calculatorapp.databinding.ActivityMainBinding
import java.lang.ref.Cleaner

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var resultText: TextView
    private lateinit var buttonC:Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        resultText = binding.resultTextView

        buttonC = binding.buttonClear
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
        val buttonProc: Button = binding.buttonProc
        val buttonEquals: Button = binding.buttonEquals

        var preNum = 0f
        var calc = 0f
        var count = 0
        var clicked = false
        var dclicked=false
        var sclicked=false
        var mclicked= false
        var pclicked = false
        var rclicked = false
        var isCleared = false
        var signed = false

        val listener = object: View.OnClickListener{
            override fun onClick(v: View?) {
                if( clicked == true ){
                    if(signed==true) {
                        signed = false
                    }
                    else { resultText.setText("") } //초기화
                    clicked = false
                }
                val num_text : String = resultText.text.toString()
                if(num_text.equals("0")==true){
                    when (v?.id) {
                        button1.id -> resultText.setText("1")
                        button2.id -> resultText.setText("2")
                        button3.id -> resultText.setText("3")
                        button4.id -> resultText.setText("4")
                        button5.id -> resultText.setText("5")
                        button6.id -> resultText.setText("6")
                        button7.id -> resultText.setText("7")
                        button8.id -> resultText.setText("8")
                        button9.id -> resultText.setText("9")
                        button0.id -> resultText.setText("0")
                    }
                }
                else if(num_text.equals("-0")==true){
                    when (v?.id) {
                        button1.id -> resultText.setText("-1")
                        button2.id -> resultText.setText("-2")
                        button3.id -> resultText.setText("-3")
                        button4.id -> resultText.setText("-4")
                        button5.id -> resultText.setText("-5")
                        button6.id -> resultText.setText("-6")
                        button7.id -> resultText.setText("-7")
                        button8.id -> resultText.setText("-8")
                        button9.id -> resultText.setText("-9")
                        button0.id -> resultText.setText("-0")
                    }
                }
                else {
                    when (v?.id) {
                        button1.id -> resultText.setText(num_text + "1")
                        button2.id -> resultText.setText(num_text + "2")
                        button3.id -> resultText.setText(num_text + "3")
                        button4.id -> resultText.setText(num_text + "4")
                        button5.id -> resultText.setText(num_text + "5")
                        button6.id -> resultText.setText(num_text + "6")
                        button7.id -> resultText.setText(num_text + "7")
                        button8.id -> resultText.setText(num_text + "8")
                        button9.id -> resultText.setText(num_text + "9")
                        button0.id -> resultText.setText(num_text + "0")
                    }
                }
                changeColorText(buttonPlus, R.color.yellow, R.color.white)
                changeColorText(buttonMinus, R.color.yellow, R.color.white)
                changeColorText(buttonDivide, R.color.yellow, R.color.white)
                changeColorText(buttonMultiply, R.color.yellow, R.color.white)
            }
        }
        button1.setOnClickListener (listener)
        button2.setOnClickListener (listener)
        button3.setOnClickListener (listener)
        button4.setOnClickListener (listener)
        button5.setOnClickListener (listener)
        button6.setOnClickListener (listener)
        button7.setOnClickListener (listener)
        button8.setOnClickListener (listener)
        button9.setOnClickListener (listener)
        button0.setOnClickListener (listener)

        buttonDot.setOnClickListener{
            if(clicked == true){
                resultText.setText("")
                clicked = false
            }
            val num_text : String = resultText.text.toString()
            fun isPoint(c: Char) = c in num_text
            if(isPoint('.') == false) {
                resultText.setText(num_text + ".")
            }
            val nt : String = resultText.text.toString()
            if(nt.indexOf('.') == 0) {
                resultText.setText("0" + nt)
            }
            changeColorText(buttonPlus, R.color.yellow, R.color.white)
            changeColorText(buttonMinus, R.color.yellow, R.color.white)
            changeColorText(buttonDivide, R.color.yellow, R.color.white)
            changeColorText(buttonMultiply, R.color.yellow, R.color.white)
        }

        buttonPlus.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        changeColorText(buttonPlus, R.color.white, R.color.black)
                        changeColorText(buttonMinus, R.color.yellow, R.color.white)
                        changeColorText(buttonDivide, R.color.yellow, R.color.white)
                        changeColorText(buttonMultiply, R.color.yellow, R.color.white)
                    }
                    MotionEvent.ACTION_MOVE ->{
                        // Код для обработки перемещения пальца по экрану без отрыва от кнопки
                    }
                    MotionEvent.ACTION_UP ->{
                        count++
                        val num_text : String = resultText.text.toString()
                        if(count==1 && num_text.equals("") == true) {
                            count =0
                        }
                        else if(count==1 || rclicked == true){
                            preNum = num_text.toFloat()
                            pclicked = true
                            clicked = true
                            rclicked = false
                        }
                        else {
                            if(isCleared == true) {
                                isCleared = false
                            }

                            pclicked = true
                            if(dclicked == true){
                                calc = preNum /  num_text.toFloat()
                                dclicked = false
                            }
                            else if(mclicked == true){
                                calc = preNum *  num_text.toFloat()
                                mclicked = false
                            }
                            else if(sclicked == true){
                                calc = preNum -  num_text.toFloat()
                                sclicked = false
                            }
                            else if(pclicked == true){
                                calc = preNum +  num_text.toFloat()
                            }
                            else {
                                calc = preNum +  num_text.toFloat()
                                pclicked = false
                            }
                            count = 1
                            preNum = calc
                            resultText.setText(calc.toString())
                            clicked = true
                            rclicked = false
                        }

                    }
                }
                return true  // необходимо вернуть true чтобы продолжить получать события touch
            }
        })

        buttonMinus.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        changeColorText(buttonMinus, R.color.white, R.color.black)
                        changeColorText(buttonPlus, R.color.yellow, R.color.white)
                        changeColorText(buttonDivide, R.color.yellow, R.color.white)
                        changeColorText(buttonMultiply, R.color.yellow, R.color.white)
                    }
                    MotionEvent.ACTION_MOVE ->{
                        // Код для обработки перемещения пальца по экрану без отрыва от кнопки
                    }
                    MotionEvent.ACTION_UP ->{
                        count++
                        val num_text : String = resultText.text.toString()
                        if(count==1 && (num_text.equals("") == true )) {
                            count = 0
                            resultText.setText("-")
                        }
                        else if(count==1 || rclicked == true){
                            preNum = num_text.toFloat()
                            sclicked = true
                            clicked = true
                            rclicked = false
                        }
                        else {
                            if(isCleared == true) {
                                isCleared = false
                            }

                            sclicked = true
                            if(pclicked == true){
                                calc = preNum +  num_text.toFloat()
                                pclicked = false

                            }
                            else if(mclicked == true){
                                calc = preNum *  num_text.toFloat()
                                mclicked = false
                            }
                            else if(dclicked == true){
                                calc = preNum /  num_text.toFloat()
                                dclicked = false
                            }
                            else if(sclicked == true){
                                calc = preNum -  num_text.toFloat()
                            }
                            else {
                                calc = preNum -  num_text.toFloat()
                                sclicked = false
                            }
                            count = 1
                            preNum = calc
                            resultText.setText(calc.toString())
                            clicked = true
                            rclicked = false
                        }
                    }
                }
                return true  // необходимо вернуть true чтобы продолжить получать события touch
            }
        })

        buttonMultiply.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        changeColorText(buttonMultiply, R.color.white, R.color.black)
                        changeColorText(buttonPlus, R.color.yellow, R.color.white)
                        changeColorText(buttonMinus, R.color.yellow, R.color.white)
                        changeColorText(buttonDivide, R.color.yellow, R.color.white)
                    }
                    MotionEvent.ACTION_MOVE ->{
                        // Код для обработки перемещения пальца по экрану без отрыва от кнопки
                    }
                    MotionEvent.ACTION_UP ->{
                        count++
                        val num_text : String = resultText.text.toString()
                        if(count==1 && num_text.equals("") == true) {
                            count =0
                        }
                        else if(count==1 || rclicked == true){
                            preNum = num_text.toFloat()
                            mclicked = true
                            clicked = true
                            rclicked = false
                        }
                        else {
                            if(isCleared == true) {
                                isCleared = false
                            }

                            mclicked = true
                            if(pclicked == true){
                                calc = preNum +  num_text.toFloat()
                                pclicked = false
                            }
                            else if(dclicked == true){
                                calc = preNum /  num_text.toFloat()
                                dclicked = false
                            }
                            else if(sclicked == true){
                                calc = preNum -  num_text.toFloat()
                                sclicked = false
                            }
                            else if(mclicked == true){
                                calc = preNum *  num_text.toFloat()
                            }
                            else {
                                calc = preNum *  num_text.toFloat()
                                mclicked = false
                            }
                            count = 1
                            preNum = calc
                            resultText.setText(calc.toString())
                            clicked = true
                            rclicked = false
                        }
                    }
                }
                return true  // необходимо вернуть true чтобы продолжить получать события touch
            }
        })

        buttonDivide.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        changeColorText(buttonDivide, R.color.white, R.color.black)
                        changeColorText(buttonPlus, R.color.yellow, R.color.white)
                        changeColorText(buttonMinus, R.color.yellow, R.color.white)
                        changeColorText(buttonMultiply, R.color.yellow, R.color.white)
                    }
                    MotionEvent.ACTION_MOVE ->{
                        // Код для обработки перемещения пальца по экрану без отрыва от кнопки
                    }
                    MotionEvent.ACTION_UP ->{
                        count++
                        var num_text : String = resultText.text.toString()
                        if(count==1 && num_text == "") {
                            count =0
                        }
                        else if(count==1 || rclicked){
                            preNum = num_text.toFloat()
                            dclicked = true
                            clicked = true
                            rclicked = false
                        }
                        else {
                            if(isCleared == true) {
                                isCleared = false
                            }

                            dclicked = true
                            if(pclicked == true){
                                calc = preNum +  num_text.toFloat()
                                pclicked = false
                            }
                            else if(mclicked == true){
                                calc = preNum *  num_text.toFloat()
                                mclicked = false
                            }
                            else if(sclicked == true){
                                calc = preNum -  num_text.toFloat()
                                sclicked = false
                            }
                            else if(dclicked){
                                calc = preNum /  num_text.toFloat()
                            }
                            else {
                                calc = preNum /  num_text.toFloat()
                                dclicked = false
                            }
                            count = 1
                            preNum = calc
                            resultText.setText(calc.toString())
                            clicked = true
                            rclicked = false
                        }
                    }
                }
                return true  // необходимо вернуть true чтобы продолжить получать события touch
            }
        })

        buttonProc.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        changeColorText(buttonProc, R.color.white, R.color.black)
                        changeColorText(buttonPlus, R.color.yellow, R.color.white)
                        changeColorText(buttonMinus, R.color.yellow, R.color.white)
                        changeColorText(buttonDivide, R.color.yellow, R.color.white)
                        changeColorText(buttonMultiply, R.color.yellow, R.color.white)
                    }
                    MotionEvent.ACTION_MOVE ->{
                        // Код для обработки перемещения пальца по экрану без отрыва от кнопки
                    }
                    MotionEvent.ACTION_UP ->{
                        if( resultText.text.toString().equals("")==true ){
                        }
                        else{
                            var num_text: Float = resultText.text.toString().toFloat()
                            var percentCalc = num_text / 100
                            resultText.setText(percentCalc.toString())
                        }
                        changeColorText(buttonProc, R.color.light, R.color.black)
                    }
                }
                return true  // необходимо вернуть true чтобы продолжить получать события touch
            }
        })

        buttonEquals.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        changeColorText(buttonPlus, R.color.yellow, R.color.white)
                        changeColorText(buttonMinus, R.color.yellow, R.color.white)
                        changeColorText(buttonDivide, R.color.yellow, R.color.white)
                        changeColorText(buttonMultiply, R.color.yellow, R.color.white)
                    }
                    MotionEvent.ACTION_MOVE ->{
                        // Код для обработки перемещения пальца по экрану без отрыва от кнопки
                    }
                    MotionEvent.ACTION_UP ->{
                        val num_text : String = resultText.text.toString()
                        if( count == 0 ){
                        }
                        else if(dclicked == true) {
                            calc = preNum / num_text.toFloat()
                            dclicked = false
                            resultText.setText(calc.toString())
                            clicked = true
                            rclicked = true
                        }
                        else if(sclicked == true){
                            calc = preNum - num_text.toFloat()
                            resultText.setText(calc.toString())
                            sclicked = false
                            clicked = true
                            rclicked = true
                        }
                        else if(mclicked == true){
                            calc = preNum * num_text.toFloat()
                            mclicked = false
                            resultText.setText(calc.toString())
                            clicked = true
                            rclicked = true
                        }
                        else if(pclicked == true){
                            calc = preNum + num_text.toFloat()
                            pclicked = false
                            resultText.setText(calc.toString())
                            clicked = true
                            rclicked = true
                        }
                    }
                }
                return true  // необходимо вернуть true чтобы продолжить получать события touch
            }
        })

        buttonC.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        changeColorText(buttonC, R.color.white, R.color.black)
                        changeColorText(buttonPlus, R.color.yellow, R.color.white)
                        changeColorText(buttonMinus, R.color.yellow, R.color.white)
                        changeColorText(buttonDivide, R.color.yellow, R.color.white)
                        changeColorText(buttonMultiply, R.color.yellow, R.color.white)
                    }
                    MotionEvent.ACTION_MOVE ->{
                        // Код для обработки перемещения пальца по экрану без отрыва от кнопки
                    }
                    MotionEvent.ACTION_UP ->{
                        preNum=0f
                        calc = 0f
                        count = 0
                        clicked = false
                        dclicked=false
                        sclicked=false
                        mclicked= false
                        pclicked = false
                        signed = false
                        resultText.setText("0")
                        changeColorText(buttonC, R.color.light, R.color.black)
                    }
                }
                return true  // необходимо вернуть true чтобы продолжить получать события touch
            }
        })

        buttonPlusMinus.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        changeColorText(buttonPlusMinus, R.color.white, R.color.black)
                        changeColorText(buttonPlus, R.color.yellow, R.color.white)
                        changeColorText(buttonMinus, R.color.yellow, R.color.white)
                        changeColorText(buttonDivide, R.color.yellow, R.color.white)
                        changeColorText(buttonMultiply, R.color.yellow, R.color.white)
                    }
                    MotionEvent.ACTION_MOVE ->{
                        // Код для обработки перемещения пальца по экрану без отрыва от кнопки
                    }
                    MotionEvent.ACTION_UP ->{
                        var nt:String = resultText.text.toString()
                        fun isNegative(c: Char) = c in nt
                        if(nt == "0"){
                            resultText.setText("0")
                        }
                        else if(clicked==true){
                            signed =  true
                            if(isNegative('-')==false) {
                                resultText.setText("-"+nt)                }
                            else {
                                resultText.setText(nt.replace("-",""))
                            }
                        }
                        else if(isNegative('-')==false) { //
                            resultText.setText("-"+nt)
                        }
                        else { //
                            resultText.setText(nt.replace("-",""))
                        }
                        changeColorText(buttonPlusMinus, R.color.light, R.color.black)
                    }
                }
                return true  // необходимо вернуть true чтобы продолжить получать события touch
            }
        })
    }
    private fun changeColorText(button:Button, color1:Int, color2:Int){
        button.backgroundTintList = ContextCompat.getColorStateList(this, color1)
        button.setTextColor(ContextCompat.getColor(this, color2))
        //FINISH
    }
}
