package mx.org.uv.api.Proyecto.error.exception;

public class ArtistAlredyExistException extends RuntimeException {
    public ArtistAlredyExistException(String name) {
        super("Artist already exist with name: " + name);
    }
}
