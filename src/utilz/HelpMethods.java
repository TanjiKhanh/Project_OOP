package utilz;

import main.Game;

import java.awt.geom.Rectangle2D;

public class HelpMethods {
    public static boolean canMoveHere(float x, float y, float width, float height, int[][] lvlData) {
        if(!isSolid(x, y, lvlData))
            if(!isSolid(x + width, y + height, lvlData))
                if(!isSolid(x + width, y, lvlData))
                    return !isSolid(x, y + height, lvlData);
        return false;
    }

    public static boolean isSolid(float x, float y, int[][] lvlData) {
        int maxLvlWidth = lvlData[0].length;
        int maxTiles = maxLvlWidth * Game.TILES_SIZE;
        if(x < 0 || x >= maxTiles)
            return true;
        if(y < 0 || y >= Game.GAME_HEIGHT)
            return false;

        float xIndex = x / Game.TILES_SIZE;
        float yIndex = y / Game.TILES_SIZE;

        int value = lvlData[(int)yIndex][(int)xIndex];

        return value != 11;
    }

    public static float GetEntityXPosNextToWall(Rectangle2D.Float hitbox, float xSpeed) {
        int currentTile;
        if (xSpeed > 0) {
            // Right
            currentTile = (int) (hitbox.x / Game.TILES_SIZE);
            int tileXPos = currentTile * Game.TILES_SIZE;
            int xOffset = (int) (Game.TILES_SIZE - hitbox.width);
            return tileXPos + xOffset - 1;
        }
        else
        {
            // Left
            currentTile = Math.round(hitbox.x / Game.TILES_SIZE);
            return currentTile * Game.TILES_SIZE + 1 ;
        }

    }



    public static float GetEntityYPosUnderRoofOrAboveFloor(Rectangle2D.Float hitbox, float airSpeed) {
        int currentTile = Math.round(hitbox.y / Game.TILES_SIZE);
        if (airSpeed > 0) {
            // Falling - touching floor
            int tileYPos = (currentTile) * Game.TILES_SIZE;
            int yOffset = (int) (Game.TILES_SIZE - hitbox.height);
            return tileYPos + yOffset - 1;
        } else
            // Jumping
            return currentTile * Game.TILES_SIZE;

    }



    public static boolean IsEntityOnFloor(Rectangle2D.Float hitbox, int[][] lvlData) {
        // Check the pixel below bottomleft and bottomright
        if (!isSolid(hitbox.x, hitbox.y + hitbox.height + 1, lvlData))
            return isSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, lvlData);

        return true;
    }

    public static boolean isOnFloor(Rectangle2D.Float hitbox, float xSpeed, int[][] lvlData) {
        // Check if it's on the floor
        boolean onFloor = false;
        if (xSpeed > 0) {
            onFloor = isSolid(hitbox.x + xSpeed + hitbox.width, hitbox.y + hitbox.height + 1, lvlData);
        } else {
            onFloor = isSolid(hitbox.x + xSpeed, hitbox.y + hitbox.height + 1, lvlData);
        }

        return onFloor;
    }


    public static boolean nextToWall(Rectangle2D.Float hitbox, float xSpeed, int[][] lvlData) {
        // Check if it's next to a wall
        boolean nextToWall = false;
        if (xSpeed > 0) {
            nextToWall = isSolid(hitbox.x + xSpeed + hitbox.width, hitbox.y, lvlData); // Check right side for a wall
        } else if (xSpeed < 0) {
            nextToWall = isSolid(hitbox.x + xSpeed, hitbox.y, lvlData); // Check left side for a wall
        }
        return  nextToWall;
    }


    public static boolean playerCollisionAboveEnemies(Rectangle2D.Float playerHitbox , Rectangle2D.Float enemyHitbox) {
        if (playerHitbox.intersects(enemyHitbox)) {
            // Small tolerance
            return playerHitbox.y + playerHitbox.height <= enemyHitbox.y + 5;
        }
        return false;
    }
}
