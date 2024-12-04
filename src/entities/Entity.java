package entities;

import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public abstract class Entity {
	protected float x , y ;
	protected int width, height;
	protected Rectangle2D.Float hitbox;

	public Entity (float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	// Create a rectangle covering Entity
	public void initHitbox( float x , float y , float width , float height ) {
		hitbox = new Rectangle2D.Float((int) x, (int) y, (int) width,(int) height);
	}

	// Updating coordinate when Entity move
	protected void updateHitBox() {
		hitbox.x = (int) x;
		hitbox.y = (int) y;
	}

	// Draw hitbox
	protected void drawHitBox(Graphics g) {
		g.setColor(Color.PINK);
		g.drawRect(  (int)hitbox.x,(int) hitbox.y, (int) hitbox.width,(int) hitbox.height);
	}

	public Rectangle2D.Float getHitbox() {
		return hitbox;
	}
}
