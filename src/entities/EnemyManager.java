package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.GamePanel;
import utilz.LoadSave;
import static utilz.Constants.EnemyConstants.*;

public class EnemyManager {
    private BufferedImage [] turtleArr;
    private BufferedImage [] mushroomArr;
    private BufferedImage [] jokerArr;
    private ArrayList<Enemy> enemies;
    private ArrayList<Turtle> turtles;
    private ArrayList<Mushroom> mushrooms;
    private ArrayList<Joker> jokers;

    public EnemyManager() {
        loadEnemyImgs();
        addEnemies();
    }


    private void addEnemies() {
        enemies = new ArrayList<>();
        turtles = LoadSave.GetTurtle(this);
        mushrooms = LoadSave.GetMushroom();
        jokers = LoadSave.GetJoker();
        enemies.addAll(turtles);
        enemies.addAll(mushrooms);
        enemies.addAll(jokers);


    }

    public void update(int[][] lvlData) {
        for(Turtle i : turtles) {
            i.update(lvlData);
        }

        for (Mushroom m : mushrooms) {
            m.update(lvlData);
        }

        for (Joker j : jokers) {
            j.update(lvlData);
        }
    }

    public void draw(Graphics g , int lvlOffset) {
        drawTurtle(g , lvlOffset);
        drawMushroom(g, lvlOffset);
        drawJoker(g, lvlOffset);
    }

    private void drawTurtle(Graphics g , int lvlOffset) {
        for(Turtle turtle : turtles) {
            g.drawImage(turtleArr[turtle.getEnemyState() + turtle.getAniIndex()] , (int)turtle.hitbox.x - lvlOffset , (int)turtle.hitbox.y  , TURTLE_WIDTH, TURTLE_HEIGHT, null);
        }
    }

    private void drawMushroom(Graphics g , int lvlOffset) {
        for(Mushroom mushroom : mushrooms) {
            g.drawImage(mushroomArr[mushroom.getEnemyState() + mushroom.getAniIndex()], (int) mushroom.hitbox.x - lvlOffset, (int) mushroom.hitbox.y  , MUSHROOM_WIDTH, MUSHROOM_HEIGHT, null);
        }
    }

    private void drawJoker(Graphics g , int lvlOffset) {
        for(Joker joker : jokers) {
            g.drawImage(jokerArr[joker.getEnemyState() + joker.getAniIndex()], (int) joker.hitbox.x - lvlOffset, (int) joker.hitbox.y, JOKER_WIDTH, JOKER_HEIGHT, null);
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

        jokerArr = new BufferedImage[2];
        BufferedImage temp2 = LoadSave.GetSpriteAtlas(LoadSave.CHARACTERS_ATLAS);
        for (int i = 0; i < jokerArr.length; i++) {
            jokerArr[i] = temp2.getSubimage(219 + i * JOKER_WIDTH_DEFAULT, 287, JOKER_WIDTH_DEFAULT, JOKER_HEIGHT_DEFAULT);
        }
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void resetAllEnermies() {
        for(Turtle i : turtles) {
            i.resetEnemy();
        }

        for(Mushroom m: mushrooms) {
            m.resetEnemy();
        }

        for(Joker j: jokers) {
            j.resetEnemy();
        }
    }
}
