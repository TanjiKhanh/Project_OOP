package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import main.Game;
import static utilz.Constants.Direction.*;
import static utilz.Constants.PlayerConstants.*;
import utilz.LoadSave;
import static utilz.LoadSave.*;

public class Player extends Entity {

    private BufferedImage img;
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 25; //UPS 200 - 4 animtion per second
    private int playerAction = IDLE;
    private int playerDir = -1;
    private boolean running = false;
    private float playerSpeed = 1.0f * Game.SCALE;
    private float xDrawOffset = 21 * Game.SCALE;
	private float yDrawOffset = 4 * Game.SCALE;

    public Player(float  x, float  y ,  int width , int height) {
        super(x, y , width , height);
        loadAnimation();
    }


    public void update() {
        setPos();
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics g) {
        g.drawImage(animations[playerAction][aniIndex], (int) x, (int)y, width , height , null );
    }


    
    private void loadAnimation() {
        img = LoadSave.GetSpriteAtlas(PLAYER_ATLAS);
        animations = new BufferedImage[9][6];
        for (int i = 0; i < animations.length; i++) {
            for (int j = 0; j < animations[i].length; j++) {
                animations[i][j] = img.getSubimage(j * 64, i * 40, 64, 40);
            }
        }
    }

    public void setPos() {
        switch (playerDir) {
            case UP:
                System.out.println(y);
                y -= playerSpeed;
                break;
            case DOWN:
                System.out.println(y);

                y += playerSpeed;
                break;
            case LEFT:
                x -= playerSpeed;
                break;
            case RIGHT:
                x += playerSpeed;
                break;
            default:
                break;
        }
    }

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
        } else {
            playerAction = IDLE;
            playerDir = -1;
        }
    }

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(playerAction)) {
                aniIndex = 0;
            }
        }
    }

    public BufferedImage[][] getAnimations() {
        return animations;
    }

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
