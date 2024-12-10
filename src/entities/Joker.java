package entities;

import main.Game;

import java.awt.geom.Rectangle2D;

import static utilz.Constants.EnemyConstants.*;

public class Joker extends Enemy {
    private int xOffSet = Game.TILES_SIZE;
    private int yOffSet = Game.TILES_SIZE;
    public Joker(float x, float y) {
        super(x, y, JOKER_WIDTH, JOKER_HEIGHT, JOKER, JOKER_IDLE);
        initHitbox(x, y, (int) (JOKER_WIDTH_DEFAULT * Game.SCALE), (int) (JOKER_HEIGHT_DEFAULT * Game.SCALE));
    }


    @Override
    public void Move(int[][] lvlData) {
        // Không làm gì cả vì Joker đứng yên
    }

    @Override
    public void updateMove(int[][] lvlData) {
        if (firstUpdate) {
            firstUpdateCheck(lvlData); // Gọi kiểm tra trạng thái ban đầu
        }
        if (inAir) {
            updateInAir(lvlData); // Xử lý khi đang ở trên không
        }
        // Joker sẽ không di chuyển nếu không ở trên không
    }

    @Override
    public void update(int[][] lvlData) {
        updateMove(lvlData);
        updateAnimationTick();
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


