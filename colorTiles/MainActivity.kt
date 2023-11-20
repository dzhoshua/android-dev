package com.example.new_16



import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import java.util.logging.Logger
import kotlin.random.Random


// тип для координат
data class Coord(val x: Int, val y: Int)
class MainActivity : AppCompatActivity() {

    private lateinit var tiles: Array<Array<View>>
    val LOG = Logger.getLogger(this.javaClass.name)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tiles = arrayOf(
            arrayOf(
                findViewById(R.id.t00),
                findViewById(R.id.t01),
                findViewById(R.id.t02),
                findViewById(R.id.t03)
            ),
            arrayOf(
                findViewById(R.id.t10),
                findViewById(R.id.t11),
                findViewById(R.id.t12),
                findViewById(R.id.t13)
            ),
            arrayOf(
                findViewById(R.id.t20),
                findViewById(R.id.t21),
                findViewById(R.id.t22),
                findViewById(R.id.t23)
            ),
            arrayOf(
                findViewById(R.id.t30),
                findViewById(R.id.t31),
                findViewById(R.id.t32),
                findViewById(R.id.t33)
            )
        )
        // LOG.info("SIZE!! ${tiles[0].size}")
        initField()
    }

    private fun initField() {
        //заполнение поля случайным цветом
        for (row in tiles) {
            for (tile in row) {
                if (Random.nextBoolean()) {
                    tile.setBackgroundColor(
                        ResourcesCompat.getColor(
                            resources,
                            R.color.bright,
                            null
                        )
                    )
                } else {
                    tile.setBackgroundColor(ResourcesCompat.getColor(resources, R.color.dark, null))
                }
            }
        }
    }

    private fun getCoordFromString(s: String): Coord {
        val coords = s.toInt()
        if (coords < 10) {
            if (coords > 0) {
                return Coord(0, coords)
            }
            return Coord(0, 0)
        }
        val y = coords % 10
        val x = coords / 10
        return Coord(x, y)
    }

    private fun changeColor(view: View) {
        val brightColor = ResourcesCompat.getColor(resources, R.color.bright, null)
        val darkColor = ResourcesCompat.getColor(resources, R.color.dark, null)
        val drawable = view.background as ColorDrawable
        if (drawable.color == brightColor) {
            view.setBackgroundColor(darkColor)
        } else {
            view.setBackgroundColor(brightColor)
        }
    }

    private fun checkVictory(): Boolean {
        val firstTileColor = (tiles[0][0].background as ColorDrawable).color
        for (row in tiles) {
            for (tile in row) {
                val nextTileColor = (tile.background as ColorDrawable).color
                if(nextTileColor != firstTileColor){
                    return false
                }
            }
        }
        return true
    }

    private fun youWin(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun onClick(v: View) {
        val coord = getCoordFromString(v.tag.toString())
        LOG.info("$coord")
        changeColor(v)

        for (i in 0..3) {
            changeColor(tiles[coord.x][i])
            changeColor(tiles[i][coord.y])
        }
        if (checkVictory()) {
            youWin("You win!!")
        }
    }


}


