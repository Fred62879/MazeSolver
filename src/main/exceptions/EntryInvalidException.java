package exceptions;

public class EntryInvalidException extends Exception {

    public EntryInvalidException() {}

    public EntryInvalidException(String msg) {
        super(msg);
    }
}
