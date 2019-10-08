package exceptions;

public class MazeRecordInvalidException extends Exception{

    public MazeRecordInvalidException() {}

    public MazeRecordInvalidException(String msg) {
        super(msg);
    }
}
