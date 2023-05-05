package data_structures;

public class PRQuadNode {
    Point point;
    PRQuadNode[] children;

    PRQuadNode(Point point) {
        this.point = point;
        this.children = null;
    }
}
