package com.example.memorina1

import android.annotation.SuppressLint
import android.app.ActionBar
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var  firstCard: ImageView? = null
    //private var  openCardsCount = 0 // число открытых карт
    private var guessedPairs = 0

    private var tags: Array<Int> = arrayOf()
    private val catViews: ArrayList<ImageView> = arrayListOf()

    private val catsImages = arrayOf(
        R.drawable.cat0, R.drawable.cat1, R.drawable.cat2, R.drawable.cat3, R.drawable.cat4,
        R.drawable.cat5, R.drawable.cat6, R.drawable.cat7
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for(i in 0..7){
            tags += i
            tags += i
        }
        tags.shuffle()

        for (i in 0..15) {
            val card = findViewById<ImageView>(resources.getIdentifier("cat$i", "id", packageName))
            val catTag = tags[i]
            card.tag = catTag
            card.setOnClickListener(cardListener)
            catViews.add(card)
        }

    }

    private val cardListener = View.OnClickListener() {
        lifecycleScope.launch (Dispatchers.Main)
        { setBackgroundWithDelay(it as ImageView) }
    }

    private suspend fun setBackgroundWithDelay(v: ImageView) {
        if (v == firstCard){
            return
        }
        val catTag = v.tag.toString().filter { it.isDigit() }.toInt()
        v.setImageResource(catsImages[catTag])
        Log.e("Tag", "$catTag")
        delay(500)

        if (firstCard == null) {
            firstCard = v
        }else{
            if (v.tag == firstCard!!.tag) {
                v.visibility = View.INVISIBLE; v.isClickable = false
                firstCard!!.visibility = View.INVISIBLE; firstCard!!.isClickable = false
                guessedPairs += 1
            } else {
                v.setImageResource(R.drawable.squarecat); v.isClickable = true
                firstCard!!.setImageResource(R.drawable.squarecat); firstCard!!.isClickable = true
                delay(500)
            }
            firstCard = null

            if (guessedPairs == 8) {
                Toast.makeText(this, "ПОБЕДА!!!", Toast.LENGTH_SHORT).show()
            }

        }
    }
}