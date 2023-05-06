package test;

import data_structures.KDTree;
import data_structures.PRQuadTree;
import data_structures.Point;
import utils.PrintStatistics;
import utils.SamplePoints;

import java.util.ArrayList;
import java.util.Random;

public class Test {

    static public void test(){

        int[] M = {200, 500, 1000, 10000, 30000, 50000, 70000, 100000};
        Random random = new Random();
        KDTree[] kdTrees = new KDTree[M.length];
        PRQuadTree[] prQuadTrees = new PRQuadTree[M.length];

        int N = (int) Math.pow(2, 16);

        int[] kdExistingKeysTotalDepth = new int[M.length];
        int[] kdNonExistingKeysTotalDepth = new int[M.length];

        int[] prExistingKeysTotalDepth = new int[M.length];
        int[] prNonExistingKeysTotalDepth = new int[M.length];

        for(int i=0;i<M.length;++i) {

            ArrayList<Point> points = SamplePoints.generateSamplePoints(N,M[i]);
            kdTrees[i] = new KDTree();
            prQuadTrees[i] = new PRQuadTree();

            int numberOfTests = 100;

            ArrayList<Point> existingKeys = SamplePoints.getRandomExistingPoints(points,numberOfTests);

            for(Point var: points){
                kdTrees[i].insert(var);
                prQuadTrees[i].insert(var);
            }

            for(int j=0;j<100;++j){

                if(kdTrees[i].search(existingKeys.get(j)) != null){
                    kdExistingKeysTotalDepth[i] += kdTrees[i].search(existingKeys.get(j)).getDepth();
                }

                if(prQuadTrees[i].search(existingKeys.get(j)) != null){
                    prExistingKeysTotalDepth[i] += prQuadTrees[i].search(existingKeys.get(j)).getDepth();
                }

                int x;
                int y;
                Point tempPoint;

                do{
                    x = random.nextInt(N);
                    y = random.nextInt(N);
                    tempPoint = new Point(x,y);

                }while(kdTrees[i].search(tempPoint).getNode() != null);
                kdNonExistingKeysTotalDepth[i] += kdTrees[i].search(tempPoint).getDepth();
                prNonExistingKeysTotalDepth[i] += prQuadTrees[i].search(new Point(x,y)).getDepth();

            }
        }

        /* //PRINTING RESULTS IN A READABLE FORMAT// */
        PrintStatistics.print(kdExistingKeysTotalDepth,kdNonExistingKeysTotalDepth,prExistingKeysTotalDepth,prNonExistingKeysTotalDepth,M);
    }

}
