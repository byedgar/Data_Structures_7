import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class GraphImpl implements Graph {

    // Creating array with Value edge
    private boolean[][] adjMat;
    private List<Vertex> vertexes;

    //Constructor for making Vertex
    public GraphImpl(int maxVertexes) {
        this.adjMat = new boolean[maxVertexes][maxVertexes];
        this.vertexes = new LinkedList<>();
    }

    /**
     * Method for adding Label
     *
     * @param label - Label name
     */
    @Override
    public void addVertex(String label) {
        Vertex vertex = new Vertex(label);
        vertexes.add(vertex);
    }

    /**
     * Method for creating edge between tho labels
     *
     * @param startLabel - first label name
     * @param endLabel   - second label name
     * @return True if ok/ False if not
     */
    @Override
    public boolean addEdge(String startLabel, String endLabel) {
        //Checking if Label exist
        int startIndex = indexOf(startLabel);
        int endIndex = indexOf(endLabel);

        if (startIndex == -1 || endIndex == -1) {
            return false;
        }
//Adding edge between two edges
        adjMat[startIndex][endIndex] = true;
        adjMat[endIndex][startIndex] = true;

        return true;
    }

    /**
     * Method for finding Labels in vertex
     *
     * @param label - label name
     * @return True if found/ False if not found
     */
    private int indexOf(String label) {
        for (int i = 0; i < vertexes.size(); i++) {
            if (vertexes.get(i).getLabel().equals(label)) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Method for displaying all Labels
     */
    @Override
    public void display() {
        System.out.println("-------------");
        for (int i = 0; i < vertexes.size(); i++) {
            Vertex vertex = vertexes.get(i);

            StringBuilder sb = new StringBuilder(vertex.getLabel());

            for (int j = 0; j < vertexes.size(); j++) {
                if (adjMat[i][j]) {
                    sb.append(" -> " + vertexes.get(j).getLabel());
                }
            }
            System.out.println(sb.toString());
        }
        System.out.println("-------------");
    }

    /**
     * Method for getting size of all elements
     *
     * @return size
     */
    @Override
    public int getSize() {
        return vertexes.size();
    }

    /**
     * Method for Depth-first search, DFS
     *
     * @param startLabel -  label name
     */
    @Override
    public void dfs(String startLabel) {
        int startIndex = indexOf(startLabel);
        if (startIndex == -1) {
            throw new IllegalArgumentException("Unknown vertex: " + startLabel);
        }

        System.out.println("DFS:");
        System.out.println("-------------");
        //Creating new stack
        Stack<Vertex> stack = new Stack<>();

        Vertex vertex = vertexes.get(startIndex);
        //Change flag to visited
        visitVertex(vertex);
        //Adding Label to stack
        stack.push(vertex);

        while (!stack.isEmpty()) {
            vertex = getAdjUnvisitedVertex(stack.peek());
            if (vertex != null) {
                visitVertex(vertex);
                stack.push(vertex);
            } else {
                stack.pop();
            }
        }

        System.out.println("-------------");

        resetVertexState();
    }

    /**
     * Method for readth-first search, BFS
     *
     * @param startLabel -  label name
     */
    @Override
    public void bfs(String startLabel) {
        int startIndex = indexOf(startLabel);
        if (startIndex == -1) {
            throw new IllegalArgumentException("Unknown vertex: " + startLabel);
        }

        System.out.println("BFS:");
        System.out.println("-------------");

        Queue<Vertex> queue = new LinkedList<>();

        Vertex vertex = vertexes.get(startIndex);

        visitVertex(vertex);
        queue.add(vertex);

        while (!queue.isEmpty()) {
            vertex = getAdjUnvisitedVertex(queue.peek());
            if (vertex != null) {
                visitVertex(vertex);
                queue.add(vertex);
            } else {
                queue.remove();
            }
        }

        System.out.println("-------------");

        resetVertexState();
    }


    @Override
    public void dfsShort(String startLabel, String lastLabel) {
        int minStep = 1000000;
        int stepCount = 0;

        int startIndex = indexOf(startLabel);
        int lastIndex = indexOf(lastLabel);
        System.out.println("Short Path from " + startLabel + " to " + lastLabel);

        if (startIndex == -1) {
            throw new IllegalArgumentException("Unknown start vertex: " + startLabel);
        }
        if (lastIndex == -1) {
            throw new IllegalArgumentException("Unknown last vertex: " + startLabel);
        }

        if (startIndex == lastIndex) {
            stepCount++;
        }
        System.out.println("-------------");
        Queue<Vertex> queue = new LinkedList<>();
        Vertex vertex = vertexes.get(startIndex);
        visitVertex(vertex);

        // queue.add(vertex);
        queue.add(vertex);
        while (!queue.isEmpty()) {

            if (startLabel.equals(lastLabel)) {
                stepCount++;
                break;
            } else {
                vertex = getAdjUnvisitedVertex(queue.peek());

                if (vertex != null) {
                    visitVertex(vertex);
                    stepCount++;
                    queue.add(vertex);
                    if (vertex.getLabel().equals(lastLabel)) {
                        if (stepCount < minStep)
                            minStep = stepCount;
                    }
                } else {

                    queue.remove();
                    stepCount--;
                }
            }


        }

        System.out.println("-------------");

        resetVertexState();
        System.out.println("Min count " + minStep);
    }

    @Override
    public boolean find(String label) {
        if (vertexes.size() == 0)
            return false;

        for (Vertex vertex : vertexes) {
            if (vertex.getLabel().equals(label))
                return true;
        }
        return false;
    }


    private void resetVertexState() {
        for (Vertex vertex : vertexes) {
            vertex.setWasVisited(false);
        }
    }

    private Vertex getAdjUnvisitedVertex(Vertex vertex) {
        int index = vertexes.indexOf(vertex);
        for (int i = 0; i < vertexes.size(); i++) {
            if (adjMat[index][i] && !vertexes.get(i).isWasVisited()) {
                return vertexes.get(i);
            }
        }

        return null;
    }

    private void visitVertex(Vertex vertex) {
        System.out.println(vertex);
        vertex.setWasVisited(true);
    }

}