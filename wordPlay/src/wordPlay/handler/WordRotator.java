package wordPlay.handler;

import java.io.IOException;
import java.util.Vector;
import wordPlay._exceptions.InvalidWordException;
import wordPlay.metrics.MetricsCalculator;
import wordPlay.util.FileProcessor;
import wordPlay.util.Results;

/**
 * WordRotator class is crucial to make sure we read input file only once. when parsing the input
 * file, word length and line numbers are tracked simultaneously and stored in metrics object.
 * special utility function rotate() manages string rotation. result of string rotation is stored in
 * the buffer of results object.
 */
public class WordRotator {
  private final FileProcessor fp;
  private final MetricsCalculator metrics;
  private final Results rs;

  /**
   * @param fp FileProcessor Object - to read words from the input file
   * @param metrics MetricsCalculator Object - to process metrics at the same time
   * @param rs Results Object - to store result of string rotation in the buffer. which can be used
   *     later to write to multiple files on consoles without re-computation.
   */
  public WordRotator(FileProcessor fp, MetricsCalculator metrics, Results rs) {
    this.fp = fp;
    this.metrics = metrics;
    this.rs = rs;
  }

  /**
   * function does two things: 1) check boundary conditions like: empty line(discards), empty
   * file(exception), Invalid words(exception), only period character on line, etc. 2) process
   * words: read in words - check validity with regex - store the word length in lineStats vector -
   * pass it to metrics object - rotate word using utility function - store it in line variable -
   * store it in the buffer of results object.
   */
  public void processFile() {
    try {
      int wordCount = 1, lineCount = 0;
      // stores length of words which are used by metrics class for calculations
      // e.g. I Am Tom => [1, 2, 3]
      Vector<Integer> lineStats = new Vector<>();
      String line = "", word = fp.poll();

      while (word != null) {
        if (!word.equals("")) {
          if (word.matches("\\w+.?")) {

            // control goes through if its the end of line
            if (word.charAt((word.length()) - 1) == '.') {
              word = word.substring(0, word.length() - 1);
              word = rotate(word, wordCount);
              lineStats.add(word.length());
              line += word + ".";

              // pass on lineStats to metrics class at the end of each line
              metrics.addLineStats(lineCount, lineStats);

              // store the results of rotation in buffer of results class
              rs.store(line);

              // reset all variables for processing next line
              line = "";
              lineStats = new Vector<>();
              wordCount = 0;
              lineCount++;

            } else {

              // for all the intermediate words
              word = rotate(word, wordCount);
              line += word + " ";
              lineStats.add(word.length());
            }

            wordCount++;
          } else {
            // control goes through if word doesn't match regex
            throw new InvalidWordException(
                "Line Numebr: [ "
                    + (lineCount + 1)
                    + " ], Invalid Word: "
                    + " [ "
                    + word
                    + " ]"
                    + "\nPlease Ensure File Contains Valid Words/Sentences");
          }
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

  /**
   * @param original - word read from the file
   * @param rotation - amount of rotation(number of characters)
   * @return String - result of rotation
   */
  private String rotate(String original, int rotation) {
    char[] temp = new char[original.length()];

    /*
     * we return the original if below condition matches. in some cases rotation yields the same
     * result as original. so, we can just return the original string. we need to check if the
     * length > 0. otherwise modulo operation throws divide by zero exception.
     */
    if (original.length() > 0 && rotation % original.length() == 0) {
      return original;
    } else {
      for (int i = 0; i < original.length(); i++) {
        int newIndex = (i + rotation) % original.length();
        temp[newIndex] = original.charAt(i);
      }
    }
    return String.valueOf(temp);
  }

  /** @return String composed of fields from metrics and results objects */
  @Override
  public String toString() {
    return "\nWordRotator : \n" + "Metrics => " + metrics + "Results => " + rs;
  }
}
