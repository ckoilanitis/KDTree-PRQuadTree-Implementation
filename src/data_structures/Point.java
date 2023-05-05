package data_structures;

public class Point {
    private int x;
    private int y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * If point other and caller are equal the function returns
     * true if they are not equal it returns false.
     * @param other Point to compare.
     * @return  True if equal False otherwise,
     */
    public boolean equals(Point other){

        if(this.x == other.getX() && this.y == other.getY()){
            return true;
        }

        return false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
