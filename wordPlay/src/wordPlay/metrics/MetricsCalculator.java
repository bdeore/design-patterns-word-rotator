package wordPlay.metrics;

import java.util.Vector;

public class MetricsCalculator {

  private double avgWordsPerSentence;
  private double avgWordLength;

  private final Vector<Vector<Integer>> metrics;

  public MetricsCalculator() {
    this.avgWordsPerSentence = 0;
    this.avgWordLength = 0;
    metrics = new Vector<Vector<Integer>>();
  }

  public double getAvgWordsPerSentence() {
    calculateAvgWordsPerSentence();
    return avgWordsPerSentence;
  }

  public double getAvgWordLength() {
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
}
