package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.JunkyardGame;

import static java.lang.Math.abs;


public class Player extends Character {

    public boolean isForRespawn;

    public Player(JunkyardGame game, World world, Texture texture, float x, float y, float width, float height) {
        super(game, world, texture, x, y, width, height);
        body.setUserData(this);
        isForRespawn = false;
    }

    public void die() {
        game.gameState = JunkyardGame.GAME_STATE.MenuState;
    }


}
