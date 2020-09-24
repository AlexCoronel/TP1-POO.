# Programación con Objetos I (2020 - Segundo cuatrimestre) / TP1

## Integrantes del equipo

- Castro, Jorge Omar
- Coronel, Alexis Martín
- Godina, Lucas Maximiliano
- Lazzarino Sucunza, Javier Ignacio
- Torres, Federico

## Decisiones de diseño tomadas

1. Eliminacion: Se elimina por Titulo de cancion y artista. Si hay mas de un registro, eliminamos el primer resultado encontrado. Al eliminar, no rellenamos con null el ultimo espacio "liberado" sino que movemos las canciones a su posición final y asignamos al puntero de ultimaCancionCargada la posición anterior.
2. La duración de las Canciones deben estar expresadas en segundos (enteros).
3. Ejecutamos el algoritmo de ordenamiento únicamente cuando es necesario (para esto necesitamos un flag para saber si la lista está ordenada o no).
4. No se nombra al metodo getPorTipo en la clase Cancion como toString porque necesita recibir un String e iria contra la firma "tradicional" de toString().
5. La ayuda del reproductor se muestra sólo la primera vez que es llamado.
6. Inicialmente se descartó para su uso el algoritmo de Selección por la información encontrada en el siguiente link <https://www.bigocheatsheet.com/>. Luego de las implementaciones, se realizaron pruebas con distintos arreglos y el algoritmo de inserción resultó apenas un poco más rápido que burbujeo. Por tal motivo, se decidió utilizar el de inserción.
7. Por más que sea código muerto, no se eliminaron los algoritmos de ordenamiento en caso que en algún momento se desee hacer un intercambio.
8. Para poder realizar pruebas unitarias con mayor facilidad, se agregó el método publico **String** *consultarTituloDeCancion(int)* que no fue solicitado en la Tarea.

## Descripción de archivos .java

La siguiente tabla incluye el nombre de cada archivo .java que contiene el proyecto, la ubicación relativa al directorio raíz y una breve descripción de su función.

| Nombre               | Path relativo      | Función |
| -------------------- | :----------------: | ------- |
| Cancion.java         | main/java/playlist | Este archivo es el encargado de manejar la clase Cancion con sus respectivos constructores, atributos y métodos. |
| PlayList.java        | main/java/playlist | Este archivo es el encargado de manejar la clase PlayList con sus respectivos constructores, atributos y métodos. |
| TestCancion.java     | test/java/playlist | Este archivo es el encargado de realizar pruebas automatizadas a la clase Cancion. |
| TestPlayList.java    | test/java/playlist | Este archivo es el encargado de realizar pruebas automatizadas a la clase PlayList. |

## Modo de uso de cada Clase

### Cancion

_Constructores_:

| Parámetro | Tipo   | Condición | Descripción |
| --------- | :----: | :-------: | ----------- |
| Título    | String | Requerido | Cadena de caracteres con el título de la canción a crear |
| Tiempo    | int    | Requerido | Número entero con la duración de la canción (en segundos) |
| Artista   | String | Opcional  | Nombre del intérprete o grupo |
| Álbum     | String | Opcional  | Nombre del álbum en el que se encuentra la canción |

| Parámetro | Tipo   | Condición | Descripción |
| --------- | :----: | :-------: | ----------- |
| Título    | String | Requerido | Cadena de caracteres con el título de la canción a crear |
| Tiempo    | int    | Requerido | Número entero con la duración de la canción (en segundos) |
| Artista   | String | Opcional  | Nombre del intérprete o grupo |

| Parámetro | Tipo   | Condición | Descripción |
| --------- | :----: | :-------: | ----------- |
| Título    | String | Requerido | Cadena de caracteres con el título de la canción a crear |
| Tiempo    | int    | Requerido | Número entero con la duración de la canción (en segundos) |
---

_Atributos_:

| Nombre   | Tipo   | Visibilidad | Valor por defecto |
| -------- | :----: | :---------: | ----------------- |
| titulo   | String | Privado     | null              |
| album    | String | Privado     | null              |
| artista  | String | Privado     | null              |
| duracion | int    | Privado     | 0                 |
---

_Métodos_:

| Nombre         | Visibilidad  | Parámetros | Tipo de dato devuelto | Descrpción                                                     |
| -------------- | :----------: | :--------: | --------------------- | :------------------------------------------------------------- |
| getTitulo      | Publico      | Ninguno    | String                | Devuelve el título de la canción                               |
| getDuracion    | Publico      | Ninguno    | int                   | Devuelve la duración de la canción                             |
| getAlbum       | Publico      | Ninguno    | String                | Devuelve el álbum en el que se encuentra la canción            |
| getArtista     | Publico      | Ninguno    | String                | Devuelve el nombre del intérprete                              |
| getPorTipo     | Publico      | String     | String                | Devuelve el título, duración, álbum o nombre según corresponda |
| setTitulo      | Privado      | String     | Ninguno               | Establece el título de la canción                              |
| setDuracion    | Privado      | int        | Ninguno               | Establece la duración de la canción                            |
| setAlbum       | Privado      | String     | Ninguno               | Establece el álbum en el que se encuentra la canción           |
| setArtista     | Privado      | String     | Ninguno               | Establece el nombre del intérprete                             |

### PlayList

_Constructores_:

| Parámetro  | Tipo    | Condición | Descripción |
| ---------  | :-----: | :-------: | ----------- |
| Posiciones | int     | Opcional  | Número de posiciones que tendrá la playlist |
| Cancion    | Cancion | Opcional  | Un objeto Cancion que deberá ser agregado en la primera posición |

