package GameConditions;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import main.Game;
import ui.SoundOption;
import utilz.LoadSave;

public class gameOption extends Condition implements ConditionMethods {

    private SoundOption soundOption;
    private int bgX, bgY, bgW, bgH;
    private BufferedImage backgroundImg, optionsBackgroundImg;

    public gameOption(Game game) {
		super(game);
		loadImgs();
		soundOption = game.getSoundOption();
	}  

    private void loadImgs() {
		backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.MENU_BACKGROUND);
		optionsBackgroundImg = LoadSave.GetSpriteAtlas(LoadSave.OPTIONS_MENU);

		bgW = (int) (optionsBackgroundImg.getWidth() * Game.SCALE);
		bgH = (int) (optionsBackgroundImg.getHeight() * Game.SCALE);
		bgX = Game.GAME_WIDTH / 2 - bgW / 2;
		bgY = (int) (33 * Game.SCALE);
	}

    @Override
    public void update() {
        soundOption.update();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImg, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
		g.drawImage(optionsBackgroundImg, bgX, bgY, bgW, bgH, null);

        soundOption.draw(g);
    }

    public void mouseDragged(MouseEvent e) {
		soundOption.mouseDragged(e);
	}

    @Override
    public void mouseClicked(MouseEvent e) {
       
    }

    @Override
    public void mousePressed(MouseEvent e) {
        soundOption.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        soundOption.mouseReleased(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        soundOption.mouseMoved(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

}
