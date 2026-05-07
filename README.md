# BreatheApp

BreatheApp es una aplicación multimedia nativa para Android desarrollada en Kotlin.
El proyecto ofrece un espacio de relajación integrando audios, videos, imágenes y ejercicios de respiración.

Proyecto de evaluación para el módulo de Programación Multimedia y Dispositivos Móviles (DAM).

## Características

* Biblioteca de Audios: Reproducción de sonidos con MediaPlayer, controles de salto temporal (+/- 10s), barra de progreso (SeekBar) y bucle automático.
* Visor de Videos: Reproductor de video personalizado con controles de reproducción integrados.
* Galería: Visualización de paisajes mediante RecyclerView y visualización individual a pantalla completa.
* Meditación: Ejercicio de respiración guiado con animaciones dinámicas gestionadas mediante Handler.
* Navegación: Botones de retorno integrados en todas las pantallas para una experiencia de usuario fluida.

## Tecnologías y Arquitectura

* Lenguaje: Kotlin.
* Interfaz: XML, RecyclerView, CardView, FrameLayout para fondos con transparencia.
* Multimedia: MediaPlayer, VideoView.
* Navegación: 9 pantallas (Activities) conectadas mediante Intents explícitos con paso de parámetros.
* Optimización: Gestión activa del ciclo de vida (onPause, onDestroy) para liberar recursos y evitar fugas de memoria.

## Estructura de Pantallas

1. MainActivity: Inicio de la aplicación.
2. BibliotecaActivity: Menú de selección multimedia.
3. ListAudioActivity / ReproductorAudioActivity: Selección y reproducción de audios.
4. ListVideoActivity / ReproductorVideoActivity: Selección y reproducción de videos.
5. ListGalleryActivity / DetalleImagenActivity: Galería y visor de imágenes.
6. MeditacionActivity: Ejercicio interactivo de respiración.

---
Desarrollado por: Daniel Martín Hernández