package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import main.Game;
import static utilz.Constants.Direction.*;
import static utilz.Constants.PlayerConstants.*;

import utilz.Constants;
import utilz.HelpMethods;
import utilz.LoadSave;
import static utilz.LoadSave.*;

public class Player extends Entity {

    private BufferedImage img;
    private BufferedImage[] animations;
    private int aniTick, aniIndex, aniSpeed = 25; //UPS 200 - 4 animtion per second
    private int playerAction = IDLE;
    private int playerDir = -1;
    private boolean running = false;
    private float playerSpeed = 1.0f * Game.SCALE;
    private float xDrawOffset =  Game.SCALE - 5;
    private float yDrawOffset = Game.SCALE - 2;
    private int[][] lvlData;

    public Player(float  x, float  y ,  int width , int height) {
        super(x, y , width , height);
        loadAnimation();
        initHitbox( x , y , 18 * Game.SCALE , 34 * Game.SCALE);

    }



    public void update() {
        setPos();
        updateHitBox();
        updateAnimationTick();
        setAnimation();
    }

    //Draw player
    public void render(Graphics g) {
        g.drawImage(animations[aniIndex + playerAction], (int) (hitbox.x - xDrawOffset), (int)(hitbox.y - yDrawOffset), width , height , null );
        drawHitBox(g);
    }

    public void loadLevelData(int[][] lvlData) {
        this.lvlData = lvlData;

    }

    //Load all animation to bufferedImage
    private void loadAnimation() {
        img = LoadSave.GetSpriteAtlas(PLAYER_ATLAS);
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
//        animations = new BufferedImage[9][6];
//        for (int i = 0; i < animations.length; i++) {
//            for (int j = 0; j < animations[i].length; j++) {
//                animations[i][j] = img.getSubimage(j * 64, i * 40, 64, 40);
//            }
//        }
    }

    public void setPos() {
        if (playerDir == -1) return; // No movement if playerDir is -1

        float xTemp = 0, yTemp = 0;

        switch (playerDir) {
            case UP:
                yTemp = -playerSpeed;
                break;
            case DOWN:
                yTemp = +playerSpeed;
                break;
            case LEFT:
                xTemp = -playerSpeed;
                break;
            case RIGHT:
                xTemp = +playerSpeed;
                break;
        }

        if (HelpMethods.canMoveHere((int) hitbox.x + xTemp, (int) hitbox.y + yTemp, hitbox.width, hitbox.height, lvlData)) {
            this.x += xTemp;
            this.y += yTemp;
        }

    }


    // public void setPos() {
    //     switch (playerDir) {
    //         case UP:
    //             y -= playerSpeed;
    //             break;
    //         case DOWN:

    //             y += playerSpeed;
    //             break;
    //         case LEFT:
    //             x -= playerSpeed;
    //             break;
    //         case RIGHT:
    //             x += playerSpeed;
    //             break;
    //         default:
    //             break;
    //     }
    // }


    public void setDirection(int direction) {
        this.playerDir = direction;
        running = true;
    }

    public void isRunning(boolean running) {
        this.running = running;
    }

    public void setAnimation() {
        if (running) {
            playerAction = RUNNING;
        }

        else {
            playerAction = IDLE;
            playerDir = -1;
        }

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

//    public BufferedImage[][] getAnimations() {
//        return animations;
//    }

    public float getDeltaX() {
        return x;
    }
    public float getDeltaY() {
        return y;
    }

    public int getAniIndex() {
        return aniIndex;
    }
    public int getPlayerAction() {
        return playerAction;
    }
}
