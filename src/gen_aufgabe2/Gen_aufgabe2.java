/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gen_aufgabe2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Chilred-pc
 */
public class Gen_aufgabe2 {

    static String path = "src/map/";
//    static String filename = "05-map-10x10-36border.txt";
//    static String filename = "05-map-10x10-36-dist42.64.txt";
    static String filename = "06-map-100x100-200.txt";

    static int gencnt = 100;
    static int genlen = 36;

    static double pc = 0.9;
    static double pcEnd = 0.9;
    static double pcStep = 0.05;
    static double pm = 0.000; // 0
    static double pmEnd = 0.2;
    static double pmStep = 0.005;
    static int max_generation = 2000; //2000
    static boolean protection = true;
    static int maxRun = 100; //50
    static double fitMax = 36;
    static String replication = "NONE";
//    static String replication = "replicate50Best";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Map map = new Map(path, filename);

//        Part1
//        startFirstRun(map);
//        startFirstRunAll(map);
//        Part2
//        Time calcute
        double bestPc = 0.85;
        double bestPm = 0.01;
//        
       approximationChecker(map, bestPc, bestPm);

//        long endTime = System.currentTimeMillis();
//        long totalTime = endTime - startTime;
//        System.out.println("totalTime: " + totalTime + " ms");
    }

    public static void startFirstRun(Map map) {
        String result = "";
        Run run = new Run(maxRun, pc, pm, gencnt, genlen, max_generation, protection, map, fitMax, replication);
        result = result + run.getAverageGeneration();
         System.out.println(run.getAverGenDouble());
    }

    public static void startFirstRunAll(Map map) {
        long startTime = System.currentTimeMillis();
        String result = "";
        for (double i = pc; i <= pcEnd; i += pcStep) {
            for (double j = pm; j < pmEnd; j += pmStep) {
                Run run = new Run(maxRun, i, j, gencnt, genlen, max_generation, protection, map, fitMax, replication);
                result = result + run.getAverageGeneration();
            }
            result = result + "\r\n";
        }
               
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        result = result + "\r\n" + "# Gesamtzeit" + totalTime + " ms";
        writeInFile(result, "5Run_last.txt");
    }

    public static void approximationChecker(Map map, double pc, double pm) {
        long startTime = System.currentTimeMillis();

        RunApproximate ra = new RunApproximate(maxRun, pc, pm, gencnt, max_generation, protection, map, replication);
        Double result[] = new Double[100];
        for (int i = 0; i < maxRun; i++) {
            String currentResult = "";
            ra.distanceApproximated();
            currentResult = i + " " + ra.getBestFitness();
            result[i] = ra.getBestFitness();
            System.out.println(currentResult);
        }
        Arrays.sort(result);
        String sResult = "";
        for (int i = 0; i < result.length; i++) {
            sResult = sResult + i + " " + result[i] + "\r\n";
        }
        
        
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        sResult = sResult + "\r\n" + "# Gesamtzeit " + totalTime + " ms";
        writeInFile(sResult, "approximationChecker_50.txt");
    }

    public static double getRandomDouble() {
        return ThreadLocalRandom.current().nextDouble(1);
    }

    public static int getRandomInt(int value) {
        return ThreadLocalRandom.current().nextInt(value);
    }

    public static void writeInFile(String result, String filename) {
        try {
            String content = result;

            File file = new File("C:/Users/Chilred-pc/workspace/gen_aufgabe2/src/" + filename);
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();

            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
