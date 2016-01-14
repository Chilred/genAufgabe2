/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gen_aufgabe2;

import static gen_aufgabe2.Gen_aufgabe2.getRandomInt;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author Chilred-pc
 */
public class Gene implements Comparable<Gene>{

    public int[] value;
    private boolean protectGene = false;
    private double fitness;
    private Map map;
    private int genlen;

    public Gene(int genlen, Map map) {
//        System.out.println(genlen);
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
            this.mutate(getRandomInt(this.genlen), getRandomInt(this.genlen));
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
//            int posCity1 = getPosOfCity(currentPos, this.value);
//            int posCity2 = getPosOfCity(currentPos, gene2.value);
            int posCity1 = this.getPosOfCity_new(currentPos);
            int posCity2 = gene2.getPosOfCity_new(currentPos);
            if (posCity1 != -1 && posCity2 != -1) {
                double getDistance1 = this.map.getDistance(currentPos+1, posCity1+1);
                double getDistance2 = this.map.getDistance(currentPos+1, posCity2+1);

                if (getDistance1 < getDistance2) {
                    if (!checkAlreadyUsed(child, posCity1)) {
                        child.value[i] = posCity1;
                    } else {
                        if (!checkAlreadyUsed(child, posCity2)) {
                            child.value[i] = posCity2;
                        } else {
                            child.value[i] = getUnusedPosRandom(child, i-1);
                        }
                    }
                } else {
                    if (!checkAlreadyUsed(child, posCity1)) {
                        child.value[i] = posCity2;
                    } else {
                        if (!checkAlreadyUsed(child, posCity1)) {
                            child.value[i] = posCity1;
                        } else {
                            child.value[i] = getUnusedPosRandom(child, i-1);
                        }
                    }
                }
            } else {
                if (posCity1 != -1) {
                    if (!checkAlreadyUsed(child, posCity1)) {
                        child.value[i] = posCity1;
                    } else {
                        child.value[i] = getUnusedPosRandom(child, i-1);
                    }
                } else if (posCity2 != -1) {
                    if (!checkAlreadyUsed(child, posCity2)) {
                        child.value[i] = posCity2;
                    } else {
                        child.value[i] = getUnusedPosRandom(child, i-1);
                    }
                } else {
                    child.value[i] = getUnusedPosRandom(child, i-1);
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
        return pos+1;
    }
    
    public int getPosOfCity_new(int value){
        for (int i=0 ; i<this.genlen-1 ; i++) {
			if (this.value[i] == value) {
				return this.value[i+1];
			}
		}
        return -1;
    }

    public boolean checkAlreadyUsed(Gene g, int usedCityPos) {
        //!checkAlreadyUsed(child, -1, posCity1))
        for (int i = 0; i < g.value.length; i++) {
            if (g.value[i] == usedCityPos) {
                return true;
            }
        }
        return false;
    }

    public int getUnusedPos(Gene g) {
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
//                System.out.println("i:" + i);
                return i;
            }
        }
        return -1;
    }
    
    public int getUnusedPosRandom(Gene g, int pos){
        ArrayList<Integer> possibleValue = new ArrayList<Integer>();
        for (int i = 0; i < this.value.length; i++) {
            boolean check = false;
            for (int j = 0; j < pos; j++) {
                if(i == g.value[j]){
                    check = true;
                    break;
                }
            }
            if (check == false) {
                possibleValue.add(i);
            }
        }
        int randomnumber = getRandomInt(possibleValue.size());
        int result = possibleValue.get(randomnumber);

        return result;
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

    @Override
    public int compareTo(Gene arg0) {   // zum sortieren nach Gene
        // TODOAuto-generated method stub
        if (this.getFitness() > arg0.getFitness()) {
            return 1;
        } else if (this.getFitness() < arg0.getFitness()) {
            return -1;
        }
        return 0;
    }
}
