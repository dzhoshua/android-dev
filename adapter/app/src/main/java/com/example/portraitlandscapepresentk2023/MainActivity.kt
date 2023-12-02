package com.example.portraitlandscapepresentk2023

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast

class MainActivity : AppCompatActivity(),AdapterView.OnItemSelectedListener {

    private lateinit var adapter: ArrayAdapter<CharSequence>
    private lateinit var picturesArray: IntArray
    private var currPos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        picturesArray = intArrayOf(R.drawable.car1, R.drawable.car2, R.drawable.car3)

        adapter = ArrayAdapter.createFromResource(this, R.array.pictures, R.layout.item)
        val spinner = findViewById<Spinner>(R.id.pictures_list)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = this
    }

    fun onChangePictureClick(v: View) {
        val iv = findViewById<ImageView>(R.id.picture)

        currPos = (currPos + 1) % picturesArray.size
        iv.setImageResource(picturesArray[currPos])

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Toast.makeText(this, "выбран элемент $position", Toast.LENGTH_SHORT ).show()
        val iv = findViewById<ImageView>(R.id.picture)
        iv.setImageResource(picturesArray[position])
        currPos = position
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        Toast.makeText(this, "не выбран элемент", Toast.LENGTH_SHORT ).show()
    }
}