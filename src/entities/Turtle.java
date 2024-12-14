package entities;

import static utilz.Constants.Direction.LEFT;
import static utilz.Constants.EnemyConstants.*;
import static utilz.HelpMethods.*;

import main.Game;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;


public class Turtle extends Enemy{
    private int xOffset = Game.TILES_SIZE;
    private int yOffset = Game.TILES_SIZE;
    private boolean isKicked = false;
    private boolean recentlyDead; // To manage a grace period
    private boolean recentlyKick;
    private long deathTime;       // Timestamp for when the turtle was marked as dead
    private long kickTime;       // Timestamp for when the turtle was marked as dead

    private EnemyManager enemyManager;
    public Turtle(float x, float y , EnemyManager enemyManager) {
        super(x, y, TURTLE_WIDTH, TURTLE_HEIGHT, TURTLE , TURTLE_RUNNING);
        initHitbox(x  , y   , (int) (20 * Game.SCALE)  , (int) (28 * Game.SCALE) );
        this.enemyManager = enemyManager;
    }

    @Override
    public void updateMove(int[][] lvlData) {


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
            recentlyKick = true;
        }
    }


    // Call this when the turtle is marked as dead
    public void setRecentlyDead(boolean recentlyDead) {
        this.recentlyDead = recentlyDead;
        if (recentlyDead) {
            deathTime = System.currentTimeMillis();
        }
    }

    // Check if the turtle is ready to be kicked
    public boolean isReadyToBeKicked() {
        if (!recentlyDead) return true; // If not recently dead, can be kicked
        return System.currentTimeMillis() - deathTime > 500; // Grace period of 500ms
    }

    // Check if the turtle is ready to be kicked
    public boolean isReadyToBeSpinned() {
        if (!recentlyKick) return true; // If not recently dead, can be kicked
        return System.currentTimeMillis() - kickTime > 500; // Grace period of 500ms
    }

    // Call this when the turtle is marked as dead
    public void setRecentlyKick(boolean recentlyDead) {
        this.recentlyKick = recentlyDead;
        if (recentlyKick) {
            kickTime = System.currentTimeMillis();
        }
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
