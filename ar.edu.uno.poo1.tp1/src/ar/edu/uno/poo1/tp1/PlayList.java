package ar.edu.uno.poo1.tp1;

import java.util.Arrays;
import java.util.Random;

public class PlayList {
    private Cancion[] lista;
    private int ultimaCancionCargada;
    private int[] ordenadasPorArtista;
    private int[] ordenadasPorTitulo;
    private boolean estaOrdenadaPorArtista;
    private boolean estaOrdenadaPorTitulo;
    private boolean seMostroAyudaDeReproductor;

    PlayList(int cantidadDeCanciones) {
        if (cantidadDeCanciones <= 1) {
            // Aún no se vió Excepciones. Mostramos un mensaje y generamos la PlayList mínima
            System.out.println("Generar una lista de menos de dos posiciones carece de sentido. Inicializándola arbitrariamente con 10 posiciones");
            cantidadDeCanciones = 10;
        } else if (cantidadDeCanciones > 10000) {
            System.out.println("La cantidad máxima de canciones es 10.000. Generando la lista con esa cantidad");
            cantidadDeCanciones = 10000;
        }
        this.lista = new Cancion[cantidadDeCanciones];

        this.ordenadasPorArtista = new int[cantidadDeCanciones];
        Arrays.fill(ordenadasPorArtista, Integer.MAX_VALUE);
        this.setEstaOrdenadaPorTipo("ARTISTA", true);

        this.ordenadasPorTitulo = new int[cantidadDeCanciones];
        Arrays.fill(ordenadasPorTitulo, Integer.MAX_VALUE);
        this.setEstaOrdenadaPorTipo("TITULO", true);
    }

    PlayList() {
        // Generamos la lista con un tamaño "razonable" por default
        this(1000);
        System.out.println("Generando la lista con 1.000 posiciones");
    }

    PlayList(int cantidadDeCanciones, Cancion cancion) {
        this(cantidadDeCanciones);
        this.agregarCancion(cancion);
    }

    PlayList(Cancion cancion) {
        this();
        this.agregarCancion(cancion);
    }

    private void setEstaOrdenadaPorTipo(String tipo, boolean valor) {
        switch (tipo) {
            case "ARTISTA":
                this.estaOrdenadaPorArtista = valor;

            case "TITULO":
                this.estaOrdenadaPorTitulo = valor;

            default:
        }
    }

    private boolean getEstaOrdenadaPorTipo(String tipo) {
        switch (tipo) {
            case "ARTISTA":
                return this.estaOrdenadaPorArtista;

            case "TITULO":
                return this.estaOrdenadaPorTitulo;

            default:
                return false;
        }
    }

    private int getUltimaCancionCargada() {
        return this.ultimaCancionCargada;
    }

    public int consultarCantidadDeCancionesCargadas() {
        return this.getUltimaCancionCargada();
    }

    private void cancionAgregada() {
        this.ultimaCancionCargada ++;
    }

    private void cancionEliminada() {
        this.ultimaCancionCargada --;
    }

    public boolean agregarCancion(Cancion cancion) {
        if (cancion == null) return false;
        if (this.lista.length == this.getUltimaCancionCargada()) {
            System.out.println("No se puede agregar la canción ya que la lista ha alcanzado su límite máximo de " + this.lista.length);
            return false;
        } else {
            if (cancion.getPorTipo("ARTISTA") == null || 
                (this.ultimaCancionCargada > 0 &&
                this.estaOrdenadaPorArtista &&
                cancion.getPorTipo("ARTISTA").compareToIgnoreCase(this.lista[this.ultimaCancionCargada -1].getPorTipo("ARTISTA")) < 0)
                ) this.setEstaOrdenadaPorTipo("ARTISTA", false);
            this.ordenadasPorArtista[this.getUltimaCancionCargada()] = this.ultimaCancionCargada;

            if (this.ultimaCancionCargada > 0 &&
                this.estaOrdenadaPorTitulo &&
                cancion.getPorTipo("TITULO").compareToIgnoreCase(this.lista[this.ultimaCancionCargada -1].getPorTipo("TITULO")) < 0
                ) this.setEstaOrdenadaPorTipo("TITULO", false);
            this.ordenadasPorTitulo[this.getUltimaCancionCargada()] = this.ultimaCancionCargada;

            this.lista[this.getUltimaCancionCargada()] = cancion;
            this.cancionAgregada();
            return true;
        }
    }

