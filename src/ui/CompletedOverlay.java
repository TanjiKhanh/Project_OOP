package ui;

import GameConditions.Playing;
import GameConditions.gameConditions;
import audio.audioPlayer;
import main.Game;
import utilz.LoadSave;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static utilz.Constants.UI.URMButtons.URM_SIZE;

public class CompletedOverlay {

    private Playing playing;
    private UrmButton menu, replay;
    private BufferedImage img;
    private int bgX, bgY, bgW, bgH;

    public CompletedOverlay(Playing playing) {
        this.playing = playing;
        initImg();
        initButtons();
    }

    private void initButtons() {
        int menuX = (int) (330 * Game.SCALE);
        int replayX = (int) (445 * Game.SCALE);
        int y = (int) (195 * Game.SCALE);
        replay = new UrmButton(replayX, y, URM_SIZE, URM_SIZE, 1);
        menu = new UrmButton(menuX, y, URM_SIZE, URM_SIZE, 2);
    }

    private void initImg() {
        img = LoadSave.GetSpriteAtlas(LoadSave.COMPLETED_IMG);
        bgW = (int) (img.getWidth() * Game.SCALE);
        bgH = (int) (img.getHeight() * Game.SCALE);
        bgX = Game.GAME_WIDTH / 2 - bgW / 2;
        bgY = (int) (75 * Game.SCALE);
    }

    public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 0, 200));
        g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);

        g.drawImage(img, bgX, bgY, bgW, bgH, null);
        replay.draw(g);
        menu.draw(g);
    }

    public void update() {
        replay.update();
        menu.update();
    }

    private boolean isIn(UrmButton b, MouseEvent e) {
        return b.getButtonsLayout().contains(e.getX(), e.getY());
    }

    public void mouseMoved(MouseEvent e) {
        replay.setMouseOver(false);
        menu.setMouseOver(false);

        if (isIn(menu, e))
            menu.setMouseOver(true);
        else if (isIn(replay, e))
            replay.setMouseOver(true);
    }

    public void mouseReleased(MouseEvent e) {
        if (isIn(menu, e)) {
            if (menu.isMousePressed()) {
                playing.resetAll();
                gameConditions.condition = gameConditions.MENU;
                playing.unpauseGame();
                playing.getGame().getAudioPlayer().playSong(audioPlayer.MENU_1);
            }
        } else if (isIn(replay, e))
            if (replay.isMousePressed()){
                playing.resetAll();
                playing.unpauseGame();
                playing.getGame().getAudioPlayer().playSong(audioPlayer.MAIN_PLAY);
            }

        menu.resetBools();
        replay.resetBools();
    }

    public void mousePressed(MouseEvent e) {
        if (isIn(menu, e))
            menu.setMousePressed(true);
        else if (isIn(replay, e))
            replay.setMousePressed(true);
    }

}
