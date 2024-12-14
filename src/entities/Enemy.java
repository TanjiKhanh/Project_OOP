package entities;

import main.Game;

import static utilz.Constants.Direction.*;
import static utilz.Constants.EnemyConstants.*;
import static utilz.HelpMethods.*;


public abstract class Enemy extends Entity{
    protected int aniIndex, enemyState, enemyType;
    private int aniTick, aniSpeed = 50;
    protected boolean firstUpdate = true;
    protected boolean inAir = false;
    protected float fallSpeed = 0;
    protected float gravity = 0.04f * Game.SCALE;
    protected float walkSpeed = 0.35f * Game.SCALE;
    protected int walkDir = LEFT;
    protected boolean isDead = false;

    public Enemy(float x, float y, int width, int height, int enemyType ,int enemyState) {
        super(x, y, width, height);
        this.enemyType = enemyType;
        this.enemyState = enemyState;
        initHitbox(x, y, width, height);
    }

    protected void firstUpdateCheck(int[][] lvlData) {
        if (!IsEntityOnFloor(hitbox, lvlData))
            inAir = true;
        firstUpdate = false;
    }

    protected void updateInAir(int[][] lvlData) {
        if (inAir) {
            if (canMoveHere(hitbox.x, hitbox.y + fallSpeed, hitbox.width, hitbox.height, lvlData)) {
                hitbox.y += fallSpeed;
                fallSpeed += gravity;
            } else {
                inAir = false;
            }
        }
    }

    public void Move(int[][] lvlData) {
        float xSpeed = 0;

        if (walkDir == LEFT)
            xSpeed = -walkSpeed;
        else
            xSpeed = walkSpeed;
        if (canMoveHere(hitbox.x, hitbox.y, hitbox.width, hitbox.height, lvlData))
            if (!isOnFloor(hitbox, xSpeed, lvlData) ){
                changeWalkDir();
            }
            else if(nextToWall(hitbox, xSpeed, lvlData))
                changeWalkDir();
            else
                hitbox.x += xSpeed;
    }

    public void updateAnimationTick() {
        aniTick++;
        if(aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= GetSpriteAmount(enemyType, enemyState))
                aniIndex = 0;
        }
    }

    public abstract void updateMove(int[][] lvlData);

    public void update(int[][] levelData)
    {
        if(isDead)
        {
            resetAniTick();
            updateAnimationTick();
            return;
        }
        updateMove(levelData);
        updateAnimationTick();
    }

    protected void changeWalkDir() {
        if (walkDir == LEFT)
            walkDir = RIGHT;
        else
            walkDir = LEFT;

    }

    protected void resetAniTick() {

        aniTick = 0;
        aniIndex = 0;
    }


    public void setEnemyState(int enemyState) {
        this.enemyState = enemyState;
    }


    public int getAniIndex() {
        return aniIndex;
    }

    public int getEnemyState() {
        return enemyState;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }


    public void resetEnemy() {
        hitbox.x = x;
        hitbox.y = y;
        firstUpdate = true;
        inAir = false;
        fallSpeed = 0;
        isDead = false;

        switch (enemyType) {
            case TURTLE:
                enemyState = TURTLE_RUNNING;
                break;
            case MUSHROOM:
                enemyState = MUSHROOM_RUNNING;
                break;
            case JOKER:
                enemyState = JOKER_IDLE;
                break;
        }

    }
}
