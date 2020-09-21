package playlist;

import java.util.Arrays;

public class PlayList {
    private Cancion[] lista;
    private int ultimaCancionCargada;
    private int[] ordenadasPorArtista;
    private int[] ordenadasPorTitulo;
    private boolean estaOrdenadaPorArtista;
    private boolean estaOrdenadaPorTitulo;

    PlayList(int cantidadDeCanciones) {
        if (cantidadDeCanciones <= 0) {
            // Aún no se vió Excepciones. Mostramos un mensaje y generamos la PlayList mínima
            System.out.println("La lista se debe iniciar con al menos una posición");
            cantidadDeCanciones = 10;
        } else if (cantidadDeCanciones > 10000) {
            System.out.println("La cantidad máxima de canciones es 10.000. Generando la lista con esa cantidad");
            cantidadDeCanciones = 10000;
        }
        this.lista = new Cancion[cantidadDeCanciones];

        this.ordenadasPorArtista = new int[cantidadDeCanciones];
        Arrays.fill(ordenadasPorArtista, Integer.MAX_VALUE);
        this.setEstaOrdenadaPorTipo(Tipo.ARTISTA, true);

        this.ordenadasPorTitulo = new int[cantidadDeCanciones];
        Arrays.fill(ordenadasPorTitulo, Integer.MAX_VALUE);
        this.setEstaOrdenadaPorTipo(Tipo.TITULO, true);
    }

    PlayList() {
        // Generamos la lista con un tamaño "razonable" por default
        this(1000);
        System.out.println("Generando la lista con 1.000 posiciones");
    }

    PlayList(Cancion cancion) {
        this();
        this.agregarCancion(cancion);
    }

    private void setEstaOrdenadaPorTipo(Tipo tipo, boolean valor) {
        switch (tipo) {
            case ARTISTA:
                this.estaOrdenadaPorArtista = valor;

            case TITULO:
                this.estaOrdenadaPorTitulo = valor;

            default:
        }
    }

    private boolean getEstaOrdenadaPorTipo(Tipo tipo) {
        switch (tipo) {
            case ARTISTA:
                return this.estaOrdenadaPorArtista;

            case TITULO:
                return this.estaOrdenadaPorTitulo;

            default:
                return false;
        }
    }

    public int consultarCantidadDeCanciones() {
        return this.ultimaCancionCargada;
    }

    private void cancionAgregada() {
        this.ultimaCancionCargada ++;
    }

    private void cancionEliminada() {
        this.ultimaCancionCargada --;
    }

    public boolean agregarCancion(Cancion cancion) {
        if (this.lista.length == this.consultarCantidadDeCanciones()) {
            System.out.println("No se puede agregar la canción ya que la lista ha alcanzado su límite máximo de " + this.lista.length);
            return false;
        } else {
            if (cancion.getPorTipo(Tipo.ARTISTA) == null || 
                (this.ultimaCancionCargada > 0 &&
                this.estaOrdenadaPorArtista &&
                cancion.getPorTipo(Tipo.ARTISTA).compareToIgnoreCase(this.lista[this.ultimaCancionCargada -1].getPorTipo(Tipo.ARTISTA)) < 0)
                ) this.setEstaOrdenadaPorTipo(Tipo.ARTISTA, false);
            this.ordenadasPorArtista[this.consultarCantidadDeCanciones()] = this.ultimaCancionCargada;

            if (this.ultimaCancionCargada > 0 &&
            this.estaOrdenadaPorTitulo &&
            cancion.getPorTipo(Tipo.TITULO).compareToIgnoreCase(this.lista[this.ultimaCancionCargada -1].getPorTipo(Tipo.TITULO)) < 0
            ) this.setEstaOrdenadaPorTipo(Tipo.TITULO, false);
            this.ordenadasPorTitulo[this.consultarCantidadDeCanciones()] = this.ultimaCancionCargada;

            this.lista[this.consultarCantidadDeCanciones()] = cancion;
            this.cancionAgregada();
            return true;
        }
    }

    public boolean quitarUltimaCancion() {
        return this.quitarCancion(this.consultarCantidadDeCanciones());
    }

