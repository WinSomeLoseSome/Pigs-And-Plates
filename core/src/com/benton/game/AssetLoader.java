package com.benton.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {

	public static Texture texture, logoTexture, readyTexture, gameOverTexture;
	
	public static TextureRegion tank1, tank2, tank3, tank4, bg, bullet, UFO1, UFO2, UFO3, UFO4, playButtonUp, playButtonDown,
	logo, gameLogo, ready, star, noStar, retry, gameOver, scoreboard, highScore, highScoreButton,
	restartButton, menuReturnButton, splashLogo, grass;
	
	public static Animation tank, UFO;
	
	public static BitmapFont font, shadow, scoreBoardFont, scoreBoardShadow;
	
	public static Preferences prefs;
	
	public static Sound appleThrow, plateSmash, plateGroundSmash;
	
	public static void load() {
		
		logoTexture = new Texture(Gdx.files.internal("data/logo.png"));
	    logoTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

	    logo = new TextureRegion(logoTexture, 0, 228, 800, 228);
	    logo.flip(false, true);

	    texture = new Texture(Gdx.files.internal("data/texture.png"));
	    texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
	    
	    splashLogo = new TextureRegion (texture, 43, 43, 136, 20);
	    playButtonUp = new TextureRegion(texture, 0, 83, 29, 16);
	    playButtonDown = new TextureRegion(texture, 29, 83, 29, 16);
	    playButtonUp.flip(false, true);
	    playButtonDown.flip(false, true);
	    highScoreButton = new TextureRegion (texture, 131, 83, 29, 16);
	    highScoreButton.flip(false, true);
	    restartButton = new TextureRegion (texture, 58, 83, 29, 16);
	    restartButton.flip(false, true);
	    menuReturnButton = new TextureRegion (texture, 116, 83, 15, 16);
	    menuReturnButton.flip(false, true);
	    
	    
	    ready = new TextureRegion(logoTexture, 0, 0, 800, 228);
		ready.flip(false, true);

		retry = new TextureRegion(texture, 59, 110, 33, 7);
		retry.flip(false, true);
		
		gameOver = new TextureRegion(logoTexture, 0, 452, 800, 228);
		gameOver.flip(false, true);

		scoreboard = new TextureRegion(texture, 160, 83, 97, 37);
		scoreboard.flip(false, true);

		star = new TextureRegion(texture, 0, 99, 15, 16);
		noStar = new TextureRegion(texture, 16, 99, 15, 16);

		star.flip(false, true);
		noStar.flip(false, true);

		highScore = new TextureRegion(texture, 59, 101, 48, 7);
		highScore.flip(false, true);

	    gameLogo = new TextureRegion(texture, 0, 55, 135, 24);
        gameLogo.flip(false, true);
			
		font = new BitmapFont(Gdx.files.internal("data/text.fnt"));
        //font.setScale(.25f, -.25f);
        shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
        //shadow.setScale(.25f, -.25f);
        
        scoreBoardFont = new BitmapFont(Gdx.files.internal("data/text.fnt"));
        //scoreBoardFont.setScale(.12f, -.12f);
        scoreBoardShadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
        //scoreBoardShadow.setScale(.12f, -.12f);
        
        //Sounds
        appleThrow = Gdx.audio.newSound(Gdx.files.internal("data/Apple-Throw.wav"));
        plateSmash = Gdx.audio.newSound(Gdx.files.internal("data/Plate-Smash.wav"));
        plateGroundSmash = Gdx.audio.newSound(Gdx.files.internal("data/Plate-Ground-Smash.wav"));
        
        bg = new TextureRegion(texture, 43, 0, 136, 43);
        bg.flip(false, true);
        
        grass = new TextureRegion(texture, 43, 65, 136, 11);
        grass.flip(false, true);
        
        tank1 = new TextureRegion(texture, 0, 0, 21, 33);
        tank1.flip(false, true);
        tank2 = new TextureRegion (texture, 179, 0, 21, 33);
        tank2.flip(false, true);
        tank3 = new TextureRegion (texture, 200, 0, 21, 33);
        tank3.flip(false, true);
        tank4 = new TextureRegion (texture, 221, 0, 21, 33);
        tank4.flip(false, true);
        
        TextureRegion[] tanks = {tank1, tank2, tank3, tank4, tank1 };
        tank = new Animation (0.06f, tanks);
        tank.setPlayMode(Animation.PlayMode.LOOP);
        
        bullet = new TextureRegion (texture, 21, 0, 7, 11);
        bullet.flip(false, true);
        
        UFO1 = new TextureRegion (texture, 28, 0, 15, 15);
        UFO1.flip(false, true);
        UFO2 = new TextureRegion (texture, 28, 15, 15, 15);
        UFO2.flip(false, true);
        UFO3 = new TextureRegion (texture, 28, 30, 15, 15);
        UFO3.flip(false, true);
        UFO4 = new TextureRegion (texture, 28, 45, 15, 15);
        UFO4.flip(false, true);
        
        TextureRegion[] UFOs = {UFO1, UFO2, UFO3, UFO4};
        UFO = new Animation (0.06f, UFOs);
        UFO.setPlayMode(Animation.PlayMode.LOOP);
        
        prefs = Gdx.app.getPreferences("Plate Thrower");

        if (!prefs.contains("highScore")) {
            prefs.putInteger("highScore", 0);
        }       
        
	}
	
	 public static void setHighScore(int val) {
	        prefs.putInteger("highScore", val);
	        prefs.flush();
	    }

	    public static int getHighScore() {
	        return prefs.getInteger("highScore");
	    }
	    
	    
	
	public static void dispose() {
        // We must dispose of the texture when we are finished.
        texture.dispose();
        font.dispose();
        shadow.dispose();
    }
	
}
