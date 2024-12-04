package ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import GameConditions.gameConditions;
import utilz.LoadSave;
import static utilz.Constants.UI.Buttons.*;

public class MenuButton {
    private int x ,y , buttonline, button;
    private gameConditions condition;
    private BufferedImage[] images;
    private boolean mouseOver, mousePressed;
    private Rectangle buttonsLayout;

    // x y: mouse position, button:  
    public MenuButton(int x, int y, int buttonline, gameConditions condition){
        this.x = x;
        this.y = y;
        this.buttonline = buttonline;
        this.condition = condition;
        loadImages();
        initButtonsLayout();
    }
            
        
    public void initButtonsLayout() {
        buttonsLayout = new Rectangle(x - 500, y, B_WIDTH, B_HEIGHT);
    }
        
        
    public void loadImages(){
        images = new BufferedImage[3];
        BufferedImage temp =  LoadSave.GetSpriteAtlas(LoadSave.MENU_BUTTONS);
        for (int i = 0; i < images.length; i++){
            images[i] = temp.getSubimage(i * B_WIDTH_DEFAULT , buttonline * B_HEIGHT_DEFAULT, B_WIDTH_DEFAULT, B_HEIGHT_DEFAULT);
        }
    }

    public void draw(Graphics g){
        g.drawImage(images[button], x - 500, y, B_WIDTH, B_HEIGHT,null);
    }

    public void update(){
        button = 0;
        if (mouseOver) button = 1;
        if (mousePressed) button = 2; 
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

    public Rectangle getButtonsLayout() {
		return buttonsLayout;
	}
    
    public void changeGameCondition(){
        gameConditions.condition = condition;
    }

    public void resetBoolean(){
        mouseOver = false;
        mousePressed = false;
    }
}
