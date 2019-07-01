package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.JunkyardGame;
import com.mygdx.game.game.GameWorld;

public class MenuScreen implements Screen {
    private final JunkyardGame game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private GameWorld world;





    public MenuScreen(JunkyardGame game){
        this.game = game;

        batch = new SpriteBatch();

        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, JunkyardGame.WIDTH, JunkyardGame.HEIGHT);
        this.camera.update();



    }

    @Override
    public void show() {

        this.batch = new SpriteBatch();
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, JunkyardGame.WIDTH,JunkyardGame.HEIGHT);
        this.camera.update();
        this.world = new GameWorld(this.game, camera);
        Gdx.gl.glClearColor(0, 1, 0.5f, 1);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        game.drawText("Press the mouse button to start the game", Color.BLACK, 5f, batch, JunkyardGame.WIDTH, JunkyardGame.HEIGHT);
        game.drawText("Your highscore is: " + JunkyardGame.highscore, Color.BLACK, 5f, batch, JunkyardGame.WIDTH, JunkyardGame.HEIGHT - 200);
        batch.end();
        if(Gdx.input.justTouched()){
            game.gameState = JunkyardGame.GAME_STATE.PlayState;
            game.setScreen(new PlayScreen(game));
        }
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
        batch.dispose();
    }
}
