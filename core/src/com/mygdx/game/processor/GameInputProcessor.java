package com.mygdx.game.processor;


import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.game.GameWorld;

public class GameInputProcessor implements InputProcessor {

    private Stage stage;
    private GameWorld world;
    public MouseJoint joint;

    public GameInputProcessor(Stage stage, GameWorld world){
        this.stage = stage;
        this.world = world;
        joint = null;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 stageCoordinates = stage.screenToStageCoordinates(new Vector2(screenX, screenY));
        if(stageCoordinates.x > world.player.getX() && stageCoordinates.x < world.player.getX() + world.player.getWidth() &&
                stageCoordinates.y > world.player.getY() && stageCoordinates.y < world. player.getY() + world.player.getHeight()) {

            Body groundBody;
            BodyDef bodyDef = new BodyDef();
            groundBody = world.world.createBody(bodyDef);

            MouseJointDef jointDef = new MouseJointDef();
            jointDef.target.set(stageCoordinates);
            jointDef.bodyA = groundBody;
            jointDef.bodyB = world.player.body;
            jointDef.maxForce = 1000.0f * world.player.body.getMass();
            jointDef.collideConnected = true;

            joint = (MouseJoint) world.world.createJoint(jointDef);
            world.player.body.setAwake(true);
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (joint != null) {
            world.world.destroyJoint(joint);
            joint = null;
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (joint != null) {
            Vector2 stageCoordinates = stage.screenToStageCoordinates(new Vector2(screenX, screenY));
            joint.setTarget(stageCoordinates);
        }

        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
