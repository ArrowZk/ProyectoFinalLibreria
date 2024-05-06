package com.example.libreriap2

data class Book(
    val id: Int,
    val title: String,
    val author: String,
    val coverImage: String,
    val year: String,
    val sinopsis: String,
    val editorial: String,
    val stock: Int,
    val pages: Int,
    val language: String,
    val recomendedAge: Int,
    val price: Double
){
    fun isForAdults(): Boolean {
        return recomendedAge >= 18
    }
}
