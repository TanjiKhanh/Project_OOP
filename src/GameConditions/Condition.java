package GameConditions;

import java.awt.event.MouseEvent;

import main.Game;
import ui.MenuButton;

public class Condition {
    protected Game game;

    public Condition(Game game){
        this.game = game;
    }

    public boolean isIn(MouseEvent e, MenuButton mb){
        return mb.getButtonsLayout().contains(e.getX(), e.getY());
    }

    public Game getgame(){
        return game;
    }

}
