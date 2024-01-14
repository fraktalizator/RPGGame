package com.embercrest.game.ashley.componenets;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.embercrest.game.EmberCrest;
import com.embercrest.game.drawing_tools.AnimationGenerator;

import java.util.ArrayList;

public class RenderComponent implements Component {
    //for non animated entities
    private Texture texture;

    //for all entities
    private int Zindex;
    private boolean isVisible = true;

    //for  animated entities
    private final boolean animate;
    private TextureRegion textureRegion;
    private float elapsedTime=0;
    private int posFrame = 0;
    private boolean freeze = false;
    private ArrayList<Animation<TextureRegion>> playerAnimation = new ArrayList<>(5);
    private float frameWidth, frameHeight;

    public RenderComponent(Texture texture, int Zindex) {
        this.Zindex = Zindex;
        this.animate = false;
        textureRegion = new TextureRegion(texture);
    }

    public RenderComponent(Texture texture, int Zindex, float frameWidth, float frameHeight) {
        this.Zindex = Zindex;
        this.animate = true;
        textureRegion = new TextureRegion(texture);
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        if(frameWidth*4 != textureRegion.getRegionWidth() || frameHeight*4 != textureRegion.getRegionHeight()) throw new IllegalArgumentException("Unable to create animation from texture this size");

        playerAnimation = AnimationGenerator.setUpAnimation(texture);
    }

    public boolean isAnimate() {
        return animate;
    }

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }
    public void setTextureRegion(TextureRegion textureRegion) {this.textureRegion = textureRegion;}
    public Texture getTexture() {
        return texture;
    }
    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public int getZindex() {return Zindex;}
    public void setZindex(int zindex) {Zindex = zindex;}

    public boolean isVisible() {return isVisible;}
    public void setVisible(boolean visible) {isVisible = visible;}

    public ArrayList<Animation<TextureRegion>> getPlayerAnimation() {return playerAnimation;}

    public float getElapsedTime() {return elapsedTime;}
    public void setElapsedTime(float elapsedTime) {this.elapsedTime = elapsedTime;}

    public int getPosFrame() {return posFrame;}
    public void setPosFrame(int posFrame) {this.posFrame = posFrame%4;}

    public boolean getFreezeAnimation() {return freeze;}
    public void setFreezeAnimation(boolean freeze) {this.freeze = freeze;}
}
