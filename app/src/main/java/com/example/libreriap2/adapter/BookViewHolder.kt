package com.example.libreriap2.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.libreriap2.Book
import com.example.libreriap2.R

class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val bookImageView: ImageView = itemView.findViewById(R.id.ivBook)
    private val bookNameTextView: TextView = itemView.findViewById(R.id.tvBookName)
    private val bookAuthorTextView: TextView = itemView.findViewById(R.id.tvBookAuthor)

    fun bind(book: Book) {
        // Set book cover image
        Glide.with(itemView.context)
            .load(book.coverImage)
            .into(bookImageView)
        // Set book name
        bookNameTextView.text = book.title

        // Set book author
        bookAuthorTextView.text = book.author
    }
}