package com.embercrest.game.screens.save_create_screen.character_setup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.embercrest.game.Assets;
import com.embercrest.game.drawing_tools.AnimationGenerator;
import com.embercrest.game.drawing_tools.ScreenUtils;

import java.util.ArrayList;
import java.util.Arrays;

public class CharacterPreview extends Window {
    private final Texture backgroundTexture = Assets.PreGameTextureAssets.CharacterPreviewBG.getTexture();
    private final Texture backgroundTextureBorder = Assets.PreGameTextureAssets.CharacterPreviewBGBorder.getTexture();

    private int posFrame;
    public float elapsedTime;

    private ArrayList<Animation<TextureRegion>> bodyAnimation;

    private ArrayList<Animation<TextureRegion>> hairAnimation;

    private ArrayList<Animation<TextureRegion>> clothAnimation;


    public CharacterPreview() {
        super("", Assets.get().getSkin());
        setBackground(new TextureRegionDrawable(backgroundTexture));
        setResizable(false);
        setMovable(false);
        resize();

        change(new Integer[]{0,0,0,0,0});
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        elapsedTime+= Gdx.graphics.getDeltaTime();
        if(bodyAnimation.size() > 0 && bodyAnimation.get(4) != null) posFrame = (int) ((bodyAnimation.get(4).getAnimationDuration()+ elapsedTime)%4);
        if(bodyAnimation.size() > 0) batch.draw(bodyAnimation.get(posFrame).getKeyFrame(elapsedTime, true), getX(), getY());
        if(hairAnimation.size() > 0) batch.draw(hairAnimation.get(posFrame).getKeyFrame(elapsedTime, true), getX(), getY());
        if(clothAnimation.size() > 0) batch.draw(clothAnimation.get(posFrame).getKeyFrame(elapsedTime, true), getX(), getY());
        //batch.draw(backgroundTextureBorder, getX(), getY(), getWidth(), getHeight());
        System.out.println(bodyAnimation);
    }

    public void change(Integer[] lookIdArray){
        int sex = lookIdArray[0];
        int hair = lookIdArray[1];
        int hairColor = lookIdArray[2];
        int body = lookIdArray[3];
        int cloth = lookIdArray[4];
        bodyAnimation = null;
        bodyAnimation = AnimationGenerator.setUpAnimation(Assets.FemaleBodyTextureAssets.values()[body].getTexture());
        hairAnimation = AnimationGenerator.setUpAnimation(Assets.FemaleHairTextureAssets.values()[hair].getTexture()); //TODO color
        clothAnimation = AnimationGenerator.setUpAnimation(Assets.FemaleClothTextureAssets.values()[cloth].getTexture());
        //System.out.println(Arrays.toString(lookIdArray)+"  +"+bodyAnimation);
    }

    public void resize(){
        Vector2 scaledSize = ScreenUtils.getScaledSizeINT(backgroundTexture.getWidth(),backgroundTexture.getHeight());

        setSize(scaledSize.x, scaledSize.y);
        setBounds(getX(), getY(), getWidth(), getHeight());
    }

    public void dispose(){
        //TODO ???
    }

}
