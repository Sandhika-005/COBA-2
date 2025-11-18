import javax.swing.*;
import java.awt.*;

class GraphPanel extends JPanel {
    private Graph graph;
    private Node draggedNode;

    public GraphPanel(Graph graph) {
        this.graph = graph;
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.WHITE);
        setupMouseListeners();
    }

    private void setupMouseListeners() {
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent e) {
                for (Node node : graph.getNodes()) {
                    if (node.contains(e.getPoint())) {
                        draggedNode = node;
                        break;
                    }
                }
            }

            public void mouseReleased(java.awt.event.MouseEvent e) {
                draggedNode = null;
            }
        });

        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent e) {
                if (draggedNode != null) {
                    draggedNode.setX(e.getX());
                    draggedNode.setY(e.getY());
                    repaint();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw edges
        g2d.setColor(Color.GRAY);
        g2d.setStroke(new BasicStroke(2));
        for (Edge edge : graph.getEdges()) {
            Node source = edge.getSource();
            Node target = edge.getTarget();

            g2d.drawLine((int) source.getX(), (int) source.getY(),
                    (int) target.getX(), (int) target.getY());

            // Draw weight if not 1
            if (edge.getWeight() != 1) {
                int midX = (int) ((source.getX() + target.getX()) / 2);
                int midY = (int) ((source.getY() + target.getY()) / 2);
                g2d.setColor(Color.RED);
                g2d.fillOval(midX - 10, midY - 10, 20, 20);
                g2d.setColor(Color.WHITE);
                g2d.drawString(String.valueOf(edge.getWeight()), midX - 5, midY + 5);
                g2d.setColor(Color.GRAY);
            }
        }

        // Draw nodes
        for (Node node : graph.getNodes()) {
            // Node circle
            g2d.setColor(new Color(70, 130, 180));
            g2d.fillOval((int) (node.getX() - node.getRadius()),
                    (int) (node.getY() - node.getRadius()),
                    node.getRadius() * 2, node.getRadius() * 2);

            // Node border
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(2));
            g2d.drawOval((int) (node.getX() - node.getRadius()),
                    (int) (node.getY() - node.getRadius()),
                    node.getRadius() * 2, node.getRadius() * 2);

            // Node label
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.BOLD, 16));
            String label = String.valueOf(node.getId());
            FontMetrics fm = g2d.getFontMetrics();
            int labelWidth = fm.stringWidth(label);
            g2d.drawString(label, (int) (node.getX() - labelWidth / 2),
                    (int) (node.getY() + fm.getAscent() / 2 - 2));
        }
    }
}