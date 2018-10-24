package app.exceptions;

public class InvalidRatingException extends DomainException {
    @Override
    public String getMessage() {
        return "Invalid rating value";
    }
}
