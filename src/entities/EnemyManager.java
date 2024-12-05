package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.Game;
import main.GamePanel;
import utilz.LoadSave;
import static utilz.Constants.EnemyConstants.*;

public class EnemyManager {
    // private Playing playing; // chua co class
    private BufferedImage [][] turtleArr;
    private ArrayList<Turtle> turtles = new ArrayList<>();
    private GamePanel gamePanel;

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

    // public void update(int[][] levelData) {
    //     for(Turtle i : turtles) {
    //         i.update(levelData);
    //     }
    // }

    public void draw(Graphics g) {
        drawTurtle(g);
    }

    private void drawTurtle(Graphics g) {
        for(Turtle i : turtles) {
            g.drawImage(turtleArr[0][0], (int)i.x - i.getxOffset(), (int)i.y - i.getyOffset(), TURTLE_WIDTH, TURTLE_HEIGHT, null);
            // getHitbox viet o x, y.
            i.drawHitBox(g);
        }
    }
    private void loadEnemyImgs() {
        turtleArr = new BufferedImage[5][9];
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.TURTLES);
        for(int j = 0; j < turtleArr.length; j++) {
            for(int i = 0; i < turtleArr[j].length; i++) {
                turtleArr[j][i] = temp.getSubimage(i * TURTLE_WIDTH_DEFAULT, j * TURTLE_HEIGHT_DEFAULT, TURTLE_WIDTH_DEFAULT, TURTLE_HEIGHT_DEFAULT);
            }
        }
    }


}
