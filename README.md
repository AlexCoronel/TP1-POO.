# Programación con Objetos I (2020 - Segundo cuatrimestre) / TP1

## Integrantes del equipo

- Castro, Jorge Omar
- Coronel, Alexis Martín
- Godina, Lucas Maximiliano
- Lazzarino Sucunza, Javier Ignacio
- Torres, Federico

## Decisiones de diseño tomadas

TODO: Una vez realizado el UML, agregar las decisiones de diseño tomadas y revisarlo antes de entregar.

## Descripción de archivos .java

La siguiente tabla incluye el nombre de cada archivo .java que contiene el proyecto, la ubicación relativa al directorio raíz y una breve descripción de su función.

| Nombre               | Path relativo      | Función |
| -------------------- | :----------------: | ------- |
| Cancion.java         | main/java/playlist | Este archivo es el encargado de manejar la clase Cancion con sus respectivos constructores, atributos y métodos. |
| PlayList.java        | main/java/playlist | Este archivo es el encargado de manejar la clase PlayList con sus respectivos constructores, atributos y métodos. |
| Genero.java          | main/java/playlist | Este archivo es el encargado de manejar el enumerable Genero con sus respectivos constructores, atributos y métodos. |
| TestCancion.java     | test/java/playlist | Este archivo es el encargado de realizar pruebas automatizadas a la clase Cancion. |
| TestPlayList.java    | test/java/playlist | Este archivo es el encargado de realizar pruebas automatizadas a la clase PlayList. |
---

## Modo de uso de cada Clase

### Cancion

_Constructores_:

| Parámetro | Tipo   | Condición | Descripción |
| --------- | :----: | :-------: | ----------- |
| Título    | String | Requerido | Cadena de caracteres con el título de la canción a crear |
| Tiempo    | int    | Requerido | Número entero con la duración de la canción (en segundos) |
| Artista   | String | Opcional  | Nombre del intérprete o grupo |
| Álbum     | String | Opcional  | Nombre del álbum en el que se encuentra la canción |
---
| Parámetro | Tipo   | Condición | Descripción |
| --------- | :----: | :-------: | ----------- |
| Título    | String | Requerido | Cadena de caracteres con el título de la canción a crear |
| Tiempo    | int    | Requerido | Número entero con la duración de la canción (en segundos) |
| Artista   | String | Opcional  | Nombre del intérprete o grupo |
---
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

| Nombre         | Visibilidad  | Parámetros | Tipo de dato devuelto |
| -------------- | :----------: | :--------: | --------------------- |
| getTitulo      | Publico      | Ninguno    | String                |
| getDuracion    | Publico      | Ninguno    | int                   |
| getAlbum       | Publico      | Ninguno    | String                |
| getArtista     | Publico      | Ninguno    | String                |
| setTitulo      | Privado      | String     | Ninguno               |
| setDuracion    | Privado      | int        | Ninguno               |
| setAlbum       | Privado      | String     | Ninguno               |
| setArtista     | Privado      | String     | Ninguno               |
---

## Conclusiones

TODO: Agregar conclusiones durante el proyecto, asegurarse de revisarlo antes de finalizar y entregarlo.

## Descargo sobre documento impreso u offline

La presente versión de este documento puede no estar actualizada con respecto a la más reciente. Para acceder a la misma, visite el siguiente repositorio de GitHub: <https://github.com/JavierLazzarino/poo1_tp1>
