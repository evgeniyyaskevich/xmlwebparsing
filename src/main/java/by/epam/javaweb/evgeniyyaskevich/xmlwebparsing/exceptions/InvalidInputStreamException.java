package by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.exceptions;

public class InvalidInputStreamException extends Exception {

    private static final long serialVersionUID = 4892571280468928733L;

    public InvalidInputStreamException() {
        super();
    }

    public InvalidInputStreamException(String message) {
        super(message);
    }

    public InvalidInputStreamException(String message, Throwable cause) {
        super(message, cause);
    }
}
