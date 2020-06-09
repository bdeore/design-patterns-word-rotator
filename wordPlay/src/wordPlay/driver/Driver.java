package wordPlay.driver;

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
      Results rs = new Results();

      WordRotator wr = new WordRotator(fp, metrics, rs);
      wr.processFile();
      metrics.printVector();

      for (String s : rs.getResultBuffer()) {
        System.out.println(s);
      }

      System.out.println(metrics.getAvgWordsPerSentence());
      System.out.println(metrics.getAvgWordLength());

    } catch (Exception e) {
      System.out.println("exception occurred in FileProcessor class");
      e.printStackTrace();
    }
  }
}
