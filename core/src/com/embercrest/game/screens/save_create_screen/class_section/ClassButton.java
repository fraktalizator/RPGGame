package com.embercrest.game.screens.save_create_screen.class_section;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Pools;
import com.embercrest.game.Assets;
import com.embercrest.game.drawing_tools.ScreenUtils;

public class ClassButton extends Table {
    private final Texture bgTexture = Assets.PreGameTextureAssets.ClassButton.getTexture();
    private final  Texture bgTextureSelected = Assets.PreGameTextureAssets.ClassButtonSelected.getTexture();
    public boolean selected;

    public ClassButton() {
        super();
        setBackground(new TextureRegionDrawable(new TextureRegion(bgTexture)));
        setTouchable(Touchable.enabled);
        resize();

        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                unSelectAllButtons();
                selectButton();
            }
        });
    }

    public void selectButton(){
        selected = true;
        setBackground(new TextureRegionDrawable(new TextureRegion(bgTextureSelected)));
    }
    
    public void unSelectButton(){
        selected = false;
        setBackground(new TextureRegionDrawable(new TextureRegion(bgTexture)));
    }

    public static int getSelectedClassId(){
        for (MagicClassButtonsContainer.MagicClasses mClass: MagicClassButtonsContainer.MagicClasses.values()) {
            if(mClass.button.selected) return mClass.getClassID();
        }

        for (TechClassButtonsContainer.TechClasses tClass: TechClassButtonsContainer.TechClasses.values()) {
            if(tClass.button.selected) return tClass.getClassID();
        }
        return -1;
    }

    public static void unSelectAllButtons(){
        for (MagicClassButtonsContainer.MagicClasses mClass: MagicClassButtonsContainer.MagicClasses.values()) {
            mClass.getButton().unSelectButton();
        }

        for (TechClassButtonsContainer.TechClasses tClass: TechClassButtonsContainer.TechClasses.values()) {
            tClass.getButton().unSelectButton();
        }
    }


    public void resize() {
        Vector2 size = ScreenUtils.getScaledSizeINT(bgTexture.getWidth(), bgTexture.getHeight());
        setSize(size.x, size.y);
        setBounds(getX(), getY(), getWidth(), getHeight());
    }
    
}
