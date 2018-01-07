package com.benton.game;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Bullets {
	 
	private int height;
	private int width;
	private float rotation;
	
	private Vector2 position;
    public Vector2 velocity;
    private Vector2 acceleration;
	
	private boolean isRising;
	
	public Bullets (float x, float y, int height, int width) {
		
		this.height = height;
		this.width = width;
		position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 460);
		
	}
	
	public float getX() {
		return position.x;
	}
	
	public float getY() {
		return position.y;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public float getRotation() {
		return rotation;
	}
	
	//public Rectangle getBoundingRect() {
	//	return boundingRect;
	//}
	
	// Updates the missile on clock tick
	// modifies: this
	// effects: missile is moved DY units (up the screen)
	public void move (float delta) {
		
		 velocity.add(acceleration.cpy().scl(delta));

	        if (velocity.y > 200) {
	            velocity.y = 200;
	        }

	     position.add(velocity.cpy().scl(delta));
	     
	     if (velocity.y < 0) {
	            rotation -= 600 * delta;

	            if (rotation < 0) {
	                rotation = 0;
	            }
	        }

	        // Rotate clockwise
	        if (isFalling()) {
	            rotation += 480 * delta;
	            if (rotation > 180) {
	                rotation = 180;
	            }
	        }
	        
	
		}
		
	 public boolean isFalling() {
	        return velocity.y > 110;
	    }

}