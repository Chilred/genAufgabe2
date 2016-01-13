/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gen_aufgabe2;

import static gen_aufgabe2.Gen_aufgabe2.getRandomInt;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author Chilred-pc
 */
public class Gene {

    private int[] value;
    private boolean protectGene = false;
    private double fitness;
    private Map map;
    private int genlen;

    public Gene(int genlen, Map map) {
        this.genlen = genlen;
        value = new int[genlen];
        this.map = map;
        for (int i = 0; i < value.length; i++) {
            value[i] = i;
        }
    }

    public void setProtectGene(Boolean status) {
        this.protectGene = status;
    }

    public boolean getProtectGene() {
        return this.protectGene;
    }

    public double getFitness() {
        return this.fitness;
    }

    public void updateFitness() {
        double fit = 0;
        for (int i = 1; i < this.genlen; i++) {
            fit = fit + this.map.getDistance(this.value[i - 1] + 1, this.value[i] + 1);
        }
        fit = fit + this.map.getDistance(this.value[this.value.length - 1] + 1, this.value[0] + 1);
        this.fitness = fit;
    }

    public void fillFitness() {
        for (int i = 0; i < value.length; i++) {
            this.mutate(getRandomInt(this.value.length), getRandomInt(this.value.length));
        }
    }
    
    //Mutate
    public void mutate(int pos, int pos2) {
        int temp = this.value[pos];
        this.value[pos] = this.value[pos2];
        this.value[pos2] = temp;
    }

    public void greedyCrossover_theoretisch(Gene gene2) {
        Gene child = new Gene(this.value.length, this.map);
        child.value[0] = this.value[0];

        this.printGene();
        gene2.printGene();

        int posCity1 = getPosOfCity(child.value[0], this.value);
        int posCity2 = getPosOfCity(child.value[0], gene2.value);
        System.out.println(posCity1);
        System.out.println(posCity2);

        int childPos = getPosOfCity(child.value[0], child.value);

        double getDistance1 = this.map.getDistance(childPos, posCity1);
        double getDistance2 = this.map.getDistance(childPos, posCity2);

        System.out.println(getDistance1);
        System.out.println(getDistance2);
    }

    public Gene greddyCrossover(Gene gene2) {
        Gene child = new Gene(this.value.length, this.map);
        child.value[0] = this.value[0];

        for (int i = 1; i < this.value.length; i++) {
            int currentPos = child.value[i-1];
            int posCity1 = getPosOfCity(currentPos, this.value);
            int posCity2 = getPosOfCity(currentPos, gene2.value); 

            if (posCity1 != -1 && posCity2 != -1) {
                double getDistance1 = this.map.getDistance(currentPos+1, posCity1+1);
                double getDistance2 = this.map.getDistance(currentPos+1, posCity2+1);

                if (getDistance1 < getDistance2) {
                    if (!checkAlreadyUsed(child, i-1, posCity1)) {
                        child.value[i] = gene2.value[posCity2];
                    } else {
                        if (!checkAlreadyUsed(child, i-1, posCity2)) {
                            child.value[i] = this.value[posCity1];
                        } else {
                            child.value[i] = getUnusedPos(child, i);
                        }
                    }
                } else {
                    if (!checkAlreadyUsed(child, i-1, posCity1)) {
                        child.value[i] = this.value[posCity1];
                    } else {
                        if (!checkAlreadyUsed(child, i-1, posCity1)) {
                            child.value[i] = gene2.value[posCity1];
                        } else {
                            child.value[i] = getUnusedPos(child, i);
                        }
                    }
                }
            } else {
                if (posCity1 == -1) {
                    if (!checkAlreadyUsed(child, -1, posCity1)) {
                        child.value[i] = posCity1;
                    } else {
                        child.value[i] = getUnusedPos(child, i);
                    }
                } else if (posCity1 == -1) {
                    if (!checkAlreadyUsed(child, -1, posCity2)) {
                        child.value[i] = posCity2;
                    } else {
                        child.value[i] = getUnusedPos(child, i);
                    }
                } else {
                    child.value[i] = getUnusedPos(child, i);
                }
            }
        }

//        for (int i = 0; i < child.value.length; i++) {
//            System.out.print(this.value[i]+ " ");
//            System.out.print(gene2.value[i]+ " ");
//            System.out.print(child.value[i] + " ");
//        }
        return child;
    }

    public int getPosOfCity(int value, int[] cityValue) {
        int pos = -1;
        for (int i = 0; i < cityValue.length; i++) {
            if (cityValue[i] == value) {
                pos = i;
                break;
            }
        }
        return pos;
    }

    public boolean checkAlreadyUsed(Gene g, int pos, int usedCityPos) {
        boolean check;
        for (int i = 0; i < g.value.length; i++) {
            if (g.value[i] == usedCityPos) {
                return true;
            }
        }
        return false;
    }

    public int getUnusedPos(Gene g, int pos) {
        // this.value [0,1,2,3,4]
        //child.value [2,1,3,0]
        for (int i = 0; i < this.value.length; i++) {
            boolean check = false;
            for (int j = 0; j < g.value.length; j++) {
                if (i == g.value[j]) {
                    check = true;
                    break;
                }
            }
            if (check == false) {
                return i;
            }
        }
        return -1;
    }
    
    public int[] getValue(){
        return this.value;
    }

    public void printGene() {
        for (int i = 0; i < this.value.length; i++) {
            System.out.print(this.value[i] + " ");
        }
        System.out.println("");
    }
//
//    public String toString() {
//        String genString = "";
////		to show all genes
////		for (int i = 0; i < value.length;i++){ 
////			genString = genString + value[i];
////		}
//        //genString = genString + " Fitness= " + getFitness();
//        genString = genString + getFitness();
//        return genString;
//    }
//

//
//    public int get_gene_pos(int pos) {
//        return this.value[pos];
//    }
//
//    public void cross_two_genes(Gene gen2, int position) {
//        // alte Methode vor dem Profiling
//        int temp;
//        for (int i = position; i < this.value.length; i++) {
//            temp = this.get_gene_pos(i);
//            set_gene_pos(i, gen2.get_gene_pos(i));
//            gen2.set_gene_pos(i, temp);
//        }
//    }
//
//    public void cross_two_genes_mod(Gene gen2, int position) {
//        // neue Methode nach dem Profiling
//        int[] tempValue = new int[this.value.length];
//        System.arraycopy(gen2.value, position, tempValue, position, value.length-position);
//        System.arraycopy(this.value, position, gen2.value, position, value.length-position);
//        System.arraycopy(tempValue, position, this.value, position, value.length-position);
//    }
//
//    public void transposition(Gene gen2, int posStart, int posEnd) {
//        // alte Methode vor dem Profiling
//        for (int i = posStart; i < posEnd; i++) {
//            gen2.set_gene_pos(i, this.get_gene_pos(i));
//        }
//    }
//    
//    public void transposition_mod(Gene gen2, int posStart, int splitLength) {
//        // neue Methode nach dem Profiling
//        System.arraycopy(this.value, posStart, gen2.value, posStart, splitLength);
//    }
//
//    @Override
//    public int compareTo(Gene arg0) {   // zum sortieren nach Gene
//        // TODOAuto-generated method stub
//        if (this.getFitness() > arg0.getFitness()) {
//            return 1;
//        } else if (this.getFitness() < arg0.getFitness()) {
//            return -1;
//        }
//        return 0;
//    }
}
