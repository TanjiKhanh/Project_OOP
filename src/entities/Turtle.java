package entities;

import static utilz.Constants.EnemyConstants.*;
import main.Game;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;


public class Turtle extends Enemy{
    private boolean isKicked = false;
    private long kickedTime = 0;

    private EnemyManager enemyManager;
    public Turtle(float x, float y , EnemyManager enemyManager) {
        super(x, y, TURTLE_WIDTH, TURTLE_HEIGHT, TURTLE , TURTLE_RUNNING);
        initHitbox(x  , y   , (int) (20 * Game.SCALE)  , (int) (28 * Game.SCALE) );
        this.enemyManager = enemyManager;
    }

    @Override
    public void updateMove(int[][] lvlData) {

        //Handle collision with another enemies
        if (isKicked) {
            ArrayList<Enemy> enemies = enemyManager.getEnemies();
            for (Enemy enemy : enemies) {
                if (enemy != this && hitbox.intersects(enemy.getHitbox())) {
                    enemy.setDead(true); // Kill other enemies
                    enemy.setEnemyState(GetDeadAnimation(enemy.enemyType));
                }
            }
            Move(lvlData);
            return;

        }
        if(isDead)
        {
            resetAniTick();
        }
        else if (inAir)
            updateInAir(lvlData);
        else
            Move(lvlData);

        if (firstUpdate) {
            firstUpdateCheck(lvlData);
        }


    }

    @Override
    public void update(int[][] levelData)
    {
        updateMove(levelData);
        updateAnimationTick();
    }



    public void setKicked(boolean kicked, int playerDirection) {
        if (!isKicked) { // Prevent re-kicking if already kicked
            isKicked = kicked;
            walkDir = playerDirection;
            walkSpeed = 1.5f;
            this.kickedTime = System.currentTimeMillis(); // Record when kicked
        }
    }

    public boolean isKickGracePeriodOver() {
        return (System.currentTimeMillis() - kickedTime) > 200; // 100ms grace period
    }

    @Override
    public void resetEnemy() {
        hitbox.x = x;
        hitbox.y = y;
        firstUpdate = true;
        inAir = false;
        fallSpeed = 0;
        isDead = false;
        isKicked = false;
        walkSpeed = 0.35f * Game.SCALE;
        enemyState = TURTLE_RUNNING;
        resetAniTick();
    }


    @Override
    public Rectangle2D.Float getHitbox() {
        return super.getHitbox();
    }

    @Override
    public void setEnemyState(int enemyState) {
        super.setEnemyState(enemyState);
    }

    @Override
    public int getEnemyState() {
        return super.getEnemyState();
    }

    @Override
    public int getAniIndex() {
        return super.getAniIndex();
    }

    public boolean isKicked() {
        return isKicked;
    }
}
