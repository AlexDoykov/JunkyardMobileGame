package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.JunkyardGame;
import com.mygdx.game.assets.AssetDescriptors;
import com.mygdx.game.game.GameWorld;
import com.mygdx.game.processor.GameInputProcessor;

public class PlayScreen implements Screen {
    private final JunkyardGame game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private GameWorld world;

    public PlayScreen(JunkyardGame game){
        this.game = game;
    }




    @Override
    public void show() {
        this.batch = new SpriteBatch();
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false,JunkyardGame.WIDTH,JunkyardGame.HEIGHT);
        this.world = new GameWorld(this.game, camera);
        Gdx.gl.glClearColor(0, 0, 1, 1);
    }

    @Override
    public void render(float delta) {
        world.render(delta);
        world.update();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
