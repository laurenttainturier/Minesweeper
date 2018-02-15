package graphical_interface;

public class Point
{
    public int x;

    public int y;

    Point(int aX, int aY)
    {
        x = aX;
        y = aY;
    }

    private int norm()
    {
        return (int) Math.sqrt(x * x + y * y);
    }

    public Point unit()
    {
        return new Point(x / this.norm(), y / this.norm());
    }

    public String toString()
    {
        return "(" + x + ", " + y + ")";
    }
}
