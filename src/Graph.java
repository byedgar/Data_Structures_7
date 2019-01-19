public interface Graph {
    //Adding Label
    void addVertex(String label);

    //Adding edge between 2 Label
    boolean addEdge(String startLabel, String endLabel);

    //Display Labels
    void display();

    // Getting size of all Labels
    int getSize();

    /**
     * Depth-first search, DFS
     */
    void dfs(String startLabel);

    /**
     * breadth-first search, BFS
     */
    void bfs(String startLabel);
}