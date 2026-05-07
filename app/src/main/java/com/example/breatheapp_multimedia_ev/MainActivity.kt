package com.example.breatheapp_multimedia_ev

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Botones principales
        val btnBiblioteca = findViewById<Button>(R.id.btn_biblioteca)
        val btnMeditacion = findViewById<Button>(R.id.btn_meditacion)

        // Ir a la biblioteca
        btnBiblioteca.setOnClickListener {
            val intent = Intent(this, BibliotecaActivity::class.java)
            startActivity(intent)
        }

        // Ir a meditación
        btnMeditacion.setOnClickListener {
            val intent = Intent(this, MeditacionActivity::class.java)
            startActivity(intent)
        }
    }
}