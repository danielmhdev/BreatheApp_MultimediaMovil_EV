package com.example.breatheapp_multimedia_ev

/**
 * Clase de datos que define la estructura de un Vídeo.
 * Incluye el título, la imagen de portada y el recurso de vídeo.
 */
data class Video(
    val titulo: String,
    val imagen: Int, // ID del recurso en drawable
    val video: Int   // ID del recurso en raw
)