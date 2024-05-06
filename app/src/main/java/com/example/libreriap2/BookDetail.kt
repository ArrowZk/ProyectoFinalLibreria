package com.example.libreriap2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class BookDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)

        // Obtener el ID del libro seleccionado del Intent
        val bookId = intent.getIntExtra("book_id", -1) // Suponiendo que el ID es de tipo Int y el valor predeterminado es -1

        // Buscar los datos del libro seleccionado utilizando el ID
        val book = BookProvider.getBookById(bookId)

        // Mostrar los datos del libro en la interfaz de usuario
        book?.let {
            Glide.with(this)
                .load(book.coverImage)
                .into(findViewById<ImageView>(R.id.ivBookCover)) // Utiliza una biblioteca de carga de imágenes para cargar la imagen desde la URL
            findViewById<TextView>(R.id.tvBookTitle).text = book.title
            findViewById<TextView>(R.id.tvBookAuthor).text = book.author
            findViewById<TextView>(R.id.tvBookYear).text = book.year
            findViewById<TextView>(R.id.tvBookSinopsis).text = book.sinopsis
            findViewById<TextView>(R.id.tvBookEditorial).text = book.editorial
            findViewById<TextView>(R.id.tvBookStock).text = book.stock.toString()
            findViewById<TextView>(R.id.tvBookPages).text = book.pages.toString()
            findViewById<TextView>(R.id.tvBookLanguage).text = book.language
            findViewById<TextView>(R.id.tvBookRecomendedAge).text = book.recomendedAge.toString()
            findViewById<TextView>(R.id.tvBookPrice).text = "$${book.price}"
        }

        // Configurar la barra de acción
        setSupportActionBar(findViewById(R.id.toolbar))

        // Mostrar el botón de "Atrás" en la barra de acción
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // Ocultar el título de la aplicación en la Toolbar
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    // Manejar clics en elementos de la barra de acción
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