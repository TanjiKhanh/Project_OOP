package main;

import entities.Player;
import inputs.KeyBoardInputs;
import inputs.MouseInput;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import levels.LevelManager;
import static main.Game.*;

public class GamePanel extends JPanel {

    private MouseInput mouseInput;
    private Player player;
    private LevelManager levelManager;

    
    
    public GamePanel() {
        mouseInput = new MouseInput(this);
        setPanelSize();
        setBackground(Color.black);

        player = new Player(200, 200, (int) (64 * Game.SCALE), (int) (40 * Game.SCALE));
        levelManager = new LevelManager();
        addKeyListener(new KeyBoardInputs(this));
        addMouseListener(mouseInput);
        addMouseMotionListener(mouseInput);
        setFocusable(true); 
        requestFocusInWindow(); 
        
    }
    public void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);

    }

    public void draw() {
        player.update();
    }
    public Player getPlayer() {
        return player;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw();
        player.render(g);
        levelManager.draw(g);
    }

}
