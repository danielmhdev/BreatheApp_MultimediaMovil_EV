package com.example.breatheapp_multimedia_ev

/**
 * Clase de datos que define la estructura de un Audio.
 * Incluye el título que verá el usuario, la imagen de portada y el recurso de audio.
 */
data class Audio(
    val titulo: String,
    val imagen: Int, // ID del recurso en drawable
    val audio: Int   // ID del recurso en raw
)