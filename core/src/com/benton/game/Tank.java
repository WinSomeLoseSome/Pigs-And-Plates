package com.benton.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Tank {
		
		private float yPosition;
		private float position;
		private float velocity;
		private float acceleration;
			
		private Direction dir;
	    
	    private float rotation; 
	    private int width;
	    private int height;
	    
	    private float originalY;
	    
	    private List<Bullets> theBullets;
	    private int inGameBullets = 0;
	    private int bulletLimit = 5;
	    
	    private boolean shouldThrow;
	    
	    public enum Direction {
	    	LEFT, RIGHT;
	    }
	    
	    public Tank(float x, float y, int width, int height, Direction dir) {
	        this.width = width;
	        this.height = height;
	        
	        this.yPosition = y;
	        this.position = x;
	        velocity = 0;
	        acceleration = 0.2f;
	        this.originalY = y;
	        
	        this.dir = dir;
	        	        
	        theBullets = new ArrayList<Bullets>();
	        shouldThrow = false;
	    }

	    public void update(float delta) {
	    	
	    	boundary();
			switch (dir) {
			case RIGHT:
				
				velocity = velocity + acceleration;
				break;
				
			case LEFT:
				
				velocity = velocity - acceleration;
				break;
			}
	    	
			
			position = position + velocity;
	     }

	    public void onClick() {
	    	
	    	switch (dir){
	    	case LEFT:
	    		dir = Direction.RIGHT;
	    		velocity += 1;
	    		break;
	    	case RIGHT:
	    		dir = Direction.LEFT;
	    		velocity -= 1;
	    		break;
	    	}
	    	
	    }
	    
	    public void boundary() {
	    	
	    	 if (position < 0 ) {
	    		 dir = Direction.RIGHT;
	    		 position = 0;
	    		 velocity += 0.2f;
		    }
		        
		    if (position > 125) {
		    	dir = Direction.LEFT;
		    	position = 125;
		    	velocity -= 0.2f;
		    }
	    }
	    
	    public void updateReady(float runTime){
	    	 position = 2 * (float) Math.sin(7 * runTime) + originalY;
	    }

	    public float getX() {
	        return position;
	    }

	    public float getY() {
	        return yPosition;
	    }

	    public float getWidth() {
	        return width;
	    }

	    public float getHeight() {
	        return height;
	    }

	    public float getRotation() {
	        return rotation;
	    }
	    
	    public Direction getDirection() {
	    	return dir;
	    }
	    
	    public List<Bullets> getBullets() {
			
			return theBullets;
		}
		
		public void moveBullets(float delta) {
			for (Bullets next : theBullets ) {
				next.move (delta);
			}	
		}
		
		public void fireBullets() {
			
			Bullets b = new Bullets (position, yPosition, 9, 7);
			
			if (theBullets.size() <= bulletLimit) {
			shouldThrow = true;
			theBullets.add(b);
			b.velocity.y = -350;
			inGameBullets++;
			AssetLoader.appleThrow.play();
			}
			
		}
		
		public void removeBullets() {
			List<Bullets> bulletsToRemove = new ArrayList<Bullets>();
			
			
			for (Bullets bullet: theBullets) {
				
				 if (bullet.getY() >= 300) {
					inGameBullets--;
					bulletsToRemove.add(bullet);
					Gdx.app.log("Tank", "Removed");
				}
			}
			theBullets.removeAll(bulletsToRemove);
		}
		
		public void onRestart(int posX) {
			 	position = posX;
		        velocity = 0;
		        acceleration = 0.15f;
		        theBullets.clear();
		        dir = Direction.LEFT;
		        
		}
		
		public boolean getShouldThrow(){
			return shouldThrow;
		}
		
		public void dontThrow(){
			shouldThrow = false;
		}

}