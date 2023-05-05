package data_structures;

public class PRQuadTree {

    /**
     * Public inner class used in order to return the desired data from the search method.
     * Specifically has the node that the search stopped on and the depth at which the search stopped.
     */
    public static class SearchResult {
        public PRQuadNode node;
        public int depth;

        public SearchResult(PRQuadNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    int maxSize = (1<<20);
    private PRQuadNode root;

    /**
     * Public insert method that calls the private insert method with the initial arguments.
     * @param point new point to be inserted.
     */
    public void insert(Point point) {
        root = insert(root, point, 0, 0, maxSize);
    }

    /**
     * Public search method that calls the private search method with the initial arguments.
     * @param point point to be searched.
     * @return returns a SearchResult obj which contains the depth at which the search stopped and if the point was found the node that had it or null node otherwise.
     */
    public SearchResult search(Point point) {
        return search(root, point, 0, 0, maxSize, 0);
    }

    /**
     * Private method search that finds whether the point is in the KD tree.
     * @param node the node at which the search is on.
     * @param point point that is searched.
     * @param x x coordinate of the top left point of the current bounding box.
     * @param y y coordinate of the top left point of the current bounding box.
     * @param size size of the current bounding box(the length of a side of the bounding box square).
     * @param depth the depth at which the search is on.
     * @return returns a SearchResult obj which contains the depth at which the search stopped and if the point was found the node that had it or null node otherwise.
     */
    private SearchResult search(PRQuadNode node, Point point, int x, int y, int size, int depth) {
        SearchResult result;

        if (node == null) {
            result = new SearchResult(null, depth + 1);
        } else if (node.point != null && node.point.equals(point)) {
            result = new SearchResult(node, depth);
        } else if (node.children != null) {
            int halfSize = size / 2;
            int index = getIndex(point, x, y, halfSize);
            int newX = x + (index & 1) * halfSize;
            int newY = y + (index >> 1) * halfSize;

            result = search(node.children[index], point, newX, newY, halfSize, depth + 1);
        } else {
            result = new SearchResult(null, depth);
        }

        return result;
    }

    /**
     * Private insert method. Uses recursion to find the correct position at which the new point should be inserted.
     * @param node the node that the insert method is on.
     * @param point the point to be inserted.
     * @param x x coordinate of the top left point of the current bounding box.
     * @param y y coordinate of the top left point of the current bounding box.
     * @param size size of the current bounding box(the length of a side of the bounding box square).
     * @return the node that it is on, lastly the root.
     */
    public PRQuadNode insert(PRQuadNode node, Point point, int x, int y, int size) {
        PRQuadNode result = node;

        if (node == null) {
            result = new PRQuadNode(point);
        } else {
            int halfSize = size / 2;
            int index = getIndex(point, x, y, halfSize);
            int newX = x + (index & 1) * halfSize;
            int newY = y + (index >> 1) * halfSize;
            if (node.children == null) {
                node.children = new PRQuadNode[4];
            }
            if (node.point != null) {
                int existingPointIndex = getIndex(node.point, x, y, halfSize);
                node.children[existingPointIndex] = new PRQuadNode(node.point);
                node.point = null;
            }
            node.children[index] = insert(node.children[index], point, newX, newY, halfSize);
        }

        return result;
    }

    /**
     * This method will return the correct sub-node in which we should continue the insertion call
     * @param point point we want to add
     * @param x center x coordinate
     * @param y center y coordinate
     * @param halfSize half size of the bounding box
     * @return the index quadrant in which we should continue the insert
     */
    private int getIndex(Point point, int x, int y, int halfSize) {
        int index = 0;

        if (point.getX() >= x + halfSize) {
            index |= 1; // index -> 0001 if point's x coordinate is greater or equal to the center x coordinate.
        }
        if (point.getY() >= y + halfSize) {
            index |= 2; // index -> 0010 or 0011 depending on the previous if and if point's y coordinate is greater or equal to the center y coordinate.
        }

        return index; // index can be 0, 1, 2 or 3 depending on the if statements.
    }








}
