package wordPlay.driver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import wordPlay._exceptions.EmptyInputFileException;
import wordPlay.handler.WordRotator;
import wordPlay.metrics.MetricsCalculator;
import wordPlay.util.FileProcessor;
import wordPlay.util.Results;

/** @author Bhagwan Deore */
public class Driver {

  public static void main(String[] args) {

    /*
     * As the build.xml specifies the arguments as input,output or metrics, in case the
     * argument value is not given java takes the default value specified in
     * build.xml. To avoid that, below condition is used
     */
    if ((args.length != 3)
        || (args[0].equals("${input}"))
        || (args[1].equals("${output}"))
        || (args[2].equals("${metrics}"))) {
      System.err.println("Error: Incorrect number of arguments. Program accepts 3 arguments.");
      System.exit(0);
    }
    // System.out.println("Hello World! Lets get started with the assignment");

    try {

      FileProcessor fp = new FileProcessor(args[0]);
      MetricsCalculator metrics = new MetricsCalculator();

      /*
       * results class is constructed with metrics so that printing out results multiple
       * times doesn't require re-computation
       */
      Results rs = new Results(metrics);

      /*
       * WordRotator reads words using FileProcessor Object
       * stores the word and line count in metrics object, which in turn is used
       * to calculate metrics. this ensures that we don't read input file multiple times.
       * Also, stores the results of rotation in results class buffer.
       */
      WordRotator wr = new WordRotator(fp, metrics, rs);
      // processFile function manages everything
      wr.processFile();

      /*
       * Results object provide uniform interface generating output
       *
       *  write() method without any parameters prints output and metrics to the console
       *  write(output_file_name, metrics_file_name) writes output and metrics to specified
       *  files.
       *
       */
      rs.write();
      rs.write("output.txt", "metrics.txt");

    } catch (InvalidPathException
        | FileNotFoundException
        | SecurityException
        | ArithmeticException
        | EmptyInputFileException e) {
      System.out.println(e);
      System.out.println("(Class Driver) Terminating Program");
      System.exit(1);
      // e.printStackTrace();
    } catch (IOException e) {
      System.out.println("IOException occurred in FileProcessor class\n" + e);
      System.exit(1);
      // e.printStackTrace();
    }
  }

  /**
   * toString() method - helpful for debugging
   *
   * @return name of the class
   */
  @Override
  public String toString() {
    return "Driver Object";
  }
}
