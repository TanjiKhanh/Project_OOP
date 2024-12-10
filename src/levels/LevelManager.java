package levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import entities.Box;
import main.Game;
import utilz.LoadSave;

import static utilz.LoadSave.GetBox;

public class LevelManager {

    private Game game;
    private BufferedImage [] levelSprite;
    private Level levelOne;
    private BufferedImage backgroundImage , smallCloudImage , bigCloudImage;
    private int [] smallCloudsPos;
    private Random random = new Random();
    private ArrayList<Box> boxes;

    public LevelManager(Game game) {
        importOutsideSprites();
        levelOne = new Level(LoadSave.GetLevelData());
        this.game = game;

        boxes = GetBox();

        smallCloudsPos = new int[16];
        for (int i = 0; i < smallCloudsPos.length; i++)
            smallCloudsPos[i] = (int) (90 * Game.SCALE) + random.nextInt((int) (100 * Game.SCALE));
    }

    private void importOutsideSprites() {
        //Background image
        backgroundImage = LoadSave.GetSpriteAtlas(LoadSave.BACKGROUND_IMAGE);


        //Big cloud image
        bigCloudImage = LoadSave.GetSpriteAtlas(LoadSave.BIG_CLOUD_IMAGE);

        //Small cloud image
        smallCloudImage = LoadSave.GetSpriteAtlas(LoadSave.SMALL_CLOUD_IMAGE);

        //Level background 2D array
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
        levelSprite = new BufferedImage[48];
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 12; i++) {
                int index = j * 12 + i;
                levelSprite[index] = img.getSubimage(i * 32, j * 32, 32, 32);
            }
        }
    }


    public void draw(Graphics g , int lvlOffset) {


        //Draw background image
        g.drawImage(backgroundImage, 0, 0, Game.GAME_WIDTH , Game.GAME_HEIGHT , null);

        //Draw big cloud
        for (int i = 0; i < 12; i++) {
                g.drawImage(bigCloudImage , i * 448 -  lvlOffset , 360 , 448 , 101, null );
        }

        //Draw small cloud
        for (int i = 0; i < smallCloudsPos.length; i++)
            g.drawImage(smallCloudImage, 74 * 4 * i - (int) (lvlOffset * 0.7), smallCloudsPos[i], 74, 24, null);

        //Draw level Background 2D array
        for (int j = 0; j < Game.TILES_IN_HEIGHT; j++) {
            for (int i = 0; i < levelOne.getLevelData()[0].length; i++) {
                int index = levelOne.getSpriteIndex(i, j);
                g.drawImage(levelSprite[index], Game.TILES_SIZE * i - lvlOffset, Game.TILES_SIZE * j, Game.TILES_SIZE, Game.TILES_SIZE, null);
            }
        }

        //Draw box
        for(Box box : boxes)
            box.draw(g , lvlOffset);
    }

    public void update() {

    }

    public Level getCurrentLevel() {
        return levelOne;
    }


}
