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
    private Gene[] geneList;

    public Genom(int gencnt, Map map) {
        this.gencnt = gencnt;
        this.map = map;
        this.genlen = this.map.getCountOfCities();

        for (int i = 0; i < gencnt; i++) {
            this.geneList[i] = new Gene(this.gencnt, map);
            this.geneList[i].fillFitness();
            this.geneList[i].updateFitness();
        }
    }

    public void setProtection(boolean status) {
        this.best_protection = status;
    }

    public void mutate(double pm) {
        Arrays.sort(geneList);
        double end = genlen * gencnt * pm;
        for (int i = 0; i < end; i++) {
            int geneId = getRandomInt(genlen);
            if (this.best_protection && geneId == this.geneList.length-1) {
                continue;
            }
            int pos = getRandomInt(genlen);
            geneList[geneId].mutate(geneId, pos);
        }
    }
    
    public void greedyCrossover(double pc){
        //Arrays.sort(geneList);
        Gene[] newGeneList = new Gene[this.gencnt];
        int end = (int)(gencnt*pc);
        int count = 0;
        
        if(this.best_protection){
            Gene newGene = new Gene(this.genlen, map);
            System.arraycopy(this.geneList[0], 0, newGene.getValue(), 0, this.genlen);
            newGeneList[count] = newGene;
            count++;
        }       
        
        for (int i = 0; i < end; i++) {
            int gen1Id = getRandomInt(this.geneList.length);
            int gen2Id = getRandomInt(this.geneList.length);
            if (this.best_protection) {
                if (gen1Id == 0 || gen2Id == 0) {
                    continue;
                }
            }
            Gene gen1 = this.geneList[gen1Id];
            Gene gen2 = this.geneList[gen2Id];
            newGeneList[count] = gen1.greddyCrossover(gen2);
            count++;
        }
        
        end = this.gencnt - count;
        for (int i = 0; i < end; i++) {
            Gene newGene = new Gene(this.genlen, this.map);
            int genepos1 = getRandomInt(this.geneList.length);
            System.arraycopy(this.geneList[genepos1], 0, newGene.getValue(), 0, this.genlen);
            newGeneList[count] = newGene;
        }
        this.geneList = newGeneList;
    }
    
    public void replicate50Best(){
        Arrays.sort(geneList);
        Gene best1 = this.geneList[this.gencnt-1];
        Gene best2 = this.geneList[this.gencnt-2];
        
        Gene[] newGeneList = new Gene[this.gencnt];
        int count = 0;
        Gene newGene;
        double length = (0.25 * this.gencnt);
        
        for (int i = 0; i < length; i++) {
            newGene = new Gene(this.genlen, this.map);
            System.arraycopy(best1.getValue(), 0, newGene.getValue(), 0, this.genlen);
            newGeneList[count] = newGene;
            count++;
        }
        
        for (int i = 0; i < length; i++) {
            newGene = new Gene(this.genlen, this.map);
            System.arraycopy(best2.getValue(), 0, newGene.getValue(), 0, this.genlen);
            newGeneList[count] = newGene;
            count++;
        }
        
        double end = this.gencnt-count;
        for (int i = 0; i < end; i++) {
            newGene = new Gene(this.genlen, this.map);
            int genepos1 = getRandomInt(this.geneList.length);
            System.arraycopy(this.geneList[genepos1], 0, newGene.getValue(), 0, this.genlen);
            newGeneList[count] = newGene;
            count++;
        }
        
        this.geneList = newGeneList;
    }
    
    public Gene getBestFitness(){
        this.updateFitness();
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
            geneList[i].updateFitness();
            if (this.geneList[i].getFitness() == this.genlen) {
                reached = true;
                break;
            }
        }
        return reached;
    }
}
