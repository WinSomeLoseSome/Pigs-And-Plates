package com.benton.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.benton.game.GameScreen;

public class MyGdxGame extends Game {
	
	@Override
	public void create () {
		
		Gdx.app.log ("SpInGame", "Game created");
		AssetLoader.load();
		
		setScreen (new GameScreen());
	}

	@Override
	public void dispose () {
	
		super.dispose();
        AssetLoader.dispose();
	}
}
