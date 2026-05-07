package com.example.breatheapp_multimedia_ev

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class DetalleImagenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_imagen)

        // 1. Identificamos los botones del diseño XML
        val imgDetalle = findViewById<ImageView>(R.id.img_detalle)
        val btnBack = findViewById<android.widget.ImageButton>(R.id.btn_back)

        // 2. Cargamos la imagen
        val imagenResId = intent.getIntExtra("imagen_res_id", -1)

        if (imagenResId != -1) {
            imgDetalle.setImageResource(imagenResId)
        }

        // 3. Cerrar la Activity y volver a la galería
        btnBack.setOnClickListener {
            finish()
        }
    }
}