package com.example.breatheapp_multimedia_ev

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MeditacionActivity : AppCompatActivity() {

    // Referencias a las vistas
    private lateinit var txtGuia: TextView
    private lateinit var txtFase: TextView
    private lateinit var txtCiclos: TextView
    private lateinit var btnEmpezar: Button
    private lateinit var circulo: View

    // Control del ejercicio
    private val handler = Handler(Looper.getMainLooper())
    private var estaActivo = false
    private var faseActual = 0 // 0:Inhala, 1:Mantén, 2:Exhala
    private var contadorCiclos = 0
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meditacion)

        // Vistas
        txtGuia = findViewById(R.id.txt_guia_respiracion)
        txtFase = findViewById(R.id.txt_fase_actual)
        txtCiclos = findViewById(R.id.txt_ciclos_completados)
        btnEmpezar = findViewById(R.id.btn_empezar_respiracion)
        circulo = findViewById(R.id.view_circulo_respiracion)

        // Botón volver
        findViewById<android.widget.ImageButton>(R.id.btn_back).setOnClickListener { finish() }

        // Audio de fondo
        mediaPlayer = MediaPlayer.create(this, R.raw.audio_rain)
        mediaPlayer?.isLooping = true

        // Control del botón principal
        btnEmpezar.setOnClickListener {
            if (!estaActivo) iniciar() else detener()
        }
    }

    // Inicia el ciclo del ejercicio
    private fun iniciar() {
        estaActivo = true
        faseActual = 0
        contadorCiclos = 0
        btnEmpezar.text = "Detener"
        txtGuia.visibility = View.VISIBLE
        mediaPlayer?.start()
        ejecutarRespiracion()
    }

    // Detiene el ejercicio y resetea
    private fun detener() {
        estaActivo = false
        btnEmpezar.text = "Empezar"
        txtGuia.visibility = View.INVISIBLE
        handler.removeCallbacksAndMessages(null)
        mediaPlayer?.pause()
        
        // Reset visual del círculo
        circulo.animate().scaleX(0.6f).scaleY(0.6f).setDuration(500).start()
    }

    // Lógica visual de cada fase
    private fun ejecutarRespiracion() {
        if (!estaActivo) return

        var tiempoFase = 4000L

        when (faseActual) {
            0 -> { // FASE: INHALAR
                txtFase.text = "INHALA"
                txtGuia.text = "Toma aire suavemente..."
                // El círculo se agranda
                circulo.animate().scaleX(1.2f).scaleY(1.2f).setDuration(4000).start()
                tiempoFase = 4000
            }
            1 -> { // FASE: MANTENER
                txtFase.text = "MANTÉN"
                txtGuia.text = "Aguanta el aire..."
                // El círculo se queda quieto
                tiempoFase = 4000 
            }
            2 -> { // FASE: EXHALAR
                txtFase.text = "EXHALA"
                txtGuia.text = "Suelta el aire despacio..."
                // El círculo se encoge
                circulo.animate().scaleX(0.6f).scaleY(0.6f).setDuration(6000).start()
                tiempoFase = 6000
            }
        }

        // Programar el cambio a la siguiente fase
        handler.postDelayed({
            avanzarFase()
        }, tiempoFase)
    }

    // Controla el orden de las fases y los ciclos
    private fun avanzarFase() {
        if (!estaActivo) return
        
        faseActual++
        
        // Si terminamos la fase 2, volvemos a la 0 y sumamos un ciclo
        if (faseActual > 2) {
            faseActual = 0
            contadorCiclos++
            txtCiclos.text = "Ciclos: $contadorCiclos"
        }
        
        // Repetimos el proceso
        ejecutarRespiracion()
    }

    // Ciclo de vida: Pausar audio al salir
    override fun onPause() {
        super.onPause()
        mediaPlayer?.pause()
    }

    // Ciclo de vida: Liberar recursos
    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
        mediaPlayer?.release()
    }
}
