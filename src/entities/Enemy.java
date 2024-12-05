package entities;

import static utilz.Constants.EnemyConstants.GetSpriteAmount;

public abstract class Enemy extends Entity{
    private int aniIndex, enemyState, enemyType;
    private int aniTick, aniSpeed = 25;
    private boolean firstUpdate = true;

    public Enemy(float x, float y, int width, int height, int enemyType) {
        super(x, y, width, height);
        this.enemyType = enemyType;
        initHitbox(x, y, width, height);
    }

    public void updateAnimationTick() {
        aniTick++;
        if(aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= GetSpriteAmount(enemyType, enemyState)) {
                aniIndex = 0;
            }
        }
    }

    public void update(int[][] levelData) {
        updateMove(levelData);
        updateAnimationTick();
    }

    private void updateMove(int[][] levelData) {

    }

    public int getAniIndex() {
        return aniIndex;
    }

    public int getEnemyState() {
        return enemyState;
    }
}
