/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gen_aufgabe2;

/**
 *
 * @author Chilred-pc
 */
public class Run {
    private Map map;
    private int maxRun;
    private double pc;
    private double pm;
    private double averageGeneration;   
    
    public Run(int maxRun, double pc, double pm, int gencnt, int genlen, int max_generation, boolean protection){
        this.maxRun = maxRun;
        this.pc = pc;
        this.pm = pm;

        int totalGeneration = 0;
        for (int running = 0; running < maxRun; running++) {
            Genom genome = new Genom(gencnt, map);
            genome.setProtection(protection);

            for (int generationCounter = 0; generationCounter < max_generation; generationCounter++) {
                genome.greedyCrossover(this.pc);
                if (genome.maxFitnessReached()) {break;}
                
                genome.mutate(pm);
                if (genome.maxFitnessReached()) {break;}
                
                genome.replicate50Best();
                
                totalGeneration++;
            }
        }
        this.averageGeneration = totalGeneration/this.maxRun;
    }
    
    public String getAverageGeneration(){
        System.out.println(pc + " " + pm + " " + averageGeneration);
        return (this.pc + " " + this.pm + " " + this.averageGeneration + "\r\n");
    }
    
    public double getAverGenDouble(){
        return this.averageGeneration;
    }
}
