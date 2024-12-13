package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utilz.LoadSave;
import static utilz.Constants.UI.PauseButton.*;

public class SoundButton extends PauseButton {
    
    private BufferedImage[][] soundImgs;
    private boolean mouseOver, mousePressed;
    private boolean muted;
    private int buttonLine, button;
    
    public SoundButton(int x, int y, int width, int height ){
        super(x, y, width, height);
        loadMusicImg();
    }

    private void loadMusicImg(){
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.SOUND_BUTTON);
        soundImgs = new BufferedImage[2][2];
        for(int j = 0; j < soundImgs.length; j++){
            for(int i = 0 ; i < soundImgs.length; i++){
                soundImgs[j][i] = temp.getSubimage( i * MUSIC_SIZE_DEFAULT, j * MUSIC_SIZE_DEFAULT, MUSIC_SIZE_DEFAULT, MUSIC_SIZE_DEFAULT);
            }
        } 
    }

    public void update(){
        if(muted) buttonLine = 1;
        else buttonLine = 0;
        button = 0;
        if(mouseOver) button = 1;
    }

    public void resetBools() {
		mouseOver = false;
		mousePressed = false;
	}

    public void draw(Graphics g){
        g.drawImage(soundImgs[buttonLine][button], getX(), getY(), getWidth(), getHeight() ,null);
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public boolean isMuted() {
        return muted;
    }

    public void setMuted(boolean muted) {
        this.muted = muted;
    }

    
}
