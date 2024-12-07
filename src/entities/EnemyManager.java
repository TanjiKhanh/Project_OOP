package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.Game;
import main.GamePanel;
import utilz.LoadSave;
import static utilz.Constants.EnemyConstants.*;
import static utilz.Constants.PlayerConstants.GetSpriteAmount;

public class EnemyManager {
    // private Playing playing; // chua co class
    private BufferedImage [] turtleArr;
    private ArrayList<Turtle> turtles = new ArrayList<>();
    private GamePanel gamePanel;
    private int aniTick, aniSpeed = 25;

    public EnemyManager() {
        loadEnemyImgs();
        addEnemies();
    }

//    public EnemyManager() {
//        loadEnemyImgs();
//    }

    private void addEnemies() {
        turtles = LoadSave.GetTurtle();
    }

    public void update(int[][] lvlData) {
        for(Turtle i : turtles) {
            i.update(lvlData);
        }
    }

    public void draw(Graphics g , int lvlOffset) {
        drawTurtle(g , lvlOffset);
    }

    private void drawTurtle(Graphics g , int lvlOffset) {
        for(Turtle turtle : turtles) {
            g.drawImage(turtleArr[turtle.getEnemyState() + turtle.getAniIndex()] , (int)turtle.hitbox.x - lvlOffset , (int)turtle.hitbox.y  , TURTLE_WIDTH, TURTLE_HEIGHT, null);
        }
    }




    private void loadEnemyImgs() {
        turtleArr = new BufferedImage[24];
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.CHARACTERS_ATLAS);
        for (int i = 0; i < turtleArr.length; i++) {
            {
//                if( i < 3)
                turtleArr[i] = temp.getSubimage(29 + i * 19,  203 , TURTLE_WIDTH_DEFAULT, TURTLE_HEIGHT_DEFAULT);
//                else
//                    turtleArr[i] = temp.getSubimage(29 + i * 18,  206 , TURTLE_WIDTH_DEFAULT, TURTLE_HEIGHT_DEFAULT);

            }
        }
    }

}
