package containers;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private final String title = "Kalendárium";
    private final int width = 1440;
    private final int height = 900;

    public MainFrame(){
        setTitle(title);
        setSize(new Dimension(width,height));
        setMinimumSize(new Dimension(width, height));
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FlowLayout fl = new FlowLayout(FlowLayout.LEFT,0,0);
        setLayout(fl);
        setResizable(false);
    }
}
