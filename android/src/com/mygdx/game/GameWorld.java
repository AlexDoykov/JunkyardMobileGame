package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.model.Player;

public class GameWorld {
    private final float worldWidth;
    private JunkyardGame game;
    private World physicsWorld;
    private Player player;
    private Stage stage;

    public GameWorld(JunkyardGame game){
        this.game = game;
        this.physicsWorld = new World(new Vector2(0,-9.8f),false);
        this.physicsWorld.setContactListener(new B2dContactListener());
        this.player = new Player(game,physicsWorld,game.assets.manager.get(Assets.player, Texture.class),
                0.25f,game.WORLD_HEIGHT / 2,1,1);
        float ratio = (float) Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth();

        this.worldWidth = JunkyardGame.WORLD_HEIGHT / ratio;
        this.stage = new Stage(new StretchViewport(worldWidth, JunkyardGame.WORLD_HEIGHT));

        this.stage.addActor(player);
    }
}
