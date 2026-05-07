package com.example.breatheapp_multimedia_ev

/**
 * Clase de datos que define la estructura de una imagen de la Galería.
 * Incluye el título, la imagen y una descripción.
 */
data class Gallery(
    val titulo: String,
    val imagen: Int,      // ID del recurso en drawable
    val descripcion: String
)