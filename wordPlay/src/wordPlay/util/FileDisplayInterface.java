package wordPlay.util;

import java.io.FileWriter;
import java.util.Vector;

public interface FileDisplayInterface {
  Vector<String> resultBuffer = null;

  public void write(String output_filename, String metrics_filename);
}
