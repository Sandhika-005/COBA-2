import java.awt.*;

class Node {
    private int id;
    private double x;
    private double y;
    private static final int RADIUS = 30;

    public Node(int id, double x, double y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getRadius() {
        return RADIUS;
    }

    public boolean contains(Point point) {
        double distance = Math.sqrt(Math.pow(point.x - x, 2) + Math.pow(point.y - y, 2));
        return distance <= RADIUS;
    }
}