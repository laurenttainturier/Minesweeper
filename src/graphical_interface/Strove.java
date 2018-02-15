package graphical_interface;

import java.awt.*;

public class Strove
{
    private int e = 1;

    private Polygon strove;

    private int turnedOn = 0;

    public Strove(int x0, int y0, int x1, int y1)
    {
        Point u = new Point(x1 - x0, y1 - y0);
        Point v = new Point(y0 - y1, x1 - x0);

        Point a0 = new Point(x0 + e * u.unit().x,
                y0 + e * u.unit().y);
        Point b0 = new Point(x0 + 3 * e * u.unit().x + e * 2 * v.unit().x,
                y0 + 3 * e * u.unit().y + e * 2 * v.unit().y);
        Point c0 = new Point(x0 + 3 * e * u.unit().x - e * 2 * v.unit().x,
                y0 + 3 * e * u.unit().y - e * 2 * v.unit().y);

        Point a1 = new Point(x1 - e * u.unit().x,
                y1 - e * u.unit().y);
        Point b1 = new Point(x1 - 3 * e * u.unit().x - e * 2 * v.unit().x,
                y1 - 3 * e * u.unit().y - e * 2 * v.unit().y);
        Point c1 = new Point(x1 - 3 * e * u.unit().x + e * 2 * v.unit().x,
                y1 - 3 * e * u.unit().y + e * 2 * v.unit().y);

        strove = new Polygon();
        strove.addPoint(c0.x, c0.y);
        strove.addPoint(a0.x, a0.y);
        strove.addPoint(b0.x, b0.y);
        strove.addPoint(c1.x, c1.y);
        strove.addPoint(a1.x, a1.y);
        strove.addPoint(b1.x, b1.y);
        strove.addPoint(c0.x, c0.y);
    }

    public void switchOn(int switched)
    {
        turnedOn = switched;
    }

    public void repaint(Graphics g)
    {
        if (turnedOn == 1)
        {
            g.setColor(Color.RED);
            g.fillPolygon(strove);
        }
    }
}
