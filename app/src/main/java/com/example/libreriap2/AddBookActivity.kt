package com.example.libreriap2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase

class AddBookActivity : AppCompatActivity() {

    private lateinit var etTitle: EditText
    private lateinit var etAuthor: EditText
    private lateinit var etCoverImage: EditText
    private lateinit var etYear: EditText
    private lateinit var etSinopsis: EditText
    private lateinit var etEditorial: EditText
    private lateinit var etStock: EditText
    private lateinit var etPages: EditText
    private lateinit var etLanguage: EditText
    private lateinit var etRecommendedAge: EditText
    private lateinit var etPrice: EditText
    private lateinit var btnAddBook: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book)

        etTitle = findViewById(R.id.etTitle)
        etAuthor = findViewById(R.id.etAuthor)
        etCoverImage = findViewById(R.id.etCoverImage)
        etYear = findViewById(R.id.etYear)
        etSinopsis = findViewById(R.id.etSinopsis)
        etEditorial = findViewById(R.id.etEditorial)
        etStock = findViewById(R.id.etStock)
        etPages = findViewById(R.id.etPages)
        etLanguage = findViewById(R.id.etLanguage)
        etRecommendedAge = findViewById(R.id.etRecommendedAge)
        etPrice = findViewById(R.id.etPrice)
        btnAddBook = findViewById(R.id.btnAddBook)

        btnAddBook.setOnClickListener {
            addBook()
        }
    }

    private fun addBook() {
        val title = etTitle.text.toString()
        val author = etAuthor.text.toString()
        val coverImage = etCoverImage.text.toString()
        val year = etYear.text.toString()
        val sinopsis = etSinopsis.text.toString()
        val editorial = etEditorial.text.toString()
        val stock = etStock.text.toString().toIntOrNull() ?: 0
        val pages = etPages.text.toString().toIntOrNull() ?: 0
        val language = etLanguage.text.toString()
        val recommendedAge = etRecommendedAge.text.toString().toIntOrNull() ?: 0
        val price = etPrice.text.toString().toDoubleOrNull() ?: 0.0

        if (title.isEmpty() || author.isEmpty()) {
            Toast.makeText(this, "Título y Autor son obligatorios", Toast.LENGTH_SHORT).show()
            return
        }

        val database = FirebaseDatabase.getInstance().reference.child("books")
        val bookId = database.push().key
        val book = Book(bookId ?: "", title, author, coverImage, year, sinopsis, editorial, stock, pages, language, recommendedAge, price)

        if (bookId != null) {
            database.child(bookId).setValue(book).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Libro agregado con éxito", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Error al agregar el libro", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
