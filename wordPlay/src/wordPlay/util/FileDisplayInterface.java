package wordPlay.util;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.Vector;
import wordPlay._exceptions.EmptyInputFileException;

/** Interface for File IO */
public interface FileDisplayInterface {
  Vector<String> resultBuffer = null;

  void write(String output_filename, String metrics_filename)
      throws ArithmeticException, InvalidPathException, IOException, EmptyInputFileException;
}
