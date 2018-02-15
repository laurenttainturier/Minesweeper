package minesweeper;

public class Cell
{
    private int number;

    private boolean visible;

    private boolean flag;

    public Cell(int aNumber)
    {
        number = aNumber;
        visible = false;
        flag = false;
    }

    public void putABomb()
    {
        number = 9;
    }

    public int getNumber()
    {
        return number;
    }

    public boolean getVisibility()
    {
        return visible;
    }

    public void setVisible()
    {
        visible = true;
    }

    public boolean getFlag()
    {
        return flag;
    }

    public void setFlag()
    {
        flag = !flag;
    }

    public void updateNeighbors()
    {
        if (number != 9)
            number++;
    }

    @Override
    public String toString()
    {
        return "(" + number + ", " + visible + ")";
    }
}
