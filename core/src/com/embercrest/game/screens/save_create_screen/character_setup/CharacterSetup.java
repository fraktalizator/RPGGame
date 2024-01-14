package com.embercrest.game.screens.save_create_screen.character_setup;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.embercrest.game.Assets;
import com.embercrest.game.EmberCrest;
import com.embercrest.game.drawing_tools.ScreenUtils;
import com.embercrest.game.drawing_tools.TextureActor;

import java.util.Arrays;

public class CharacterSetup extends Table {
    public final static int WIDTH = 474;
    public final static int HEIGHT = 882;

    private final CharacterPreview characterPreview = new CharacterPreview();

    private final CustomizeOptionsContainer customizeOptionsContainer = new CustomizeOptionsContainer();

    public CharacterSetup() {
        super();
        left().top();
        resize();
        setDebug(false);
        setUpContent();
        customizeOptionsContainer.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                characterPreview.change(customizeOptionsContainer.getInformation());
            }
        });
    }

    private void setUpContent(){
        addTopSpacer();

        addLeftSpacer();

        add(characterPreview).width(characterPreview.getWidth()).height(characterPreview.getHeight());

        addLeftSpacer();

        addSpacer();

        //TextArea nameEntry = new TextArea("",Assets.get().getSkin());
        TextureActor nameEntryPH = new TextureActor(Assets.PreGameTextureAssets.CustomizeOptionLabelBackground.getTexture());
        add(nameEntryPH).colspan(3);

        addSpacer();
        //TODO czasami wystaje na dole a czasami za krotkie, poprawic height
        bottom().left();
        add(customizeOptionsContainer).width(customizeOptionsContainer.getWidth()).height(customizeOptionsContainer.getHeight()).colspan(3);

    }

    private void addTopSpacer() {
        int spacerHeight = 53;
        int spacerWidth = 475;
        Vector2 scaledSize = ScreenUtils.getScaledSizeINT(spacerWidth, spacerHeight);
        add().width(scaledSize.x).height(scaledSize.y).colspan(3);
        row();
    }

    private void addLeftSpacer() {
        int spacerHeight = 91;
        int spacerWidth = 91;
        Vector2 scaledSize = ScreenUtils.getScaledSizeINT(spacerWidth, spacerHeight);
        add().width(scaledSize.x).height(scaledSize.y);
    }


    private void addSpacer(){
        int spacerHeight = 25;
        Vector2 scaled = ScreenUtils.getScaledSizeINT(spacerHeight, spacerHeight);
        row();
        add().width(scaled.x).height(scaled.y).colspan(3);
        row();
    }

    public void resize(){
        Vector2 scaledSize = ScreenUtils.getScaledSizeINT(WIDTH, HEIGHT);
        setWidth(scaledSize.x);
        setHeight(scaledSize.y);

        setBounds(getX(), getY(), getWidth(), getHeight());

        characterPreview.resize();
        customizeOptionsContainer.resize();
    }
}
