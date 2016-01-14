/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gen_aufgabe2;

import static gen_aufgabe2.Gen_aufgabe2.getRandomInt;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Chilred-pc
 */
public class Genom {

    private int gencnt;
    private int genlen;
    private boolean best_protection;
    private Map map;
    public Gene[] geneList;

    public Genom(int gencnt, Map map) {
        this.gencnt = gencnt;
        this.map = map;
        this.genlen = this.map.getCountOfCities();
        this.geneList = new Gene[this.gencnt];

        for (int i = 0; i < gencnt; i++) {
            this.geneList[i] = new Gene(this.genlen, map);
            this.geneList[i].fillFitness();
//            this.geneList[i].updateFitness();
//            this.geneList[i].printGene();
        }
    }

    public void setProtection(boolean status) {
        this.best_protection = status;
    }

    public void mutate(double pm) {
//    static int gencnt = 100;
//    static int genlen = 36;
        
        updateFitness();
        Arrays.sort(geneList);
        double end = genlen * gencnt * pm;
        for (int i = 0; i < end; i++) {
            int geneId = getRandomInt(gencnt);
            if (this.best_protection && geneId == this.geneList.length-1) {
                continue;
            }
            int pos = getRandomInt(genlen);
            int pos2 = getRandomInt(genlen);
            geneList[geneId].mutate(pos, pos2);
        }
    }
    
    public void greedyCrossover(double pc){
//        updateFitness();
//        Arrays.sort(geneList);
        Gene[] newGeneList = new Gene[this.gencnt];
        int end = (int)(gencnt*pc);
        int count = 0;
        
        if(this.best_protection){
            updateFitness();
            Arrays.sort(geneList);
            Gene newGene = new Gene(this.genlen, map);
            System.arraycopy(this.geneList[geneList.length-1].value, 0, newGene.getValue(), 0, this.genlen);
            newGeneList[count] = newGene;
            count++;
        }       
        
        for (int i = 0; i < end; i++) {
            int gen1Id = getRandomInt(this.geneList.length);
            int gen2Id = getRandomInt(this.geneList.length);
            if (this.best_protection) {
                if (gen1Id == geneList.length-1 || gen2Id == geneList.length-1) {
                    continue;
                }
            }
            Gene gen1 = this.geneList[gen1Id];
            Gene gen2 = this.geneList[gen2Id];
            Gene child = gen1.greedyCrossover(gen2);
            child.updateFitness();
//            System.out.println(child.isValid());
            newGeneList[count] = child;
            count++;
        }
        
        end = this.gencnt - count;
        for (int i = 0; i < end; i++) {
            Gene newGene = new Gene(this.genlen, this.map);
            int genepos1 = getRandomInt(this.gencnt);
            System.arraycopy(this.geneList[genepos1].value, 0, newGene.value, 0, this.genlen);
//            newGene.printGene();
            newGeneList[count] = newGene;
            count++;
        }
        this.geneList = newGeneList;
    }
    
    public void replicate50Best(){
        updateFitness();
        Arrays.sort(geneList);
        Gene best1 = this.geneList[this.gencnt-1];
        Gene best2 = this.geneList[this.gencnt-2];
        
        Gene[] newGeneList = new Gene[this.gencnt];
        int count = 0;
        Gene newGene;
        double length = (int)(0.25 * this.gencnt);
        
        for (int i = 0; i < length; i++) {
            newGene = new Gene(this.genlen, this.map);
            System.arraycopy(best1.getValue(), 0, newGene.getValue(), 0, this.genlen);
            newGeneList[count] = newGene;
            newGeneList[count].updateFitness();
            count++;
        }
        
        for (int i = 0; i < length; i++) {
            newGene = new Gene(this.genlen, this.map);
            System.arraycopy(best2.getValue(), 0, newGene.value, 0, this.genlen);
            newGeneList[count] = newGene;
            newGeneList[count].updateFitness();
            count++;
        }
        
        double end = this.gencnt-count;
        for (int i = 0; i < end; i++) {
            newGene = new Gene(this.genlen, this.map);
            int genepos1 = getRandomInt(this.geneList.length);
            System.arraycopy(this.geneList[genepos1].value, 0, newGene.value, 0, this.genlen);
            newGeneList[count] = newGene;
            newGeneList[count].updateFitness();
            count++;
        }
        this.geneList = newGeneList;
    }
    
    public Gene getBestFitness(){
        this.updateFitness();
        Arrays.sort(geneList);
        return this.geneList[geneList.length-1];
    }
    
     public void updateFitness() {
        for (int i = 0; i < this.geneList.length; i++) {
            geneList[i].updateFitness();
        }
    }
     
    public boolean maxFitnessReached(){
        boolean reached = false;
        for (int i = 0; i < this.geneList.length; i++) {
//            geneList[i].updateFitness();
//            System.out.println(this.geneList[i].getFitness());
            if (this.geneList[i].getFitness() == this.genlen) {
                reached = true;
                break;
            }
        }
        return reached;
    }
}
