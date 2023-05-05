package test;

import data_structures.KDTree;
import data_structures.PRQuadTree;
import data_structures.Point;
import utils.SamplePoints;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class Test {

    static public void test(){

        int[] M = {200, 500, 1000, 10000, 30000, 50000, 70000, 100000};
        Random random = new Random();
        KDTree[] kdTrees = new KDTree[M.length];
        PRQuadTree[] prQuadTrees = new PRQuadTree[M.length];

        int N = (int) Math.pow(2, 20);

        int[] kdExistingKeys = new int[M.length];
        int[] kdNonExistingKeys = new int[M.length];

        int[] prExisitngKeys = new int[M.length];
        int[] prNonExistingKeys = new int[M.length];

        for(int i=0;i<M.length;++i) {

            ArrayList<Point> points = SamplePoints.generateSamplePoints(N,M[i]);
            kdTrees[i] = new KDTree();
            prQuadTrees[i] = new PRQuadTree();

            int numberOfTests = 100;

            ArrayList<Point> existingKeys = SamplePoints.getRandomExistingPoints(points,numberOfTests);

            for(Point var: points){
                kdTrees[i].insert(var.getX(), var.getY());
                prQuadTrees[i].insert(var);
            }

            for(int j=0;j<100;++j){

                if(kdTrees[i].search(existingKeys.get(j).getX(),existingKeys.get(j).getY()) != null){
                    kdExistingKeys[i] += kdTrees[i].search(existingKeys.get(j).getX(),existingKeys.get(j).getY()).getDepth();
                }

                if(prQuadTrees[i].search(existingKeys.get(j)) != null){
                    prExisitngKeys[i] += prQuadTrees[i].search(existingKeys.get(j)).depth;
                }

                int x;
                int y;

                do{
                    x = random.nextInt(N);
                    y = random.nextInt(N);

                }while(kdTrees[i].search(x,y).getNode() != null);
                kdNonExistingKeys[i] += kdTrees[i].search(x,y).getDepth();
                prNonExistingKeys[i] += prQuadTrees[i].search(new Point(x,y)).depth;

            }
        }
        
        /* PRINTING RESULTS IN A READABLE FORMAT// */
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.println("=================================================================================================");
        System.out.println("Project 2 Search for 2-dimensional points");
        System.out.println("=================================================================================================");
        System.out.println("Tests for KD tree");
        System.out.println("\t Existing Keys \t \t \t \t \t  Non-Existing Keys");
        
        for(int z=0;z<M.length;++z) {
            if (z < 2)
                System.out.println("KD: " + df.format((float) kdExistingKeys[z] / 100.00) + "|" + "\t" + "Search[" + M[z] + "]" + "\t\t\t\t" + df.format((float) kdNonExistingKeys[z] / 100.00) + "| Search[" + M[z] + "]");
            else
                System.out.println("KD: " + df.format((float) kdExistingKeys[z] / 100.00) + "|" + "\t" + "Search[" + M[z] + "]" + "\t\t\t" + df.format((float) kdNonExistingKeys[z] / 100.00) + "| Search[" + M[z] + "]");
        }
        
        System.out.println("=================================================================================================");
        System.out.println("Tests for PR Quad tree");
        System.out.println("\t Existing Keys \t \t \t \t \t  Non-Existing Keys");
        
        for(int h=0;h<M.length;++h) {
            if (h < 2)
                System.out.println("PR: " + df.format((float) prExisitngKeys[h] / 100.00) + "|" + "\t" + "Search[" + M[h] + "]" + "\t\t\t\t" + df.format((float) prNonExistingKeys[h] / 100.00) + "| Search[" + M[h] + "]");
            else
                System.out.println("PR: " + df.format((float) prExisitngKeys[h] / 100.00) + "|" + "\t" + "Search[" + M[h] + "]" + "\t\t\t" + df.format((float) prNonExistingKeys[h] / 100.00) + "| Search[" + M[h] + "]");
        }
    }

}
