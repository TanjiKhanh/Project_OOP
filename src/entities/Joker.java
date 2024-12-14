package entities;

import main.Game;

import java.awt.geom.Rectangle2D;

import static utilz.Constants.EnemyConstants.*;

public class Joker extends Enemy {
    public Joker(float x, float y) {
        super(x, y, JOKER_WIDTH, JOKER_HEIGHT, JOKER, JOKER_IDLE);
        initHitbox(x, y, (int) (JOKER_WIDTH_DEFAULT * Game.SCALE), (int) (JOKER_HEIGHT_DEFAULT * Game.SCALE));
    }


    //Not move
    @Override
    public void updateMove(int[][] lvlData){
        if (firstUpdate) {
            firstUpdateCheck(lvlData);
        }
        if (inAir)
            updateInAir(lvlData);
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


