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
public class RunApproximate {

    private Map map;
    private int maxRun;
    private double pc;
    private double pm;
    private double averageGeneration;
    private double fitMax;
    private String replication;
    private int gencnt;
    private boolean protection;
    private int maxGeneration;
    private String bestFitness;

    public RunApproximate(int maxRun, double pc, double pm, int gencnt, int maxGeneration, boolean protection, Map map, String replication) {
        this.maxRun = maxRun;
        this.pc = pc;
        this.pm = pm;
        this.map = map;
        this.fitMax = getFitnessFromMap();
        this.replication = replication;
        this.protection = protection;
        this.maxGeneration = maxGeneration;
        this.bestFitness = "-1";
        this.gencnt = gencnt;
    }

    public void distanceApproximated() {
            Genom genome = new Genom(gencnt, map);
            genome.setProtection(protection);
            for (int generationCounter = 0; generationCounter < maxGeneration; generationCounter++) {
                genome.greedyCrossover(this.pc);
                genome.mutate(pm);
                if (replication.equals("replicate50Best")) {
                    genome.replicate50Best();
                }
            }
        bestFitness = genome.getBestFitness();
    }
    
    public String getBestFitness(){
        return this.bestFitness;
    }

    public double getFitnessFromMap() {
        double a = map.getGridSize() * map.getGridSize();
        double n = this.map.getCountOfCities();
        return (0.765 * Math.sqrt((n + 1) * a));
    }
}
