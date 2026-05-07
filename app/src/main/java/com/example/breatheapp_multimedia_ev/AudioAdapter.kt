package com.example.breatheapp_multimedia_ev

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Adaptador para la lista de Audios.
 * Gestiona cómo se muestran los datos de la clase Audio en el RecyclerView.
 */
class AudioAdapter(
    private val listaAudios: List<Audio>, private val onClick: (Audio) -> Unit
) : RecyclerView.Adapter<AudioAdapter.AudioViewHolder>() {

    // El ViewHolder identifica los componentes visuales de cada fila
    class AudioViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titulo: TextView = view.findViewById(R.id.txt_item_audio_titulo)
        val imagen: ImageView = view.findViewById(R.id.img_item_audio)
    }

    // Inicializa la interfaz visual definida en item_audio.xml
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AudioViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_audio, parent, false)
        return AudioViewHolder(vista)
    }

    override fun getItemCount(): Int = listaAudios.size

    // Une los datos de un Audio concreto con los elementos visuales
    override fun onBindViewHolder(holder: AudioViewHolder, position: Int) {
        val audio = listaAudios[position]
        holder.titulo.text = audio.titulo
        holder.imagen.setImageResource(audio.imagen)

        // Configuramos el evento de clic para toda la fila
        holder.itemView.setOnClickListener {
            onClick(audio)
        }
    }
}