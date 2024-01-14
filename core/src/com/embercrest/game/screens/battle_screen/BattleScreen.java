package com.embercrest.game.screens.battle_screen;

import static com.embercrest.game.ashley.componenets.PositionComponent.GRIDSIZE;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.embercrest.game.EmberCrest;
import com.embercrest.game.ashley.systems.EntityInputManager;
import com.embercrest.game.game.Items.consumables.Apple;
import com.embercrest.game.game.maps.Xentos;
import com.embercrest.game.game.hud.HUD;
import com.embercrest.game.game_tools.Save;

import box2dLight.DirectionalLight;
import box2dLight.PointLight;
import box2dLight.RayHandler;

public class BattleScreen implements Screen {

    private final OrthographicCamera camera;
    private final Engine engine;

    private final EntityInputManager entityInputManager;
    private final InputMultiplexer inputMultiplexer;

    private final HUD hud;

    private final World world;
    private final Box2DDebugRenderer box2dDebugRenderer;
    private final RayHandler rayHandler;

    private final Xentos xentos;
    private final Save save;

    private boolean inCombatState = false;



    public BattleScreen(Xentos xentos, HUD hud, Save save) {
        this.xentos = xentos;
        this.save = save;
        this.hud = hud;
        engine = xentos.getEngine();
        camera = new OrthographicCamera();
        inputMultiplexer = new InputMultiplexer();

        entityInputManager = new EntityInputManager(camera, xentos);

        world = new World(new Vector2(0, 0), true);
        rayHandler = new RayHandler(world);
        box2dDebugRenderer = new Box2DDebugRenderer();

        inputMultiplexer.addProcessor(hud.stage);
        inputMultiplexer.addProcessor(entityInputManager);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputMultiplexer);
        new DirectionalLight(rayHandler,100, new Color(255/166f, 255/42f, 255/42f, 0.5f), 50);
        new PointLight(rayHandler,100, new Color(255/166f, 255/42f, 255/42f, 0.8f), 400, 13*GRIDSIZE, 20*GRIDSIZE);

        ((BattleScreen)((EmberCrest)Gdx.app.getApplicationListener()).getScreen()).getHud().AddItemToInventory(new Apple(7));
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,0,1);
        camera.update();
        xentos.mapRenderer.setView(camera);
        xentos.mapRenderer.render();

        if (Gdx.input.isKeyPressed(Input.Keys.W)) camera.translate(new Vector2(0, 10f));
        if (Gdx.input.isKeyPressed(Input.Keys.S)) camera.translate(new Vector2(0, -10f));
        if (Gdx.input.isKeyPressed(Input.Keys.A)) camera.translate(new Vector2(-10, 0f));
        if (Gdx.input.isKeyPressed(Input.Keys.D)) camera.translate(new Vector2(10, 0f));

        world.step(delta, 6, 2);
        box2dDebugRenderer.render(world, camera.combined);
        rayHandler.setCombinedMatrix(camera);
        rayHandler.updateAndRender();

        engine.update(delta);

        hud.stage.act(delta);
        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
        hud.resize(width, height);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        hud.dispose();
        world.dispose();
        box2dDebugRenderer.dispose();
        rayHandler.dispose();
        xentos.dispose();
    }

    public Engine getEngine() {
        return engine;
    }
    public boolean isInCombatState() {
        return inCombatState;
    }
    public void setInCombatState(boolean inCombatState) {
        this.inCombatState = inCombatState;
    }
    public Xentos getXentos() {
        return xentos;
    }
    public OrthographicCamera getCamera() {return camera;}

    public HUD getHud() {
        return hud;
    }
}
