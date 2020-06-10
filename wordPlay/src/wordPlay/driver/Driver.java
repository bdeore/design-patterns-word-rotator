package wordPlay.driver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;
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
    System.out.println("Hello World! Lets get started with the assignment");

    try {
      FileProcessor fp = new FileProcessor(args[0]);
      MetricsCalculator metrics = new MetricsCalculator();
      Results rs = new Results(metrics);

      WordRotator wr = new WordRotator(fp, metrics, rs);
      wr.processFile();
      metrics.printVector();

      rs.write();
      rs.write("output.txt", "metrics.txt");

    } catch (InvalidPathException | FileNotFoundException | SecurityException e) {
      System.out.println(e);
      System.out.println("(Class Driver) Terminating Program");
      e.printStackTrace();
      System.exit(1);
    } catch (IOException e) {
      System.out.println("IOException occurred in FileProcessor class\n" + e);
      e.printStackTrace();
      System.exit(1);
    }
  }
}
