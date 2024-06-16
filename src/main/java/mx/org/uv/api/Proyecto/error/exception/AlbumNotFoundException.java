package mx.org.uv.api.Proyecto.error.exception;

public class AlbumNotFoundException extends RuntimeException{
    public AlbumNotFoundException(String id) {
        super("Album not found with id: " + id);
    }
}
