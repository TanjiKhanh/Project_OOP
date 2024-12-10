package utilz;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;

import entities.Box;
import entities.Mushroom;
import entities.Turtle;
import main.Game;

import static utilz.Constants.BoxConstannts.BOX;
import static utilz.Constants.EnemyConstants.*;
import static utilz.Constants.PineConstants.PINE;

public class LoadSave {
	public static final String CHARACTERS_ATLAS = "characters.gif";
	public static final String LEVEL_ATLAS = "outside_sprites.png";
	public static final String LEVEL_ONE_DATA = "level_one_data_long.png";
	public static final String MENU_BUTTONS = "button_atlas.png";
	public static final String MENU_BACKGROUND = "menu_background.png";
	public static final String BACKGROUND_IMAGE = "playing_bg_img.png";
	public static final String BIG_CLOUD_IMAGE = "big_clouds.png";
	public static final String SMALL_CLOUD_IMAGE = "small_clouds.png";
	public static final String BOX_IMAGE = "7cQjJM.png";
	public static final String PINE_IMAGE = "tiles.png";

	public static BufferedImage GetSpriteAtlas(String fileName) {
		BufferedImage img = null;
		InputStream is = LoadSave.class.getResourceAsStream("/res/" + fileName);
		try {
			img = ImageIO.read(is);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return img;
	}

	//Get position of Turtle in level
	public static ArrayList<Turtle> GetTurtle() {
		BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
		ArrayList<Turtle> list = new ArrayList<>();
		for (int j = 0; j < img.getHeight(); j++)
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getGreen();
				if (value == TURTLE)
					list.add(new Turtle(i * Game.TILES_SIZE, j * Game.TILES_SIZE ));
			}
		return list;
	}

	public static ArrayList<Mushroom> GetMushroom() {
		BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
		ArrayList<Mushroom> list = new ArrayList<>();
		for (int j = 0; j < img.getHeight(); j++)
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getGreen();
				if(value == MUSHROOM)
					list.add(new Mushroom(i * Game.TILES_SIZE, j * Game.TILES_SIZE ));
			}
		return list;
	}

	public static ArrayList<Box> GetBox() {
		BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
		ArrayList<Box> boxes = new ArrayList<>();
		for (int j = 0; j < img.getHeight(); j++)
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getGreen();
				if (value == BOX)
					boxes.add(new Box(i * Game.TILES_SIZE, j * Game.TILES_SIZE ));
			}
		return boxes;
	}


	public static int[][] GetLevelData() {
		BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
		int[][] lvlData = new int[img.getHeight()][img.getWidth()];

		for (int j = 0; j < img.getHeight(); j++)
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getRed();
				if (value >= 50)
					value = 0;
				lvlData[j][i] = value;
			}
		return lvlData;

	}
}
