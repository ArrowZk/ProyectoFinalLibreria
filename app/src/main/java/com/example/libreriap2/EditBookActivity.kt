package com.example.libreriap2

import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class EditBookActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var bookId: String
    private lateinit var etBookCover: EditText
    private lateinit var etBookTitle: EditText
    private lateinit var etBookAuthor: EditText
    private lateinit var etBookYear: EditText
    private lateinit var etBookSinopsis: EditText
    private lateinit var etBookEditorial: EditText
    private lateinit var etBookStock: EditText
    private lateinit var etBookPages: EditText
    private lateinit var etBookLanguage: EditText
    private lateinit var etBookRecomendedAge: EditText
    private lateinit var etBookPrice: EditText
    private lateinit var btnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_book)

        // Obtener el ID del libro seleccionado del Intent
        bookId = intent.getStringExtra("book_id")!!

        // Inicializar la referencia a la base de datos
        database = FirebaseDatabase.getInstance().reference.child("books").child(bookId)

        // Configurar la barra de acción
        setSupportActionBar(findViewById(R.id.toolbar))
        // Mostrar el botón de "Atrás" en la barra de acción
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // Ocultar el título de la aplicación en la Toolbar
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // Inicializar los campos de entrada
        etBookTitle = findViewById(R.id.etBookTitle)
        etBookAuthor = findViewById(R.id.etBookAuthor)
        etBookYear = findViewById(R.id.etBookYear)
        etBookCover = findViewById(R.id.etBookCover)
        etBookSinopsis = findViewById(R.id.etBookSinopsis)
        etBookEditorial = findViewById(R.id.etBookEditorial)
        etBookStock = findViewById(R.id.etBookStock)
        etBookPages = findViewById(R.id.etBookPages)
        etBookLanguage = findViewById(R.id.etBookLanguage)
        etBookRecomendedAge = findViewById(R.id.etBookRecomendedAge)
        etBookPrice = findViewById(R.id.etBookPrice)
        btnSave = findViewById(R.id.btnSave)

        // Cargar los datos del libro desde Firebase
        loadBookDetails()

        // Configurar el botón de guardar
        btnSave.setOnClickListener {
            saveBookDetails()
        }
    }

    private fun loadBookDetails() {
        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val book = snapshot.getValue(Book::class.java)
                book?.let {
                    etBookTitle.setText(book.title)
                    etBookAuthor.setText(book.author)
                    etBookYear.setText(book.year)
                    etBookCover.setText(book.coverImage)
                    etBookSinopsis.setText(book.sinopsis)
                    etBookEditorial.setText(book.editorial)
                    etBookStock.setText(book.stock.toString())
                    etBookPages.setText(book.pages.toString())
                    etBookLanguage.setText(book.language)
                    etBookRecomendedAge.setText(book.recomendedAge.toString())
                    etBookPrice.setText(book.price.toString())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Manejar error
            }
        })
    }

    private fun saveBookDetails() {
        val updatedBook = Book(
            id = bookId,
            title = etBookTitle.text.toString(),
            author = etBookAuthor.text.toString(),
            coverImage = etBookCover.text.toString(),
            year = etBookYear.text.toString(),
            sinopsis = etBookSinopsis.text.toString(),
            editorial = etBookEditorial.text.toString(),
            stock = etBookStock.text.toString().toInt(),
            pages = etBookPages.text.toString().toInt(),
            language = etBookLanguage.text.toString(),
            recomendedAge = etBookRecomendedAge.text.toString().toInt(),
            price = etBookPrice.text.toString().toDouble()
        )

        database.setValue(updatedBook).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(applicationContext, "Libro actualizado correctamente", Toast.LENGTH_SHORT).show()
                finish() // Volver a la actividad anterior
            } else {
                Toast.makeText(applicationContext, "Error al actualizar", Toast.LENGTH_SHORT).show()
                // Manejar error
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> { // Si se hace clic en el botón de "Atrás"
                onBackPressed() // Volver a la actividad anterior
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
