package wordPlay.util;

import java.nio.file.InvalidPathException;
import java.util.Vector;
import wordPlay._exceptions.EmptyInputFileException;

/** interface for standard out IO */
public interface StdoutDisplayInterface {
  Vector<String> resultBuffer = null;

  void write() throws ArithmeticException, InvalidPathException, EmptyInputFileException;
}
