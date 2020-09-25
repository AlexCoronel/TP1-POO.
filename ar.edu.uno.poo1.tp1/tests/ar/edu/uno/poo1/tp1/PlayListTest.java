package ar.edu.uno.poo1.tp1;

import static org.junit.jupiter.api.Assertions.*;

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
    public void verificaQueSeCreaPlayListConTamaño() {
        PlayList playlist = new PlayList(10000);

        assertEquals(0, playlist.consultarCantidadDeCancionesCargadas());
    }

    @Test
    public void laCargaDefaultEsCorrectaYhayErrorConLimiteDePosiciones() {
        new PlayList();
        for (int i = 0; i < 1001; i++) playlistDefault.agregarCancion(cancionTest);

        assertEquals("Generando la lista con 1.000 posiciones\nNo se puede agregar la canción ya que la lista ha alcanzado su límite máximo de 1000\n", salidasDeConsola.toString());
    }

    @Test
    public void verificaQueSeCreaPlayListConTamañoYcancion() {
        PlayList playlist = new PlayList(10000, cancionTest);

        assertEquals(1, playlist.consultarCantidadDeCancionesCargadas());
    }

    @Test
    public void verificaQueSeCreaPlayListSoloConCancion() {
        PlayList playlist = new PlayList(cancionTest);

        assertEquals(1, playlist.consultarCantidadDeCancionesCargadas());
    }

    @Test
    public void verificaQueConParametrosErroneosSeCreaPlaylistMinima() {
        PlayList playlistErronea = new PlayList(-10);

        for (int i = 0; i < 11; i++) playlistErronea.agregarCancion(cancionTest);

        assertEquals("Generar una lista de menos de dos posiciones carece de sentido. Inicializándola arbitrariamente con 10 posiciones\nNo se puede agregar la canción ya que la lista ha alcanzado su límite máximo de 10\n", salidasDeConsola.toString());
    }

    // Tests de métodos
    @Test
    public void verificaQueSePuedaCargarUnaCancion() {
        playlistDefault.agregarCancion(cancionTest);

        assertEquals("Titulo de cancion", playlistDefault.consultarTituloDeCancion(1));
    }

    @Test
    public void verificaQueSePuedanAgregarCancionesHastaElMaximoAdmitido() {
        for (int i = 0; i < 1000; i++) playlistDefault.agregarCancion(cancionTest);

        assertEquals("Titulo de cancion", playlistDefault.consultarTituloDeCancion(1000));
    }

    @Test
    public void verificaQueSePuedaConsultarLaDuracionTotalPorTitulo() {
        for (int i = 0; i < 10; i++) playlistDefault.agregarCancion(cancionTest);
        playlistDefault.agregarCancion(new Cancion("Titulo corto", 90, "Segundo Artista"));

        assertEquals(1450, playlistDefault.consultarDuracion("Titulo de cancion"));
    }

    @Test
    public void verificaQueSePuedaEliminarUnaCancion() {
        for (int i = 0; i < 10; i++) playlistDefault.agregarCancion(cancionTest);
        playlistDefault.agregarCancion(new Cancion("Titulo corto", 90, "Segundo Artista"));

        assertEquals("Titulo corto", playlistDefault.consultarTituloDeCancion(11));
        assertEquals(true, playlistDefault.eliminarCancion("Titulo de cancion", "El artista"));
        assertEquals("Titulo corto", playlistDefault.consultarTituloDeCancion(10));
    }

    @Test
    public void verificaQueSePuedaConsultarLaDuracionTotal() {
        for (int i = 0; i < 10; i++) playlistDefault.agregarCancion(cancionTest);
        playlistDefault.agregarCancion(new Cancion("Titulo corto", 90, "Segundo Artista"));

        assertEquals(1540, playlistDefault.consultarDuracionTotal());
    }

    @Test
    public void verificaQueSePuedaConsultarLaCantidadDeCanciones() {
        for (int i = 0; i < 10; i++) playlistDefault.agregarCancion(cancionTest);

        assertEquals(10, playlistDefault.consultarCantidadDeCancionesCargadas());
    }

    @Test
    public void verificaQueElOrdenamientoYmuestraPorTituloSeaCorrecto() {
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
    public void verificaQueElOrdenamientoYmuestraPorArtistaYtituloSeaCorrecto() {
        Cancion cancionLarga = new Cancion("Titulo largo", 300, "Primer Artista");
        Cancion cancionCorta = new Cancion("Titulo corto", 90, "Segundo Artista");
        Cancion sinArtistaNiAlbum = new Cancion("Titulo de cancion", 320);
        Cancion sinArtistaConAlbum = new Cancion("Titulo de cancion", 110, null, "Titulo Del Album");
        Cancion sinArtistaConAlbum2 = new Cancion("Titulo de cancion 2", 120, null, "Titulo Del Album");

        playlistDefault.agregarCancion(cancionTest);
        playlistDefault.agregarCancion(cancionLarga);
        playlistDefault.agregarCancion(cancionCorta);
        playlistDefault.agregarCancion(sinArtistaNiAlbum);
        playlistDefault.agregarCancion(sinArtistaConAlbum);
        playlistDefault.agregarCancion(sinArtistaConAlbum2);
        playlistDefault.agregarCancion(cancionTest);

        playlistDefault.mostrarPlaylistOrdenadaPorArtistaYtitulo();
        assertEquals("La lista ordenada por artista y título es:\n\tEl artista:\n\t\tTitulo de cancion\n\t\tTitulo de cancion\n\tPrimer Artista:\n\t\tTitulo largo\n\tSegundo Artista:\n\t\tTitulo corto\n\tSin artista:\n\t\tTitulo de cancion\n\t\tTitulo de cancion\n\t\tTitulo de cancion 2\n", salidasDeConsola.toString());
    }

    @Test
    public void verificaQueLaReproduccionPorArtistaSeaCorrecta() {
        Cancion cancionLarga = new Cancion("Titulo largo", 300, "Primer Artista");
        Cancion cancionCorta = new Cancion("Titulo corto", 90, "Segundo Artista");
        Cancion sinArtistaNiAlbum = new Cancion("Titulo de cancion", 320);
        Cancion sinArtistaConAlbum = new Cancion("Titulo de cancion", 110, null, "Titulo Del Album");
        Cancion sinArtistaConAlbum2 = new Cancion("Titulo de cancion 2", 120, null, "Titulo Del Album");

        playlistDefault.agregarCancion(cancionTest);
        playlistDefault.agregarCancion(cancionLarga);
        playlistDefault.agregarCancion(cancionCorta);
        playlistDefault.agregarCancion(sinArtistaNiAlbum);
        playlistDefault.agregarCancion(sinArtistaConAlbum);
        playlistDefault.agregarCancion(sinArtistaConAlbum2);
        playlistDefault.agregarCancion(cancionTest);

        playlistDefault.reproducirPlaylist("ARTISTA");
        assertEquals("Reproduciendo Titulo de cancion y su duración es de 320 segundos\nReproduciendo Titulo de cancion del álbum Titulo Del Album y su duración es de 110 segundos\nReproduciendo Titulo de cancion 2 del álbum Titulo Del Album y su duración es de 120 segundos\nReproduciendo Titulo de cancion de El artista del álbum El album y su duración es de 145 segundos\nReproduciendo Titulo de cancion de El artista del álbum El album y su duración es de 145 segundos\nReproduciendo Titulo largo de Primer Artista y su duración es de 300 segundos\nReproduciendo Titulo corto de Segundo Artista y su duración es de 90 segundos\n", salidasDeConsola.toString());
    }

    @Test
    public void verificaQueLaReproduccionPorTituloSeaCorrecta() {
        Cancion cancionLarga = new Cancion("Titulo largo", 300, "Primer Artista");
        Cancion cancionCorta = new Cancion("Titulo corto", 90, "Segundo Artista");
        Cancion sinArtistaNiAlbum = new Cancion("Titulo de cancion", 320);
        Cancion sinArtistaConAlbum = new Cancion("Titulo de cancion", 110, null, "Titulo Del Album");
        Cancion sinArtistaConAlbum2 = new Cancion("Titulo de cancion 2", 120, null, "Titulo Del Album");

        playlistDefault.agregarCancion(cancionTest);
        playlistDefault.agregarCancion(cancionLarga);
        playlistDefault.agregarCancion(cancionCorta);
        playlistDefault.agregarCancion(sinArtistaNiAlbum);
        playlistDefault.agregarCancion(sinArtistaConAlbum);
        playlistDefault.agregarCancion(sinArtistaConAlbum2);
        playlistDefault.agregarCancion(cancionTest);

        playlistDefault.reproducirPlaylist("TITULO");
        assertEquals("Reproduciendo Titulo corto de Segundo Artista y su duración es de 90 segundos\nReproduciendo Titulo de cancion de El artista del álbum El album y su duración es de 145 segundos\nReproduciendo Titulo de cancion y su duración es de 320 segundos\nReproduciendo Titulo de cancion del álbum Titulo Del Album y su duración es de 110 segundos\nReproduciendo Titulo de cancion de El artista del álbum El album y su duración es de 145 segundos\nReproduciendo Titulo de cancion 2 del álbum Titulo Del Album y su duración es de 120 segundos\nReproduciendo Titulo largo de Primer Artista y su duración es de 300 segundos\n", salidasDeConsola.toString());
    }

    @Test
    public void verificaQueLaReproduccionAleatoriaNoArrojeErrores() {
        playlistDefault.agregarCancion(cancionTest);
        playlistDefault.agregarCancion(cancionTest);
        playlistDefault.agregarCancion(cancionTest);

        playlistDefault.reproducirPlaylist("ALEATORIO");
        assertEquals("Reproduciendo Titulo de cancion de El artista del álbum El album y su duración es de 145 segundos\nReproduciendo Titulo de cancion de El artista del álbum El album y su duración es de 145 segundos\nReproduciendo Titulo de cancion de El artista del álbum El album y su duración es de 145 segundos\n", salidasDeConsola.toString());
    }

    @Test
    public void verificaQueSeDevuelvaLaCancionMasCorta() {
        Cancion cancionLarga = new Cancion("Titulo largo", 300, "Primer Artista");
        Cancion cancionCorta = new Cancion("Titulo corto", 90, "Segundo Artista");
        Cancion sinArtistaNiAlbum = new Cancion("Titulo de cancion", 320);
        Cancion sinArtistaConAlbum = new Cancion("Titulo de cancion", 110, null, "Titulo Del Album");
        Cancion sinArtistaConAlbum2 = new Cancion("Titulo de cancion 2", 120, null, "Titulo Del Album");

        playlistDefault.agregarCancion(cancionTest);
        playlistDefault.agregarCancion(cancionLarga);
        playlistDefault.agregarCancion(cancionCorta);
        playlistDefault.agregarCancion(sinArtistaNiAlbum);
        playlistDefault.agregarCancion(sinArtistaConAlbum);
        playlistDefault.agregarCancion(sinArtistaConAlbum2);

        assertEquals(3, playlistDefault.consultarCancionMasCorta());
    }

    @Test
    public void verificaQueSeDevuelvaLaCancionMasLarga() {
        Cancion cancionLarga = new Cancion("Titulo largo", 300, "Primer Artista");
        Cancion cancionCorta = new Cancion("Titulo corto", 90, "Segundo Artista");
        Cancion sinArtistaNiAlbum = new Cancion("Cancion más larga", 320);
        Cancion sinArtistaConAlbum = new Cancion("Titulo de cancion", 110, null, "Titulo Del Album");
        Cancion sinArtistaConAlbum2 = new Cancion("Titulo de cancion 2", 120, null, "Titulo Del Album");

        playlistDefault.agregarCancion(cancionTest);
        playlistDefault.agregarCancion(cancionLarga);
        playlistDefault.agregarCancion(cancionCorta);
        playlistDefault.agregarCancion(sinArtistaNiAlbum);
        playlistDefault.agregarCancion(sinArtistaConAlbum);
        playlistDefault.agregarCancion(sinArtistaConAlbum2);

        assertEquals(4, playlistDefault.consultarCancionMasLarga());
    }

    /*
        Esperando respuesta al siguiente hilo:
        http://campusvirtual.uno.edu.ar/moodle/mod/forum/discuss.php?d=21709

    @Test
    public void verificaQueLaReproduccionPorOrdenDeCargaSeaCorrecta() {
        Cancion cancionLarga = new Cancion("Titulo largo", 300, "Primer Artista");
        Cancion cancionCorta = new Cancion("Titulo corto", 90, "Segundo Artista");
        Cancion sinArtistaNiAlbum = new Cancion("Titulo de cancion", 320);
        Cancion sinArtistaConAlbum = new Cancion("Titulo de cancion", 110, null, "Titulo Del Album");
        Cancion sinArtistaConAlbum2 = new Cancion("Titulo de cancion 2", 120, null, "Titulo Del Album");

        playlistDefault.agregarCancion(cancionTest);
        playlistDefault.agregarCancion(cancionLarga);
        playlistDefault.agregarCancion(cancionCorta);
        playlistDefault.agregarCancion(sinArtistaNiAlbum);
        playlistDefault.agregarCancion(sinArtistaConAlbum);
        playlistDefault.agregarCancion(sinArtistaConAlbum2);
        playlistDefault.agregarCancion(cancionTest);

        playlistDefault.reproducirPlaylist(null);
        assertEquals("###############################\nEl reproductor admite cuatro modos de reproducción:\n- 'ARTISTA': Las canciones son reproducidas con sus Artistas en orden alfabético\n- 'TITULO': Las canciones son reproducidas con sus Títulos en orden alfabético\n- 'ALEATORIO': Las canciones son reproducidas en orden aleatorio\n- (predeterminado): Las canciones son reproducidas en el mismo orden que fueron cargadas a la PlayList\nNótese que el modo es case sensitive\n###############################\nReproduciendo Titulo de cancion de El artista del álbum El album y su duración es de 145 segundos\nReproduciendo Titulo largo de Primer Artista y su duración es de 300 segundos\nReproduciendo Titulo corto de Segundo Artista y su duración es de 90 segundos\nReproduciendo Titulo de cancion y su duración es de 320 segundos\nReproduciendo Titulo de cancion del álbum Titulo Del Album y su duración es de 110 segundos\nReproduciendo Titulo de cancion 2 del álbum Titulo Del Album y su duración es de 120 segundos\nReproduciendo Titulo de cancion de El artista del álbum El album y su duración es de 145 segundos\n", salidasDeConsola.toString());
    }

    @Test
    public void veriicaQueLaAyudaDelReproductorSeMuestreSoloUnaVez() {
        playlistDefault.agregarCancion(cancionTest);

        playlistDefault.reproducirPlaylist(null);
        assertEquals("###############################\nEl reproductor admite cuatro modos de reproducción:\n- 'ARTISTA': Las canciones son reproducidas con sus Artistas en orden alfabético\n- 'TITULO': Las canciones son reproducidas con sus Títulos en orden alfabético\n- 'ALEATORIO': Las canciones son reproducidas en orden aleatorio\n- (predeterminado): Las canciones son reproducidas en el mismo orden que fueron cargadas a la PlayList\nNótese que el modo es case sensitive\n###############################\nReproduciendo Titulo de cancion de El artista del álbum El album y su duración es de 145 segundos\n", salidasDeConsola.toString());

        playlistDefault.reproducirPlaylist(null);
        assertEquals("Reproduciendo Titulo de cancion de El artista del álbum El album y su duración es de 145 segundos\n", salidasDeConsola.toString());
    }
    */


/*
    Agregar tests para canciones de menor / mayor duracion y para los distintos tipos de reproducciones
*/


    // Verificaciones de errores y métodos auxiliares o "extra" 
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
    public void verificaQueElOrdenamientoPorArtistaAlbumYtituloSeaCorrecto() {
        Cancion cancionLarga = new Cancion("Titulo largo", 300, "Primer Artista");
        Cancion cancionCorta = new Cancion("Titulo corto", 90, "Segundo Artista");
        Cancion sinArtistaNiAlbum = new Cancion("Titulo de cancion", 320);
        Cancion sinArtistaConAlbum = new Cancion("Titulo de cancion", 110, null, "Titulo Del Album");
        Cancion sinArtistaConAlbum2 = new Cancion("Titulo de cancion 2", 120, null, "Titulo Del Album");

        playlistDefault.agregarCancion(cancionTest);
        playlistDefault.agregarCancion(cancionLarga);
        playlistDefault.agregarCancion(cancionCorta);
        playlistDefault.agregarCancion(sinArtistaNiAlbum);
        playlistDefault.agregarCancion(sinArtistaConAlbum);
        playlistDefault.agregarCancion(sinArtistaConAlbum2);
        playlistDefault.agregarCancion(cancionTest);

        playlistDefault.mostrarPlaylistOrdenadaPorArtistaAlbumYtitulo();
        assertEquals("La lista ordenada por artista, álbum y título es:\n\tSin artista:\n\t\t Álbum desconocido\n\t\t\tTitulo de cancion - 320 segundos\n\t\t Titulo Del Album\n\t\t\tTitulo de cancion - 110 segundos\n\t\t\tTitulo de cancion 2 - 120 segundos\n\tEl artista:\n\t\t El album\n\t\t\tTitulo de cancion - 145 segundos\n\t\t\tTitulo de cancion - 145 segundos\n\tPrimer Artista:\n\t\t Álbum desconocido\n\t\t\tTitulo largo - 300 segundos\n\tSegundo Artista:\n\t\t Álbum desconocido\n\t\t\tTitulo corto - 90 segundos\n", salidasDeConsola.toString());
    }

}
