package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import GameConditions.Playing;
import main.Game;

import static utilz.Constants.PlayerConstants.*;
import static utilz.Constants.EnemyConstants;

import utilz.LoadSave;

import static utilz.HelpMethods.*;
import static utilz.LoadSave.*;

public class Player extends Entity {

    private Playing playing;

    private BufferedImage img;
    private BufferedImage[] bigMarioAnimations;
    private int aniTick, aniIndex, aniSpeed = 25; //UPS 200 - 4 animtion per second
    private int playerAction = IDLE;
    private int playerDir = -1;
    private boolean running = false;
    private boolean left, right, jump;


    private float playerSpeed = 1.0f * Game.SCALE;
    private float xDrawOffset =  Game.SCALE - 4;
    private float yDrawOffset = Game.SCALE - 3;
    private int[][] lvlData;

    // Jumping / Gravity
    private float airSpeed = 0f;
    private float gravity = 0.04f * Game.SCALE;
    private float jumpSpeed = -2.25f * Game.SCALE;
    private float fallSpeedAfterCollision = 0.5f * Game.SCALE;
    private boolean inAir = false;
    private EnemyManager enemyManager;


    //Dead
    private boolean isDead = false; // Tracks if the player is in the death animation
    private float deathVelocityY = -1.2f * Game.SCALE; // Initial bounce velocity
    private float deathGravity = 0.01f * Game.SCALE; // Gravity for death animation

    public Player(float  x, float  y ,  int width , int height , EnemyManager enemyManager , Playing playing) {
        super(x, y , width , height);
        loadAnimation();
        initHitbox( x , y , 18 * Game.SCALE , 34 * Game.SCALE);
        this.enemyManager = enemyManager;
        this.playing = playing;
    }




    public void update() {

        if (isDead) {
            handleDeathAnimation(); // Continue death animation if necessary
            return; // Skip collision and other updates
        }
        //collision before setPos to handle reset airspeed
        collisionEnemy();
        setPos();
        setAnimation();
        updateAnimationTick();
    }

    //Draw player
    public void render(Graphics g , int lvlOffset) {
        g.drawImage(bigMarioAnimations[playerAction + aniIndex], (int) (hitbox.x - xDrawOffset)  - lvlOffset, (int)(hitbox.y - yDrawOffset), (int)(BIG_MARIO_WIDTH_DEFAULT * Game.SCALE) ,  (int)(BIG_MARIO_HEIGHT_DEFAULT * Game.SCALE) , null );
    }

    public void loadLevelData(int[][] lvlData) {
        this.lvlData = lvlData;

    }

    //Load all animation to bufferedImage
    private void loadAnimation() {
        img = LoadSave.GetSpriteAtlas(CHARACTERS_ATLAS);

        //Load big mario animation
        bigMarioAnimations = new BufferedImage[29];
        for(int i = 0; i < bigMarioAnimations.length ; i++ )
        {
            if( i < 9 )
                bigMarioAnimations[i] = img.getSubimage(BIG_MARIO_WIDTH_DEFAULT * i, 0 , BIG_MARIO_WIDTH_DEFAULT , BIG_MARIO_HEIGHT_DEFAULT );
            else if ( i < 14 )
                bigMarioAnimations[i] = img.getSubimage(BIG_MARIO_WIDTH_DEFAULT * i + 2, 0 , BIG_MARIO_WIDTH_DEFAULT , BIG_MARIO_HEIGHT_DEFAULT );
            else if( i == 28)
                bigMarioAnimations[i] = img.getSubimage(12, 44 , 16 , 17 );
            else
                bigMarioAnimations[i] = img.getSubimage(BIG_MARIO_WIDTH_DEFAULT * i + 7, 0 , BIG_MARIO_WIDTH_DEFAULT , BIG_MARIO_HEIGHT_DEFAULT );

        }


    }

    private void setAnimation() {
        int startAni = playerAction;

        //if isDead = true return and reset aniTick
        if(isDead)
        {
            resetAniTick();
            return;
        }
        if (running)
            playerAction = RUNNING;
        else
            playerAction = IDLE;

        if (inAir) {
            if (airSpeed < 0)
                playerAction = JUMP;
            else
                playerAction = FALLING;
        }

        if (startAni != playerAction)
            resetAniTick();
    }



