package playlist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayListTest {
    private Cancion cancionTest;

    @BeforeEach
    public void inicializarCancionGnericaParaTests() {
        cancionTest = new Cancion("Titulo de cancion", 145, "El artista", "El album");
    }

    @Test
    public void intentaCrearCancionConTiempoNegativo() {
        Cancion cancionFallida = new Cancion("Titulo de cancion", -145, "El artista", "El album");

        assertEquals(null, cancionFallida.getTitulo());
        assertEquals(0, cancionFallida.getDuracion());
        assertEquals(null, cancionFallida.getArtista());
        assertEquals(null, cancionFallida.getAlbum());
    }

    @Test
    public void creaCancionConTresParametros() {
        Cancion cancionTresParamtros = new Cancion("Titulo de cancion", 145, "El artista");

        assertEquals("Titulo de cancion", cancionTresParamtros.getTitulo());
        assertEquals(145, cancionTresParamtros.getDuracion());
        assertEquals("El artista", cancionTresParamtros.getArtista());
        assertEquals(null, cancionTresParamtros.getAlbum());
    }

    @Test
    public void creaCancionConDosParametros() {
        Cancion cancionDosParamtros = new Cancion("Titulo de cancion", 145);

        assertEquals("Titulo de cancion", cancionDosParamtros.getTitulo());
        assertEquals(145, cancionDosParamtros.getDuracion());
        assertEquals(null, cancionDosParamtros.getArtista());
        assertEquals(null, cancionDosParamtros.getAlbum());
    }

    @Test
    public void creaCancionConTodosLosParametros() {
        assertEquals("Titulo de cancion", cancionTest.getTitulo());
        assertEquals(145, cancionTest.getDuracion());
        assertEquals("El artista", cancionTest.getArtista());
        assertEquals("El album", cancionTest.getAlbum());
    }

}
