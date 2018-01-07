package com.benton.game;

import java.util.List;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;


public class GameRenderer {
	
	private GameWorld myWorld;
	private OrthographicCamera cam;
	private SpriteBatch batcher;
	
	private ShapeRenderer shapeRenderer;
	
	private int midPointY;
	private int gameHeight;
	
	// Objects
	private Tank tank;
	private List<Bullets> apples;
	private List<UnIdentifiedObject> plates;
	
	// Game Assets
	private TextureRegion bg, tankDown, apple, ready,
	logo, gameOver, highScore, scoreboard, star, noStar, retry;
	
	private Animation plate;
	//Tween Stuff
	
	// Buttons
	private List<Button> menuButtons;
	private Button restartButton;
	private Button menuReturnButton;
//	private Button muteButton;
	
	private Color transitionColor;
	
	public GameRenderer(GameWorld world, int gameHeight, int midPointY) {
		myWorld = world;
		
		this.gameHeight = gameHeight;
		this.midPointY = midPointY;
		this.menuButtons = myWorld.getMenuButtons();
		this.restartButton = myWorld.getRestartButton();
		this.menuReturnButton = myWorld.getMenuReturnButton();
//		this.muteButton = myWorld.getMuteButton();
		
		cam = new OrthographicCamera();
        cam.setToOrtho(true, 136, 204);
        
        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);
        
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
        
        initGameObjects();
        initAssets();
        
