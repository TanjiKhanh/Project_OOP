package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import GameConditions.Playing;
import GameConditions.gameConditions;

import java.awt.event.MouseEvent;

import main.Game;
import utilz.LoadSave;
import static utilz.Constants.UI.PauseButton.*;
import static utilz.Constants.UI.VolumeButtons.*;
import static utilz.Constants.UI.URMButtons.*;

public class PauseOverlay {
    
    private BufferedImage backgroundImg;
    private int x, y, width, height;
    private SoundOption soundOption;
    private UrmButton menuB, replayB, unpauseB;
    private Playing playing;
    
    public PauseOverlay(Playing playing){
        this.playing = playing;
        loadBackground();
        createUrmButtons();
        soundOption = playing.getGame().getSoundOption();
    }

    private void createUrmButtons() {
        int menuX = (int) (313 * Game.SCALE);
        int replayX = (int) (387 * Game.SCALE);
        int unpauseX = (int) (462 * Game.SCALE);
        int bY = (int) (325 * Game.SCALE);

        menuB = new UrmButton(menuX, bY, URM_SIZE, URM_SIZE, 2);
        replayB = new UrmButton(replayX, bY, URM_SIZE, URM_SIZE, 1);
        unpauseB = new UrmButton(unpauseX, bY, URM_SIZE, URM_SIZE, 0);
    }



    private void loadBackground() {
        backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.PAUSE_BACKGROUND);
        width = (int) (backgroundImg.getWidth() * Game.SCALE);
        height = (int) (backgroundImg.getHeight() * Game.SCALE);
        x = Game.GAME_WIDTH / 2 - width / 2 ;
        y = Game.GAME_HEIGHT / 2 - height / 2 ; 
    }
          
    public void update() {
        soundOption.update();
        menuB.update();
        replayB.update();
        unpauseB.update();
    }

    public void draw(Graphics g){
        //backgorund
        g.drawImage(backgroundImg, x, y, width, height,null);
        //button
        soundOption.draw(g);
        // UrmButtons
        menuB.draw(g);
        replayB.draw(g);
        unpauseB.draw(g);
    }

    public void mousePressed(MouseEvent e) {
        soundOption.mousePressed(e);
        if (isIn(e, menuB))
            menuB.setMousePressed(true);
        else if (isIn(e, replayB))
            replayB.setMousePressed(true);
        else if (isIn(e, unpauseB))
            unpauseB.setMousePressed(true);
    }

    public void mouseReleased(MouseEvent e) {
        soundOption.mouseReleased(e);
        if (isIn(e, menuB)) {
            if (menuB.isMousePressed()) {
                gameConditions.condition = gameConditions.MENU;
                playing.unpauseGame();
            }
        }

        else if (isIn(e, replayB)) {
            if (replayB.isMousePressed()) {
                System.out.println("replay lvl!");
            }
        }

        else if (isIn(e, unpauseB)) {
            if (unpauseB.isMousePressed()) {
                playing.unpauseGame();
            }
        }

        menuB.resetBools();
        replayB.resetBools();
        unpauseB.resetBools();
    }

    public void mouseMoved(MouseEvent e) {
        soundOption.mouseMoved(e);
        menuB.setMouseOver(false);
        replayB.setMouseOver(false);
        unpauseB.setMouseOver(false);

        if (isIn(e, menuB))
            menuB.setMouseOver(true);
        else if (isIn(e, replayB))
            replayB.setMouseOver(true);
        else if (isIn(e, unpauseB))
            unpauseB.setMouseOver(true);
    }

    public void mouseDragged(MouseEvent e) {
		soundOption.mouseDragged(e);
	}

    private boolean isIn(MouseEvent e, PauseButton b) {
		return b.getButtonsLayout().contains(e.getX(), e.getY());
	}
}
