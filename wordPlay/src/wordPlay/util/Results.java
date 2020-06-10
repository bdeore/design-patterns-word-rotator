package wordPlay.util;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.Vector;
import wordPlay._exceptions.EmptyInputFileException;
import wordPlay.metrics.MetricsCalculator;

/**
 * Results Class: maintains a result buffer(vector of string objects) which is updated by
 * WordRotator class through store() method. implements two overrides of write() method declared in
 * interfaces. when write() method without parameters is called, output is printed out on console.
 * when write(output_filename, metrics_filename) is called, appropriate files are created and
 * written to. write() method exceptions are handled by calling code in Driver class.
 */
public class Results implements FileDisplayInterface, StdoutDisplayInterface {

  private final Vector<String> resultBuffer;
  private final MetricsCalculator metrics;

  /**
   * parameterized constructor
   *
   * @param metrics MetricsCalculator object
   */
  public Results(MetricsCalculator metrics) {
    this.resultBuffer = new Vector<String>();
    this.metrics = metrics;
  }

  /**
   * method to write output and metrics on standard out. exceptions are handled by calling code in
   * Driver class.
   *
   * @throws ArithmeticException on divide by zero error
   * @throws InvalidPathException on invalid file path
   * @throws EmptyInputFileException if input file is empty
   */
  @Override
  public void write() throws ArithmeticException, InvalidPathException, EmptyInputFileException {
    try {
      double temp = metrics.getAvgWordsPerSentence();
      System.out.println("\nMetrics: ");
      System.out.println("-----------------------------------");
      System.out.println("AVG_NUM_WORDS_PER_SENTENCE - " + temp);
      System.out.println("AVG_WORD_LENGTH - " + metrics.getAvgWordLength());
      System.out.println("-----------------------------------");

      System.out.println("\nOutput: ");
      System.out.println("-----------------------------------");
      for (String line : resultBuffer) {
        System.out.println(line);
      }
      System.out.println("-----------------------------------");
    } catch (EmptyInputFileException | ArithmeticException e) {
      System.out.println(e);
      System.out.println("(Class Results) Terminating Program");
      // e.printStackTrace();
      System.exit(1);
    }
  }

  /**
   * method to write output and metrics on standard out. exceptions are handled by calling code in
   * Driver class.
   *
   * @param output_filename - name of the output file
   * @param metrics_filename - name of the metrics file
   * @throws ArithmeticException on divide by zero error
   * @throws InvalidPathException on invalid file path
   * @throws EmptyInputFileException if input file is empty
   * @throws FileNotFoundException if cannot create and open the file e.g. if directory exists with
   *     same name
   */
  @Override
  public void write(String output_filename, String metrics_filename)
      throws ArithmeticException, InvalidPathException, IOException, EmptyInputFileException,
          FileNotFoundException {
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

    } finally {
      if (output_file != null) output_file.close();
      if (metrics_file != null) metrics_file.close();
    }
  }

  /**
   * public method to store rotated string in the results buffer
   *
   * @param line - rotated line passed in WordRotator class
   */
  public void store(String line) {
    resultBuffer.add(line);
  }

  /**
   * getter for resultBuffer
   *
   * @return vector of rotated string objects
   */
  public Vector<String> getResultBuffer() {
    return resultBuffer;
  }

  /**
   * toString() method for debugging
   *
   * @return string
   */
  @Override
  public String toString() {
    return "\nResults : \n" + "resultBuffer => " + resultBuffer + "\nmetrics => " + metrics;
  }
}
