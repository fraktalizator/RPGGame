package com.embercrest.game.drawing_tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.embercrest.game.EmberCrest;

public class TextureActor extends Actor {
    private Texture texture;
    private String filePath;

//    public TextureActor(AssetDescriptor<Texture> textureAssetDescriptor){
//        texture = Assets.get()2.getTextureAsset(textureAssetDescriptor);
//        this.filePath = texture.toString();
//        setSize(32, 32);
//        setBounds(0, 0, getWidth(), getHeight());
//    }

    public TextureActor(Texture texture){
        this.texture = texture;
        this.filePath = texture.toString();
        setSize(texture.getWidth(), texture.getHeight());
        setBounds(0, 0, getWidth(), getHeight());
    }

//    public TextureActor(Texture texture){
//        this.texture = texture;
//        this.filePath = texture.toString();
//        setSize(texture.getWidth(), texture.getHeight());
//        setBounds(0, 0, getWidth(), getHeight());
//    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
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

    public void setTexture(Texture texture){
        this.texture = texture;
        filePath = texture.toString();
        setSize(getWidth(), getHeight());
    }

    public void setTexture(String filePath){
        this.texture = new Texture(Gdx.files.internal(filePath));
        this.filePath = filePath;
        setSize(texture.getWidth(), texture.getHeight());
    }

    public Texture getTexture(){
        return texture;
    }

    public String getTextureFilePath(){
        return filePath;
    }

//    public void dispose(){
//        if(texture != null) texture.dispose();
//    }
}
