package com.example.breatheapp_multimedia_ev

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Adaptador para la lista de Vídeos.
 * Gestiona cómo se muestran los datos de la clase Video en el RecyclerView.
 */
class VideoAdapter(
    private val listaVideos: List<Video>,
    private val onClick: (Video) -> Unit
) : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    // El ViewHolder identifica los componentes visuales de cada fila
    class VideoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titulo: TextView = view.findViewById(R.id.txt_item_video_titulo)
        val imagen: ImageView = view.findViewById(R.id.img_item_video)
    }

    // Inicializa la interfaz visual definida en item_video.xml
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false)
        return VideoViewHolder(vista)
    }

    override fun getItemCount(): Int = listaVideos.size

    // Une los datos de un Vídeo concreto con los elementos visuales
    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val video = listaVideos[position]
        holder.titulo.text = video.titulo
        holder.imagen.setImageResource(video.imagen)

        // Configuramos el evento de clic para toda la fila
        holder.itemView.setOnClickListener {
            onClick(video)
        }
    }
}