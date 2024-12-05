package entities;

import static utilz.Constants.EnemyConstants.*;

import main.Game;



public class Turtle extends Enemy{
    private int xOffset = Game.TILES_SIZE;
    private int yOffset = Game.TILES_SIZE;
    public Turtle(float x, float y) {
        super(x, y, TURTLE_WIDTH, TURTLE_HEIGHT, TURTLE);
        initHitbox(x, y, (int) (72 * Game.SCALE)  , (int) (32 * Game.SCALE) );

    }

    public int getxOffset() {
        return xOffset;
    }

    public int getyOffset() {
        return yOffset;
    }
}
