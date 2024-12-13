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

public class PauseOverlay {
    
    private BufferedImage backgroundImg;
    private int x, y, width, height;
    private SoundOption soundOption;
    private Playing playing;
    
    public PauseOverlay(Playing playing){
        this.playing = playing;
        loadBackground();
        soundOption = playing.getGame().getSoundOption();
    }



    private void loadBackground() {
        backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.PAUSE_BACKGROUND);
        width = (int) (backgroundImg.getWidth() * Game.SCALE);
        height = (int) (backgroundImg.getHeight() * Game.SCALE);
        x = Game.GAME_WIDTH / 2 - width / 2 ;
        y = Game.GAME_HEIGHT / 2 - height / 2 ; 
    }
          
    public void update(){
        soundOption.update();
    }

    public void draw(Graphics g){
        //backgorund
        g.drawImage(backgroundImg, x, y, width, height,null);
        //button
        soundOption.draw(g);
    }

    public void mousePressed(MouseEvent e) {
        soundOption.mousePressed(e);
    }

    public void mouseReleased(MouseEvent e) {
        soundOption.mouseReleased(e);
    }

    public void mouseMoved(MouseEvent e) {
        soundOption.mouseMoved(e);
    }

    public void mouseDragged(MouseEvent e) {
		soundOption.mouseDragged(e);
	}

    private boolean isIn(MouseEvent e, PauseButton b) {
		return b.getButtonsLayout().contains(e.getX(), e.getY());
	}
}