    public int consultarDuracionTotal(){
        int duracionTotal = 0;
        for (int i = 0; i < this.getUltimaCancionCargada(); i++){
            duracionTotal += this.lista[i].getDuracion();
        }

        return duracionTotal;
    }

    public int consultarDuracion(String titulo){
        if (this.getUltimaCancionCargada() == 0) return -1;

        int duracionTotal = 0;
        for (int i = 0; i < this.getUltimaCancionCargada(); i++){
            if (this.lista[i].getPorTipo("TITULO").compareToIgnoreCase(titulo) == 0) duracionTotal += this.lista[i].getDuracion();
        }

        if (duracionTotal == 0) return -1;
        return duracionTotal;
    }

    public boolean eliminarCancion(String titulo, String artista) {
        if (titulo == null) titulo = "";
        if (artista == null) artista = "";

        for (int i = 0; i < this.getUltimaCancionCargada(); i++) {
            if (this.lista[i].getPorTipo("TITULO").compareToIgnoreCase(titulo) == 0) {
                if (this.lista[i].getPorTipo("ARTISTA").compareToIgnoreCase(artista) == 0) {
                    return this.quitarCancionPorPosicion(i +1);
                }
            }
        }
        System.out.println("La canción buscada no se encontraba en la playlist");
        return false;
    }

    private boolean quitarCancionPorPosicion(int posicion) {
        if (this.laCancionEstaCargada(posicion)) {
            for (int i = posicion -1; i < this.getUltimaCancionCargada(); i++) {
                this.lista[i] = this.lista[i+1];
            }
            this.cancionEliminada();
            System.out.println("¡La canción fue removida!");
            return true;
        } else {
            this.mostrarErrorDeIndice(posicion);
            return false;
        }
    }

    public int consultarCancionMasCorta() {
        if (getUltimaCancionCargada() == 0) {
            System.out.println("Aún no se cargaron canciones a la playlist");
            return -1;
        }

        int duracionMasCorta = Integer.MAX_VALUE;
        int posicionMasCorta = 0;
        for (int i = 0; i < getUltimaCancionCargada(); i ++) {
            if (this.lista[i].getDuracion() < duracionMasCorta) {
                duracionMasCorta = this.lista[i].getDuracion();
                posicionMasCorta = i +1;
            }
        }

        return posicionMasCorta;
    }

    public int consultarCancionMasLarga() {
        if (getUltimaCancionCargada() == 0) {
            System.out.println("Aún no se cargaron canciones a la playlist");
            return -1;
        }

        int duracionMasLarga = Integer.MIN_VALUE;
        int posicionMasLarga = 0;
        for (int i = 0; i < getUltimaCancionCargada(); i ++) {
            if (this.lista[i].getDuracion() > duracionMasLarga) {
                duracionMasLarga = this.lista[i].getDuracion();
                posicionMasLarga = i +1;
            }
        }

        return posicionMasLarga;
    }

    public boolean reproducirPlaylist(String modo) {
        if (getUltimaCancionCargada() == 0) {
            System.out.println("Aún no se cargaron canciones a la playlist. No se puede reproducir");
            return false;
        } else {
            if (modo == null) modo = "";
    
            switch (modo) {
                case "ARTISTA":
                    if (!this.estaOrdenadaPorArtista) this.ordenarConSeleccion(this.ordenadasPorArtista, "ARTISTA");
                    this.reproducir(this.ordenadasPorArtista);
                    break;
            
                case "TITULO":
                    if (!this.estaOrdenadaPorTitulo) this.ordenarConSeleccion(this.ordenadasPorTitulo, "TITULO");
                    this.reproducir(this.ordenadasPorTitulo);
                    break;
    
                case "ALEATORIO":
                    int[] arrayDeAleatorios = this.generadorDeAleatoriosRecursivo(new Random(), new int[this.getUltimaCancionCargada()], 0);
                    this.reproducir(arrayDeAleatorios);
                    break;
    
                default:
                    if (!seMostroAyudaDeReproductor) {
                        System.out.println("###############################");
                        System.out.println("El reproductor admite cuatro modos de reproducción:");
                        System.out.println("- 'ARTISTA': Las canciones son reproducidas con sus Artistas en orden alfabético");
                        System.out.println("- 'TITULO': Las canciones son reproducidas con sus Títulos en orden alfabético");
                        System.out.println("- 'ALEATORIO': Las canciones son reproducidas en orden aleatorio");
                        System.out.println("- (predeterminado): Las canciones son reproducidas en el mismo orden que fueron cargadas a la PlayList");
                        System.out.println("\nNótese que el modo es case sensitive");
                        System.out.println("###############################");
                        this.seMostroAyudaDeReproductor = !this.seMostroAyudaDeReproductor;
                    }
                    this.reproducir(null);
                    break;
            }
        }
        return true;
    }

