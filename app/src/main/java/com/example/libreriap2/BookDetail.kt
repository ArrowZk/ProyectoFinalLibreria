package com.example.libreriap2

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.libreriap2.utils.BookDatabaseHelper
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class BookDetail : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private var bookId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)

        // Obtener el ID del libro seleccionado del Intent
        bookId = intent.getStringExtra("book_id") // Suponiendo que el ID es de tipo String

        // Inicializar la referencia a la base de datos
        database = FirebaseDatabase.getInstance().reference.child("books").child(bookId!!)

        // Configurar la barra de acción
        setSupportActionBar(findViewById(R.id.toolbar))
        // Mostrar el botón de "Atrás" en la barra de acción
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // Ocultar el título de la aplicación en la Toolbar
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // Cargar los datos del libro desde Firebase
        loadBookDetails()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_book_detail, menu)
        return true
    }

    // Manejar clics en elementos de la barra de acción
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> { // Si se hace clic en el botón de "Atrás"
                onBackPressed() // Volver a la actividad anterior
                true
            }
            R.id.action_new_activity -> {
                // Lógica para abrir una nueva Activity
                val intent = Intent(this, EditBookActivity::class.java)
                intent.putExtra("book_id", bookId)
                startActivity(intent)
                true
            }
            R.id.action_call_function -> {
                bookId?.let { id ->
                    BookDatabaseHelper.deleteBook(
                        this,
                        id,
                        onSuccess = {
                            Toast.makeText(this, "Eliminacion exitosa", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, MainActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                            finish()
                        },
                        onFailure = { e ->
                            Toast.makeText(this, "Fallo en la eliminacion", Toast.LENGTH_SHORT).show()
                            e.printStackTrace()
                        }
                    )
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun loadBookDetails() {
        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val book = snapshot.getValue(Book::class.java)
                book?.let {
                    Glide.with(this@BookDetail)
                        .load(book.coverImage)
                        .into(findViewById<ImageView>(R.id.ivBookCover))
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
            }

            override fun onCancelled(error: DatabaseError) {
                // Manejar error
                Toast.makeText(applicationContext, "Error en la base de datos", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
