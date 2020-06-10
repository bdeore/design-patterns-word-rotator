package wordPlay._exceptions;

/**
 * user-defined exception class - this exception is thrown if invalid word is found in input file.
 */
public class InvalidWordException extends Exception {

  public InvalidWordException() {
    super("InvalidWordException : Please Ensure File contains Valid Words/Sentences");
  }

  public InvalidWordException(String message) {
    super(message);
  }
}
