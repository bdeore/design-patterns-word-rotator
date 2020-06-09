package wordPlay.driver;

import java.util.Vector;
import wordPlay.metrics.MetricsCalculator;
import wordPlay.util.FileProcessor;

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
      System.out.println(args[0]);
      FileProcessor fp = new FileProcessor(args[0]);

      String temp = fp.poll();
      while (temp != null) {
        System.out.println(temp);
        temp = fp.poll();
      }

    } catch (Exception e) {
      System.out.println("exception occurred in FileProcessor class");
      e.printStackTrace();
    }

    Vector<Integer> vec = new Vector<Integer>();
    vec.add(7);
    vec.add(2);
    vec.add(6);
    vec.add(8);
    vec.add(6);
    vec.add(4);
    MetricsCalculator newTest = new MetricsCalculator();
    newTest.addLineStats(0, vec);

    vec = new Vector<Integer>();
    vec.add(5);
    vec.add(7);
    vec.add(2);
    vec.add(4);
    vec.add(10);
    vec.add(7);
    newTest.addLineStats(1, vec);
    newTest.printVector();
    System.out.println(newTest.getAvgWordsPerSentence());
    System.out.println(newTest.getAvgWordLength());
  }
}
