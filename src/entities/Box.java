package entities;

import main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilz.Constants.BoxConstannts.*;
import static utilz.LoadSave.*;

public class Box extends Entity{
    private BufferedImage boxImg;
    public Box(float x, float y) {
        super(x, y, BOX_SIDES_DEFAULT, BOX_SIDES_DEFAULT);
        importImg();
    }

    public void draw(Graphics g , int lvlOffset) {
        g.drawImage( boxImg ,  (int) x - lvlOffset , (int) y , (int)(BOX_SIDES_DEFAULT * Game.SCALE ), (int)(BOX_SIDES_DEFAULT * Game.SCALE) , null);
    }

    public void importImg() {
        BufferedImage img = GetSpriteAtlas(BOX_IMAGE);
        boxImg = img.getSubimage(64 , 384 , BOX_SIDES_DEFAULT , BOX_SIDES_DEFAULT);

    }




}
