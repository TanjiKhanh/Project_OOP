package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.GamePanel;
import utilz.LoadSave;
import static utilz.Constants.EnemyConstants.*;

public class EnemyManager {
    // private Playing playing; // chua co class
    private BufferedImage [] turtleArr;
    private BufferedImage [] mushroomArr;
    private ArrayList<Turtle> turtles = new ArrayList<>();
    private ArrayList<Mushroom> mushrooms = new ArrayList<>();
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
        for(Turtle turtle : turtles){
            turtle.setEnemyState(TURTLE_IDLE);
        }
        mushrooms = LoadSave.GetMushroom();
        for(Mushroom m : mushrooms)
        {
            m.setEnemyState(MUSHROOM_RUNNING);
        }
    }

    public void update(int[][] lvlData) {
        for(Turtle i : turtles) {
            i.update(lvlData);
        }

        for (Mushroom m : mushrooms) {
            m.update(lvlData);
        }
    }

    public void draw(Graphics g , int lvlOffset) {
        drawTurtle(g , lvlOffset);
        drawMushroom(g, lvlOffset);
    }

    private void drawTurtle(Graphics g , int lvlOffset) {
        for(Turtle turtle : turtles) {
            g.drawImage(turtleArr[turtle.getEnemyState() + turtle.getAniIndex()] , (int)turtle.hitbox.x - lvlOffset , (int)turtle.hitbox.y  , TURTLE_WIDTH, TURTLE_HEIGHT, null);
        }
    }

    private void drawMushroom(Graphics g , int lvlOffset) {
        for(Mushroom mushroom : mushrooms) {
            g.drawImage(mushroomArr[mushroom.getEnemyState() + mushroom.getAniIndex()], (int) mushroom.hitbox.x - lvlOffset, (int) mushroom.hitbox.y  , MUSHROOM_WIDTH, MUSHROOM_HEIGHT, null);
            mushroom.drawHitBox(g);
        }
    }

    private void loadEnemyImgs() {
        turtleArr = new BufferedImage[24];
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.CHARACTERS_ATLAS);
        for (int i = 0; i < turtleArr.length; i++) {
            turtleArr[i] = temp.getSubimage(29 + i * 19,  203 , TURTLE_WIDTH_DEFAULT, TURTLE_HEIGHT_DEFAULT);
        }
        mushroomArr = new BufferedImage[3];
        BufferedImage temp1 = LoadSave.GetSpriteAtlas(LoadSave.CHARACTERS_ATLAS);
        for (int i = 0; i < mushroomArr.length; i++) {
            mushroomArr[i] = temp1.getSubimage(276 + i * MUSHROOM_WIDTH_DEFAULT, 185, MUSHROOM_WIDTH_DEFAULT, MUSHROOM_HEIGHT_DEFAULT);
        }
    }

    public ArrayList<Turtle> getTurtles() {
        return turtles;
    }
    public ArrayList<Mushroom> getMushrooms() { return mushrooms; }

    public void resetAllEnermy() {
        for(Turtle i : turtles) {
            i.resetEnemy();
        }
    }
}
