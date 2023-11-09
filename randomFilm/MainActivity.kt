package com.example.randomfilm


import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Random

class MainActivity : AppCompatActivity() {

    private lateinit var movies: MutableList<String>
    private lateinit var tvTitle: TextView
    private val r = Random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvTitle = findViewById(R.id.title)
        movies = resources.getStringArray(R.array.movies).toMutableList()
    }

    fun onNextClick(view: View) {
        if (movies.isEmpty()) {
            tvTitle.text = getString(R.string.end)
        } else {
            val i = r.nextInt(movies.size)
            val movie = movies[i]
            movies.removeAt(i)
            tvTitle.text = movie
        }
    }

    fun onClearClick(view: View) {
        movies = resources.getStringArray(R.array.movies).toMutableList()
        tvTitle.text = ""
    }

}