package com.example.randomfilm

data class Movie (val name: String, val year: Int, val rating: Float,
                  val time: Int, val genre : List<String>, val country : List<String>) {
}