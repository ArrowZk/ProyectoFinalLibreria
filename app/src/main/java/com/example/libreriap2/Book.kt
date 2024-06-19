package com.example.libreriap2

data class Book(
    val id: String = "",
    val title: String = "",
    val author: String = "",
    val coverImage: String = "",
    val year: String = "",
    val sinopsis: String = "",
    val editorial: String = "",
    val stock: Int = 0,
    val pages: Int = 0,
    val language: String = "",
    val recomendedAge: Int = 0,
    val price: Double = 0.0
) {
    fun isForAdults(): Boolean {
        return recomendedAge!! >= 18
    }
}
