package ar.edu.uno.poo1.tp1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

public class CancionTest {
    private final ByteArrayOutputStream salidasDeConsola = new ByteArrayOutputStream();

    @Test
    public void intentaCrearCancionConTiempoNegativo() {
        System.setOut(new PrintStream(salidasDeConsola));
        Cancion cancionErronea = new Cancion("Titulo de cancion", -145, "El artista", "El album");

        assertEquals(null, cancionErronea.getTitulo());
        assertEquals(null, cancionErronea.getDuracion());
        assertEquals(null, cancionErronea.getArtista());
        assertEquals(null, cancionErronea.getAlbum());
        assertEquals("La duración de la canción no puede ser negativa o cero\n", salidasDeConsola.toString());
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
    
    @Test
    public void verificaQueGetPorTipoFuncionaConTitulo() {
    	Cancion cancionTest = new Cancion("Titulo de cancion", 145, "El artista", "El album");

    	assertEquals("Titulo de cancion", cancionTest.getPorTipo("TITULO"));
    }

    @Test
    public void verificaQueGetPorTipoFuncionaConDuracion() {
    	Cancion cancionTest = new Cancion("Titulo de cancion", 145, "El artista", "El album");

    	assertEquals("145", cancionTest.getPorTipo("DURACION"));
    }

    @Test
    public void verificaQueGetPorTipoFuncionaConArtista() {
    	Cancion cancionTest = new Cancion("Titulo de cancion", 145, "El artista", "El album");

    	assertEquals("El artista", cancionTest.getPorTipo("ARTISTA"));
    }

    @Test
    public void verificaQueGetPorTipoFuncionaConAlbum() {
    	Cancion cancionTest = new Cancion("Titulo de cancion", 145, "El artista", "El album");

    	assertEquals("El album", cancionTest.getPorTipo("ALBUM"));
    }
}