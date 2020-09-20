package playlist;

import java.util.Arrays;

public class PlayList {
    private Cancion[] lista;
    private int[] ordenadasPorTitulo;
    private int[] ordenadasPorTiempo;
    private int ultimaCancionCargada;
    private boolean estaOrdenadaPorTitulo;
    private boolean estaOrdenadaPorTiempo;

    PlayList(int cantidadDeCanciones) {
        if (cantidadDeCanciones <= 0) {
            // Aún no se vió Excepciones. Mostramos un mensaje y generamos la PlayList mínima
            System.out.println("La lista se debe iniciar con al menos una posición");
            cantidadDeCanciones = 1;
        } else if (cantidadDeCanciones > 10000) {
            System.out.println("La cantidad máxima de canciones es 10.000. Generando la lista con esa cantidad");
            cantidadDeCanciones = 10000;
        }
        this.lista = new Cancion[cantidadDeCanciones];
        this.ordenadasPorTitulo = new int[cantidadDeCanciones];
        Arrays.fill(ordenadasPorTitulo, -1);
        this.ordenadasPorTiempo = new int[cantidadDeCanciones];
        Arrays.fill(ordenadasPorTiempo, -1);
        this.setEstaOrdenadaPorTiempo();
        this.setEstaOrdenadaPorTitulo();
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

    private void setEstaOrdenadaPorTiempo() {
        this.estaOrdenadaPorTiempo = !this.estaOrdenadaPorTiempo;
    }

    private void setEstaOrdenadaPorTitulo() {
        this.estaOrdenadaPorTitulo = !this.estaOrdenadaPorTitulo;
    }

    public int consultarCantidadDeCancionesCargadas() {
        return this.ultimaCancionCargada;
    }
    private void cancionAgregada() {
        this.ultimaCancionCargada ++;
    }

    private void cancionRemovida() {
        this.ultimaCancionCargada --;
    }

    public boolean agregarCancion(Cancion cancion) {
        if (this.lista.length == this.consultarCantidadDeCancionesCargadas()) {
            System.out.println("No se puede agregar la canción ya que la lista ha alcanzado su límite máximo de " + this.lista.length);
            return false;
        } else {
            if (this.consultarCantidadDeCancionesCargadas() > 0 && 
                this.estaOrdenadaPorTiempo &&
                cancion.getDuracion() < this.lista[this.ordenadasPorTiempo[consultarCantidadDeCancionesCargadas() -1]].getDuracion()
                ) this.setEstaOrdenadaPorTiempo();
            // Agregar manejo de orden (como el de arriba) para el titulo
            this.ordenadasPorTiempo[this.consultarCantidadDeCancionesCargadas()] = this.consultarCantidadDeCancionesCargadas() -1;
            this.ordenadasPorTitulo[this.consultarCantidadDeCancionesCargadas()] = this.consultarCantidadDeCancionesCargadas() -1;
            this.lista[this.consultarCantidadDeCancionesCargadas()] = cancion;
            this.cancionAgregada();
            return true;
        }
    }

    public boolean quitarUltimaCancion() {
        return this.quitarCancion(this.consultarCantidadDeCancionesCargadas() +1);
    }

    public boolean quitarCancion(int posicion) {
        /*
            Decidir si dejamos el contenido de la ultima cancion
            en la cola o "rellenamos" la posicion con un null
        */
        if (this.laCancionEstaCargada(posicion)) {
            for (int i = posicion -1; i < this.consultarCantidadDeCancionesCargadas(); i++) {
                this.lista[i] = this.lista[i+1];
            }
            this.cancionRemovida();
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
        return (posicion -1 <= this.consultarCantidadDeCancionesCargadas() && this.lista[posicion -1] != null);
    }

    public String consultarTituloDeCancion(int posicion) {
        if (laCancionEstaCargada(posicion)) return this.lista[posicion -1].getTitulo();
        mostrarErrorDeIndice(posicion);
        return null;
    }

    public void mostrarPlaylistOrdenadaPorTiempo() {

    }

    // Se utilizan únicamente algortimos de ordenamiento vistos en Algoritmos y Programación 1, obviando Bubble por su pobre rendiemiento
    private int[] ordenarPorTituloConSeleccion(int[] posiciones) {
        this.ordenarPorTituloConSeleccion(this.ordenadasPorTiempo);

        return posiciones;
    }

    private void ordenarPorTiempoConSeleccion(int[] posiciones) {
        if (this.consultarCantidadDeCancionesCargadas() == 0) {
            System.err.println("Aún no se cargaron canciones en la playlist. No se puede ordenar");
        } else if (!this.estaOrdenadaPorTiempo) {

            for (int i = 0; i < this.consultarCantidadDeCancionesCargadas(); i++) {
                // Declaro la variable para la posicion minima dentro del loop para que su scope sea lo menor posible
                int minima = i;

                for (int j = i +1; j < this.consultarCantidadDeCancionesCargadas(); j++) {
                    // Si el valor en la posicion j es menor al que estaba en la posicion minima, lo asigno como nueva minima
                    if (this.ordenadasPorTiempo[j] < this.ordenadasPorTiempo[minima]) minima = j;
                }

                // Hago el reemplazo de valores en las posiciones que encontre, uso una variable auxiliar porque no me salio hacerlo con suma / resta
                int temporaria = this.ordenadasPorTiempo[minima];
                this.ordenadasPorTiempo[minima] = this.ordenadasPorTiempo[i];
                this.ordenadasPorTiempo[i] = temporaria;
            }
        
            this.setEstaOrdenadaPorTiempo();
        }
    }

    private int[] ordenarPorTituloConInsercion(int[] posiciones) {

        return posiciones;
    }

    private void ordenarPorTiempoConInsercion(int[] posiciones) {
        if (this.consultarCantidadDeCancionesCargadas() == 0) {
            System.err.println("Aún no se cargaron canciones en la playlist. No se puede ordenar");
        } else if (!this.estaOrdenadaPorTiempo) {
            for (int i = 1; i < this.consultarCantidadDeCancionesCargadas(); i++) {
                // Guardo el valor que actualmente esta en el puntero i dentro de varloActual y luego seteo a j en la posicion anterior a i (para no volver a recorrer posiciones ya verificadas)
                int valorActual = this.ordenadasPorTiempo[i];
                int j = i -1;

                // Mientras que haya registros en el array y el valor evaluado sea mayor que el valorActual, muevo el valor "para la izquierda"
                while (j >= 0 && this.ordenadasPorTiempo[j] > valorActual) {
                    // Realizo el movimiento y muevo el puntero j
                    this.ordenadasPorTiempo[j +1] = this.ordenadasPorTiempo[j];
                    j = j -1;
                }

                // Copiamos el valorActual a su posición final y vamos a la proxima iteración
                this.ordenadasPorTiempo[j +1] = valorActual;
            }

            this.setEstaOrdenadaPorTiempo();
        }
    }

}