        transitionColor = new Color();
		
	}
	
	 
	 private void initGameObjects() {
		 tank = myWorld.getTank();
		 plates = myWorld.getUFOs();
		 apples = tank.getBullets();
		 
		}
	 
	 private void initAssets() {
		 bg = AssetLoader.bg;
//		 tankDown = AssetLoader.tank;
		 apple = AssetLoader.bullet;
		 plate = AssetLoader.UFO;
		 ready = AssetLoader.ready;
		 logo = AssetLoader.logo;
		 gameOver = AssetLoader.gameOver;
		 highScore = AssetLoader.highScore;
		 scoreboard = AssetLoader.scoreboard;
		 retry = AssetLoader.retry;
		 star = AssetLoader.star;
		 noStar = AssetLoader.noStar;
	 }
	 
	 private void drawTankAnimation(float runTime) {
		 
		 tank = myWorld.getTank();
		// draws pig
	        batcher.draw(AssetLoader.tank.getKeyFrame(runTime),
	                tank.getX(), tank.getY(), tank.getWidth(), tank.getHeight());
		 
	 }
	 
	 private void drawTank(){
		 tank = myWorld.getTank();
			// draws pig
		        batcher.draw(AssetLoader.tank1,
		                tank.getX(), tank.getY(), tank.getWidth(), tank.getHeight());
	 }
	 
	 private void drawBullets() {
		 
		 // draws apples
	        for (Bullets next: tank.getBullets()) {
	        	batcher.draw (AssetLoader.bullet, next.getX(), next.getY(), next.getHeight()/ 2.0f,
	        			next.getWidth() /2.0f, next.getWidth(), next.getHeight(), 1, 1, next.getRotation());
	        }
	 }
	 
	 private void drawUFOs() {
		
		 // draws plates
	        for (UnIdentifiedObject next: myWorld.getUFOs()) {
	        	batcher.draw (AssetLoader.UFO1, next.getX(), next.getY(), next.getHeight(), 
	        			next.getWidth());
	        }
	 }
	 
	 private void drawUFOsAnimation(float runTime) {
			
		 // draws plates
	        for (UnIdentifiedObject next: myWorld.getUFOs()) {
	        	batcher.draw (AssetLoader.UFO.getKeyFrame(runTime), next.getX(), next.getY(), next.getHeight(), 
	        			next.getWidth());
	        }
	 }
	 
	 private void drawScore() {
		 
		 int length = ("" + myWorld.getScore()).length();
	        AssetLoader.shadow.draw(batcher, "" + myWorld.getScore(),
	                68 - (3 * length), midPointY - 82);
	        AssetLoader.font.draw(batcher, "" + myWorld.getScore(),
	                68 - (3 * length), midPointY - 83);
		 
	 }
	 
	 private void drawMenuUI() {
		 batcher.draw(AssetLoader.logo, 136 / 2 - 75, midPointY - 50,
	                150,
	                43);

	        for (Button button : menuButtons) {
	            button.draw(batcher);
	        }
	     
//	     muteButton.draw(batcher);
	 }
	 
	 private void drawScoreboard() {
			batcher.draw(scoreboard, 22, midPointY - 40, 97, 37);

			batcher.draw(noStar, 27, midPointY - 27, 10, 10);
			batcher.draw(noStar, 39, midPointY - 27, 10, 10);
			batcher.draw(noStar, 51, midPointY - 27, 10, 10);
			batcher.draw(noStar, 63, midPointY - 27, 10, 10);
			batcher.draw(noStar, 75, midPointY - 27, 10, 10);

			if (myWorld.getScore() > 2) {
				batcher.draw(star, 27, midPointY - 27, 10, 10);
			}

			if (myWorld.getScore() > 7) {
				batcher.draw(star, 39, midPointY - 27, 10, 10);
			}

			if (myWorld.getScore() > 15) {
				batcher.draw(star, 51, midPointY - 27, 10, 10);
			}

			if (myWorld.getScore() > 30) {
				batcher.draw(star, 63, midPointY - 27, 10, 10);
			}

			if (myWorld.getScore() > 50) {
				batcher.draw(star, 75, midPointY - 27, 10, 10);
			}

			int length = ("" + myWorld.getScore()).length();

			AssetLoader.scoreBoardFont.draw(batcher, "" + myWorld.getScore(),
					103 - (2 * length), midPointY - 30);

			int length2 = ("" + AssetLoader.getHighScore()).length();
			AssetLoader.scoreBoardFont.draw(batcher, "" + AssetLoader.getHighScore(),
					103 - (2.5f * length2), midPointY - 13);
			
			restartButton.draw(batcher);
			menuReturnButton.draw(batcher);
//			muteButton.draw(batcher);

		}
	 
	 private void drawRetry() {
			batcher.draw(retry, 36, midPointY + 10, 66, 14);
		}
	 
	 private void drawReady() {
			batcher.draw(ready, 136 / 2 - 75, midPointY - 50,
	                150,
	                43);
		}

		private void drawGameOver() {
			batcher.draw(gameOver, 136 / 2 - 75, midPointY - 73,
	                155,
	                43);
			restartButton.draw(batcher);
			menuReturnButton.draw(batcher);
		}
		
	private void drawHighScore() {
			batcher.draw(highScore, 22, midPointY - 50, 96, 14);
			
		}
	
	private void drawGrass() {
		 batcher.draw(AssetLoader.grass, 0, midPointY + 66, 136, 11);
	}
	

	public void render (float delta, float runTime) {
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
        // Begin ShapeRenderer
        shapeRenderer.begin(ShapeType.Filled);

        // Draw Background color
        shapeRenderer.setColor(0, 169 / 255.0f, 236 / 255.0f, 1);
        shapeRenderer.rect(0, 0, 136, midPointY + 66);

        // Draw Grass
       
        shapeRenderer.setColor(111 / 255.0f, 186 / 255.0f, 45 / 255.0f, 1);
        shapeRenderer.rect(0, midPointY + 66, 136, 11);

        // Draw Dirt
        shapeRenderer.setColor(147 / 255.0f, 80 / 255.0f, 27 / 255.0f, 1);
        shapeRenderer.rect(0, midPointY + 77, 136, 52);
        

        // End ShapeRenderer
        shapeRenderer.end();
        
        // Begin SpriteBatch
        batcher.begin();
        // Disable transparency
        // This is good for performance when drawing images that do not require
        // transparency.
        batcher.disableBlending();
        batcher.draw(AssetLoader.bg, 0, midPointY + 23, 136, 43);
        drawGrass();
       
       
        
        batcher.enableBlending();
       
        
        if (myWorld.isRunning()) {
        	if (tank.getShouldThrow() && AssetLoader.tank.getKeyFrameIndex(runTime) <= 3){
        		drawTankAnimation(runTime);
        	} else if (tank.getShouldThrow() && AssetLoader.tank.getKeyFrameIndex(runTime) == 4){
        		drawTankAnimation(runTime);
        		tank.dontThrow();
        	} else {
        		drawTank();
        		tank.dontThrow();
        	}
        	drawScore();
        	drawBullets();
            drawUFOs();
            	          
        	
        } else if (myWorld.isReady()) {
        	drawReady();
        	drawTank();
        	drawScore();
        } else if (myWorld.isMenu()) {
        	drawTank();
        	drawMenuUI();
        } else if (myWorld.isGameOver()) {
        	drawTank();
        	drawGameOver();
			drawRetry();
			drawScoreboard();
        } else if (myWorld.isHighScore()) {
        	drawTank();
        	drawScoreboard();
        	drawHighScore();
			drawRetry();
        }

        batcher.end();
       // drawTransition(delta);
  
    }
	
	
	
}
