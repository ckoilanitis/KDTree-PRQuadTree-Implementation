package data_structures;

public class KDNode {
    private final Point point;
    private KDNode left,right;
    private final int axis;

    public KDNode(Point point, int level){
        this.point = point;
        this.axis = level % 2;
        this.left = null;
        this.right = null;
    }

    public int getAxis() {
        return axis;
    }

    public Point getPoint() {
        return point;
    }

    public KDNode getLeft() {
        return left;
    }

    public void setLeft(KDNode left) {
        this.left = left;
    }

    public KDNode getRight() {
        return right;
    }

    public void setRight(KDNode right) {
        this.right = right;
    }
}
