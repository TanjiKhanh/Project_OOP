package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import main.Game;

import static utilz.Constants.PlayerConstants.*;

import utilz.LoadSave;

import static utilz.HelpMethods.*;
import static utilz.LoadSave.*;

public class Player extends Entity {

    private BufferedImage img;
    private BufferedImage[] animations;
    private int aniTick, aniIndex, aniSpeed = 25; //UPS 200 - 4 animtion per second
    private int playerAction = IDLE;
    private int playerDir = -1;
    private boolean running = false;
    private boolean left, up, right, down, jump;

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

    public Player(float  x, float  y ,  int width , int height) {
        super(x, y , width , height);
        loadAnimation();
        initHitbox( x , y , 18 * Game.SCALE , 34 * Game.SCALE);

    }




    public void update() {
        setPos();
        updateAnimationTick();
        setAnimation();
    }

    //Draw player
    public void render(Graphics g , int lvlOffset) {
        g.drawImage(animations[aniIndex + playerAction], (int) (hitbox.x - xDrawOffset)  - lvlOffset, (int)(hitbox.y - yDrawOffset), width , height , null );
        drawHitBox(g);
    }

    public void loadLevelData(int[][] lvlData) {
        this.lvlData = lvlData;

    }

    //Load all animation to bufferedImage
    private void loadAnimation() {
        img = LoadSave.GetSpriteAtlas(CHARACTERS_ATLAS);
        animations = new BufferedImage[28];
        for( int i = 0 ; i < animations.length ; i++ )
        {
            if( i < 9 )
                animations[i] = img.getSubimage(18 * i, 0 , 18 , 34 );
            else if ( i < 14 )
                animations[i] = img.getSubimage(18 * i + 2, 0 , 18 , 34 );
            else
                animations[i] = img.getSubimage(18 * i + 7, 0 , 18 , 34 );


        }
    }

    private void setAnimation() {
        int startAni = playerAction;

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

    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
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
            } else {
//                hitbox.y = GetEntityYPosUnderRoofOrAboveFloor(hitbox , airSpeed);
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
}
