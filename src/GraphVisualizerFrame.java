import javax.swing.*;
import java.awt.*;

class GraphVisualizerFrame extends JFrame {
    private Graph graph;
    private GraphPanel graphPanel;

    public GraphVisualizerFrame(int[][] adjacencyMatrix) {
        this.graph = new Graph(adjacencyMatrix);
        setupUI();
    }

    private void setupUI() {
        setTitle("Graph Visualizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create graph panel
        graphPanel = new GraphPanel(graph);
        add(graphPanel, BorderLayout.CENTER);

        // Create control panel
        JPanel controlPanel = new JPanel();
        JButton resetButton = new JButton("Reset Layout");
        resetButton.addActionListener(e -> resetLayout());
        controlPanel.add(resetButton);

        JButton infoButton = new JButton("Show Matrix");
        infoButton.addActionListener(e -> showAdjacencyMatrix());
        controlPanel.add(infoButton);

        add(controlPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void resetLayout() {
        this.graph = new Graph(graph.getAdjacencyMatrix());
        graphPanel = new GraphPanel(graph);
        getContentPane().removeAll();
        add(graphPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        JButton resetButton = new JButton("Reset Layout");
        resetButton.addActionListener(e -> resetLayout());
        controlPanel.add(resetButton);

        JButton infoButton = new JButton("Show Matrix");
        infoButton.addActionListener(e -> showAdjacencyMatrix());
        controlPanel.add(infoButton);

        add(controlPanel, BorderLayout.SOUTH);
        revalidate();
        repaint();
    }

    private void showAdjacencyMatrix() {
        StringBuilder sb = new StringBuilder("Adjacency Matrix:\n\n");
        int[][] matrix = graph.getAdjacencyMatrix();

        sb.append("   ");
        for (int i = 0; i < matrix.length; i++) {
            sb.append(i).append(" ");
        }
        sb.append("\n");

        for (int i = 0; i < matrix.length; i++) {
            sb.append(i).append("  ");
            for (int j = 0; j < matrix[i].length; j++) {
                sb.append(matrix[i][j]).append(" ");
            }
            sb.append("\n");
        }

        JOptionPane.showMessageDialog(this, sb.toString(), "Adjacency Matrix",
                JOptionPane.INFORMATION_MESSAGE);
    }
}