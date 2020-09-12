package playlist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayListTest {
    private Cancion cancionTest;

    @BeforeEach
    public void inicializarCancionGenericaParaTests() {
        cancionTest = new Cancion("Titulo de cancion", 145, "El artista", "El album");
    }

    @Test
    public void verificaQueLosDefaultsSeanCorrectos() {
    }

}
