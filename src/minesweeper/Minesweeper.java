package minesweeper;

import graphical_interface.GUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Minesweeper
{
    // number of lines
    public static int linesNb = 20;

    // number of columns
    public static int columnsNb = 35;

    // number of bombs
    private static int bombsNb = 110;

    public static int time = 0;

    public static boolean gameInProcess = false;

    public static boolean gameOver = false;

    public static int bombsNotFound;

    private static int cellsDiscovered = 0;

    public static ArrayList<ArrayList<Cell>> table;

    private Minesweeper()
    {
        resetGame();

        // creation of the display
        GUI graphicalInterface = new GUI();
        try
        {
            GiveTime.main();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public static void resetGame()
    {
        gameOver = false;
        bombsNotFound = bombsNb;
        setBombs();
        GiveTime.reset();
    }

    public static void startGame()
    {
        gameInProcess = true;
        GiveTime.start();
    }

    private static void setBombs()
    {
        table = new ArrayList<>();
        List<Integer> integerList = new ArrayList<>();
        ArrayList<Cell> line = new ArrayList<>();
        for (int i = 0; i < columnsNb * linesNb; i++)
        {
            if (!(i % columnsNb == 0 && (i / columnsNb == 0 || i / columnsNb == linesNb - 1) ||
                    (i % columnsNb == columnsNb - 1 && (i / columnsNb == 0 || i / columnsNb == linesNb - 1))))
                integerList.add(i);
            line.add(new Cell(0));
            if (i % columnsNb == columnsNb - 1)
            {
                table.add(line);
                line = new ArrayList<>();
            }
        }
        Random random = new Random();
        int randInt;
        int bombInt;
        for (int i = 0; i < bombsNb; i++)
        {
            // random int between 0 and integerList.size()
            randInt = random.nextInt(integerList.size());
            bombInt = integerList.get(randInt);
            integerList.remove(randInt);

            updateCell(bombInt);
        }
    }

    private static void updateCell(int n)
    {
        int line = n / columnsNb;
        int column = n % columnsNb;

        for (int i = 0; i < 9; i++)
        {
            int neighborLine = line + i / 3 - 1;
            int neighborColumn = column + i % 3 - 1;
            if (i != 4 && neighborColumn >= 0 && neighborColumn < columnsNb && neighborLine >= 0 && neighborLine < linesNb)
            {
                table.get(line + i / 3 - 1).get(column + i % 3 - 1).updateNeighbors();
            } else
            {
                table.get(line).get(column).putABomb();
            }
        }
    }

    private static void showCellNeighbors(int line, int column)
    {
        Cell aCell = table.get(line).get(column);
        if (!aCell.getVisibility() && aCell.getNumber() != 9)
        {
            if (aCell.getNumber() == 0)
                showCell(line, column);
            else
            {
                table.get(line).get(column).setVisible();
                cellsDiscovered++;
            }
        }
    }

    public static void showCell(int line, int column)
    {
        if (!gameOver)
        {
            Cell aCell = table.get(line).get(column);
            if (!aCell.getVisibility() && !aCell.getFlag())
            {
                if (aCell.getNumber() == 9)
                {
                    gameLost();
                } else
                {
                    table.get(line).get(column).setVisible();
                    cellsDiscovered++;
                    for (int i = 0; i < 9; i++)
                    {
                        int neighborLine = line + i / 3 - 1;
                        int neighborColumn = column + i % 3 - 1;
                        if (i != 4 && neighborColumn >= 0 && neighborColumn < columnsNb && neighborLine >= 0 && neighborLine < linesNb)
                        {
                            showCellNeighbors(neighborLine, neighborColumn);
                        }
                    }
                }
            }
            if (cellsDiscovered == linesNb * columnsNb - bombsNb)
            {
                gameWon();
            }
        }
    }

    public static void setFlag(int line, int column)
    {
        if (bombsNotFound > 0 || table.get(line).get(column).getFlag())
        {
            table.get(line).get(column).setFlag();
            if (table.get(line).get(column).getFlag())
                bombsNotFound--;
            else
                bombsNotFound++;
        }
    }

    private static void revealAll(boolean win)
    {
        for (int i = 0; i < linesNb; i++)
        {
            for (int j = 0; j < columnsNb; j++)
            {
                Cell aCell = table.get(i).get(j);
                if (!win)
                {
                    if ((aCell.getNumber() == 9 && !aCell.getFlag()) || (aCell.getNumber() != 9 && aCell.getFlag()))
                        table.get(i).get(j).setVisible();
                }
                else
                {
                    if (aCell.getNumber() == 9 && !aCell.getFlag())
                        table.get(i).get(j).setFlag();
                }
            }
        }
    }

    private static void gameLost()
    {
        GiveTime.stop();
        gameInProcess = false;
        gameOver = true;
        GUI.tiles.smileyNb = 3;
        revealAll(false);
    }

    private static void gameWon()
    {
        GiveTime.stop();
        gameInProcess = false;
        gameOver = true;
        GUI.tiles.smileyNb = 2;
        revealAll(true);
    }

    public static void main(String[] args)
    {
        new Minesweeper();
    }
}

class GiveTime
{
    public static boolean passedBy;

    public static void main() throws InterruptedException
    {
        passedBy = false;
        Minesweeper.time = 0;
        for (;;)
        {
            if (passedBy)
            {
                Minesweeper.time++;
                GUI.tiles.repaint();
            }
            Thread.sleep(1000);
        }
    }

    public static void stop()
    {
        passedBy = false;
    }

    public static void reset()
    {
        Minesweeper.time = 0;
    }

    public static void start()
    {
        passedBy = true;
    }
}
