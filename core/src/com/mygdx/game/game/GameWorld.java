package com.mygdx.game.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.JunkyardGame;
import com.mygdx.game.listener.GameContactListener;
import com.mygdx.game.model.Enemy;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.Character;
import com.mygdx.game.processor.GameInputProcessor;
import com.mygdx.game.screen.MenuScreen;

public class GameWorld{
    private final Box2DDebugRenderer debugRenderer;
    private final Camera camera;
    public Player player;
    private Stage stage;
    public World world;
    private JunkyardGame game;
    private float timeAux;
    private SpriteBatch batch;
    private GameInputProcessor inputProcessor;

    public int currentScore;

    public GameWorld(JunkyardGame game, Camera camera){
        this.game = game;
        this.camera = camera;
        currentScore = 0;
        timeAux = 2f;
        Vector2 gravity = new Vector2(0, -9.8f);
        world = new World(gravity, false);
        float ratio = (float) Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth();
        float worldWidth = JunkyardGame.WORLD_HEIGHT / ratio;
        this.stage = new Stage(new StretchViewport(worldWidth, JunkyardGame.WORLD_HEIGHT));
        createPlayerActor();
        inputProcessor = new GameInputProcessor(stage, this);
        Gdx.input.setInputProcessor(inputProcessor);



        this.batch = new SpriteBatch();
        this.debugRenderer = new Box2DDebugRenderer();

    }


    private void createPlayerActor(){
        Texture texture = game.assets.manager.get(Character.chooseAsset());
        player = new Player(game, world, texture, JunkyardGame.WORLD_WIDTH * 0.5f,JunkyardGame.WORLD_HEIGHT, 2,2);
        this.stage.addActor(player);

        player.setTouchable(Touchable.enabled);
        world.setContactListener(new GameContactListener(this));
    }

    public void render(float deltaTime) {
        Gdx.gl.glClearColor(1, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.batch.begin();
        this.debugRenderer.render(world,stage.getCamera().combined);
        this.batch.end();


        world.step(deltaTime, 6, 2);
        stage.draw();

        if(player.getY() < 0 || player.isForRespawn){
            if(inputProcessor.joint != null){
                world.destroyJoint(inputProcessor.joint);
                inputProcessor.joint = null;
            }
            world.destroyBody(player.body);
            player.remove();
            createPlayerActor();
            if(player.isForRespawn){
                currentScore = 0;
            }
        }
    }

    public void update() {
        if(timeAux>=2){ //10 seconds
            Texture texture = game.assets.manager.get(Character.chooseAsset());
            Enemy enemy = new Enemy(game, world, texture, JunkyardGame.WORLD_WIDTH - 0.7f, JunkyardGame.WORLD_HEIGHT, 2,2);
            this.stage.addActor(enemy);

            Texture texture1 = game.assets.manager.get(Character.chooseAsset());
            Enemy enemy1 = new Enemy(game, world, texture1, 1, JunkyardGame.WORLD_HEIGHT, 2,2);
            this.stage.addActor(enemy1);
            timeAux=0;
        }else{
            timeAux+=Gdx.graphics.getDeltaTime();
        }

        stage.act();
        if(game.gameState == JunkyardGame.GAME_STATE.MenuState) {
            game.setScreen(new MenuScreen(game));
        }

    }
}
