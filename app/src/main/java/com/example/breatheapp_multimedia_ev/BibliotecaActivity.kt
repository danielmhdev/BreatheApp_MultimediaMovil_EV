package com.example.breatheapp_multimedia_ev

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class BibliotecaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_biblioteca)

        // 1. Identificamos los botones del diseño XML
        val btnAudios = findViewById<Button>(R.id.btn_Ir_Audios)
        val btnVideos = findViewById<Button>(R.id.btn_Ir_Videos)
        val btnGaleria = findViewById<Button>(R.id.btn_Ir_Galeria)

        // Botón de volver
        findViewById<android.widget.ImageButton>(R.id.btn_back).setOnClickListener {
            finish()
        }

        // 2. Acción al hacer clic al botón de Audios
        btnAudios.setOnClickListener {
            // Creamos el Intent para ir hacia la clase ListAudioActivity
            val intent = Intent(this, ListAudioActivity::class.java)
            startActivity(intent)
        }

        // 3. Acción al hacer clic al botón de Vídeos
        btnVideos.setOnClickListener {
            // Creamos el Intent para ir hacia la clase ListVideoActivity
            val intent = Intent(this, ListVideoActivity::class.java)
            startActivity(intent)
        }

        // 4. Acción al hacer clic al botón de Galería
        btnGaleria.setOnClickListener {
            // Creamos el Intent para ir hacia la clase ListGalleryActivity
            val intent = Intent(this, ListGalleryActivity::class.java)
            startActivity(intent)
        }
    }
}