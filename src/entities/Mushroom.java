package entities;

import main.Game;

import java.awt.geom.Rectangle2D;

import static utilz.Constants.EnemyConstants.*;

public class Mushroom extends Enemy {
    private int xOffset = Game.TILES_SIZE;
    private int yOffset = Game.TILES_SIZE;
    public Mushroom(float x, float y) {
        super(x, y, MUSHROOM_WIDTH, MUSHROOM_HEIGHT, MUSHROOM);
        initHitbox(x, y, (int) (19 * Game.SCALE), (int) (19 * Game.SCALE));
    }

    public void update(int[][] lvlData) {
        updateMove(lvlData);
        updateAnimationTick();
    }

    private void updateMove(int[][] lvlData) {
        if (firstUpdate) {
            firstUpdateCheck(lvlData);
        }
        if(inAir)
            updateInAir(lvlData);
        else
            Move(lvlData);

    }

    public int getxOffset() { return xOffset; }
    public int getyOffset() { return yOffset; }

    @Override
    public Rectangle2D.Float getHitbox() { return super.getHitbox(); }

    @Override
    public void setEnemyState(int enemyState) { super.setEnemyState(enemyState);}

    @Override
    public int getEnemyState() { return super.getEnemyState(); }

    @Override
    public int getAniIndex() { return super.getAniIndex(); }
}

