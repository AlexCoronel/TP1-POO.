package playlist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CancionTest {

    @Test
    public void intentaCrearCancionConTiempoNegativo() {
        Cancion cancionFallida = new Cancion("Titulo de cancion", -145, "El artista", "El album");

        assertEquals(null, cancionFallida.getTitulo());
        assertEquals(0, cancionFallida.getDuracion());
        assertEquals(null, cancionFallida.getArtista());
        assertEquals(null, cancionFallida.getAlbum());
    }

    @Test
    public void verificaConstructorConTituloTiempoYArtista() {
        Cancion cancionTresParamtros = new Cancion("Titulo de cancion", 145, "El artista");

        assertEquals("Titulo de cancion", cancionTresParamtros.getTitulo());
        assertEquals(145, cancionTresParamtros.getDuracion());
        assertEquals("El artista", cancionTresParamtros.getArtista());
        assertEquals(null, cancionTresParamtros.getAlbum());
    }

    @Test
    public void verificaConstructorConTituloYtiempo() {
        Cancion cancionDosParamtros = new Cancion("Titulo de cancion", 145);

        assertEquals("Titulo de cancion", cancionDosParamtros.getTitulo());
        assertEquals(145, cancionDosParamtros.getDuracion());
        assertEquals(null, cancionDosParamtros.getArtista());
        assertEquals(null, cancionDosParamtros.getAlbum());
    }

    @Test
    public void verificaConstructorConTodosLosParametros() {
        Cancion cancionTest = new Cancion("Titulo de cancion", 145, "El artista", "El album");

        assertEquals("Titulo de cancion", cancionTest.getTitulo());
        assertEquals(145, cancionTest.getDuracion());
        assertEquals("El artista", cancionTest.getArtista());
        assertEquals("El album", cancionTest.getAlbum());
    }

}
