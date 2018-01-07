package com.benton.game;

import java.util.List;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

public class Toucher implements InputProcessor {

	private Tank myTank;
	private GameWorld myWorld; 
	
	
	private List<Button> menuButtons;

    private Button playButton;
    private Button highScoreButton;
    private Button restartButton;
    private Button menuReturnButton;
    private Button muteButton;

    private float scaleFactorX;
    private float scaleFactorY;
	
	
	
	public Toucher (GameWorld myWorld, float scaleFactorX, float scaleFactorY) {
		
		this.myWorld = myWorld;
		myTank = myWorld.getTank();
		
		int midPointY = myWorld.getMidPointY();

	    this.scaleFactorX = scaleFactorX;
	    this.scaleFactorY = scaleFactorY;
	    
	    menuButtons = myWorld.getMenuButtons();
	    playButton = myWorld.getPlayButton();
	    highScoreButton = myWorld.getHighScoreButton();
	    restartButton = myWorld.getRestartButton();
	    menuReturnButton = myWorld.getMenuReturnButton();
//	    muteButton = myWorld.getMuteButton();
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		 screenX = scaleX(screenX);
	     screenY = scaleY(screenY);
	     System.out.println(screenX + " " + screenY);
	     boolean isRunOnce = false;
	     
	     if (myWorld.isMenu()) {
	        if (playButton.isTouchDown(screenX, screenY)) {
	        	myWorld.restart();
	        }
	        if (highScoreButton.isTouchDown(screenX, screenY)) {
	        	myWorld.goHighScore();
	        }
//	        if (muteButton.isTouchDown(screenX, screenY)) {
//	        	myWorld.mute();
//	        }
	         
	     } else if (myWorld.isReady() && !isRunOnce) {
	         myWorld.start();
	         myTank.onClick();
	         myTank.fireBullets();
	         isRunOnce = true;
	     } else if (myWorld.isReady() && isRunOnce) {
	    	 myWorld.restart();
	         myTank.onClick();
	         myTank.fireBullets();
	    	 
	     } else if (myWorld.isRunning()) {
	    	 myTank.onClick();
		     myTank.fireBullets();
		}
	     
	    if (myWorld.isGameOver()) {
	    	if (restartButton.isTouchDown(screenX, screenY)){
	    	myWorld.restart();
	    	}
	    	if (menuReturnButton.isTouchDown(screenX, screenY)) {
	    		myWorld.goMenu();
	    	}
          }
	    
	    if (myWorld.isHighScore()) {
	    	if (restartButton.isTouchDown(screenX, screenY)){
	    		myWorld.restart();
	    	}
	    	if (menuReturnButton.isTouchDown(screenX, screenY)) {
	    		myWorld.goMenu();
	    	}
	    }
		
		return true;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		 if (keycode == Keys.SPACE) {

	            if (myWorld.isMenu()) {
	                myWorld.ready();
	            } else if (myWorld.isReady()) {
	                myWorld.start();
	            }

	            myTank.onClick();
	            myTank.fireBullets();

	            if (myWorld.isGameOver() || myWorld.isHighScore()) {
	                myWorld.restart();
	            }

	        }
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		 screenX = scaleX(screenX);
	     screenY = scaleY(screenY);

	     if (myWorld.isMenu()) {
	    	 if (playButton.isTouchUp(screenX, screenY)) {
        	     myWorld.ready();
	             return true;
	         }
	    	 if (highScoreButton.isTouchUp(screenX,  screenY)) {
	    		 myWorld.goHighScore();
	    		 return true;
	    	 }
	    	 if (menuReturnButton.isTouchUp(screenX, screenY)) {
		    		myWorld.goMenu();
		    		return true;
		    	}
	     }

		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private int scaleX(int screenX) {
        return (int) (screenX / scaleFactorX);
    }

    private int scaleY(int screenY) {
        return (int) (screenY / scaleFactorY);
    }

    public List <Button> getMenuButtons() {
        return menuButtons;
    }

}


