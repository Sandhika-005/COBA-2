import java.util.ArrayList;
import java.util.List;

class Graph {
    private List<Node> nodes;
    private List<Edge> edges;
    private int[][] adjacencyMatrix;

    public Graph(int[][] adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
        this.nodes = new ArrayList<>();
        this.edges = new ArrayList<>();
        initializeGraph();
    }

    private void initializeGraph() {
        int numNodes = adjacencyMatrix.length;

        // Create nodes in a circular layout
        double centerX = 400;
        double centerY = 300;
        double radius = 200;

        for (int i = 0; i < numNodes; i++) {
            double angle = 2 * Math.PI * i / numNodes - Math.PI / 2;
            double x = centerX + radius * Math.cos(angle);
            double y = centerY + radius * Math.sin(angle);
            nodes.add(new Node(i, x, y));
        }

        // Create edges based on adjacency matrix
        for (int i = 0; i < numNodes; i++) {
            for (int j = i; j < numNodes; j++) {
                if (adjacencyMatrix[i][j] != 0) {
                    edges.add(new Edge(nodes.get(i), nodes.get(j), adjacencyMatrix[i][j]));
                }
            }
        }
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public int[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }
}