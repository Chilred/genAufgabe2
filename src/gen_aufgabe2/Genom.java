/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gen_aufgabe2;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Chilred-pc
 */

public class Genom {

    int gencnt;
    int genlen;
    double initrate;
    ArrayList<Gene> geneList;
    ArrayList<Gene> best_gene, bad_gene;
    boolean best_protection;
    
    private int protectBestInMutate = 0;
    private int protectBestInCrossover = 0;

    public Genom(int gencnt, int genlen, double initrate) {
        this.gencnt = gencnt;
        this.genlen = genlen;
        this.initrate = initrate;

        this.geneList = new ArrayList<Gene>();
        // Gene erstellen
        for (int i = 0; i < gencnt; i++) {
            this.geneList.add(new Gene(this.genlen));
        }
        double end = genlen*gencnt*initrate;
        for(int j = 0; j < end; j++){
            int pos = Run.getRandomInt(this.genlen);
            this.geneList.get(Run.getRandomInt(this.gencnt)).set_gene_pos(pos, 1);
        }
    }

    public void mutate(double pm) {
//        if(best_protection){
//            Collections.sort(geneList);
//        }
//        double end = genlen * gencnt * pm;
//        for (int i = 0; i < end; i++) {
//            int geneId = Run.getRandomInt(gencnt);
//            if (this.best_protection) {
//                if (geneId == geneList.size() - 1) {
//                  //  protectBestInMutate++;
//                  //  System.out.println("mutate " +protectBestInMutate);
//                    continue;
//                }
//            }
//            int pos = Run.getRandomInt(genlen);
//            this.geneList.get(geneId).set_gene_pos(pos, -1);
//        }
    }

