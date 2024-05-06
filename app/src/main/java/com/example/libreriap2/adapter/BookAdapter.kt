package com.example.libreriap2.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.example.libreriap2.Book
import com.example.libreriap2.BookDetail
import com.example.libreriap2.R

class BookAdapter(private val books: List<Book>) : RecyclerView.Adapter<BookViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]
        holder.bind(book)

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, BookDetail::class.java)
            intent.putExtra("book_id", book.id) // Si la clase Book tiene un campo id
            it.context.startActivity(intent)
            // Verificar si el libro es para mayores de 18 años
            if (book.isForAdults()) {
                // Mostrar el Toast de advertencia
                Toast.makeText(holder.itemView.context, "Este libro es para mayores de 18 años", Toast.LENGTH_SHORT).show()
            } else {
                // Si no es para mayores de 18 años
            }
        }
    }

    override fun getItemCount(): Int {
        return books.size
    }

}