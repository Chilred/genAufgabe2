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
        double initrate = 0.5;
        
        
        Map map = new Map(path,filename);

        Gene gene1 = new Gene(genlen, map);
        gene1.fillFitness();
        gene1.updateFitness();
   
        Gene gene2 = new Gene(genlen, map);
        gene2.fillFitness();
        gene2.updateFitness();   
        
        gene1.printGene();
        gene2.printGene();
        
        gene1.greddyCrossover(gene2);
    }
    
     public static double getRandomDouble() {
        return ThreadLocalRandom.current().nextDouble(1);
    }

    public static int getRandomInt(int value) {
        return ThreadLocalRandom.current().nextInt(value);
    }
}
