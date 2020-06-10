package wordPlay._exceptions;

public class EmptyInputFileException extends Exception {

  public EmptyInputFileException() {
    super("EmptyInputFileException : Input File Is Empty");
  }

  public EmptyInputFileException(String message) {
    super(message);
  }
}