| Parámetro  | Tipo    | Condición | Descripción |
| ---------  | :-----: | :-------: | ----------- |
| Posiciones | int     | Opcional  | Número de posiciones que tendrá la playlist |

| Parámetro  | Tipo    | Condición | Descripción |
| ---------  | :-----: | :-------: | ----------- |
| Cancion    | Cancion | Opcional  | Un objeto Cancion que deberá ser agregado en la primera posición |

| Parámetro        | Descripción |
| ---------------- | ----------- |
| (Sin parámetros) | Genera una PlayList con 1.000 posiciones vacías |
---

_Atributos_:

| Nombre                       | Tipo      | Visibilidad | Valor por defecto          |
| ---------------------------- | :------:  | :---------: | -------------------------- |
|  lista                       | Cancion[] | Privado     | Array de nulls             |
|  ultimaCancionCargada        | int       | Privado     | 0                          |
|  ordenadasPorArtista         | int[]     | Privado     | Array de Integer.MAX_VALUE |
|  ordenadasPorTitulo          | int[]     | Privado     | Array de Integer.MAX_VALUE |
|  estaOrdenadaPorArtista      | boolean   | Privado     | true                       |
|  estaOrdenadaPorTitulo       | boolean   | Privado     | true                       |
|  seMostroAyudaDeReproductor  | boolean   | Privado     | true                       |
---

Nota: Los valores por defecto que se detallan son los asignados por los constructores.

_Métodos_:

| Nombre                                        | Visibilidad  | Parámetros         | Tipo de dato devuelto | Descripción |
| --------------------------------------------- | :----------: | :----------------: | --------------------- | :---------- |
| consultarCantidadDeCancionesCargadas          | Publico      | Ninguno            | int                   | Devuelve un entero con la cantidad de canciones cargadas en la playlist |
| agregarCancion                                | Publico      | Cancion            | boolean               | Agrega un objeto Cancion a la playlist |
| consultarDuracionTotal                        | Publico      | Ninguno            | int                   | Devuelve la duración total de la playlist |
| consultarDuracion                             | Publico      | String             | int                   | Devuelve la duracion de una canción en particular |
| eliminarCancion                               | Publico      | String, String     | boolean               | Remueve una canción de la playlist |
| consultarCancionMasCorta                      | Publico      | Ninguno            | int                   | Busca y devuelve la posición de la canción más corta |
| consultarCancionMasLarga                      | Publico      | Ninguno            | int                   | Busca y devuelve la posición de la canción más larga |
| reproducirPlaylist                            | Publico      | String             | boolean               | Simula la reproducción de la playlist (imprimiendo sus datos por consola) |
| consultarTituloDeCancion                      | Publico      | int                | int                   | Devuelve el título de una canción basándose en su posición en la lista |
| mostrarPlaylistOrdenadaPorArtistaYtitulo      | Publico      | Ninguno            | Ninguno               | Muestra la playlist ordenada por artista y título |
| mostrarPlaylistOrdenadaPorArtistaAlbumYtitulo | Publico      | Ninguno            | Ninguno               | Muestra la playlist ordenada por artista, álbum y título |
| mostrarPlaylistOrdenadaPorTitulo              | Publico      | Ninguno            | Ninguno               | Muestra la playlist ordenada por título |
| setEstaOrdenadaPorTipo                        | Privado      | String, boolean    | Ninguno               | Establece el valor de verdad de un array ordenable (titulo o artista) |
| getEstaOrdenadaPorTipo                        | Privado      | String             | boolean               | Devuelve el valor de verdad de un array ordenable (titulo o artista) |
| getUltimaCancionCargada                       | Privado      | Ninguno            | int                   | Devuelve la posición en la que se encuentra el puntero de carga |
| cancionAgregada                               | Privado      | Ninguno            | Ninguno               | Mueve el puntero de carga a la siguiente posición |
| cancionEliminada                              | Privado      | Ninguno            | Ninguno               | Mueve el puntero de carga a la posición anterior |
| quitarCancionPorPosicion                      | Privado      | int                | boolean               | Remueve la canción ubicada en la posición especiicada |
| generadorDeAleatoriosRecursivo                | Privado      | Random, int[], int | int[]                 | Genera un array con números únicos entre 0 y la cantidad de canciones cargadas |
| reproducir                                    | Privado      | int[]              | Ninguno               | Imprime los datos de cada Cancion de la lista según un array de posiciones que se le pase |
| mostrarErrorDeIndice                          | Privado      | int                | Ninguno               | Muestra un error relacionado a problemas de índice |
| laCancionEstaCargada                          | Privado      | int                | boolean               | Evalúa si hay una Cancion cargada en una posición dada |
| artistasUnicos                                | Privado      | Ninguno            | int[]                 | Devuelve un arreglo de posiciones en lista donde se encuentran artistas únicos (no repetidos) |
| ordenarConBurbujeo                            | Privado      | int[], String      | int[]                 | Ordena un arreglo de enteros basándose en atributos de Cancion por Burbujeo |
| ordenarConSeleccion                           | Privado      | int[], String      | int[]                 | Ordena un arreglo de enteros basándose en atributos de Cancion por Selección |
| ordenarConInsercion                           | Privado      | int[], String      | int[]                 | Ordena un arreglo de enteros basándose en atributos de Cancion por Inserción |
---

## Conclusiones

TODO: Agregar conclusiones durante el proyecto, asegurarse de revisarlo antes de finalizar y entregarlo.

## Descargo sobre documento impreso u offline

La presente versión de este documento puede no estar actualizada con respecto a la más reciente. Para acceder a la misma, visite el siguiente repositorio de GitHub: <https://github.com/JavierLazzarino/poo1_tp1>
