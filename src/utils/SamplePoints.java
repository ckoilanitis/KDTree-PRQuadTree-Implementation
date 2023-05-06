package utils;
import data_structures.Point;
import java.util.ArrayList;
import java.util.Random;

public class SamplePoints {


    /**
     * Finding and returning a set number of points that already exist in another array of points.
     * @param points the array of points that we can select from.
     * @param numberOfPoints the set number of points that we need.
     * @return the array of the randomly selected points.
     */
    static public ArrayList<Point> getRandomExistingPoints(ArrayList<Point> points, int numberOfPoints) {
        ArrayList<Point> existingPoints = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < numberOfPoints; i++) {
            int index = random.nextInt(points.size());
            existingPoints.add(points.get(index));
        }

        return existingPoints;
    }

    /**
     * Generates and returns a set number of random 2D-points.
     * @param N max number that the points' coordinates can take.
     * @param numberOfPoints set number of points we need.
     * @return the array of the randomly generated points.
     */
    static public ArrayList<Point> generateSamplePoints(int N, int numberOfPoints) {
        ArrayList<Point> points = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < numberOfPoints; i++) {
            int x = random.nextInt(N);
            int y = random.nextInt(N);
            points.add(new Point(x, y));

        }
        return points;
    }
}
