package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.assets.AssetDescriptors;
import com.mygdx.game.assets.Assets;
import com.mygdx.game.screen.MenuScreen;
import com.mygdx.game.screen.PlayScreen;

public class JunkyardGame extends Game {
	public static final float WORLD_HEIGHT = 20;
	public static final float WORLD_WIDTH =12;
    public static final float WIDTH = 2520;
    public static final float HEIGHT = 4160;
    public Assets assets;
	private SpriteBatch batch;

	public static int highscore;

	public enum GAME_STATE {
        MenuState,
        PlayState
    }


    public GAME_STATE gameState;

	public void drawText(String text, Color color, float scale, SpriteBatch batch, float x, float y){

		final BitmapFont font = new BitmapFont();
		font.getData().setScale(scale);
		font.setColor(color);

		GlyphLayout layout = new GlyphLayout(font, text);

		float fontX = 0 + (x - layout.width) / 2;
		float fontY = 0 + (y + layout.height) / 2;
		font.draw(batch, layout, fontX, fontY);
	}

	@Override
	public void create () {
		assets = new Assets();
		batch = new SpriteBatch();
		assets.load();
		while (!assets.manager.update()) { // Load some, will return true if done loading
            //System.out.println("Loading ... " + assets.manager.getLoadedAssets());
        }
        this.gameState = GAME_STATE.MenuState;
        this.setScreen(new MenuScreen(this));

        highscore = 0;
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		assets.dispose();
	}
}