    public void cross_two_genes(double pc) {
//        int end = (int)(gencnt*pc);
//        for (int i = 0; i < end; i++) {
//            int splitpos = Run.getRandomInt(genlen);
//            int gen1Id = Run.getRandomInt(this.geneList.size());
//            int gen2Id = Run.getRandomInt(this.geneList.size());
//            if (this.best_protection) {
//                if (gen1Id == 0 || gen2Id == 0) {
//                    protectBestInCrossover++;
//                 //   System.out.println("cross " +protectBestInCrossover);
//                    continue;
//                }
//            }
//            Gene gen1 = this.geneList.get(gen1Id);
//            Gene gen2 = this.geneList.get(gen2Id);
//           // gen1.cross_two_genes(gen2, splitpos);
//            gen1.cross_two_genes_mod(gen2, splitpos);
//        }
    }
    
//     public void transposition(double pc) {
//        int end = (int) (gencnt * pc);
//        for (int i = 0; i < end; i++) {
//            int splitpos = Run.getRandomInt(genlen);
//            int splitLength = Run.getRandomInt(genlen-splitpos);//splitpos + Run.getRandomInt((genlen - splitpos) + 1);
//            int gen1Id = Run.getRandomInt(this.geneList.size());
//            int gen2Id = Run.getRandomInt(this.geneList.size());
//            if (this.best_protection) {
//                if (gen1Id == 0 || gen2Id == 0) {
//                    continue;
//                }
//            }
//            Gene gen1 = this.geneList.get(gen1Id);
//            Gene gen2 = this.geneList.get(gen2Id);
//            gen1.transposition_mod(gen2, splitpos, splitLength);
//            //  gen1.transposition(gen2, splitpos, endsplitpos);
//        }
//     }

//    public void replication10Best() {
//        // alte Methode vor dem Profiling
//        updateFitness();
//        Collections.sort(geneList);
//        this.best_gene = new ArrayList<Gene>();
//        for (int i = 0; i < gencnt * 0.1; i++) {
//            best_gene.add(geneList.get(geneList.size() - i - 1));
//        }
//        this.geneList = new ArrayList<Gene>();
//        for (int i = 0; i < best_gene.size(); i++) {
//            for (int j = 0; j < 10; j++) {
//                Gene newGene = new Gene(genlen);
//                for (int y = 0; y < genlen; y++) {
//                    newGene.set_gene_pos(y, best_gene.get(i).get_gene_pos(y));
//                }
//                this.geneList.add(newGene);
//            }
//        }
//    }
//    public void replication10Best_mod() {
//        // neue Methode nach dem Profiling
////        updateFitness();
////        Collections.sort(geneList);
////        this.best_gene = new ArrayList<Gene>();
////        for (int i = 0; i < gencnt * 0.1; i++) {
////            best_gene.add(geneList.get(geneList.size() - i - 1));
////        }
////        this.geneList = new ArrayList<Gene>();
////        for (int i = 0; i < best_gene.size(); i++) {
////            Gene tempGene = best_gene.get(i);
////            for (int j = 0; j < 10; j++) {
////                Gene newGene = new Gene(genlen);             
////                System.arraycopy(tempGene.value, 0, newGene.value, 0, this.genlen);
////                this.geneList.add(newGene);
////            }
////        }
//    }
//
//    public void replicateRankBased(Double[] rankList) {
//        // alte Methode vor dem Profiling
////        updateFitness();
////        Collections.sort(geneList);
////        ArrayList<Gene> newGeneList = new ArrayList<Gene>();
////        for (int i = 0; i < gencnt; i++) {
////            double ranNumber = Run.getRandomDouble();
////            for (int j = rankList.length - 1 - 1; j >= 0; j--) {
////                if (rankList[j] < ranNumber) {
////                    Gene newGene = new Gene(genlen);
////                    for (int y = 0; y < genlen; y++) { //copy array
////                        newGene.set_gene_pos(y, geneList.get(j + 1).get_gene_pos(y));
////                    }
////                    newGeneList.add(newGene);
////                    break;
////                }
////            }
////        }
////        this.geneList = newGeneList;
//    }
//    
//    public void replicateRankBased_mod(Double[] rankList) {
//        // neue Methode nach dem Profiling
////        updateFitness();
////        Collections.sort(geneList);
////        double ranNumber;
////        ArrayList<Gene> newGeneList = new ArrayList<Gene>();
////        for (int i = 0; i < gencnt; i++) {
////            ranNumber = Run.getRandomDouble();
////            for (int j = rankList.length - 1 - 1; j >= 0; j--) {
////                if (rankList[j] < ranNumber) {
////                    Gene newGene = new Gene(genlen);
////                    System.arraycopy(geneList.get(j+1).value, 0, newGene.value, 0, this.genlen);
////                    newGeneList.add(newGene);
////                    break;
////                }
////            }
////        }
////        this.geneList = newGeneList;
////    }
//
////    public Gene getBestFitness() {
////        updateFitness();
////        Collections.sort(geneList);
////        return geneList.get(geneList.size() - 1);
////    }
//
////    public Gene getLowestFitness() {
////        return geneList.get(0);
////    }
//
////    public int getAverage() {
////        int average = 0;
////        for (int i = 0; i < geneList.size(); i++) {
////            average += geneList.get(i).getFitness();
////        }
////        average = average / geneList.size();
////        return average;
////    }
//
//    public boolean maxFitnessReached(int len) {
////        boolean reached = false;
////        for (int i = 0; i < this.geneList.size(); i++) {
////            geneList.get(i).updateFitness();
////            if (this.geneList.get(i).getFitness() == len) {
////                reached = true;
////                break;
////            }
////        }
////        return reached;
//    }
//
//    public void setProtection(boolean status) {
////        this.best_protection = status;
//    }
//    
//    public void updateFitness() {
////        for (int i = 0; i < this.geneList.size(); i++) {
////            geneList.get(i).updateFitness();
////        }
//    }
//    
//    public void setMutate(){
////        protectBestInMutate = 0;
//    }
//    public void setCross(){
////        protectBestInCrossover = 0;
//    }
}

