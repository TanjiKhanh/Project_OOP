package utilz;

import main.Game;

import java.awt.geom.Rectangle2D;

public class HelpMethods {
    public static boolean canMoveHere(float x, float y, float width, float height, int[][] lvlData) {
        //Collision on top left
        if(!isSolid(x, y, lvlData))
            //bottom right
            if(!isSolid(x + width, y + height, lvlData))
                //top right
                if(!isSolid(x + width, y, lvlData))
                    //bottom left
                    if(!isSolid(x, y + height, lvlData))
                        return true;
        return false;
    }

    //check whether the frame of player is collision with lvldata or not
    public static boolean isSolid(float x, float y, int[][] lvlData) {
        int maxLvlWidth = lvlData[0].length;
        int maxTiles = maxLvlWidth * Game.TILES_SIZE;
        if(x < 0 || x >= maxTiles)
            return true;
        if(y < 0 || y >= Game.GAME_HEIGHT)
            return true;

        float xIndex = x / Game.TILES_SIZE;
        float yIndex = y / Game.TILES_SIZE;

        int value = lvlData[(int)yIndex][(int)xIndex];

        //value = 11 is a empty space can move
        if(value >= 48 || value < 0 || value != 11)
            return true;
        return false;
    }
    public static float GetEntityXPosNextToWall(Rectangle2D.Float hitbox, float xSpeed) {
        int currentTile = (int) (hitbox.x / Game.TILES_SIZE);
        if (xSpeed > 0) {
            // Right
            int tileXPos = currentTile * Game.TILES_SIZE;
            int xOffset = (int) (Game.TILES_SIZE - hitbox.width);
            return tileXPos + xOffset - 1;
        } else
            // Left
            return currentTile * Game.TILES_SIZE;
    }

        public static float GetEntityYPosUnderRoofOrAboveFloor(Rectangle2D.Float hitbox, float airSpeed) {
        int currentTile = (int) (hitbox.y / Game.TILES_SIZE) + 1 ;
        if (airSpeed > 0) {
            // Falling - touching floor
            int tileYPos = currentTile * Game.TILES_SIZE;
            int yOffset = (int) (Game.TILES_SIZE - hitbox.height);
            return tileYPos + yOffset - 1;
        } else
            // Jumping
            currentTile = (int) (hitbox.y / Game.TILES_SIZE);
            return currentTile * Game.TILES_SIZE ;

    }

    public static boolean IsEntityOnFloor(Rectangle2D.Float hitbox, int[][] lvlData) {
        // Check the pixel below bottomleft and bottomright
        if (!isSolid(hitbox.x, hitbox.y + hitbox.height + 1, lvlData))
            if (!isSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, lvlData))
                return false;

        return true;

    }
    public static boolean isFloor(Rectangle2D.Float hitbox, float xSpeed, int[][] lvlData) {
        return isSolid(hitbox.x + xSpeed + hitbox.width, hitbox.y + hitbox.height + 1, lvlData);
    }
    public static boolean nextToWall(Rectangle2D.Float hitbox, float xSpeed, int[][] lvlData) {
        return isSolid(hitbox.x + xSpeed, hitbox.y + hitbox.height + 1, lvlData) ;
    }
}
