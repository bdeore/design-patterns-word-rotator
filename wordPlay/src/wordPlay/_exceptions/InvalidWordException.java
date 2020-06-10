package wordPlay._exceptions;

public class InvalidWordException extends Exception {

  public InvalidWordException() {
    super("InvalidWordException : Please Ensure File contains Valid Words/Sentences");
  }

  public InvalidWordException(String message) {
    super(message);
  }
}
