package main;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class GameWindow {
    private JFrame jFrame;
    public GameWindow( JPanel jPanel)
    {
        jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
        jFrame.setLayout(new BorderLayout());
        jFrame.add(jPanel , BorderLayout.CENTER);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
        jFrame.setVisible(true);

    }
    
}
