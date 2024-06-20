package com.example.libreriap2.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

object BookDatabaseHelper {
    private const val TAG = "BookDatabaseHelper"
    private lateinit var dr: DatabaseReference
    fun deleteBook(context: Context, bookId: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        Log.d(TAG, "Attempting to delete book with ID: $bookId")

        dr = FirebaseDatabase.getInstance().getReference("books")
        dr.child(bookId).removeValue()
            .addOnSuccessListener {
                Toast.makeText(context, "Libro eliminado correctamente", Toast.LENGTH_SHORT).show()
                Log.d(TAG, "Book deleted successfully.")
            }.addOnFailureListener{
                Toast.makeText(context, "Eliminacion fallida", Toast.LENGTH_SHORT).show()
                Log.e(TAG, "Failed to delete book: ")
            }
    }
}