    private int[] generadorDeAleatoriosRecursivo(Random generador, int[] array, int indice) {
        if (indice == this.getUltimaCancionCargada()) return array;

        int numeroAleatorio = generador.nextInt(this.getUltimaCancionCargada());
        boolean encontrado = false;

        for (int i = 0; i < indice && !encontrado; i++) {
            if (array[i] == numeroAleatorio) encontrado = true;
        }

        if (encontrado) {
            this.generadorDeAleatoriosRecursivo(generador, array, indice);
        } else {
            array[indice] = numeroAleatorio;
            this.generadorDeAleatoriosRecursivo(generador, array, indice +1);
        }

        // Nunca vamos a llegar a este punto pero lo agregamos para evitar errores de compilación
        return array;
    }

    private void reproducir(int[] array) {
        if (array == null) {
            for (int posicion = 0; posicion < this.ultimaCancionCargada; posicion++) {
                System.out.println("Reproduciendo " + this.lista[posicion].getPorTipo("TITULO") +
                (((this.lista[posicion].getPorTipo("ARTISTA").compareTo("") == 0) ? "" : " de " + this.lista[posicion].getPorTipo("ARTISTA")) +
                (((this.lista[posicion].getPorTipo("ALBUM").compareTo("") == 0) ? "" : " del álbum " + this.lista[posicion].getPorTipo("ALBUM")) +
                " y su duración es de " + this.lista[posicion].getPorTipo("DURACION") + " segundos")));
            }
        } else {
            for (int posicion = 0; posicion < this.ultimaCancionCargada; posicion++) {
                System.out.println("Reproduciendo " + this.lista[array[posicion]].getPorTipo("TITULO") +
                (((this.lista[array[posicion]].getPorTipo("ARTISTA").compareTo("") == 0) ? "" : " de " + this.lista[array[posicion]].getPorTipo("ARTISTA")) +
                (((this.lista[array[posicion]].getPorTipo("ALBUM").compareTo("") == 0) ? "" : " del álbum " + this.lista[array[posicion]].getPorTipo("ALBUM")) +
                " y su duración es de " + this.lista[array[posicion]].getPorTipo("DURACION") + " segundos")));
            }
        }
    }

    private void mostrarErrorDeIndice(int posicion) {
        if (posicion > this.lista.length) System.out.println("La posición solicitada excede el tamaño de la playlist (" + this.lista.length + ")");
        else System.out.println("No hay ninguna canción cargada en la posición " + posicion);
    }

    private boolean laCancionEstaCargada(int posicion) {
        return (posicion -1 <= this.getUltimaCancionCargada() && this.lista[posicion -1] != null);
    }

    public String consultarTituloDeCancion(int posicion) {
        if (laCancionEstaCargada(posicion)) return this.lista[posicion -1].getTitulo();
        mostrarErrorDeIndice(posicion);
        return null;
    }

