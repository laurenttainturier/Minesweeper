package graphical_interface;

import minesweeper.Minesweeper;

import java.awt.*;
import java.util.ArrayList;

public class DisplayNumbers
{
    private ArrayList<Digit> digits = new ArrayList<>();

    private Polygon rect = new Polygon();

    public DisplayNumbers(int x0, int y0, int x1, int y1)
    {
        int width = (x1 - x0) / 10;
        int height = (y1 - y0) / 6;

        rect.addPoint(x0, y0);
        rect.addPoint(x1, y0);
        rect.addPoint(x1, y1);
        rect.addPoint(x0, y1);

        digits.add(new Digit(x0 + 7 * width, y0 + height, x0 + 9 * width, y0 + 5 * height));
        digits.add(new Digit(x0 + 4 * width, y0 + height, x0 + 6 * width, y0 + 5 * height));
        digits.add(new Digit(x0 + width, y0 + height, x0 + 3 * width, y0 + 5 * height));
    }

    public void repaint(Graphics g, int n)
    {
        g.setColor(new Color(30, 30, 30));
        g.fillPolygon(rect);

        int i = 0;
        if (n < 1000)
        {
            while (n > 0)
            {
                digits.get(i++).repaint(g, n % 10);
                n /= 10;
            }
            while (i < 3)
            {
                digits.get(i++).repaint(g, 0);
            }
        }
    }
}
