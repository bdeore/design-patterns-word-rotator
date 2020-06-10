package wordPlay.metrics;

import java.util.Vector;
import wordPlay._exceptions.EmptyInputFileException;

/**
 * MetricsCalculator class maintains a vector of vectors that stores line numbers and word length.
 * vector is updated by WordRotator class. position of inner vector represents line number and
 * elements represent the word length. e.g. I Am Tom Shaw. I Do Not Like Salad.
 *
 * <p>metrics vector: [[1, 2, 3], [1, 2, 3, 4]]
 *
 * <p>Average words per sentence and average word length metrics are calculated from this vector.
 */
public class MetricsCalculator {

  private final Vector<Vector<Integer>> metrics;
  private double avgWordsPerSentence;
  private double avgWordLength;

  public MetricsCalculator() {
    this.avgWordsPerSentence = 0;
    this.avgWordLength = 0;
    metrics = new Vector<Vector<Integer>>();
  }

  /**
   * getter for avgWordsPerSentence
   *
   * @return double value- average words count rounded up two digits
   * @throws EmptyInputFileException if input file is empty
   */
  public double getAvgWordsPerSentence() throws EmptyInputFileException {
    calculateAvgWordsPerSentence();
    if (metrics.size() == 0) {
      throw new EmptyInputFileException("Input File Is Empty");
    }
    return avgWordsPerSentence;
  }

  /**
   * getter for avgWordLength
   *
   * @return double value - average word length rounded up two digits
   * @throws EmptyInputFileException if input file is empty
   */
  public double getAvgWordLength() throws EmptyInputFileException {
    if (metrics.size() == 0) {
      throw new EmptyInputFileException("Input File Is Empty");
    }
    calculateAvgWordLength();
    return avgWordLength;
  }

  /**
   * public function to allow additions to metrics vector
   *
   * @param lineNumber represents index of second vector
   * @param elements vector of stats for currents line processed by wordRotator
   */
  public void addLineStats(int lineNumber, Vector<Integer> elements) {
    if (lineNumber >= 0 && elements.size() > 0) {
      metrics.add(lineNumber, elements);
    }
  }

  /** utility function useful for debugging. prints the vector of vectors */
  public void printVector() {
    for (Vector<Integer> tempReference : metrics) {
      for (Integer integer : tempReference) {
        System.out.print(integer + " ");
      }
      System.out.println();
    }
  }

  /**
   * calculates average number of words per sentence
   *
   * @throws ArithmeticException on division by zero (when metrics.size() is zero)
   */
  private void calculateAvgWordsPerSentence() throws ArithmeticException {
    int size = 0;
    for (Vector<Integer> tempReference : metrics) {
      size += tempReference.size();
    }
    avgWordsPerSentence = (double) size / metrics.size();
    avgWordsPerSentence = roundUp(avgWordsPerSentence);
  }

  /**
   * calculates average word length
   *
   * @throws ArithmeticException on division by zero (when metrics.size() is zero)
   */
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

  /**
   * utility function for round up to two digits
   *
   * @param value - double to be rounded
   * @return double rounded off representation of value
   */
  private double roundUp(double value) {
    return ((double) Math.round(value * 100) / 100);
  }

  /**
   * toString() function
   *
   * @return string prints out metrics
   */
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
