package com.example.breatheapp_multimedia_ev

import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class ReproductorVideoActivity : AppCompatActivity() {

    private lateinit var videoView: VideoView
    private lateinit var seekBarVideo: SeekBar
    private lateinit var btnPlayPauseVideo: Button
    private lateinit var txtTiempoActualVideo: TextView
    private lateinit var txtTiempoTotalVideo: TextView
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reproductor_video)

        // Botón de volver
        findViewById<android.widget.ImageButton>(R.id.btn_back).setOnClickListener {
            finish()
        }

        // 1. Enlazamos los componentes
        val txtTituloVideo = findViewById<TextView>(R.id.txt_titulo_video)
        videoView = findViewById(R.id.video_view)
        seekBarVideo = findViewById(R.id.seek_bar_video)
        btnPlayPauseVideo = findViewById(R.id.btn_play_pause_video)
        txtTiempoActualVideo = findViewById(R.id.txt_tiempo_actual_video)
        txtTiempoTotalVideo = findViewById(R.id.txt_tiempo_total_video)
        val btnRetrocederVideo = findViewById<Button>(R.id.btn_retroceder_video)
        val btnAdelantarVideo = findViewById<Button>(R.id.btn_adelantar_video)

        // 2. Recogemos el ID del vídeo enviado desde la lista
        val videoId = intent.getIntExtra("video", 0)
        val titulo = intent.getStringExtra("titulo") ?: "Vídeo"
        txtTituloVideo.text = titulo

        val uri = Uri.parse("android.resource://$packageName/$videoId")
        videoView.setVideoURI(uri)

        // 3. Cuando el vídeo esté listo, configuramos la barra e iniciamos la reproducción
        videoView.setOnPreparedListener { mediaPlayer ->
            mediaPlayer.isLooping = true
            seekBarVideo.max = videoView.duration
            txtTiempoTotalVideo.text = formatearTiempo(videoView.duration)
            btnPlayPauseVideo.text = "Pausar"
            videoView.start()
            actualizarProgreso()
        }

        // 4. Botón de play / pausa
        btnPlayPauseVideo.setOnClickListener {
            if (videoView.isPlaying) {
                videoView.pause()
                btnPlayPauseVideo.text = "Reproducir"
            } else {
                videoView.start()
                btnPlayPauseVideo.text = "Pausar"
                actualizarProgreso()
            }
        }

        // 5. Botones de saltar 10 segundos
        btnAdelantarVideo.setOnClickListener {
            // Sumamos 10 segundos a la posición actual y saltamos
            videoView.seekTo(videoView.currentPosition + 10000)
        }

        btnRetrocederVideo.setOnClickListener {
            // Restamos 10 segundos, pero nunca bajamos de 0
            val nuevaPosicion = videoView.currentPosition - 10000
            if (nuevaPosicion < 0) {
                videoView.seekTo(0)
            } else {
                videoView.seekTo(nuevaPosicion)
            }
        }

        // 6. Control manual de la barra de progreso
        seekBarVideo.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(sb: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    videoView.seekTo(progress)
                    txtTiempoActualVideo.text = formatearTiempo(progress)
                }
            }

            override fun onStartTrackingTouch(sb: SeekBar?) {}
            override fun onStopTrackingTouch(sb: SeekBar?) {}
        })
    }

    // --- FUNCIONES AUXILIARES ---

    // Mueve la barra de progreso automáticamente cada segundo
    private fun actualizarProgreso() {
        handler.postDelayed({
            if (videoView.isPlaying) {
                val posicionActual = videoView.currentPosition
                seekBarVideo.progress = posicionActual
                txtTiempoActualVideo.text = formatearTiempo(posicionActual)
                actualizarProgreso()
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
        // Si el usuario sale de la pantalla, pausamos el vídeo
        if (videoView.isPlaying) {
            videoView.pause()
            btnPlayPauseVideo.text = "Reproducir"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Liberar recursos
        handler.removeCallbacksAndMessages(null)
    }
}