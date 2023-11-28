package com.example.guessnumber


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onGuessClick(view: View) {

        val min = findViewById<View>(R.id.begin) as EditText
        val minRange = min.text.toString().toIntOrNull()

        val max = findViewById<View>(R.id.end) as EditText
        val maxRange = max.text.toString().toIntOrNull()

        if ( minRange != null && maxRange != null &&  minRange < maxRange && (maxRange - minRange) > 1) {
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("begin", minRange)
            intent.putExtra("end", maxRange)
            startActivity(intent)
        } else {
            val text = getString(R.string.incorrect_input_range)
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
        }
    }
}