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
    private Game game;
    
    
    public GamePanel(Game game) {

        mouseInput = new MouseInput(this);
        this.game = game;
        setPanelSize();


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
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.render(g);


    }

    public Game getGame() {
        return game;
    }
}
