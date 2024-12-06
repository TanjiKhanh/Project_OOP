package entities;

import static utilz.Constants.EnemyConstants.*;

import main.Game;
import utilz.LoadSave;

import java.awt.image.BufferedImage;


public class Turtle extends Enemy{
    private int xOffset = Game.TILES_SIZE;
    private int yOffset = Game.TILES_SIZE;
    public Turtle(float x, float y) {
        super(x, y, TURTLE_WIDTH, TURTLE_HEIGHT, TURTLE);
        initHitbox(x  , y   , (int) (20 * Game.SCALE)  , (int) (28 * Game.SCALE) );

    }

    public void update(int[][] lvlData) {
        updateMove(lvlData);
        updateAnimationTick();
    }


    private void updateMove(int[][] lvlData) {
        if (firstUpdate) {
            firstUpdateCheck(lvlData);
        }
        if (inAir)
            updateInAir(lvlData);
        else
            switch (enemyState) {
                case IDLE:
                    enemyState = RUNNING;
                    break;
                case RUNNING:
                    Move(lvlData);
                    break;
            }
    }

    public int getxOffset() {
        return xOffset;
    }

    public int getyOffset() {
        return yOffset;
    }

    @Override
    public int getEnemyState() {
        return super.getEnemyState();
    }

    @Override
    public int getAniIndex() {
        return super.getAniIndex();
    }
}
