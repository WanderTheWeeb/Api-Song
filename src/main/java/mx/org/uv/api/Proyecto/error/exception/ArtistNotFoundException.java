package mx.org.uv.api.Proyecto.error.exception;

public class ArtistNotFoundException extends RuntimeException{
    public ArtistNotFoundException(String id) {
        super("Artist not found with id: " + id);
    }
}
