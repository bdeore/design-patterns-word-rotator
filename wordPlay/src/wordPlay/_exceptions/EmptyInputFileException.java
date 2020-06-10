package wordPlay._exceptions;

/** user-defined exception class - this exception is thrown if input file is empty */
public class EmptyInputFileException extends Exception {

  public EmptyInputFileException() {
    super("EmptyInputFileException : Input File Is Empty");
  }

  public EmptyInputFileException(String message) {
    super(message);
  }
}
