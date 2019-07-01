package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.JunkyardGame;

import static java.lang.Math.abs;


public class Enemy extends Character {


    public Enemy(JunkyardGame game, World world, Texture texture, float x, float y, float width, float height) {
        super(game, world, texture, x, y, width, height);
        body.setGravityScale(0.1f);
        body.setUserData(this);
    }

    public void die(){
        this.remove();
    }

}
