package GameConditions;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import ui.MenuButton;
import main.Game;
import utilz.LoadSave;

public class Menu extends Condition implements ConditionMethods{

    private MenuButton[] buttons = new MenuButton[3];
    private BufferedImage backgroundImg, menuIMG;
    private int  backGroundWidth, backGroundHeight, backGroundX, backGroundY;
    
    public Menu(Game game){
        super(game);
        loadButtons();
        loadBackground();
    }

    private void loadBackground() {
        backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.MENU_BACKGROUND);
        backGroundWidth = (int) (Game.GAME_WIDTH);
		backGroundHeight = (int) (Game.GAME_HEIGHT);
		backGroundX = 0;
		backGroundY = 0;
    }

    public void loadButtons(){
        buttons[0] = new MenuButton(Game.GAME_WIDTH/2, (int) (150 * Game.SCALE), 0, gameConditions.PLAYING);
        buttons[1] = new MenuButton(Game.GAME_WIDTH/2, (int) (220 * Game.SCALE), 1, gameConditions.OPTIONS);
        buttons[2] = new MenuButton(Game.GAME_WIDTH/2, (int) (290 * Game.SCALE), 2, gameConditions.QUIT);
    }
    
    @Override
    public void update() {
        for(MenuButton mb : buttons) mb.update();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImg, backGroundX, backGroundY, backGroundWidth, backGroundHeight, null);
        for(MenuButton mb : buttons) mb.draw(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
       
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (MenuButton mb : buttons) {
			if (isIn(e, mb)) mb.setMousePressed(true);
        }   
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (MenuButton mb : buttons) {
			if (isIn(e, mb)) {
				if (mb.isMousePressed()) mb.changeGameCondition();
				break;
			}
		}
		resetButtons();
    }

    private void resetButtons() {
		for (MenuButton mb : buttons) mb.resetBoolean();
    }    

    @Override
    public void mouseMoved(MouseEvent e) {
        for (MenuButton mb : buttons) mb.setMouseOver(false);
		for (MenuButton mb : buttons)
			if (isIn(e, mb)) {
				mb.setMouseOver(true);
				break;
		}
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
			gameConditions.condition = gameConditions.PLAYING;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
    }


    
    
}
