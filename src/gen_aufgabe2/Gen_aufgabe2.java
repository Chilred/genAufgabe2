/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gen_aufgabe2;

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

    static double pc = 0;
    static double pcEnd = 0.9;
    static double pcStep = 0.05;
    static double pm = 0;
    static double pmEnd = 0.2;
    static double pmStep = 0.005;
    static int max_generation = 100; //1000
    static boolean protection = false;
    static int maxRun = 1; //50
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//
        Map map = new Map(path,filename);
//        Gene gene1 = new Gene(genlen, map);
//        gene1.fillFitness();
//        gene1.updateFitness();
//   
//        Gene gene2 = new Gene(genlen, map);
//        gene2.fillFitness();
//        gene2.updateFitness();   
//        
//        gene1.printGene();
//        gene2.printGene();
//        
//        gene1.greddyCrossover(gene2);
       
          Genom genome = new Genom(gencnt, map);
        
        long startTime = System.currentTimeMillis();
//        Part1
//        Map map = new Map(path,filename);
//        startFirstRun();
//        startFirstRunAll();
        
//        Part2
        
//        Time calcute
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;    
    }
    
    public static void startFirstRun(){
        String result = "";
         Run run = new Run(maxRun, pc, pm, gencnt, genlen, max_generation, protection);
         result = result + run.getAverageGeneration();
//         System.out.println(run.getAverGenDouble());
    }
    
     public static void startFirstRunAll(){
         String result = "";
         for (double i = pc; i < pcEnd; i += pcStep) {
             for (double j = pm; j < pmEnd; j += pmStep) {
                 Run run = new Run(maxRun, i, j, gencnt, genlen, max_generation, protection);
                 result = result + run.getAverageGeneration();
             }
             result = result + "\r\n";
         }
     }
    
    public static void approximationChecker(int maxRun, double pc, double pm, int gencnt, int genlen, int max_generation, boolean protection){
        Run run = new Run(maxRun, pc, pm, gencnt, genlen, max_generation, protection);
        String result = run.getAverageGeneration();
        
        TSPApproximationChecker tspAC = new TSPApproximationChecker();
        
    }
    
     public static double getRandomDouble() {
        return ThreadLocalRandom.current().nextDouble(1);
    }

    public static int getRandomInt(int value) {
        return ThreadLocalRandom.current().nextInt(value);
    }
}
