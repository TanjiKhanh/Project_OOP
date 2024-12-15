package GameConditions;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import main.Game;
import ui.SoundOption;
import ui.UrmButton;
import utilz.LoadSave;

import static utilz.Constants.UI.URMButtons.URM_SIZE;

public class gameOption extends Condition implements ConditionMethods {

    private UrmButton menuB;
    private SoundOption soundOption;
    private int bgX, bgY, bgW, bgH;
    private BufferedImage backgroundImg, optionsBackgroundImg;

    public gameOption(Game game) {
		super(game);
		loadImgs();
		soundOption = game.getSoundOption();
        loadBackButton();
	}

    private void loadBackButton() {
        int menuX = (int) (387 * Game.SCALE);
        int bY = (int) (330 * Game.SCALE);
        menuB = new UrmButton(menuX , bY , URM_SIZE , URM_SIZE , 2);

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
        menuB.update();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImg, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
		g.drawImage(optionsBackgroundImg, bgX, bgY, bgW, bgH, null);
        menuB.draw(g);
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
        if (isIn(e, menuB))
            menuB.setMousePressed(true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        soundOption.mouseReleased(e);
        if (isIn(e, menuB)) {
            if (menuB.isMousePressed()) {
                gameConditions.condition = gameConditions.MENU;
            }
        }
        menuB.resetBools();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        menuB.setMouseOver(false);
        soundOption.mouseMoved(e);
        if (isIn(e, menuB))
            menuB.setMouseOver(true);
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private boolean isIn(MouseEvent e, UrmButton b) {
        return b.getButtonsLayout().contains(e.getX(), e.getY());
    }
}
