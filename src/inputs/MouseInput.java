package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import main.GamePanel;
import GameConditions.*;

public class MouseInput implements MouseListener , MouseMotionListener {

    private GamePanel gamePanel;
    public MouseInput(GamePanel gamePanel)
    {
        this.gamePanel = gamePanel;
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        switch (gameConditions.condition) {
            case MENU:
                gamePanel.getGame().getMenu().mouseMoved(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().mouseMoved(e);
                break;
            case OPTIONS:
                gamePanel.getGame().getGameOption().mouseMoved(e);
                break;
            default:
                break;
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        switch (gameConditions.condition) {
            case PLAYING:
                gamePanel.getGame().getPlaying().mouseClicked(e);
                break;
            default:
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (gameConditions.condition) {
            case MENU:
                gamePanel.getGame().getMenu().mousePressed(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().mousePressed(e);
                break;
            case OPTIONS:
                gamePanel.getGame().getGameOption().mousePressed(e);
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (gameConditions.condition) {
            case MENU:
                gamePanel.getGame().getMenu().mouseReleased(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().mouseReleased(e);
                break;
            case OPTIONS:
                gamePanel.getGame().getGameOption().mouseReleased(e);
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        switch (gameConditions.condition) {
            case PLAYING:
                gamePanel.getGame().getPlaying().mouseDragged(e);
                break;
            case OPTIONS:
                gamePanel.getGame().getGameOption().mouseDragged(e);
                break;
            default:
                break;
        }

    }
}