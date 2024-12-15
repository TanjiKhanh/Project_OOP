package ui;

import GameConditions.Playing;
import GameConditions.gameConditions;
import main.Game;
import utilz.LoadSave;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static utilz.Constants.UI.URMButtons.URM_SIZE;

public class GameOverOverLap {

    private Playing playing;
    private UrmButton menuB , replayB;
    private BufferedImage img;
    private int imgX , imgY , imgW , imgH;
    public GameOverOverLap(Playing playing)
    {
        this.playing = playing;
        loadImg();
        createUrmButtons();
    }

    private void createUrmButtons() {
        int menuX = (int) (330 * Game.SCALE);
        int replayX = (int) (440 * Game.SCALE);
        int bY = (int) (210 * Game.SCALE);

        menuB = new UrmButton(menuX, bY, URM_SIZE, URM_SIZE, 2);
        replayB = new UrmButton(replayX, bY, URM_SIZE, URM_SIZE, 1);
    }

    private void loadImg() {
        img = LoadSave.GetSpriteAtlas(LoadSave.GAMEOVER_BACKGROUND);
        imgW = (int) (img.getWidth() * Game.SCALE);
        imgH = (int) (img.getHeight() * Game.SCALE);
        imgX = Game.GAME_WIDTH / 2 - imgW/2;
        imgY = Game.GAME_HEIGHT / 2 - imgH/2;
    }

    public void update() {
        menuB.update();
        replayB.update();
    }
    public void draw(Graphics g)
    {
        g.setColor(new Color(0, 0, 0 , 200));
        g.fillRect(0 , 0 , Game.GAME_WIDTH , Game.GAME_HEIGHT);

        g.drawImage(img , imgX , imgY , imgW , imgH, null);
        menuB.draw(g);
        replayB.draw(g);
    }

    public void mousePressed(MouseEvent e) {
        if (isIn(e, menuB))
            menuB.setMousePressed(true);
        else if (isIn(e, replayB))
            replayB.setMousePressed(true);
    }

    public void mouseReleased(MouseEvent e) {
        if (isIn(e, menuB)) {
            if (menuB.isMousePressed()) {
                gameConditions.condition = gameConditions.MENU;
                playing.resetAll();
                playing.unpauseGame();
            }
        }

        else if (isIn(e, replayB)) {
            if (replayB.isMousePressed()) {
                playing.resetAll();
                playing.unpauseGame();
            }
        }

        menuB.resetBools();
        replayB.resetBools();
    }

    public void mouseMoved(MouseEvent e) {
        menuB.setMouseOver(false);
        replayB.setMouseOver(false);

        if (isIn(e, menuB))
        {
            menuB.setMouseOver(true);
        }
        else if (isIn(e, replayB))
            replayB.setMouseOver(true);

    }


    private boolean isIn(MouseEvent e, UrmButton b) {
        return b.getButtonsLayout().contains(e.getX(), e.getY());
    }
}
