package com.example.breatheapp_multimedia_ev

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListAudioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Cargamos el diseño del audio
        setContentView(R.layout.activity_list_audio)

        // Botón de volver
        findViewById<android.widget.ImageButton>(R.id.btn_back).setOnClickListener {
            finish()
        }

        // 2. Buscamos el RecyclerView en el diseño XML
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_audios)

        // 3. Configuramos cómo se debe mostrar (lista vertical)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // 4. Creamos la lista de datos usando la clase Audio
        val listaAudios = listOf(
            Audio("Lluvia suave", R.drawable.rain_audio_cover, R.raw.audio_rain),
            Audio("Canto Pajaro", R.drawable.bird_audio_cover, R.raw.audio_bird),
            Audio("Relax Sleep", R.drawable.cat_audio_cover, R.raw.audio_relax_sleep),
            Audio("Olas del mar", R.drawable.waves_audio_cover, R.raw.audio_waves),
            Audio("Viento bosque", R.drawable.wind_audio_cover, R.raw.audio_wind),
            Audio("Fuego crackling", R.drawable.fire_audio_cover, R.raw.audio_fire)
        )


        // 5. Creamos el Adapter y configuramos qué pasa al hacer clic
        val adapter = AudioAdapter(listaAudios) { audioSeleccionado ->

            // Navegamos al reproductor de audio
            val intent = Intent(this, ReproductorAudioActivity::class.java)

            // Pasamos los datos que el reproductor necesita para mostrar la portada, el título y reproducir el audio
            intent.putExtra("audio_id", audioSeleccionado.audio)
            intent.putExtra("titulo", audioSeleccionado.titulo)
            intent.putExtra("imagen_id", audioSeleccionado.imagen)

            startActivity(intent)
        }

        // 6. Asignamos el adaptador al RecyclerView
        recyclerView.adapter = adapter
    }
}