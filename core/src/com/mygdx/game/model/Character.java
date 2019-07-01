package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.JunkyardGame;
import com.mygdx.game.assets.AssetDescriptors;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Character extends Image {

    public Body body;
    protected JunkyardGame game;
    protected final World world;

    private Texture texture;


    public Character(JunkyardGame game, World world, Texture texture, float x, float y, float width, float height) {
        super(texture);

        this.texture = texture;
        this.game = game;
        this.world = world;
        this.setX(x);
        this.setY(y);
        this.setOrigin(x, y);
        this.setWidth(width);
        this.setHeight(height);
        createBody(getX(), getY());

    }

    public String getTextureName(){
        return texture.toString();
    }

    protected void createBody(float x, float y){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);
        body = world.createBody(bodyDef);


        PolygonShape shape = new PolygonShape();
        shape.setAsBox(getWidth() / 2, getHeight() / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 0f;
        fixtureDef.friction = 1f;
        fixtureDef.restitution = 1f;
        fixtureDef.shape = shape;

        body.createFixture(fixtureDef);

        shape.dispose();
    }

    @Override
    public void act(float delta){
        setPosition(body.getPosition().x - getWidth() / 2,body.getPosition().y - getHeight() / 2);
    }

    public static AssetDescriptor<Texture> chooseAsset(){
        List<AssetDescriptor<Texture>> assetsList = AssetDescriptors.assetList();
        Random generator = new Random();
        int assenNumber = generator.nextInt(assetsList.size());
        return assetsList.get(assenNumber);
    }



}
