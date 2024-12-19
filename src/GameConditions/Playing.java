package GameConditions;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;


import audio.audioPlayer;
import entities.EnemyManager;
import entities.Player;
import levels.LevelManager;
import main.Game;
import ui.CompletedOverlay;
import ui.GameOverOverLap;
import ui.PauseOverlay;
import utilz.LoadSave;
import static utilz.Constants.PlayerConstants.*;
import static main.Game.GAME_WIDTH;


public class Playing extends Condition implements ConditionMethods{

    private Player player;
    private LevelManager levelManager;
    private EnemyManager enemyManager;
    private PauseOverlay pauseOverlay;
    private GameOverOverLap gameOverOverLap;
    private CompletedOverlay completedOverlay;
    private boolean paused = false;

    //Game cam focus variable
    private int rightBorder = (int) ( GAME_WIDTH * 0.8 );
    private int leftBorder = (int) ( GAME_WIDTH * 0.2 );
    private int lvlOffset;
    private int maxTilesLvlWidth = LoadSave.GetLevelData()[0].length;
    private int maxTilesOffset = maxTilesLvlWidth - Game.TILES_IN_WIDTH;
    private int maxLvlOffset = maxTilesOffset * Game.TILES_SIZE;

    private boolean gameOver = false;
    private boolean isWinning = false;

    public Playing(Game game){
        super(game);
        initClasses();
    }


    public void resetAll() {
        gameOver = false;
        isWinning = false;
        player.resetAll();
        enemyManager.resetAllEnermies();
    }
    private void initClasses() {
        levelManager = new LevelManager(game);
        enemyManager = new EnemyManager();
        player = new Player(200, 200, (int) ( SMALL_MARIO_WIDTH_DEFAULT * Game.SCALE), (int) (SMALL_MARIO_HEIGHT_DEFAULT * Game.SCALE) , enemyManager , this , levelManager );
        player.loadLevelData(levelManager.getCurrentLevel().getLevelData());
        pauseOverlay = new PauseOverlay(this);
        gameOverOverLap = new GameOverOverLap(this);
        completedOverlay = new CompletedOverlay(this);
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
        if(gameOver) {
            gameOverOverLap.update();
            return;
        }

        if(isWinning)
        {
            completedOverlay.update();
            return;
        }
        if (!paused) {
            levelManager.update();
            player.update();
            levelUpdate();
            enemyManager.update(levelManager.getCurrentLevel().getLevelData());
        } else {
            pauseOverlay.update();
        }
    }



    @Override
    public void draw(Graphics g) {
        levelManager.draw(g , lvlOffset );
        player.render(g , lvlOffset);
        enemyManager.draw(g , lvlOffset);
        if (paused) pauseOverlay.draw(g);
        if(gameOver) gameOverOverLap.draw(g);
        if(isWinning) completedOverlay.draw(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (paused)
            pauseOverlay.mousePressed(e);
        else if(isWinning)
            completedOverlay.mousePressed(e);
        else
            gameOverOverLap.mousePressed(e);


    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (paused)
            pauseOverlay.mouseReleased(e);
        else if(isWinning)
            completedOverlay.mouseReleased(e);
        else
            gameOverOverLap.mouseReleased(e);

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (paused)
            pauseOverlay.mouseMoved(e);
        else if (isWinning)
            completedOverlay.mouseMoved(e);
        else
            gameOverOverLap.mouseMoved(e);

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
                getGame().getAudioPlayer().playEffect(audioPlayer.JUMP);
                break;
            case KeyEvent.VK_ESCAPE:
                getGame().getAudioPlayer().playEffect(audioPlayer.PAUSE);
                paused = !paused;
                break;
            case KeyEvent.VK_E:
                player.setInAir(false);
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

    public void setWinning(boolean winning) {
        isWinning = winning;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void mouseDragged(MouseEvent e) {
        if (paused) pauseOverlay.mouseDragged(e);
    }

    public void unpauseGame() {
        paused = false;
    }
}
