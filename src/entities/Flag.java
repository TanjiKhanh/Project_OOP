package entities;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilz.Constants.FlagConstants.*;
import static utilz.LoadSave.FLAG_IMAGE;
import static utilz.LoadSave.GetSpriteAtlas;

public class Flag extends Entity{
    private BufferedImage  flag;
    public Flag(float x, float y)
    {
        super(x, y, FLAG_DEFAULT_WIDTH, FLAG_DEFAULT_HEIGHT);
        initHitbox(x + 28 , y + 19 , FLAG_DEFAULT_WIDTH - 40 , FLAG_DEFAULT_HEIGHT );
        flag = GetSpriteAtlas(FLAG_IMAGE);
    }

    public void draw(Graphics g , int lvlOffset)
    {
        g.drawImage(flag , (int) x  - lvlOffset, (int) y + 19 ,  (int) (FLAG_DEFAULT_WIDTH  ) , (int) (FLAG_DEFAULT_HEIGHT  ) , null );
        drawHitBox(g);
    }
}
