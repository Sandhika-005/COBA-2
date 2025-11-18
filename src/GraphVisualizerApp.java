import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.List;

public class GraphVisualizerApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Example adjacency matrix (5 nodes)
            int[][] adjacencyMatrix = {
                    {0, 1, 1, 0, 0},
                    {1, 0, 1, 1, 0},
                    {1, 1, 0, 1, 1},
                    {0, 1, 1, 0, 1},
                    {0, 0, 1, 1, 0}
            };

            new GraphVisualizerFrame(adjacencyMatrix);
        });
    }
}