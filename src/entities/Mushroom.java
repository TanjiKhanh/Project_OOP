package entities;

import main.Game;

import java.awt.geom.Rectangle2D;

import static utilz.Constants.EnemyConstants.*;

public class Mushroom extends Enemy {

    public Mushroom(float x, float y) {
        super(x, y, MUSHROOM_WIDTH, MUSHROOM_HEIGHT, MUSHROOM , MUSHROOM_RUNNING);
        initHitbox(x, y, (int) (19 * Game.SCALE), (int) (19 * Game.SCALE));
    }

    @Override
    public void updateMove(int[][] lvlData) {
        if (firstUpdate) {
            firstUpdateCheck(lvlData);
        }
        if (inAir)
            updateInAir(lvlData);
        else
            Move(lvlData);
    }



    @Override
    public Rectangle2D.Float getHitbox() { return super.getHitbox(); }

    @Override
    public void setEnemyState(int enemyState) { super.setEnemyState(enemyState);}

    @Override
    public int getEnemyState() { return super.getEnemyState(); }

    @Override
    public int getAniIndex() { return super.getAniIndex(); }
}

