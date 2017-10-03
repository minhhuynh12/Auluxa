package net.dirox.auluxa.utils.Polygon;

/**
 * Created by an on 6/23/2017.
 */

public class Point {
    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float x;
    public float y;

    @Override
    public String toString() {
        return String.format("(%.2f,%.2f)", x, y);
    }
}
