package data_structures;

public class KDTree{

    /**
     * Public inner class used in order to return the desired data from the search method.
     * Specifically has the node that the search stopped on and the depth at which the search stopped.
     */
    public static class SearchResult {
        KDNode KDNode;
        int depth;

        public SearchResult(KDNode KDNode, int depth) {
            this.KDNode = KDNode;
            this.depth = depth;
        }

        public KDNode getNode() {
            return KDNode;
        }

        public int getDepth() {
            return depth;
        }
    }

    private KDNode root;

    /**
     * Public method insert that calls the private insert method with the initial arguments.
     * @param point point to be inserted.
     */
    public void insert(Point point) {
        root = insert(root, point, 0);
    }

    /**
     * Private insert method. Uses recursion to find the correct position at which the new point should be inserted.
     * @param KDNode the node that the method is on, at the moment.
     * @param point the point that is to be inserted.
     * @param depth the depth the method is on, at the moment.
     * @return the node that it is on, lastly the root.
     */
    private KDNode insert(KDNode KDNode, Point point, int depth) {
        data_structures.KDNode result = KDNode;

        if (KDNode == null) {
            result = new KDNode(point, depth);
        } else if (!point.equals(KDNode.getPoint())) {
            int cmp = comparePoints(point, KDNode.getPoint(), KDNode.getAxis());
            if (cmp < 0) {
                KDNode.setRight(insert(KDNode.getRight(), point, depth + 1));
            } else {
                KDNode.setLeft(insert(KDNode.getLeft(), point, depth + 1));
            }
        }

        return result;
    }

    /**
     * This method is used to compare either the x's coordinates of point 1 and point 2
     * or the y's coordinates of point 1 and point 2, depending on axis parameter of the node.
     * @param p1 point 1.
     * @param p2 point 2.
     * @param axis axis of the node (0 -> x || 1 -> y).
     * @return the difference between x's or y's of points 1 and 2.
     */
    private int comparePoints(Point p1, Point p2, int axis){
        return axis == 0? p1.getX() - p2.getX(): p1.getY() - p2.getY();
    }

    /**
     * Public method search that calls the private search method with the initial arguments.
     * @param point point that is searched.
     * @return returns a SearchResult obj which contains the depth at which the search stopped and if the point was found the node that had it or null node otherwise.
     */
    public SearchResult search(Point point) {
        return search(root, point, 0);
    }

    /**
     * Private method search that finds whether the point is in the KD tree.
     * @param KDNode the node the search method is on.
     * @param point the point the search method is searching.
     * @param depth the depth the search method is on.
     * @return returns a SearchResult obj which contains the depth at which the search stopped and if the point was found the node that had it or null node otherwise.
     */
    private SearchResult search(KDNode KDNode, Point point, int depth) {
        SearchResult result;
        if (KDNode == null) {
            result = new SearchResult(null, depth);// KDNode not found, return depth at which search stopped
        } else if (point.equals(KDNode.getPoint())) {
            result = new SearchResult(KDNode, depth);// KDNode found, return KDNode and its depth
        } else {
            int cmp = comparePoints(point, KDNode.getPoint(), KDNode.getAxis());
            if (cmp < 0) {
                result = search(KDNode.getRight(), point, depth + 1);
            } else {
                result = search(KDNode.getLeft(), point, depth + 1);
            }
        }

        return result;
    }

}
