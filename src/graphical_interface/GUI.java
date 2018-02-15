package graphical_interface;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame
{
    private static JPanel frame = new JPanel();

    public static TilesArea tiles;

    private static JTextField menu = new JTextField();

    private static int borders = 10;

    public GUI()
    {
        this.setTitle("Minesweeper");
        this.setVisible(true);

        // stop the program when the application is closed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tiles = new TilesArea();

        this.setSize(tiles.width + 2 * borders, tiles.height + 2 * borders);

        frame.setBackground(new Color(191, 191, 191));
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        frame.add(tiles, gbc);
        this.setContentPane(frame);

        // set the application in the center of the display
        this.setLocationRelativeTo(null);
    }
}


