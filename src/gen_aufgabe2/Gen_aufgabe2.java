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
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Chilred-pc
 */
public class Gen_aufgabe2 {

    static String path = "src/map/";
    static String filename = "05-map-10x10-36border.txt";
    static int gencnt = 100;
    static int genlen = 36;

    static double pc = 0.95;
    static double pcEnd = 0.9;
    static double pcStep = 0.05;
    static double pm = 0.005; // 0
    static double pmEnd = 0.2;
    static double pmStep = 0.005;
    static int max_generation = 2000; //2000
    static boolean protection = true;
    static int maxRun = 10; //50
    static int fitMax = 36;
//    static String replication = "NONE";
    static String replication = "replicate50Best";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Map map = new Map(path, filename);
//        Gene gene1 = new Gene(36, map);
//        gene1.fillFitness();
//        gene1.updateFitness();
//        System.out.println(gene1);
//        Gene gene2 = new Gene(36, map);
//        gene2.fillFitness();
//        gene2.updateFitness();
//        Gene child = gene1.greedyCrossover(gene2);
//        child.updateFitness();
//        System.out.println(child);
//
//        int pos = getRandomInt(genlen);
//        int pos2 = getRandomInt(genlen);
//        System.out.println("pos1 " + pos);
//        System.out.println("pos2 " + pos2);
//        child.mutate(pos, pos2);
//        child.updateFitness();
//        System.out.println(child);
//        
//
//        
//        Genom genome = new Genom(gencnt, map);
//        genome.setProtection(protection);
//        genome.greedyCrossover(pc);
//        if (genome.maxFitnessReached(fitMax)) {System.out.println("1");}
//        genome.mutate(pm);
//        if (genome.maxFitnessReached(fitMax)) {System.out.println("2");}
//        genome.replicate50Best();
//        if (genome.maxFitnessReached(fitMax)) {System.out.println("3");}
//        
//        for (int i = 0; i < genome.geneList.length; i++) {
//            System.out.println(genome.geneList[i]);
//        }

//        Gene g = new Gene(genlen, map);
//        Part1
        startFirstRun(map);
//        startFirstRunAll(map);
//        Part2
//        Time calcute
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("totalTime: " + totalTime + " ms");
    }

    public static void startFirstRun(Map map) {
        String result = "";
        Run run = new Run(maxRun, pc, pm, gencnt, genlen, max_generation, protection, map, fitMax, replication);
        result = result + run.getAverageGeneration();
//         System.out.println(run.getAverGenDouble());
    }

    public static void startFirstRunAll(Map map) {
        String result = "";
        for (double i = pc; i < pcEnd; i += pcStep) {
            for (double j = pm; j < pmEnd; j += pmStep) {
                Run run = new Run(maxRun, i, j, gencnt, genlen, max_generation, protection, map, fitMax, replication);
                result = result + run.getAverageGeneration();
//                 System.out.println(result);
            }
            result = result + "\r\n";
        }
//        System.out.println(result);
        writeInFile(result, "FirstRun.txt");
    }

//    public static void approximationChecker(int maxRun, double pc, double pm, int gencnt, int genlen, int max_generation, boolean protection){
//        Run run = new Run(maxRun, pc, pm, gencnt, genlen, max_generation, protection, map);
//        String result = run.getAverageGeneration();
//        
//        TSPApproximationChecker tspAC = new TSPApproximationChecker();
//        
//    }
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
