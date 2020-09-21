package playlist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayListTest {
    private final ByteArrayOutputStream salidasDeConsola = new ByteArrayOutputStream();
    private Cancion cancionTest;
    private PlayList playlistDefault;

    @BeforeEach
    public void inicializarRecursosParaTests() {
        cancionTest = new Cancion("Titulo de cancion", 145, "El artista", "El album");
        playlistDefault = new PlayList();
        System.setOut(new PrintStream(salidasDeConsola));
    }

    // Tests de Constructores
    @Test
    public void verificaQueLaCargaDefaultSeaCorrecta() {
        new PlayList();

        for (int i = 0; i < 1001; i++) {
            playlistDefault.agregarCancion(cancionTest);
        }

        assertEquals("Generando la lista con 1.000 posiciones\nNo se puede agregar la canción ya que la lista ha alcanzado su límite máximo de 1000\n", salidasDeConsola.toString());
    }

    @Test
    public void verificaQueConParametrosErroneosSeCreaPlaylistMinima() {
        PlayList playlistErronea = new PlayList(-10);

        for (int i = 0; i < 11; i++) {
            playlistErronea.agregarCancion(cancionTest);
        }

        assertEquals("La lista se debe iniciar con al menos una posición\nNo se puede agregar la canción ya que la lista ha alcanzado su límite máximo de 10\n", salidasDeConsola.toString());
    }

    @Test
    public void verificaQueSePuedaCargarUnaCancion() {
        playlistDefault.agregarCancion(cancionTest);

        assertEquals("Titulo de cancion", playlistDefault.consultarTituloDeCancion(1));
    }

    @Test
    public void verificaQueDevuelvaNullYmuestreErrorCuandoAccedemosAunaPosicionVacia() {
        assertEquals(null, playlistDefault.consultarTituloDeCancion(10));
        assertEquals("No hay ninguna canción cargada en la posición 10\n", salidasDeConsola.toString());
    }

    @Test
    public void verificaQueDevuelvaNullYmuestreErrorCuandoAccedemosAunaPosicionMayorQueLaPlaylist() {
        assertEquals(null, playlistDefault.consultarTituloDeCancion(10001));
        assertEquals("La posición solicitada excede el tamaño de la playlist (1000)\n", salidasDeConsola.toString());
    }

    @Test
    public void verificaQueElOrdenamientoPorArtistaSeaCorrecto() {
        Cancion cancionLarga = new Cancion("Titulo largo", 300, "Primer Artista");
        Cancion cancionCorta = new Cancion("Titulo corto", 90, "Segundo Artista");

        playlistDefault.agregarCancion(cancionTest);
        playlistDefault.agregarCancion(cancionLarga);
        playlistDefault.agregarCancion(cancionCorta);

        playlistDefault.mostrarPlaylistOrdenadaPorArtista();
        assertEquals("La lista ordenada por artista es:\n\t1. El artista:\n\t\t↳ El album\n\t\t\tTitulo de cancion - 145 segundos\n\t2. Primer Artista:\n\t\t↳ Álbum desconocido\n\t\t\tTitulo largo - 300 segundos\n\t3. Segundo Artista:\n\t\t↳ Álbum desconocido\n\t\t\tTitulo corto - 90 segundos\n", salidasDeConsola.toString());
    }

    @Test
    public void verificaQueElOrdenamientoPorTituloSeaCorrecto() {
        Cancion cancionCorta = new Cancion("Título corto", 90);
        Cancion cancionIgual = new Cancion("Título igual", 30);
        Cancion cancionMasLarga = new Cancion("Título más largo", 300);
        Cancion cancionTodaviaMasLarga = new Cancion("Título todavía más largo", 200);

        playlistDefault.agregarCancion(cancionIgual);
        playlistDefault.agregarCancion(cancionCorta);
        playlistDefault.agregarCancion(cancionIgual);
        playlistDefault.agregarCancion(cancionTodaviaMasLarga);
        playlistDefault.agregarCancion(cancionMasLarga);

        playlistDefault.mostrarPlaylistOrdenadaPorTitulo();
        assertEquals("La lista ordenada por título es:\n\t1. Título corto.\n\t2. Título igual.\n\t3. Título igual.\n\t4. Título más largo.\n\t5. Título todavía más largo.\n", salidasDeConsola.toString());
    }

    @Test
    public void verificaVelocidadDeAlgoritmoDeOrdenamiento() {
        PlayList playlistVelocidad = new PlayList(10000);
        for (int i = 0; i < 10000; i++) {
            playlistVelocidad.agregarCancion(cancionTest);
        }

        playlistVelocidad.mostrarPlaylistOrdenadaPorArtista();
    }

}