    private void handleDeathAnimation() {
        hitbox.y += deathVelocityY; // Move the player vertically
        deathVelocityY += deathGravity; // Apply gravity to velocity
        if(hitbox.y >= Game.GAME_HEIGHT)
            playing.setGameOver(true);


    }
//
//    private void deadMovement() {
//        if (isDead) return; // Prevent restarting the animation
//        isDead = true; // Set death state
//        playerAction = DEAD; // Set animation to dead
//        deathVelocityY = -0.05f  * Game.SCALE; // Reset upward bounce velocity
//    }

//    private void resetDeathAnimation() {
//        playerAction = IDLE; // Reset to idle state after animation
//        deathVelocityY = 0; // Reset velocity
//    }

    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }


    //Handle collision player with enemy
    private void collisionEnemy()
    {
        //Turtle Collision
        ArrayList<Turtle> turtles = enemyManager.getTurtles();
        for( int i = 0 ; i < turtles.size() ; i++)
        {
            Turtle turtle = turtles.get(i);
            if(turtle.isDead())
                continue;
            //if collision above the turtles frames
            if(playerCollisionAboveEnemies(hitbox , turtle.getHitbox() ))
            {
                //set enemy animation dead
                turtle.setEnemyState(EnemyConstants.TURTLE_DEAD);
                //Smoothly movement
                airSpeed += 1.5f * jumpSpeed;
                turtle.setDead(true);
            }
            //Collsion other direction
            else if( hitbox.intersects(turtle.getHitbox()))
                deadMovement();
        }


        //Mushroom Collision
        ArrayList<Mushroom> mushrooms = enemyManager.getMushrooms();
        for( int i = 0 ; i < mushrooms.size() ; i++)
        {
            Mushroom mushroom = mushrooms.get(i);
            if(mushroom.isDead())
                continue;
            //if collision above the turtles frames
            if(playerCollisionAboveEnemies(hitbox , mushroom.getHitbox() ))
            {
                //set enemy animation dead
                mushroom.setEnemyState(EnemyConstants.MUSHROOM_DEAD);
                //Smoothly movement
                airSpeed += 1.5f * jumpSpeed;
                mushroom.setDead(true);
            }
            //Collsion other direction
            else if( hitbox.intersects(mushroom.getHitbox()))
                deadMovement();
        }
    }



    private void setPos() {
        running = false;

        if (jump)
            jump();

        if (!inAir)
            if ((!left && !right) || (right && left))
                return;

        float xSpeed = 0;

        if (left)
            xSpeed -= playerSpeed;
        if (right)
            xSpeed += playerSpeed;

        if (!inAir)
            if (!IsEntityOnFloor(hitbox, lvlData))
                inAir = true;
        if (inAir) {
            if (canMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, lvlData)) {

                hitbox.y += airSpeed;
                airSpeed += gravity;
                updateXPos(xSpeed);
                if(hitbox.y >= Game.GAME_HEIGHT)
                {
                    deadMovement();
                    return;
                }
            } else {
                hitbox.y = GetEntityYPosUnderRoofOrAboveFloor(hitbox , airSpeed );

                //hit the floor reset air speed
                if (airSpeed > 0)
                    resetInAir();
                else
                    airSpeed = fallSpeedAfterCollision;
                updateXPos(xSpeed);
            }

        } else
            updateXPos(xSpeed);

        running = true;
    }


    private void deadMovement()
    {
        playerAction = DEAD;
        isDead = true;
    }

    private void jump() {
        if (inAir)
            return;
        inAir = true;
        airSpeed = jumpSpeed;
    }

    private void resetInAir() {
        inAir = false;
        airSpeed = 0;

    }

    private void updateXPos(float xSpeed) {
        if (canMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData)) {
            hitbox.x += xSpeed;
        }
        else
            hitbox.x = GetEntityXPosNextToWall(hitbox , xSpeed);

    }


    //Draw animation per tick
    private void updateAnimationTick() {
        if(isDead)
            return;
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if ( aniIndex > GetSpriteAmount(playerAction)  ) {
                aniIndex = 0;
            }
        }
    }


    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }

    public int getAniIndex() {
        return aniIndex;
    }
    public int getPlayerAction() {
        return playerAction;
    }

    public void resetAll() {
        hitbox.x = x;
        hitbox.y = y;
        resetInAir();
        running = false;
        playerAction = IDLE;
        left = false;
        right = false;
        jump = false;
        isDead = false;
    }
}
