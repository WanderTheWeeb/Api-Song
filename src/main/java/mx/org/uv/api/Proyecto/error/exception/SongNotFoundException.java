package mx.org.uv.api.Proyecto.error.exception;

public class SongNotFoundException extends RuntimeException {
    public SongNotFoundException(String id) {
        super("Song not found with id: " + id);
    }
}
