package utils;

import java.text.DecimalFormat;

public class PrintStatistics {

    /**
     * This is an auxiliary method that helps in printing the data tha has been collected in Test class in a readable format.
     * @param kdExistingKeysTotalDepth an array with the total depth reached for each successful search (on different amounts of data that  exist in a KD-tree).
     * @param kdNonExistingKeysTotalDepth an array with the total depth reached for each unsuccessful search (on different amounts of data that don't exist in a KD-tree).
     * @param prExistingKeysTotalDepth an array with the total depth reached for each successful search (on different amounts of data that  exist in a PR Quad-tree).
     * @param prNonExistingKeysTotalDepth an array with the total depth reached for each unsuccessful search (on different amounts of data that don't exist in a PR Quad-tree).
     * @param M an array which holds the different amounts of data for each search.
     */
    public static void print(int[] kdExistingKeysTotalDepth, int[] kdNonExistingKeysTotalDepth, int[] prExistingKeysTotalDepth, int[] prNonExistingKeysTotalDepth, int[] M){

        DecimalFormat df = new DecimalFormat("0.00");
        System.out.println("=================================================================================================");
        System.out.println("Project 2 Search for 2-dimensional points");
        System.out.println("=================================================================================================");
        System.out.println("Tests for KD tree");
        System.out.println("\t Existing Keys \t \t \t \t \t  Non-Existing Keys");

        for(int z=0;z<M.length;++z) {
            if (z < 2)
                System.out.println("KD: " + df.format((float) kdExistingKeysTotalDepth[z] / 100.00) + "|" + "\t" + "Search[" + M[z] + "]" + "\t\t\t\t" + df.format((float) kdNonExistingKeysTotalDepth[z] / 100.00) + "| Search[" + M[z] + "]");
            else
                System.out.println("KD: " + df.format((float) kdExistingKeysTotalDepth[z] / 100.00) + "|" + "\t" + "Search[" + M[z] + "]" + "\t\t\t" + df.format((float) kdNonExistingKeysTotalDepth[z] / 100.00) + "| Search[" + M[z] + "]");
        }

        System.out.println("=================================================================================================");
        System.out.println("Tests for PR Quad tree");
        System.out.println("\t Existing Keys \t \t \t \t \t  Non-Existing Keys");

        for(int h=0;h<M.length;++h) {
            if (h < 2)
                System.out.println("PR: " + df.format((float) prExistingKeysTotalDepth[h] / 100.00) + "|" + "\t" + "Search[" + M[h] + "]" + "\t\t\t\t" + df.format((float) prNonExistingKeysTotalDepth[h] / 100.00) + "| Search[" + M[h] + "]");
            else
                System.out.println("PR: " + df.format((float) prExistingKeysTotalDepth[h] / 100.00) + "|" + "\t" + "Search[" + M[h] + "]" + "\t\t\t" + df.format((float) prNonExistingKeysTotalDepth[h] / 100.00) + "| Search[" + M[h] + "]");
        }
    }

}
