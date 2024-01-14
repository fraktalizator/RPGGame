package com.embercrest.game.screens.save_create_screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.embercrest.game.EmberCrest;
import com.embercrest.game.Assets;
import com.embercrest.game.drawing_tools.ScreenUtils;
import com.embercrest.game.screens.MenuScreen;
import com.embercrest.game.screens.save_create_screen.character_setup.CharacterSetup;
import com.embercrest.game.screens.save_create_screen.class_section.MagicClassButtonsContainer;
import com.embercrest.game.screens.save_create_screen.class_section.TechClassButtonsContainer;

public class SaveCreateWindow extends Table {
    private final Texture backgroundTexture = Assets.PreGameTextureAssets.SaveCreateWindowBG.getTexture();

    private final MagicClassButtonsContainer magicClasses = new MagicClassButtonsContainer();
    private final CharacterSetup characterSetup = new CharacterSetup();
    private final TechClassButtonsContainer techCLasses = new TechClassButtonsContainer();

    public SaveCreateWindow() {
        super();

        setBackground(new TextureRegionDrawable(backgroundTexture));

        setDebug(false);

        //TODO
        // character preview
        // change body
        // create, random buttons
        // better save format -> load game after save creation
        setUpContent();
        resize();
    }

    private void setUpContent() {
        Vector2 borderSize = ScreenUtils.getResolutionProportion().scl(6);

        top().left(); // set alignment

        add().width(borderSize.x) // TOP Border
                .height(borderSize.y)
                .colspan(7);

        row();

        add().width(borderSize.x).height(borderSize.y); // right border

        add(magicClasses).width(magicClasses.getWidth()).height(magicClasses.getHeight());

        add().width(borderSize.x).height(borderSize.y);  // middle left border

        add(characterSetup).width(characterSetup.getWidth()).height(characterSetup.getHeight());

        add().width(borderSize.x).height(borderSize.y);  // middle right border

        add(techCLasses).width(techCLasses.getWidth()).height(techCLasses.getHeight());

        add().width(borderSize.x).height(borderSize.y);  //  left border

        row();

        setUpButtons();
    }

    private void setUpButtons() {
        TextButton backButton = new TextButton("<- Back", Assets.get().getSkin());
        TextButton randomButton = new TextButton("Random", Assets.get().getSkin());
        TextButton createButton = new TextButton("Create ->", Assets.get().getSkin());
        TextButton animationManageButton = new TextButton("Stop animation", Assets.get().getSkin());

        EmberCrest game = (EmberCrest)Gdx.app.getApplicationListener();

        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen());
                // TODO dispose SaveCreateScreen
            }
        });
        createButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
//                SaveButton selectedSave = getSelectedSaveButton();
//                if(selectedSave != null){
//                    game.setScreen(new LoadingScreen(selectedSave.getSave()));
//                    dispose();
//                }//TODO warning text else warningText.popMessage(2);
                // TODO dispose SaveCreateScreen
            }
        });
        animationManageButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //TODO freeze animation, or change animation to cast spell...
            }
        });

        Table buttonsTable = new Table();
        buttonsTable.setDebug(false);
        buttonsTable.setWidth(getWidth());
        buttonsTable.setHeight(getHeight()/backgroundTexture.getHeight()*54);
        buttonsTable.add(backButton).expand().center();
        buttonsTable.add(randomButton).expand().center();
        buttonsTable.add(animationManageButton).expand().center();
        buttonsTable.add(createButton).expand().center();
        add(buttonsTable).width(buttonsTable.getWidth()).height(buttonsTable.getHeight()).bottom().expand().padBottom(getHeight()/ backgroundTexture.getHeight()*6).colspan(7);
    }

    public void resize(){
        setSize(EmberCrest.VIEVPORT_WIDTH /64*48, EmberCrest.VIEVPORT_HEIGHT /64*48);

        setX(EmberCrest.VIEVPORT_WIDTH /2- EmberCrest.VIEVPORT_WIDTH /64*48/2);
        setY(EmberCrest.VIEVPORT_HEIGHT /2- EmberCrest.VIEVPORT_HEIGHT /64*48/2);
//
//        Vector2 scaledSIze = ScreenUtils.getScaledSizeINT(backgroundTexture.getWidth(), backgroundTexture.getHeight());
//        setWidth(scaledSIze.x);
//        setHeight(scaledSIze.y);
//
//        Vector2 centerPosition = ScreenUtils.getScreenCenterOfImageSizeOfActorINT(this);
//        setPosition(centerPosition.x, centerPosition.y);

        setBounds(getX(),getY(),getWidth(),getHeight());

        magicClasses.resize();
        characterSetup.resize();
        techCLasses.resize();

    }
}
