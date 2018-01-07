package com.benton.game;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class UnIdentifiedObject {
	
	public static final int SIZE_X = 12;
	public static final int SIZE_Y = 12;
	
	public static final int DY = 2;
	
	public static final int JIGGLE_X = 1;
	
	private float x;
	private float y;
	
	private int gameWidth = 136;
	
	
	//private Rectangle boundingRect;
	
	public UnIdentifiedObject (int x, int y) {
		this.x = x;
		this.y =y;
		//boundingRect = new Rectangle(x - SIZE_X / 2, y - SIZE_Y / 2, SIZE_X, SIZE_Y);
		
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public int getHeight(){
		return SIZE_Y;
	}
	
	public int getWidth(){
		return SIZE_X;
	}
	
	//public Rectangle getBoundingRect() {
		
	//	return boundingRect;
	//}
	
	public void moveUFO() {
		x = x + GameWorld.RND.nextInt(2 * JIGGLE_X + 1) - JIGGLE_X;
		y = y + DY;
		
		handleboundary();
	}
	
	private void handleboundary() {
		if (x <= 0) {
			x = 0;
		} 
		
		else if (x >= gameWidth) {
			x = gameWidth;
		}
	}
	
	// TODO constantly being called; fix
	public boolean collidedWith (Bullets b) {
		Circle UFOBoundingCircle = new Circle (getX() - SIZE_X / 2, getY() - SIZE_X / 2, SIZE_X - 2);
		Circle bulletBoundingCircle = new Circle (b.getX() - b.getWidth() / 2, b.getY() - b.getHeight() /2, 
				b.getWidth() - 2);
		
		
		//Gdx.app.log("UFO", "Hit");
		return Intersector.overlaps(UFOBoundingCircle, bulletBoundingCircle);
		//return boundingRect.intersects(b.getBoundingRect());
	}
	
	public boolean hitGround() {
		
		Rectangle groundBoundingRect = new Rectangle (0, 185, 300, 10);
		Circle UFOBoundingCircle = new Circle (getX() - SIZE_X / 2, getY() - SIZE_X / 2, SIZE_X);
		
		
		return Intersector.overlaps(UFOBoundingCircle, groundBoundingRect);
		
	}
	
	
	
}