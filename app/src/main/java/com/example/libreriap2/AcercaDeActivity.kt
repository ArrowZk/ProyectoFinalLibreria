package com.example.libreriap2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class AcercaDeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acerca_de)

        // Load the SupportMapFragment
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.map_container, SupportMapFragment())
                .commit()
        }
    }
}