    private int[] artistasUnicos() {
        int[] copiaDeOrdenadasPorArtista = this.ordenadasPorArtista;

        for (int i = 0; i < this.getUltimaCancionCargada(); i++) {
            if (copiaDeOrdenadasPorArtista[i] == Integer.MAX_VALUE) continue;
            for (int j = i +1; j < this.getUltimaCancionCargada() -1; j ++) {
                if (copiaDeOrdenadasPorArtista[j] == Integer.MAX_VALUE) continue;
                if (this.lista[copiaDeOrdenadasPorArtista[i]].getPorTipo("ARTISTA").compareToIgnoreCase(this.lista[copiaDeOrdenadasPorArtista[j]].getPorTipo("ARTISTA")) == 0) copiaDeOrdenadasPorArtista[j] = Integer.MAX_VALUE;
            }
        }

        Arrays.sort(copiaDeOrdenadasPorArtista);

        return copiaDeOrdenadasPorArtista;
    }

    public void mostrarPlaylistOrdenadaPorArtistaYtitulo() {
        if (!this.estaOrdenadaPorArtista) {
            this.ordenarConSeleccion(this.ordenadasPorArtista, "ARTISTA");
        }

        if (!this.estaOrdenadaPorTitulo) {
            this.ordenarConSeleccion(this.ordenadasPorTitulo, "TITULO");
        }

        int[] artistasUnicos = this.artistasUnicos();
        String artistaPrevio = "";

        System.out.println("La lista ordenada por artista y título es:");
        for (int i = 0; i < this.getUltimaCancionCargada() && artistasUnicos[i] != Integer.MAX_VALUE; i++) {

            String artista = this.lista[artistasUnicos[i]].getArtista();
            if (artista == null) {
                artista = "Sin artista:";
            } else {
                artista = artista + ":";
            }

            if (artista.compareTo(artistaPrevio) != 0) {
                artistaPrevio = artista;
                System.out.println("\t" + artista);
            }

            for (int j = 0; j < this.ultimaCancionCargada; j++) {
                if (this.lista[this.ordenadasPorTitulo[j]].getArtista() == this.lista[artistasUnicos[i]].getArtista()) System.out.println("\t\t" + this.lista[this.ordenadasPorTitulo[j]].getTitulo());
            }
        }
    }

    public void mostrarPlaylistOrdenadaPorArtistaAlbumYtitulo() {
        if (!this.estaOrdenadaPorArtista) {
            this.ordenarConSeleccion(this.ordenadasPorArtista, "ARTISTA");
        }

        if (!this.estaOrdenadaPorTitulo) {
            this.ordenarConSeleccion(this.ordenadasPorTitulo, "TITULO");
        }

        System.out.println("La lista ordenada por artista, álbum y título es:");
        String artistaPrevio = "";
        String albumPrevio = "";
        for (int i = 0; i < this.ordenadasPorArtista.length && this.ordenadasPorArtista[i] != Integer.MAX_VALUE; i++) {
            String artista = this.lista[this.ordenadasPorArtista[i]].getArtista();
            if (artista == null) {
                artista = "Sin artista:";
            } else {
                artista = artista + ":";
            }

            boolean esElMismoArtista = artista.compareTo(artistaPrevio) == 0;

            if (!esElMismoArtista) {
                artistaPrevio = artista;
                System.out.println("\t" + artista);
            }

            String album = this.lista[this.ordenadasPorArtista[i]].getAlbum();
            if (album == null) {
                album = "\t Álbum desconocido";
            } else {
                album = "\t " + album;
            }

            if (!esElMismoArtista || album.compareTo(albumPrevio) != 0) {
                albumPrevio = album;
                System.out.println("\t" + album);
            }

            System.out.println("\t\t\t" + this.lista[this.ordenadasPorArtista[i]].getTitulo() + " - " + this.lista[this.ordenadasPorArtista[i]].getDuracion() + " segundos");
        }
    }

    public void mostrarPlaylistOrdenadaPorTitulo() {
        if (!this.estaOrdenadaPorTitulo) {
            this.ordenarConSeleccion(this.ordenadasPorTitulo, "TITULO");
        }

        System.out.println("La lista ordenada por título es:");
        for (int i = 0; i < this.ordenadasPorTitulo.length && this.ordenadasPorTitulo[i] != Integer.MAX_VALUE; i++) {
            System.out.println("\t" + (i +1) + ". " + this.lista[this.ordenadasPorTitulo[i]].getTitulo() + ".");
        }
    }

