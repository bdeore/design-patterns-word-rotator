package wordPlay.handler;

import java.util.Vector;
import wordPlay.metrics.MetricsCalculator;
import wordPlay.util.FileProcessor;
import wordPlay.util.Results;

public class WordRotator {
  FileProcessor fp;
  MetricsCalculator metrics;
  Results rs;

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
          if (word.charAt((word.length()) - 1) == '.') {
            word = word.substring(0, word.length() - 1);
            word = rotate(word, wordCount);
            lineStats.add(word.length());
            line += word + ".";

            metrics.addLineStats(lineCount, lineStats);
            rs.store(line);
            line = "";
            lineCount++;
            lineStats = new Vector<>();
            wordCount = 0;

          } else {
            word = rotate(word, wordCount);
            line += word + " ";
            lineStats.add(word.length());
          }
          wordCount++;
        }
          word = fp.poll();
      }
    } catch (Exception e) {
      System.out.println(
          "exception" + e + " occurred in WordRotator class -> processFile function");
      e.printStackTrace();
    }
  }

  private String rotate(String original, int rotation) {
    char[] temp = new char[original.length()];

    if (original.length() == rotation) {
      return original;
    } else {
      for (int i = 0; i < original.length(); i++) {
        int newIndex = (i + rotation) % original.length();
        temp[newIndex] = original.charAt(i);
      }
      return String.valueOf(temp);
    }
  }
}
