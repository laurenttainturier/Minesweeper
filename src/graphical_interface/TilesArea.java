package graphical_interface;

import minesweeper.Minesweeper;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class TilesArea extends JPanel implements MouseListener
{
    private static String tilesFileName = "doc/images/tiles.jpg";

    private static String smileysFileName = "doc/images/smileys.png";

    private static Image tilesImage;

    private static Image smileysImage;

    public int width;

    public int height;

    private static int borderLayout = 6;

    private static int menuSize = 70;

    private int tilesWidth = 32;

    private int tilesHeight = 32;

    private int imagesNb = 11;

    private int eachImageWidth;

    private int imageWidth;

    private int imageHeight;

    public int smileyNb = 0;

    private DisplayNumbers bombToDisplay;

    private DisplayNumbers timeToDisplay;

    public TilesArea()
    {
        try
        {
            tilesImage = ImageIO.read(new File(tilesFileName));
            smileysImage = ImageIO.read(new File(smileysFileName));
        } catch (IOException e)
        {
            System.out.println("Une des images <" + tilesFileName + "> ou <" + smileysFileName + "> n'a pas été trouvée");
            System.exit(-1);
        }

        imageWidth = tilesImage.getWidth(this);
        imageHeight = tilesImage.getHeight(this);
        eachImageWidth = imageWidth / imagesNb - 2;

        width = Minesweeper.columnsNb * tilesWidth + 2 * borderLayout;
        height = Minesweeper.linesNb * tilesHeight + 2 * borderLayout + menuSize;

        bombToDisplay = new DisplayNumbers(borderLayout, borderLayout, 80, menuSize - 3 * borderLayout);
        timeToDisplay = new DisplayNumbers(width - borderLayout - 80, borderLayout, width - borderLayout, menuSize - 3 * borderLayout);

        this.setSize(width, height);
        this.setPreferredSize(new Dimension(width, height));

        addMouseListener(this);
    }

    public void paintComponent(Graphics g)
    {
        repaintNumbers(g);
        repaintBorders(g);
        repaintCells(g);

        int smileyWidth = menuSize - 3 * borderLayout;
        int x0 = width / 2 - smileyWidth / 2 + borderLayout;
        int y0 = borderLayout;
        int x1 = x0 + smileyWidth;
        int y1 = y0 + smileyWidth;

        int smileyIconWidth = smileysImage.getWidth(this) / 2;
        g.drawImage(smileysImage, x0, y0, x1, y1,
                smileyNb % 2 * smileyIconWidth,
                smileyNb / 2 * smileyIconWidth,
                (smileyNb % 2 + 1) * smileyIconWidth,
                (smileyNb / 2 + 1) * smileyIconWidth,
                this);
    }

    private void repaintNumbers(Graphics g)
    {
        // Display the remaining flags
        g.setColor(Color.BLACK);
        //g.fillRect(borderLayout, borderLayout, 100, menuSize - 3 * borderLayout);
        // new NumberDisplay(g, borderLayout, borderLayout, 100, menuSize - 3 * borderLayout);
        bombToDisplay.repaint(g, Minesweeper.bombsNotFound);
        timeToDisplay.repaint(g, Minesweeper.time);
    }


    private void repaintBorders(Graphics g)
    {
        // display each cell with the correct image
        for (int i = 0; i < borderLayout; i++)
        {
            g.setColor(new Color(130, 130, 130));

            // Left vertical border
            g.drawLine(i, menuSize + i, i, height - i - 2);

            // top horizontal border
            g.drawLine(i + 1, menuSize + i, width - i - 1, menuSize + i);

            g.setColor(new Color(255, 255, 255));

            // bottom horizontal border
            g.drawLine(i, height - i, width - i - 1, height - i);

            // right vertical border
            g.drawLine(width - i, menuSize + i, width - i, height - i);
            // g.drawLine(width + i, i, width + i, height - i - 1);

        }
    }

    private void repaintCells(Graphics g)
    {
        for (int i = 0; i < Minesweeper.columnsNb; i++)
        {
            for (int j = 0; j < Minesweeper.linesNb; j++)
            {
                int n;
                if (Minesweeper.table.get(j).get(i).getVisibility())
                {
                    n = (Minesweeper.table.get(j)).get(i).getNumber();
                } else if (Minesweeper.table.get(j).get(i).getFlag())
                {
                    n = 11;
                } else
                {
                    n = 10;
                }
                g.drawImage(tilesImage,
                        borderLayout + tilesWidth * i, menuSize + borderLayout + tilesHeight * j,
                        borderLayout + tilesWidth * (i + 1), menuSize + borderLayout + tilesHeight * (j + 1),
                        eachImageWidth * n, 0,
                        eachImageWidth * (n + 1), imageHeight,
                        this);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent)
    {
        int cellYPosition = mouseEvent.getY() - borderLayout - menuSize;
        int cellXPosition = mouseEvent.getX() - borderLayout;
        int line = cellYPosition / tilesHeight;
        int column = cellXPosition / tilesWidth;

        if (line >= 0 && line < Minesweeper.linesNb
                && column >= 0 &&  column < Minesweeper.columnsNb
                && cellXPosition >= 0 && cellYPosition >= 0)
        {
            if (!Minesweeper.gameOver)
            {
                if (!Minesweeper.gameInProcess)
                {
                    Minesweeper.startGame();
                }

                if (mouseEvent.getButton() == 1)
                {
                    Minesweeper.showCell(line, column);
                } else if (mouseEvent.getButton() == 3)
                {
                    Minesweeper.setFlag(line, column);
                }
            }
        }
        else
        {
            smileyNb = 0;
            Minesweeper.resetGame();
        }
        this.repaint();
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent)
    {
        if (!Minesweeper.gameOver)
        {
            smileyNb = 1;
            this.repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent)
    {
        if (!Minesweeper.gameOver)
        {
            smileyNb = 0;
            this.repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent)
    {
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent)
    {
    }
}
