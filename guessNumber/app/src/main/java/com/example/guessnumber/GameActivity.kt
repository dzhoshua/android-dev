package com.example.guessnumber

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast

class GameActivity : AppCompatActivity() {


    private var begin: Int = 0
    private var end: Int = 100
    private var number:Int = 0
    private lateinit var tvAnswer: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        begin = intent.getIntExtra("begin", 0)
        end = intent.getIntExtra("end", 100)

        Log.d("MYTAG", "begin = $begin")
        Log.d("MYTAG", "end = $end")

        tvAnswer = findViewById(R.id.answer)
        guessNumber()
    }

    private fun guessNumber(){
        number = begin + (end - begin) / 2
        tvAnswer.text = number.toString()
    }

    @SuppressLint("SetTextI18n")
    fun onYesNoClick(view: View) {

        when (view.id) {
            R.id.yes -> {
                begin = number
                Log.d("MYTAG", "yes")
            }
            R.id.no -> {
                end = number
                Log.d("MYTAG", "no")
            }
        }
        if (end - begin <= 2) {
            val tvQ = findViewById<TextView>(R.id.question)
            tvQ.text = getString(R.string.finish)
            tvAnswer.text = (begin+1).toString()
            val text = getString(R.string.theEnd)
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
            finish()
        }
        else{
            guessNumber()
        }

    }


}