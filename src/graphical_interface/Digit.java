package graphical_interface;

import java.awt.*;
import java.util.ArrayList;

public class Digit
{
    private int[][] displayStrove = {
            {1, 1, 1, 1, 1, 1, 0},  // 0
            {0, 1, 1, 0, 0, 0, 0},  // 1
            {1, 1, 0, 1, 1, 0, 1},  // 2
            {1, 1, 1, 1, 0, 0, 1},  // 3
            {0, 1, 1, 0, 0, 1, 1},  // 4
            {1, 0, 1, 1, 0, 1, 1},  // 5
            {1, 0, 1, 1, 1, 1, 1},  // 6
            {1, 1, 1, 0, 0, 0, 0},  // 7
            {1, 1, 1, 1, 1, 1, 1},  // 8
            {1, 1, 1, 1, 0, 1, 1}}; // 9

    private ArrayList<Strove> stroves = new ArrayList<>();

    public Digit(int x0, int y0, int x1, int y1)
    {
        int width = x1 - x0;
        int height = (y1 - y0) / 2;
        int e = Math.min(width, height);
        x0 = x0 + (width - e) / 2;
        x1 = x0 + e;
        y0 = y0 + (height - e) / 2;
        y1= y0 + 2 * e;
        int y2 = y0 + (y1 - y0) / 2;

        stroves.add(new Strove(x0, y0, x1, y0));  // strove A
        stroves.add(new Strove(x1, y0, x1, y2));  // strove B
        stroves.add(new Strove(x1, y2, x1, y1));  // strove C
        stroves.add(new Strove(x0, y1, x1, y1));  // strove D
        stroves.add(new Strove(x0, y2, x0, y1));  // strove E
        stroves.add(new Strove(x0, y0, x0, y2));  // strove F
        stroves.add(new Strove(x0, y2, x1, y2));  // strove G
    }

    public void repaint(Graphics g, int numberToDisplay)
    {
        for (int i = 0; i < 7; i++)
        {
            stroves.get(i).switchOn(displayStrove[numberToDisplay][i]);
            stroves.get(i).repaint(g);
        }
    }
}
