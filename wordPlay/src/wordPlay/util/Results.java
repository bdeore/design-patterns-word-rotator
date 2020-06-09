package wordPlay.util;

import java.util.Vector;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {

  private Vector<String> resultBuffer;

  public Results() {
    this.resultBuffer = new Vector<String>();
  }

  public void store(String line) {
    resultBuffer.add(line);
  }

  public Vector<String> getResultBuffer() {
    return resultBuffer;
  }
}
