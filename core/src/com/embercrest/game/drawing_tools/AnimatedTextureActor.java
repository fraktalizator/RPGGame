package com.embercrest.game.drawing_tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;

public class AnimatedTextureActor extends Actor {
    private final ArrayList<Animation<TextureRegion>> playerAnimation;
    private int posFrame = 0;
    private float elapsedTime;
    private boolean freeze = false;
    private boolean autoIncrementPosFrame;

    private Texture textureForDispose;

    // this method can by used only if assets wosnt loaded yet !!!!!!!!!
    public AnimatedTextureActor(Texture texture) {
        setSize(texture.getWidth()/4f, texture.getHeight()/4f);
        playerAnimation = AnimationGenerator.setUpAnimation(texture);
        textureForDispose = texture;
    }

    public AnimatedTextureActor(ArrayList<Animation<TextureRegion>> playerAnimation) {
        setSize(playerAnimation.get(0).getKeyFrame(0).getRegionWidth(), playerAnimation.get(0).getKeyFrame(0).getRegionHeight());
        this.playerAnimation = playerAnimation;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (!freeze) elapsedTime+= Gdx.graphics.getDeltaTime();
        if(playerAnimation.size() > 0 && playerAnimation.get(posFrame) != null) batch.draw(playerAnimation.get(posFrame).getKeyFrame(elapsedTime, true), getX(), getY());
        if(autoIncrementPosFrame && playerAnimation.size() > 0 && playerAnimation.get(4) != null) posFrame = (int) ((playerAnimation.get(4).getAnimationDuration()+ elapsedTime)%4);
    }

    public void dispose(){
        if(textureForDispose != null) textureForDispose.dispose();
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        setBounds(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        setBounds(getX(), getY(), getWidth(), getHeight());
    }

    public void setPosFrame(int posFrame){
        this.posFrame = posFrame%4;
    }

    public void resetElapsedTime(){
        elapsedTime = 0;
    }

    public void freeze(boolean freeze){
        this.freeze = freeze;
    }
    public boolean isFrozen(){return freeze;}

    public void setAutoIncrementPosFrame(boolean bool){
        autoIncrementPosFrame = bool;
    }
}
