package wordPlay.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import wordPlay.metrics.MetricsCalculator;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {

  private final Vector<String> resultBuffer;
  private final MetricsCalculator metrics;

  public Results(MetricsCalculator metrics) {
    this.resultBuffer = new Vector<String>();
    this.metrics = metrics;
  }

  @Override
  public void write() {
    System.out.println("\nOutput: ");
    System.out.println("-----------------------------------");
    for (String line : resultBuffer) {
      System.out.println(line);
    }

    System.out.println("\nMetrics: ");
    System.out.println("-----------------------------------");
    System.out.println("AVG_NUM_WORDS_PER_SENTENCE - " + metrics.getAvgWordsPerSentence());
    System.out.println("AVG_WORD_LENGTH - " + metrics.getAvgWordLength());
    System.out.println("-----------------------------------");
  }

  @Override
  public void write(String output_filename, String metrics_filename) {
    FileWriter output_file = null;
    FileWriter metrics_file = null;

    try {
      output_file = new FileWriter("output.txt");
      metrics_file = new FileWriter("metrics.txt");
      for (String line : resultBuffer) {
        output_file.write(line + "\n");
      }
      metrics_file.write("AVG_NUM_WORDS_PER_SENTENCE - " + metrics.getAvgWordsPerSentence() + "\n");
      metrics_file.write("AVG_WORD_LENGTH - " + metrics.getAvgWordLength());
    } catch (IOException e) {
      System.out.println("IOException Occurred");
      e.printStackTrace();
    } finally {
      try {
        if (output_file != null) output_file.close();
        if (metrics_file != null) metrics_file.close();

      } catch (IOException e) {
        System.out.println("IOException Occurred");
        e.printStackTrace();
      }
    }
  }

  public void store(String line) {
    resultBuffer.add(line);
  }
}
