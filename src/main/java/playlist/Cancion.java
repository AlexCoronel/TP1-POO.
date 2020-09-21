package playlist;
import playlist.Tipo;

public class Cancion {
    private String titulo;
    private String album;
    private String artista;
    private Integer duracion;
    
    Cancion(String titulo, int duracion) {
        this.setDuracion(duracion);
        this.setTitulo(titulo);
    }

    Cancion(String titulo, int duracion, String artista) {
        this(titulo, duracion);
        this.setArtista(artista);
    }

    Cancion(String titulo, int duracion, String artista, String album) {
        this(titulo, duracion, artista);
        this.setAlbum(album);
    }

    public String getTitulo() {
        return this.titulo;
    }

    public Integer getDuracion() {
        return this.duracion;
    }

    public String getAlbum() {
        return this.album;
    }

    public String getArtista() {
        return this.artista;
    }

    private void setTitulo(String titulo) {
        if (this.duracion > 0) this.titulo = titulo;
    }

    private void setDuracion(int duracion) {
        if (duracion > 0) {
            this.duracion = duracion;
        } else {
            // Aún no se vió Excepciones. Mostramos un mensaje y no seteamos los campos
            System.out.println("La duración de la canción no puede ser negativa o cero");
        }
    }

    private void setAlbum(String album) {
        if (this.duracion > 0) this.album = album;
    }

    private void setArtista(String artista) {
        if (this.duracion > 0) this.artista = artista;
    }

    public String getPorTipo(Tipo tipo) {
        switch (tipo) {
            case TITULO:
                return this.getTitulo();

            case DURACION:
                return this.getDuracion().toString();
        
            case ARTISTA:
                return this.getArtista();
            
            case ALBUM:
                return this.getAlbum();

            default:
                return "El tipo " + tipo + "no existe";
        }
    }

}
