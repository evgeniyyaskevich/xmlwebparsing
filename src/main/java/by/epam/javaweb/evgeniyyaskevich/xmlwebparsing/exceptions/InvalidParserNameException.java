package by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.exceptions;

public class InvalidParserNameException extends Exception{
    private static final long serialVersionUID = -1670629048752657329L;

    public InvalidParserNameException() {
    }

    public InvalidParserNameException(Throwable cause) {
        super(cause);
    }

    public InvalidParserNameException(String message) {
        super(message);
    }

    public InvalidParserNameException(String message, Throwable cause) {
        super(message, cause);
    }
}
