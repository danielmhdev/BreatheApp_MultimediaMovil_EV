package com.example.breatheapp_multimedia_ev

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListVideoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Cargamos el diseño del video
        setContentView(R.layout.activity_list_video)

        // Botón de volver
        findViewById<android.widget.ImageButton>(R.id.btn_back).setOnClickListener {
            finish()
        }

        // 2. Buscamos el RecyclerView en el diseño XML
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_videos)

        // 3. Configuramos cómo se debe mostrar (lista vertical)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // 4. Creamos la lista de datos usando la clase Video
        val listaVideos = listOf(
            Video("Cascada en calma", R.drawable.cascada_video_cover, R.raw.video_cascada),
            Video("Cuenco tibetano", R.drawable.cuenco_video_cover, R.raw.video_cuenco),
            Video("Gong Mar", R.drawable.gong_video_cover, R.raw.video_gong_mar),
            Video("Lluvia nocturna", R.drawable.rain_video_cover, R.raw.video_rain),
            Video("Amanecer zen", R.drawable.sunrise_video_cover, R.raw.video_sunrise),
            Video("Bosque en calma", R.drawable.forest_video_cover, R.raw.video_forest)
        )

        // 5. Creamos el Adapter y configuramos qué pasa al hacer clic
        val adapter = VideoAdapter(listaVideos) { videoSeleccionado ->

            // Navegamos al reproductor de video
            val intent = Intent(this, ReproductorVideoActivity::class.java)

            // Pasamos los datos que el reproductor necesita para mostrar la portada y reproducir el vídeo
            intent.putExtra("video", videoSeleccionado.video)
            intent.putExtra("titulo", videoSeleccionado.titulo)

            startActivity(intent)
        }

        // 6. Asignamos el adaptador al RecyclerView
        recyclerView.adapter = adapter
    }
}