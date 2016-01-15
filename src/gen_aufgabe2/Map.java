/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gen_aufgabe2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 *
 * @author Chilred-pc
 */
public class Map {
    private double[][] distanceArray;
    private ArrayList<int[]> citys;
    private int gridsize = 0;
    
    public Map(String path, String filename) {
        citys = readFile(path, filename);
        distanceArray = distanceArray(citys);
//        printMap(citys,distanceArray );
    }
    
    private ArrayList<int[]> readFile(String path, String filename) {
        filename = path + filename;
        File file = new File(filename);
        BufferedReader br = null;
        ArrayList<int[]> coords = new ArrayList<int[]>();

        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader(filename));
            int y = 0;
            while ((sCurrentLine = br.readLine()) != null) {
                String[] line = sCurrentLine.split(" ");
                int x = 0;
                for (int i = 0; i < line.length; i++) {
                    if (!line[i].equals("")) {
                        if (!line[i].equals("0") && !line[i].equals("00")) {
                            int[] temp = new int[3];
                            temp[0] = Integer.valueOf(line[i]);
                            temp[1] = x;
                            temp[2] = y;
                            coords.add(temp);
                        }
                        x++;
                    }
                }
                y++;
            }
            gridsize = y;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return coords;
    }

    private double[][] distanceArray(ArrayList<int[]> citys) {
        double[][] distance = new double[citys.size()+1][citys.size()+1];
        for (int i = 0; i < citys.size(); i++) {
            distance[0][i] = -1;
            distance[i][0] = -1;
        }
                
        int[] city1, city2;
        for (int i = 0; i < citys.size(); i++) {
            city1 = citys.get(i);
            for (int j = 0; j < citys.size(); j++) {
                city2 = citys.get(j);
                int diff1 = city1[1] - city2[1];
                int diff2 = city1[2] - city2[2];
                double res = Math.sqrt((diff1 * diff1) + (diff2 * diff2));
                distance[city1[0]][city2[0]] = res;//round(res, 2);
            }
        }
        return distance;
    }
    
    private void printMap(ArrayList<int[]> citys, double[][] distanceArray){
//        for (int[] temp : citys) {
//            System.out.println(temp[1] + " " + temp[2] + " = " + temp[0]);
//        }
        for (int i = 0; i < distanceArray.length; i++) {
            for (int j = 0; j < distanceArray.length; j++) {
                System.out.print(distanceArray[i][j] + " ");

            }
            System.out.println("");
        }      
    }
    
    public int getGridSize(){
        return this.gridsize;
    }
    
    public int getCountOfCities(){
        return this.distanceArray.length-1;
    }
    
    public double getDistance(int pos, int end) {
        return this.distanceArray[pos][end];
    }

    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
