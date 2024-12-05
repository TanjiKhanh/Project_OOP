package GameConditions;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import entities.EnemyManager;
import entities.Player;
import levels.LevelManager;
import main.Game;
import utilz.LoadSave;
import static main.Game.GAME_WIDTH;


public class Playing extends Condition implements ConditionMethods{

    private Player player;
	private LevelManager levelManager;
    private EnemyManager enemyManager;


    //Game cam focus variable
    private int rightBorder = (int) ( GAME_WIDTH * 0.8 );
    private int leftBorder = (int) ( GAME_WIDTH * 0.2 );
    private int lvlOffset;
    private int maxTilesLvlWidth = LoadSave.GetLevelData()[0].length;
    private int maxTilesOffset = maxTilesLvlWidth - Game.TILES_IN_WIDTH;
    private int maxLvlOffset = maxTilesOffset * Game.TILES_SIZE;
    
    public Playing(Game game){
        super(game);
        initClasses();
    }
    
    private void initClasses() {
		levelManager = new LevelManager(game);
		player = new Player(200, 200, (int) (18 * Game.SCALE), (int) (34 * Game.SCALE));
		player.loadLevelData(levelManager.getCurrentLevel().getLevelData());
        enemyManager = new EnemyManager();
	}

    private void levelUpdate() {

        int playerX =  (int) player.getHitbox().x ;
        int diff = playerX - lvlOffset;


        if( diff > rightBorder )
        {
            lvlOffset += diff - rightBorder;
        }
        else if( diff < leftBorder  )
            lvlOffset += diff - leftBorder;
        if( lvlOffset > maxLvlOffset)
            lvlOffset = maxLvlOffset;
        else if( lvlOffset < 0)
            lvlOffset = 0;
    }

    @Override
    public void update() {
		levelManager.update();
		player.update();
        levelUpdate();
	}

    @Override
    public void draw(Graphics g) {
		levelManager.draw(g , lvlOffset );
		player.render(g , lvlOffset);
        enemyManager.draw(g);
	}

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                player.setLeft(true);
                break;
            case KeyEvent.VK_D:
                player.setRight(true);
                break;
            case KeyEvent.VK_SPACE:
                player.setJump(true);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                player.setLeft(false);
                break;
            case KeyEvent.VK_D:
                player.setRight(false);
                break;
            case KeyEvent.VK_SPACE:
                player.setJump(false);
                break;
        }
    }

	public Player getPlayer() {
		return player;
	}
}
