package wordPlay.metrics;

import java.util.Vector;
import wordPlay._exceptions.EmptyInputFileException;

public class MetricsCalculator {

  private final Vector<Vector<Integer>> metrics;
  private double avgWordsPerSentence;
  private double avgWordLength;

  public MetricsCalculator() {
    this.avgWordsPerSentence = 0;
    this.avgWordLength = 0;
    metrics = new Vector<Vector<Integer>>();
  }

  public double getAvgWordsPerSentence() throws EmptyInputFileException {
    calculateAvgWordsPerSentence();
    if (metrics.size() == 0) {
      throw new EmptyInputFileException("Input File Is Empty");
    }
    return avgWordsPerSentence;
  }

  public double getAvgWordLength() throws EmptyInputFileException {
    if (metrics.size() == 0) {
      throw new EmptyInputFileException("Input File Is Empty");
    }
    calculateAvgWordLength();
    return avgWordLength;
  }

  public void addLineStats(int lineNumber, Vector<Integer> elements) {
    if (lineNumber >= 0 && elements.size() > 0) {
      metrics.add(lineNumber, elements);
    }
  }

  public void printVector() {
    for (Vector<Integer> tempReference : metrics) {
      for (Integer integer : tempReference) {
        System.out.print(integer + " ");
      }
      System.out.println();
    }
  }

  private void calculateAvgWordsPerSentence() throws ArithmeticException {
    int size = 0;
    for (Vector<Integer> tempReference : metrics) {
      size += tempReference.size();
    }
    avgWordsPerSentence = (double) size / metrics.size();
    avgWordsPerSentence = roundUp(avgWordsPerSentence);
  }

  private void calculateAvgWordLength() throws ArithmeticException {
    int charCount = 0, wordCount = 0;
    for (Vector<Integer> tempReference : metrics) {
      wordCount += tempReference.size();
      for (Integer integer : tempReference) {
        charCount += integer;
      }
    }
    avgWordLength = (double) charCount / wordCount;
    avgWordLength = roundUp(avgWordLength);
    // avgWordLength = (double) ((int)(avgWordLength * 100)) / 100;
  }

  private double roundUp(double value) {
    return ((double) Math.round(value * 100) / 100);
  }

  @Override
  public String toString() {
    try {
      return "MetricsCalculator : \n"
          + "avgWordsPerSentence => "
          + getAvgWordsPerSentence()
          + "\navgWordLength => "
          + getAvgWordLength()
          + "\n";
    } catch (EmptyInputFileException e) {
      e.printStackTrace();
    }
    return "";
  }
}
