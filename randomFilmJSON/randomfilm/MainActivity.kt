package com.example.randomfilm


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import java.io.InputStreamReader
import java.util.Random

class MainActivity : AppCompatActivity() {

    private lateinit var movies: MutableList<Movie>
    private lateinit var mTitle: TextView
    private lateinit var mYear: TextView
    private lateinit var mRating: TextView
    private lateinit var mTime: TextView
    private lateinit var mGenre: TextView
    private lateinit var mCountry: TextView

    private val r = Random()
    private val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mTitle = findViewById(R.id.title)
        mYear = findViewById(R.id.year)
        mRating = findViewById(R.id.rating)
        mTime = findViewById(R.id.time)
        mGenre = findViewById(R.id.genre)
        mCountry = findViewById(R.id.country)

        val stream = resources.openRawResource(R.raw.movies)
        movies = gson.fromJson(InputStreamReader(stream), Movies::class.java).movies.toMutableList()
        Log.d("MY_TAG", "Loaded movies ${movies.size}")


    }

    fun onNextClick(view: View) {
        if (movies.isEmpty()) {
            mTitle.text = getString(R.string.end)
            mYear.text = ""
            mRating.text = ""
            mTime.text = ""
            mGenre.text = ""
            mCountry.text = ""
        } else {
            val i = r.nextInt(movies.size)
            val movie = movies[i]
            movies.removeAt(i)
            mTitle.text = movie.name
            mYear.text = movie.year.toString()
            mRating.text = movie.rating.toString()
            mTime.text = movie.time.toString() + " minutes"
            mGenre.text = movie.genre.joinToString()
            mCountry.text = movie.country.joinToString()

        }
    }

    fun onClearClick(view: View) {
        val stream = resources.openRawResource(R.raw.movies)
        movies = gson.fromJson(InputStreamReader(stream), Movies::class.java).movies.toMutableList()
        mTitle.text = ""
        mYear.text = ""
        mRating.text = ""
        mTime.text = ""
        mGenre.text = ""
        mCountry.text = ""
    }

}