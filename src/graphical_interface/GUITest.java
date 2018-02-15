package graphical_interface;

import javax.swing.*;
import java.awt.*;

public class GUITest extends JFrame
{
    private static JPanel frame = new JPanel();

    public GUITest()
    {
        this.setTitle("Minesweeper");
        this.setVisible(true);

        // stop the program when the application is closed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setSize(1000, 700);

        FrameWork frameWork = new FrameWork();
        frameWork.setLayout(new GridBagLayout());

        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        frame.add(frameWork, gbc);
        this.setContentPane(frame);


        // set the application in the center of the display
        this.setLocationRelativeTo(null);
    }

    public void main(String[] args)
    {
        new GUITest();
    }
}

class FrameWork extends JPanel
{
    private Strove strove;

    public FrameWork()
    {
        strove = new Strove(10, 10, 10, 50);
    }

    public void paintComponents(Graphics g)
    {
        setBackground(Color.CYAN);

        strove.repaint(g);
    }
}
