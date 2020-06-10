package wordPlay.handler;

import java.io.IOException;
import java.util.Vector;
import wordPlay._exceptions.InvalidWordException;
import wordPlay.metrics.MetricsCalculator;
import wordPlay.util.FileProcessor;
import wordPlay.util.Results;

public class WordRotator {
  private final FileProcessor fp;
  private final MetricsCalculator metrics;
  private final Results rs;

  public WordRotator(FileProcessor fp, MetricsCalculator metrics, Results rs) {
    this.fp = fp;
    this.metrics = metrics;
    this.rs = rs;
  }

  public void processFile() {
    try {
      int wordCount = 1, lineCount = 0;
      Vector<Integer> lineStats = new Vector<>();
      String line = "", word = fp.poll();

      while (word != null) {
        if (!word.equals("")) {
          if (!word.matches("\\w.*")) {
            throw new InvalidWordException(
                " ( " + word + " ) Please Ensure File contains Valid Words/Sentences");
          }
          if (word.charAt((word.length()) - 1) == '.') {
            word = word.substring(0, word.length() - 1);
            word = rotate(word, wordCount);
            lineStats.add(word.length());
            line += word + ".";

            metrics.addLineStats(lineCount, lineStats);
            rs.store(line);

            line = "";
            lineStats = new Vector<>();
            wordCount = 0;
            lineCount++;

          } else {
            word = rotate(word, wordCount);
            line += word + " ";
            lineStats.add(word.length());
          }
          wordCount++;
        }
        word = fp.poll();
      }
    } catch (InvalidWordException e) {
      System.out.println(e);
      System.out.println("Terminating Program");
      System.exit(1);
    } catch (IOException e) {
      System.out.println(e);
      System.out.println("(poll() method) Terminating Program");
      System.exit(1);
    }
  }

  private String rotate(String original, int rotation) {
    char[] temp = new char[original.length()];

    if (rotation % original.length() == 0) {
      return original;
    } else {
      for (int i = 0; i < original.length(); i++) {
        int newIndex = (i + rotation) % original.length();
        temp[newIndex] = original.charAt(i);
      }
    }
    return String.valueOf(temp);
  }
}
