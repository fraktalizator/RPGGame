package com.embercrest.game.screens.loading_screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.embercrest.game.Assets;
import com.embercrest.game.EmberCrest;
import com.embercrest.game.ashley.systems.AshleyScene2dConector;
import com.embercrest.game.ashley.systems.CombatRenderSystem;
import com.embercrest.game.ashley.systems.MovementSystem;
import com.embercrest.game.ashley.systems.RenderSystem;
import com.embercrest.game.drawing_tools.CharacterTextureGeneratorFromParts;
import com.embercrest.game.game.hud.HUD;
import com.embercrest.game.game.maps.Xentos;
import com.embercrest.game.game_tools.Save;
import com.embercrest.game.screens.battle_screen.BattleScreen;
import com.embercrest.game.screens.game_screen.GameScreen;

public class LoadingScreen implements Screen {
    private final EmberCrest game;

    private final OrthographicCamera camera;
    private final Stage stage;
    private final Sprite backgroundSprite;
    private final ProgressBar progressBar;

    private String loadingInfoString = "Loading Assets...";
    private ClassInitializer classInitializer;

    // passed to GameScreen after init
    private BattleScreen battleScreen;
    private GameScreen gameScreen;
    private HUD hud;

    private final Save save;

    public LoadingScreen(){
        //TODO REMOVE !!!!!!! ONLY FOR TESTING
        this(null);
    }


    public LoadingScreen(Save save){
        this.save = save;
        game = (EmberCrest)Gdx.app.getApplicationListener();
        //Gdx.app.log("Loading save", save.getPlayerData().get("Name"));
        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        stage = new Stage(new ScreenViewport(camera), game.mainBatch);
        Assets.get().loadGameAssets();
        Gdx.input.setInputProcessor(stage);

        backgroundSprite = new Sprite(new Texture(Gdx.files.internal("Ui/MenuScreen/BG.jpg")));
        progressBar = new ProgressBar(0, 1, 0.01f, false, Assets.get().getSkin());
        progressBar.setWidth(300);
        progressBar.setHeight(30);
        //progressBar.getStyle().knob = GetDrawableShape.getColoredDrawable(0, 30, new Color(0x2CA120ff));
        //progressBar.getStyle().knobBefore = GetDrawableShape.getColoredDrawable(300, 30, new Color(0x2CA120ff));
        progressBar.setPosition(Gdx.graphics.getWidth()/2f- progressBar.getWidth()/2, Gdx.graphics.getHeight()/2.5f- progressBar.getHeight()/2);
        stage.addActor(progressBar);

    }

    @Override
    public void show() {
        //load all assets to que
        classInitializer = new ClassInitializer();
    }

    @Override
    public void render(float delta) {
        stage.act(delta);
        ScreenUtils.clear(0, 0, 0,1);

        try {
            loadAssets();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        stage.getBatch().begin();
        backgroundSprite.draw(stage.getBatch());
        Assets.get().getSkin().getFont("antek").draw(game.mainBatch, loadingInfoString, Gdx.graphics.getWidth()/2f- progressBar.getWidth()/2, Gdx.graphics.getHeight()/2f- progressBar.getHeight()/2);
        stage.getBatch().end();
        stage.draw();

    }
    public void loadAssets() throws Exception {
        if(Assets.get().update()){

            if(classInitializer.update()) {

                game.setScreen(battleScreen);
                dispose();

            } else progressBar.setValue(0.5f+(classInitializer.progress/2));

        }else progressBar.setValue(Assets.get().assetManager.getProgress()/2);
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
        stage.getViewport().update(width, height);
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
        stage.dispose();
        backgroundSprite.getTexture().dispose();
    }

    private class ClassInitializer {
        public float progress = 0;
        private int task = 0;

        private Xentos xentos;

        public ClassInitializer(){
        }

        public boolean update() throws Exception {
            return initGameDone();
        }

        public void hudInit(){
            Gdx.app.debug("hudInit", "started");
            hud = new HUD(((EmberCrest)Gdx.app.getApplicationListener()).mainBatch);
        }

        public void worldInit(){
            Gdx.app.debug("worldInit", "started");

            xentos = new Xentos(new Vector2(0, 0));

        }

        public void abilitiesAndItemsInit(){
            Gdx.app.debug("abilitiesInit", "started");

        }

        private void interactablesInit() throws Exception {
            Gdx.app.debug("interactablesInit", "started");
            xentos.initNPC();
            xentos.initDoors();
            xentos.initGatherables();
        }


        public void PartyInit(){
            Gdx.app.debug("partyInit", "started");
            battleScreen = new BattleScreen(xentos, hud, save);
            MovementSystem movementSystem = new MovementSystem(xentos);
            xentos.getEngine().addSystem(new RenderSystem(game.mainBatch, battleScreen.getCamera()));
            xentos.getEngine().addSystem(movementSystem);
            xentos.getEngine().addSystem(new AshleyScene2dConector(hud.stage, hud, battleScreen.getCamera()));
            xentos.getEngine().addSystem(new CombatRenderSystem(game.mainBatch, battleScreen.getCamera()));


            hud.setPartyFrameAndManager(xentos.getParty());
            hud.setMinimap(xentos, battleScreen.getCamera());
            hud.init();
        }

        public boolean initGameDone() throws Exception {
            int taskQuantity = 6;
            if (task >= taskQuantity){
                progress = 1;
                Gdx.app.debug("Loading Done", "initGameDone");
                return true;
            }
            else if (task == 0) {
                loadingInfoString = "hud Init...";
                task++;
                return false;
            }

            else if (task == 1) {
                hudInit();
                progress += 0.2f;
                task++;
                loadingInfoString = "world Init....";
                return false;
            }

            else if (task == 2) {
                worldInit();
                progress += 0.2f;
                task++;
                loadingInfoString = "items and abilities Init...";
                return false;
            }

            else if (task == 3) {
                abilitiesAndItemsInit();
                progress += 0.2f;
                task++;
                loadingInfoString = "NPC init";
                return false;
            }
            else if (task == 4) {
                interactablesInit();
                progress += 0.2f;
                task++;
                loadingInfoString = "Party init...";
                return false;
            }
            else if (task == 5){
                PartyInit();
                progress += 0.2f;
                task++;

                return false;
            }
            return false;
        }

    }
}
