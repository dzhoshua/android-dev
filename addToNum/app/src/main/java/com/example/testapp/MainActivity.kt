package com.example.testapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(v: View) {
        val etA = findViewById<EditText>(R.id.numA)
        val etB = findViewById<EditText>(R.id.numB)
        val tvSum = findViewById<TextView>(R.id.sum)

        val strA = etA.text.toString()
        val strB = etB.text.toString()

        if(strA.isEmpty() or strB.isEmpty()){
            val m: String = "You should enter 2 numbers."
            tvSum.text = m
        }
        else{
            try{
                val sum = strA.toFloat() + strB.toFloat()
                val strSum = sum.toString()
                tvSum.text = strSum
            }
            catch (e: NumberFormatException){
                val m: String = "You should only enter numbers!"
                tvSum.text = m
            }
        }
    }
}