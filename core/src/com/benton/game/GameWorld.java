package com.benton.game;

import java.util.ArrayList;
import java.util.List;


import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.benton.game.Tank.Direction;

public class GameWorld {
	
	int gameWidth = 136;
	
	private Tank tank;
	private int tankYdifference = 45;
	
	public static final Random RND = new Random();
	private static final int INVASION_INTERVAL = 50;
	
	private List<UnIdentifiedObject> UFOs;
	private int UFOKills = 0;
	
	private float runTime = 0;
	
	private int midPointY;
	private boolean isMuted;
	private boolean shouldBreak;
	
//	private Button muteButton;
	private Button highScoreButton;
	private Button playButton;
	private Button restartButton;
	private Button menuReturnButton;
	private List<Button> menuButtons;
	
	private GameRenderer renderer;
	public enum GameState {
		MENU, READY, RUNNING, GAMEOVER, HIGHSCORE;
	}
	
	private GameState currentState;
	
	public GameWorld(int midPointY) {
		tank = new Tank (33, midPointY + tankYdifference, 15, 25, Direction.LEFT);
		UFOs = new ArrayList<UnIdentifiedObject>();
		
		currentState = GameState.MENU;
		menuButtons = new ArrayList <Button>();
		playButton = new Button(92 - (AssetLoader.playButtonUp.getRegionWidth() / 2),	             
		    		 midPointY + 0, 29, 16, AssetLoader.playButtonUp, AssetLoader.playButtonDown);
		highScoreButton = new Button (44 - (AssetLoader.highScoreButton.getRegionWidth() / 2), midPointY + 0, 29, 16, AssetLoader.highScoreButton, 
						  AssetLoader.highScoreButton);
		restartButton = new Button (136 / 2 - (AssetLoader.restartButton.getRegionWidth() / 2),	             
	    		 midPointY + 5, 29, 16, AssetLoader.restartButton, AssetLoader.restartButton);
		menuReturnButton = new Button (0, 50, 14, 16, AssetLoader.menuReturnButton, AssetLoader.menuReturnButton);
//		muteButton = new Button (130, 20, 14, 16, AssetLoader.menuReturnButton, AssetLoader.menuReturnButton);
		menuButtons.add(playButton);
		menuButtons.add(highScoreButton);
		
		shouldBreak = false;
		isMuted = false;
	}
	
	public void update (float delta) {
		 runTime += delta;

	        switch (currentState) {
	        case READY:
	        case MENU:
	            updateReady(delta);
	            break;

	        case RUNNING:
	            updateRunning(delta);
	            break;
	        default:
	            break;
	        }
	}
	
	
	private void updateGameOver(float delta) {
		// TODO Auto-generated method stub
		
	}

	private void updateReady(float delta) {
		// TODO Auto-generated method stub
		
	}

	public void updateRunning (float delta){
		 if (delta > .15f) {
	         delta = .15f;
	     }
		
		tank.update(delta);
		tank.moveBullets(delta);	
		tank.removeBullets();
		moveUFOs();
		
		attack();
		checkCollisions();
		checkGroundCollision();
	//	UFOKills+=500;
		
	}
	
	public Tank getTank() {
		
		return tank;
	}
	
	private void moveUFOs() {
		for (UnIdentifiedObject next : UFOs) {
			next.moveUFO();
		}
	}
	
	private void attack() {
		if (RND.nextInt(INVASION_INTERVAL) < 1) {
			UnIdentifiedObject i = new UnIdentifiedObject(RND.nextInt(121), 0);
			// sets the max number of plates that can be falling at once
			if (UFOs.size() <= 2){
				// keeps UFO's away from edges
				if (14 < i.getX()) {
					UFOs.add(i);
				}
			}
		}
	}
	
	public List<UnIdentifiedObject> getUFOs(){
		return UFOs;
	}
	
	// TODO fix this so it works and actually removes UFOs
	private void checkCollisions() {
		
		List<UnIdentifiedObject> UFOsToRemove = new ArrayList<UnIdentifiedObject>();
		List<Bullets> bulletsToRemove = new ArrayList<Bullets>();
		
		for (UnIdentifiedObject target : UFOs) {
			if (checkUFOHit(target, bulletsToRemove)) {
				UFOsToRemove.add(target);
				AssetLoader.plateSmash.play();
				shouldBreak = true;
			}
			
		}
		
		UFOs.removeAll(UFOsToRemove);
		tank.getBullets().removeAll(bulletsToRemove);
	}
	
	private void checkGroundCollision() {
		
		List<UnIdentifiedObject> allUFOsForClear = new ArrayList<UnIdentifiedObject>();
		
		for (UnIdentifiedObject target: UFOs) {
			
			if (target.hitGround()) {
				AssetLoader.plateGroundSmash.play();
				currentState = GameState.GAMEOVER;
				
				 if (UFOKills > AssetLoader.getHighScore()) {
		                AssetLoader.setHighScore(UFOKills);
		                currentState = GameState.HIGHSCORE;
		            }

			}
			
		}
		
	}
	
	// TODO may be part of the above problem
	private boolean checkUFOHit(UnIdentifiedObject target, List<Bullets> bulletsToRemove) {
		
		for (Bullets next : tank.getBullets()) {
			if (target.collidedWith(next)) {
				UFOKills++;
				Gdx.app.log("GameWorld", "UFOHit");
				return true;
	        }
		}
		return false;
	}
	
	 public void restart() {
	        currentState = GameState.READY;
	        UFOKills = 0;
	        tank.onRestart(33);
	        UFOs.clear();
	        
	 }
	 
	// public void mute() {
	//	 if (!isMuted) {
	//		 isMuted = true;
	//	 } else {
	//		 isMuted = false;
	//	 }
	// }
	
	public int getScore() {
		
		return UFOKills;
	}
	
	public boolean isReady() {
        return currentState == GameState.READY;
    }
	
	public void ready() {
		currentState = GameState.READY;
	}

    public void start() {
        currentState = GameState.RUNNING;
    }
    
    public boolean isRunning() {
    	return currentState == GameState.RUNNING;
    }
    
    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }

	//public Button getPlayButton() {
		
	//	return playButton;
	//}
	
	public boolean isHighScore() {
	    return currentState == GameState.HIGHSCORE;
	}
	
	public boolean isMenu() {
		return currentState == GameState.MENU;
	}
	
	public void goMenu() {
		currentState = GameState.MENU;
	}
	
	public void goHighScore() {
		currentState = GameState.HIGHSCORE;
	}
	
	public int getMidPointY() {
	    return midPointY;
	}
	
	public GameRenderer setRenderer(GameRenderer renderer) {
		return renderer;
	}
	
	public List<Button> getMenuButtons() {
		return menuButtons;
	}
	
	public Button getPlayButton() {
		return playButton;
	}
	
	public Button getHighScoreButton() {
		return highScoreButton;
	}
	
	public Button getRestartButton() {
		return restartButton;
	}
    
	public Button getMenuReturnButton() {
		return menuReturnButton;
	}
	
//	public Button getMuteButton() {
//		return muteButton;
//	}
	
	public boolean getIsMuted() {
		return isMuted;
	}
	
	public boolean getShouldBreak() {
		return shouldBreak;
	}
	
	public void dontBreak() {
		shouldBreak = false;
	}
}

