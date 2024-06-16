package mx.org.uv.api.Proyecto.error.exception;

public class SongAlreadyExistsException extends RuntimeException {
    public SongAlreadyExistsException(String id) {
        super("Song already exists with id: " + id);
    }
}