    // Se utilizan únicamente algortimos de ordenamiento vistos en Algoritmos y Programación 1
    private int[] ordenarConBurbujeo(int[] arrayDePosiciones, String tipo) {
        if (this.getUltimaCancionCargada() == 0) {
            System.err.println("Aún no se cargaron canciones en la playlist. No se puede ordenar");
        } else if (!this.getEstaOrdenadaPorTipo(tipo)) {

            // El primer for debe recorrer el array una vez, el segundo lo hará para cada posición de i
            for (int i = 0; i < this.getUltimaCancionCargada(); i++) {
                for (int j = 0; j < this.getUltimaCancionCargada() -i -1; j++) {
                    // Si el tamaño del primero es mayor a la del segundo (adyacente), se intercambian valores
                    if (this.lista[arrayDePosiciones[j]].getPorTipo(tipo).compareToIgnoreCase(this.lista[arrayDePosiciones[j +1]].getPorTipo(tipo)) > 0) {
                        int temporaria = arrayDePosiciones[j];
                        arrayDePosiciones[j] = arrayDePosiciones[j +1];
                        arrayDePosiciones[j +1] = temporaria;
                    }
                }
            }
        }

        return arrayDePosiciones;
    }

    private int[] ordenarConSeleccion(int[] arrayDePosiciones, String tipo) {
        if (this.getUltimaCancionCargada() == 0) {
            System.err.println("Aún no se cargaron canciones en la playlist. No se puede ordenar");
        } else if (!this.getEstaOrdenadaPorTipo(tipo)) {

            for (int i = 0; i < this.getUltimaCancionCargada(); i++) {
                // Declaro la variable para la posicion minima dentro del loop para que su scope sea lo menor posible
                String menorValor = this.lista[arrayDePosiciones[i]].getPorTipo(tipo);
                int posicionMinima = i;

                for (int j = i +1; j < this.getUltimaCancionCargada(); j++) {
                    // Si el valor en la posicion j es menor al que estaba en la posicion minima, lo asigno como nueva minima
                    if (menorValor != null &&
                        this.lista[arrayDePosiciones[j]].getPorTipo(tipo) != null &&
                        this.lista[arrayDePosiciones[j]].getPorTipo(tipo).compareToIgnoreCase(menorValor) < 0) {
                        menorValor = this.lista[arrayDePosiciones[j]].getPorTipo(tipo);
                        posicionMinima = j;
                    }
                }

                // Hago el reemplazo de valores en las posiciones que encontre, uso una variable auxiliar porque no me salio hacerlo con suma / resta
                int temporaria = arrayDePosiciones[i];
                arrayDePosiciones[i] = arrayDePosiciones[posicionMinima];
                arrayDePosiciones[posicionMinima] = temporaria;
            }
        
            this.setEstaOrdenadaPorTipo(tipo, true);
        }

        return arrayDePosiciones;
    }

    private int[] ordenarConInsercion(int[] arrayDePosiciones, String tipo) {
        if (this.ultimaCancionCargada == 0) {
            System.err.println("Aún no se cargaron canciones en la playlist. No se puede ordenar");
        } else if (!this.getEstaOrdenadaPorTipo(tipo)) {
            for (int i = 1; i < this.getUltimaCancionCargada(); i++) {
                // Guardo el valor que actualmente esta en el puntero i dentro de varloActual y luego seteo a j en la posicion anterior a i (para no volver a recorrer posiciones ya verificadas)
                int posicionActual = arrayDePosiciones[i];
                int j = i -1;

                // Mientras que haya registros en el array y el valor evaluado sea mayor que el valorActual, muevo el valor "para la izquierda"
                while (j >= 0 && this.lista[arrayDePosiciones[j]].getPorTipo(tipo).compareToIgnoreCase(this.lista[arrayDePosiciones[posicionActual]].getPorTipo(tipo)) > 0) {
                    // Realizo el movimiento y muevo el puntero j
                    arrayDePosiciones[j +1] = j;
                    j = j -1;
                }

                // Copiamos la posicionActual a su posición final y vamos a la proxima iteración
                arrayDePosiciones[j +1] = posicionActual;
            }

            this.setEstaOrdenadaPorTipo(tipo, true);
        }

        return arrayDePosiciones;
    }
}
