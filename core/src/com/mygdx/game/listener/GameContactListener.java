package com.mygdx.game.listener;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.mygdx.game.JunkyardGame;
import com.mygdx.game.assets.AssetsNames;
import com.mygdx.game.game.GameWorld;
import com.mygdx.game.model.Enemy;
import com.mygdx.game.model.Player;
import com.mygdx.game.processor.GameInputProcessor;

public class GameContactListener implements ContactListener {

    private GameWorld parent;

    public GameContactListener(GameWorld parent){
        this.parent = parent;
    }

    private void kill(Player player, Enemy enemy){
        if(enemy.getTextureName().equals(player.getTextureName())) {
            enemy.die();
            player.isForRespawn = true;
            parent.currentScore = parent.currentScore + 1;
            System.out.println(parent.currentScore);
        }else{
            if(parent.currentScore > JunkyardGame.highscore){
                JunkyardGame.highscore = parent.currentScore;
            }
            player.die();
        }
    }

    @Override
    public void beginContact(Contact contact) {
        String classA = contact.getFixtureA().getBody().getUserData().getClass().getName();
        String classB = contact.getFixtureB().getBody().getUserData().getClass().getName();

        if(classA.equals("com.mygdx.game.model.Player") && classB.equals("com.mygdx.game.model.Enemy")){
            kill(
                    (Player)contact.getFixtureA().getBody().getUserData(),
                    (Enemy)contact.getFixtureB().getBody().getUserData()
            );
        }
        else if(classA.equals("com.mygdx.game.model.Enemy") && classB.equals("com.mygdx.game.model.Player")){
            kill(
                    (Player)contact.getFixtureB().getBody().getUserData(),
                    (Enemy)contact.getFixtureA().getBody().getUserData()
            );

        }

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
