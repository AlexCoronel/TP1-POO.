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

        assertEquals("Generando la lista con 1.000 posiciones\n", salidasDeConsola.toString());
        assertEquals(0, playlistDefault.consultarCantidadDeCancionesCargadas());
    }

    @Test
    public void verificaQueConParametrosErroneosSeCreaPlaylistMinima() {
        PlayList playlistErrones = new PlayList(-10);

        playlistErrones.agregarCancion(cancionTest);
        playlistErrones.agregarCancion(cancionTest);

        assertEquals("La lista se debe iniciar con al menos una posición\nNo se puede agregar la canción ya que la lista ha alcanzado su límite máximo de 1\n", salidasDeConsola.toString());
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
    public void verificaQueElOrdenamientoSeaCorrecto() {
        Cancion cancionLarga = new Cancion("Titulo largo", 300);
        Cancion cancionCorta = new Cancion("Titulo corto", 90);

        playlistDefault.agregarCancion(cancionTest);
        playlistDefault.agregarCancion(cancionLarga);
        playlistDefault.agregarCancion(cancionCorta);

        playlistDefault.mostrarPlaylistOrdenadaPorTiempo();
        assertEquals("La lista ordenada por duración es:\n\t1. Titulo corto: 90 segundos\n\t2. Titulo de cancion: 145 segundos\n\t3. Titulo largo: 300 segundos\n", salidasDeConsola.toString());
    }

}
