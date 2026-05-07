package com.example.breatheapp_multimedia_ev

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListGalleryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Cargamos el diseño de la galería
        setContentView(R.layout.activity_list_gallery)

        // Botón de volver
        findViewById<android.widget.ImageButton>(R.id.btn_back).setOnClickListener {
            finish()
        }

        // 2. Buscamos el RecyclerView en el diseño XML
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_galeria)

        // 3. Configuramos cómo se debe mostrar (lista vertical)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // 4. Creamos la lista de datos con la clase Gallery
        val listaGaleria = listOf(
            Gallery("Mar en calma", R.drawable.sea_gallery, "Vista relajante del océano"),
            Gallery("Rocas", R.drawable.rocks_gallery, "Formaciones rocosas naturales"),
            Gallery("Nubes", R.drawable.mar_nubes_gallery, "El mar bajo un cielo nublado"),
            Gallery("Montañas", R.drawable.moutains_gallery, "Cumbres para meditar"),
            Gallery("Lago Dolomitas", R.drawable.lake_gallery, "Lago de montaña"),
            Gallery("Flor de loto", R.drawable.lotus_gallery, "Símbolo de paz interior"),
            Gallery("Atardecer", R.drawable.sunset_gallery, "El sol se pone en calma")
        )

        // 5. Creamos el Adapter y configuramos qué pasa al hacer clic
        val adapter = GalleryAdapter(listaGaleria) { imagenSeleccionada ->
            val intent = Intent(this, DetalleImagenActivity::class.java)
            intent.putExtra("imagen_res_id", imagenSeleccionada.imagen)
            startActivity(intent)
        }

        // 6. Asignamos el adaptador al RecyclerView
        recyclerView.adapter = adapter
    }
}