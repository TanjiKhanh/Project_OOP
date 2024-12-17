package ui;

import static utilz.Constants.UI.PauseButton.MUSIC_SIZE;
import static utilz.Constants.UI.VolumeButtons.SLIDER_WIDTH;
import static utilz.Constants.UI.VolumeButtons.VOLUME_HEIGHT;
import java.awt.Graphics;

import java.awt.event.MouseEvent;

import main.Game;

public class SoundOption{

    private SoundButton musicButton, sfxButton;
    private VolumeButton volumeButton;
    private Game game;

    public SoundOption(Game game){
        this.game = game;
        loadSoundButton();
        loadVolumeButton();
    }
    
    private void loadVolumeButton() {
		int vX = (int) (309 * Game.SCALE);
		int vY = (int) (278 * Game.SCALE);
		volumeButton = new VolumeButton(vX, vY, SLIDER_WIDTH, VOLUME_HEIGHT);
	}
    
    private void loadSoundButton(){
        int soundX = (int) (450 * Game.SCALE);
        int musicY = (int) (155 * Game.SCALE);
        int sfxY = (int) (196 * Game.SCALE);
        musicButton = new SoundButton(soundX, musicY, MUSIC_SIZE , MUSIC_SIZE);
        sfxButton = new SoundButton(soundX, sfxY, MUSIC_SIZE , MUSIC_SIZE);
    }
    
    public void update(){
        musicButton.update();
		sfxButton.update();

        volumeButton.update();
    }

    public void draw(Graphics g){
        //Sound Button
        musicButton.draw(g);
        sfxButton.draw(g);
        // Volume Button
		volumeButton.draw(g);
    }

    public void mousePressed(MouseEvent e) {
        if (isIn(e, musicButton))
			musicButton.setMousePressed(true);
		else if (isIn(e, sfxButton))
			sfxButton.setMousePressed(true);
        else if (isIn(e, volumeButton))
			volumeButton.setMousePressed(true);
    }

    public void mouseReleased(MouseEvent e) {
        if (isIn(e, musicButton)) {
			if (musicButton.isMousePressed()) {
                musicButton.setMuted(!musicButton.isMuted());
                game.getAudioPlayer().toggleSongMute();
            }
		} else if (isIn(e, sfxButton)) {
			if (sfxButton.isMousePressed()) {
                sfxButton.setMuted(!sfxButton.isMuted());
                game.getAudioPlayer().toggleEffectMute();
            }
        }

        musicButton.resetBools();
        sfxButton.resetBools();
        volumeButton.resetBools();
    }

    public void mouseMoved(MouseEvent e) {
        musicButton.setMouseOver(false);
		sfxButton.setMouseOver(false);
        volumeButton.setMouseOver(false);

        if (isIn(e, musicButton))
            musicButton.setMousePressed(true);
        else if (isIn(e, sfxButton))
            sfxButton.setMousePressed(true);
        else if (isIn(e, volumeButton))
			volumeButton.setMouseOver(true);
    }

    public void mouseDragged(MouseEvent e) {
        if (volumeButton.isMousePressed()) {
            float valueBefore = volumeButton.getFloatvalue();
            volumeButton.changeX(e.getX());
            float valueAfter = volumeButton.getFloatvalue();
            if(valueBefore != valueAfter) {
                game.getAudioPlayer().setVolume(valueAfter);
            }
        }
    }

    private boolean isIn(MouseEvent e, PauseButton b) {
		return b.getButtonsLayout().contains(e.getX(), e.getY());
	}
}
