package mx.org.uv.api.Proyecto.error.exception;

public class AlbumAlreadyExistsException extends RuntimeException {
    public AlbumAlreadyExistsException(String id) {
        super("Album already exists with id: " + id);
    }
}
