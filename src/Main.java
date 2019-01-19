import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) {
        final int MAX_GRAPH_SIZE = 10;
        final String STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //randomGraph(MAX_GRAPH_SIZE, STRING);
        createGraph(MAX_GRAPH_SIZE);
    }

    private static void createGraph(int size) {
        Graph graph = new GraphImpl(size);
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");
        graph.addVertex("G");
        graph.addVertex("H");
        graph.addVertex("I");
        graph.addVertex("J");

        graph.addEdge("A", "B");
        graph.addEdge("A", "D");
        graph.addEdge("A", "F");
        graph.addEdge("A", "E");
        graph.addEdge("B", "C");
        graph.addEdge("B", "H");
        graph.addEdge("D", "F");
        graph.addEdge("A", "B");
        graph.addEdge("G", "I");
        graph.addEdge("I", "J");


        System.out.println("Graph size = " + graph.getSize());
        graph.display();

        // graph.dfs("A");
        graph.dfsShort("A", "F");
    }

    private static void randomGraph(int size, String string) {
        int randomNum;
        int inserted = 0;
        String s, s2;
        Graph graph = new GraphImpl(size);
        char[] stringToCharArray = string.toCharArray();
        int stringSize = stringToCharArray.length;
        boolean isFull = false;
        while (!isFull) {
            randomNum = ThreadLocalRandom.current().nextInt(0, stringSize);
            s = Character.toString(stringToCharArray[randomNum]);
            if (!graph.find(s)) {
                inserted++;
                graph.addVertex(s);
            }
            if (size == inserted)
                isFull = true;
        }

        for (int i = 0; i < 100; i++) {
            randomNum = ThreadLocalRandom.current().nextInt(0, graph.getSize());
            s = Character.toString(stringToCharArray[randomNum]);
            randomNum = ThreadLocalRandom.current().nextInt(0, graph.getSize());
            s2 = Character.toString(stringToCharArray[randomNum]);
            graph.addEdge(s, s2);
        }
        System.out.println("Graph size = " + graph.getSize());
        graph.display();
    }
}
