package main;

import ui.SoundOption;

import java.awt.*;
import audio.audioPlayer;
import GameConditions.*;
import GameConditions.Menu;

public class Game implements Runnable {
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
	private Playing playing;
	private Menu menu;
	private gameOption gameOption;
	private SoundOption soundOption;
	private audioPlayer audioPlayer;
	private final int FPS_SET = 120;
    private final int UPS_SET = 200;
	public final static int TILES_DEFAULT_SIZE = 32;
	public final static float SCALE = 1.5f;
	public final static int TILES_IN_WIDTH = 26;
	public final static int TILES_IN_HEIGHT = 14;
	public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
	public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
	public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;

    public Game()
    {
		initialClasses();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
		gamePanel.setFocusable(true);
        startGameLoop();
		audioPlayer = new audioPlayer();

    }

	private void initialClasses()
	{
		soundOption = new SoundOption(this);
		menu = new Menu(this);
		playing = new Playing(this);
		gameOption = new gameOption(this);
	}
    
    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    private void update() {
		switch (gameConditions.condition) {
			case MENU:
				menu.update();
				break;
			case PLAYING:
				playing.update();
				break;
			case OPTIONS:
				gameOption.update();
				break;
			case QUIT:
			default:
				System.exit(0);
				break;
			}
    }

	public void render(Graphics g)
	{
		switch (gameConditions.condition) {
		case MENU:
			menu.draw(g);
			break;
		case PLAYING:
			playing.draw(g);
			break;
		case OPTIONS:
			gameOption.draw(g);
			break;
		default:
			break;
		}
	}
    @Override
	public void run() {

		
		double timePerFrame = 1000000000.0 / FPS_SET;
		double timePerUpdate = 1000000000.0 / UPS_SET;

		long previousTime = System.nanoTime();

		int frames = 0;
		int updates = 0;
		long lastCheck = System.currentTimeMillis();

		double deltaU = 0;
		double deltaF = 0;

		while (true) {
			long currentTime = System.nanoTime();

			deltaU += (currentTime - previousTime) / timePerUpdate;
			deltaF += (currentTime - previousTime) / timePerFrame;
			previousTime = currentTime;

			if (deltaU >= 1) {
				update();
				updates++;
				deltaU--;
			}

			if (deltaF >= 1) {
				gamePanel.repaint();
				frames++;
				deltaF--;
			}

			if (System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				System.out.println("FPS: " + frames + " | UPS: " + updates);
				frames = 0;
				updates = 0;

			}
		}

	}


	public Menu getMenu() {
		return menu;
	}

	public Playing getPlaying() {
		return playing;
	}


	public SoundOption getSoundOption(){
		return soundOption;
	}

	public gameOption getGameOption() {
		return gameOption;
	}

	public audioPlayer getAudioPlayer() {
		return audioPlayer;
	}
}
