package com.example.breatheapp_multimedia_ev

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Adaptador de la galería
class GalleryAdapter(
    private val listaGallery: List<Gallery>,
    private val onClick: (Gallery) -> Unit
) : RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {

    // El ViewHolder identifica los componentes visuales de cada fila
    class GalleryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imagen: ImageView = view.findViewById(R.id.img_item_galeria)
        val nombre: TextView = view.findViewById(R.id.txt_item_galeria_titulo)
    }

    // Inicializa la interfaz visual definida en item_gallery.xml
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_gallery, parent, false)
        return GalleryViewHolder(vista)
    }

    override fun getItemCount(): Int = listaGallery.size

    // Une los datos de una imagen concreta con los elementos visuales
    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val item = listaGallery[position]
        holder.nombre.text = item.titulo
        holder.imagen.setImageResource(item.imagen)

        // Configuramos el evento de clic para toda la fila
        holder.itemView.setOnClickListener {
            onClick(item)
        }
    }
}