package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import main.GamePanel;
import static utilz.Constants.Direction.DOWN;
import static utilz.Constants.Direction.LEFT;
import static utilz.Constants.Direction.RIGHT;
import static utilz.Constants.Direction.UP;



public class KeyBoardInputs implements KeyListener {
    private GamePanel gamePanel;
    public KeyBoardInputs( GamePanel gamePanel)
    {
        this.gamePanel = gamePanel;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_W:
                gamePanel.getGame().getPlayer().setDirection(UP);
                break;
            case KeyEvent.VK_A:
                gamePanel.getGame().getPlayer().setDirection(LEFT);
                break;    
            case KeyEvent.VK_S:
                gamePanel.getGame().getPlayer().setDirection(DOWN);
                break;
            case KeyEvent.VK_D:
                gamePanel.getGame().getPlayer().setDirection(RIGHT);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_W:
            case KeyEvent.VK_A:
            case KeyEvent.VK_S:
            
            case KeyEvent.VK_D:
                gamePanel.getGame().getPlayer().isRunning(false);
                break;
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
        // System.out.println("A");
    }

}