    public boolean quitarCancion(int posicion) {
        /*
            Decidir si dejamos el contenido de la ultima cancion
            en la cola o "rellenamos" la posicion con un null
        */
        if (this.laCancionEstaCargada(posicion)) {
            for (int i = posicion -1; i < this.consultarCantidadDeCanciones(); i++) {
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

    private void mostrarErrorDeIndice(int posicion) {
        if (posicion > this.lista.length) System.out.println("La posición solicitada excede el tamaño de la playlist (" + this.lista.length + ")");
        else System.out.println("No hay ninguna canción cargada en la posición " + posicion);
    }

    private boolean laCancionEstaCargada(int posicion) {
        return (posicion -1 <= this.consultarCantidadDeCanciones() && this.lista[posicion -1] != null);
    }

    public String consultarTituloDeCancion(int posicion) {
        if (laCancionEstaCargada(posicion)) return this.lista[posicion -1].getTitulo();
        mostrarErrorDeIndice(posicion);
        return null;
    }

    public void mostrarPlaylistOrdenadaPorArtista() {
        if (!this.estaOrdenadaPorArtista) {
            this.ordenarConSeleccion(this.ordenadasPorArtista, Tipo.ARTISTA);
        }

        System.out.println("La lista ordenada por artista es:");
        for (int i = 0; i < this.ordenadasPorArtista.length && this.ordenadasPorArtista[i] != Integer.MAX_VALUE; i++) {
            String artista = this.lista[this.ordenadasPorArtista[i]].getArtista() + ":";
            if (artista == null + ":") artista = "Sin artista:";
            if (i != 0 && artista == this.lista[this.ordenadasPorArtista[i -1]].getArtista() + ":") artista = "\t";
            System.out.println("\t" + (i +1) + ". " + artista);

            String album = this.lista[this.ordenadasPorArtista[i]].getAlbum();
            if (album == null) {
                album = "\t↳ Álbum desconocido";
            } else {
                album = "\t↳ " + album;
            }
            if (i != 0 && album == "\t↳ " + this.lista[this.ordenadasPorArtista[i -1]].getAlbum()) album = "\t\t";
            System.out.println("\t" + album);

            System.out.println("\t\t\t" + this.lista[this.ordenadasPorArtista[i]].getTitulo() + " - " + this.lista[this.ordenadasPorArtista[i]].getDuracion() + " segundos");
        }
    }

    public void mostrarPlaylistOrdenadaPorTitulo() {
        if (!this.estaOrdenadaPorTitulo) {
            this.ordenarConInsercion(this.ordenadasPorTitulo, Tipo.TITULO);
        }

        System.out.println("La lista ordenada por título es:");
        for (int i = 0; i < this.ordenadasPorTitulo.length && this.ordenadasPorTitulo[i] != Integer.MAX_VALUE; i++) {
            System.out.println("\t" + (i +1) + ". " + this.lista[this.ordenadasPorTitulo[i]].getTitulo() + ".");
        }
    }

    // Se utilizan únicamente algortimos de ordenamiento vistos en Algoritmos y Programación 1, obviando Bubble por su pobre rendiemiento
    private int[] ordenarConSeleccion(int[] arrayDePosiciones, Tipo tipo) {
        if (this.consultarCantidadDeCanciones() == 0) {
            System.err.println("Aún no se cargaron canciones en la playlist. No se puede ordenar");
        } else if (!this.getEstaOrdenadaPorTipo(tipo)) {

            for (int i = 0; i < this.consultarCantidadDeCanciones(); i++) {
                // Declaro la variable para la posicion minima dentro del loop para que su scope sea lo menor posible
                String menorValor = this.lista[arrayDePosiciones[i]].getPorTipo(tipo);
                int posicionMinima = i;

                for (int j = i +1; j < this.consultarCantidadDeCanciones(); j++) {
                    // Si el valor en la posicion j es menor al que estaba en la posicion minima, lo asigno como nueva minima
                    if (this.lista[arrayDePosiciones[j]].getPorTipo(tipo).compareToIgnoreCase(menorValor) < 0) {
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

    private int[] ordenarConInsercion(int[] arrayDePosiciones, Tipo tipo) {
        if (this.ultimaCancionCargada == 0) {
            System.err.println("Aún no se cargaron canciones en la playlist. No se puede ordenar");
        } else if (!this.getEstaOrdenadaPorTipo(tipo)) {
            for (int i = 1; i < this.consultarCantidadDeCanciones(); i++) {
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
