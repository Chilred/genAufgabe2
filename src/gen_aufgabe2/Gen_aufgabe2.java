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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String path = "src/map/";
        String filename = "05-map-10x10-36border.txt";
        int gencnt = 100;
        int genlen = 36;

        double pc = 0;
        double pcEnd = 0.9;
        double pcStep = 0.05;
        double pm = 0;
        double pmEnd = 0.2;
        double pmStep = 0.005;
        int max_generation = 100; //1000
        boolean protection = false;
        int maxRun = 10; //50
        
        Map map = new Map(path,filename);
//
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
        
        long startTime = System.currentTimeMillis();
        String result = "";
        for (double i = pc; i < pcEnd; i += pcStep) {
            for (double j = pm; j < pmEnd; j += pmStep) {
                Run run = new Run(maxRun, i, j, gencnt, genlen, max_generation, protection);
                result = result + run.getAverageGeneration();
            }
            result = result + "\r\n";
        }
         long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;               
    }
    
     public static double getRandomDouble() {
        return ThreadLocalRandom.current().nextDouble(1);
    }

    public static int getRandomInt(int value) {
        return ThreadLocalRandom.current().nextInt(value);
    }
}
