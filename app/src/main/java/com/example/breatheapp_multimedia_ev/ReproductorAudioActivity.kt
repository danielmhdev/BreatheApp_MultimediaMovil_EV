package com.example.breatheapp_multimedia_ev

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ReproductorAudioActivity : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reproductor_audio)

        // 1. Enlazamos los componentes
        val imgPortada = findViewById<ImageView>(R.id.img_portada_reproductor_audio)
        val txtTitulo = findViewById<TextView>(R.id.txt_titulo_reproductor)
        val btnPlayPause = findViewById<Button>(R.id.btn_play_pause)
        val btnRetroceder = findViewById<Button>(R.id.btn_retroceder)
        val btnAdelantar = findViewById<Button>(R.id.btn_adelantar)
        val seekBarAudio = findViewById<SeekBar>(R.id.seek_bar_audio)
        val txtTiempoActual = findViewById<TextView>(R.id.txt_tiempo_actual_audio)
        val txtTiempoTotal = findViewById<TextView>(R.id.txt_tiempo_total_audio)

        // Botón de volver
        findViewById<android.widget.ImageButton>(R.id.btn_back).setOnClickListener {
            finish()
        }

        // 2. Recogemos los datos enviados desde la lista
        val audioId = intent.getIntExtra("audio_id", 0)
        val titulo = intent.getStringExtra("titulo") ?: "Audio"
        val imagenId = intent.getIntExtra("imagen_id", R.drawable.fondo_bosque_background)

        txtTitulo.text = titulo
        imgPortada.setImageResource(imagenId)

        // 3. Inicializamos el reproductor MediaPlayer y arrancamos la reproducción
        mediaPlayer = MediaPlayer.create(this, audioId)
        mediaPlayer?.isLooping = true
        mediaPlayer?.start()
        btnPlayPause.text = "Pausar"

        seekBarAudio.max = mediaPlayer?.duration ?: 0
        txtTiempoTotal.text = formatearTiempo(mediaPlayer?.duration ?: 0)
        actualizarProgreso(seekBarAudio, txtTiempoActual)
        actualizarProgreso(seekBarAudio, txtTiempoActual)

        // 4. Botón de play / pausa
        btnPlayPause.setOnClickListener {
            if (mediaPlayer?.isPlaying == true) {
                mediaPlayer?.pause()
                btnPlayPause.text = "Reproducir"
            } else {
                mediaPlayer?.start()
                btnPlayPause.text = "Pausar"
                actualizarProgreso(seekBarAudio, txtTiempoActual)
            }
        }

        // 5. Botones de saltar 10 segundos
        btnAdelantar.setOnClickListener {
            // Sumamos 10 segundos a la posición actual y saltamos
            mediaPlayer?.seekTo((mediaPlayer?.currentPosition ?: 0) + 10000)
        }

        btnRetroceder.setOnClickListener {
            // Restamos 10 segundos, pero nunca bajamos de 0
            val nuevaPosicion = (mediaPlayer?.currentPosition ?: 0) - 10000
            if (nuevaPosicion < 0) {
                mediaPlayer?.seekTo(0)
            } else {
                mediaPlayer?.seekTo(nuevaPosicion)
            }
        }

        // 6. Control manual de la barra de progreso
        seekBarAudio.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer?.seekTo(progress)
                    txtTiempoActual.text = formatearTiempo(progress)
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    // --- FUNCIONES AUXILIARES ---

    // Mueve la barra de progreso automáticamente cada segundo
    private fun actualizarProgreso(seekBar: SeekBar, txtTiempoActual: TextView) {
        handler.postDelayed({
            if (mediaPlayer?.isPlaying == true) {
                val posicion = mediaPlayer?.currentPosition ?: 0
                seekBar.progress = posicion
                txtTiempoActual.text = formatearTiempo(posicion)
                actualizarProgreso(seekBar, txtTiempoActual)
            }
        }, 1000)
    }

    // Convierte milisegundos a minutos, segundos.
    private fun formatearTiempo(milisegundos: Int): String {
        val segundos = (milisegundos / 1000) % 60
        val minutos = (milisegundos / 1000) / 60
        return String.format("%02d:%02d", minutos, segundos)
    }

    // --- CICLO DE VIDA ---

    override fun onPause() {
        super.onPause()
        // Si el usuario sale de la pantalla, pausamos el audio
        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.pause()
            findViewById<Button>(R.id.btn_play_pause).text = "Reproducir"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Liberar recursos
        handler.removeCallbacksAndMessages(null)
        mediaPlayer?.release()
        mediaPlayer = null
    }
}