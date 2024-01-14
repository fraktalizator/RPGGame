package com.embercrest.game.drawing_tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.embercrest.game.EmberCrest;
import com.embercrest.game.settings.individual_settings_tables.VideoSettings;

public class ScreenUtils {

    public static Vector2 getScreenCenter(){
        return new Vector2(Gdx.graphics.getWidth()/2f, Gdx.graphics.getHeight()/2f);
    }
    public static Vector2 getScreenCenterOfImageSize(Vector2 size){
        return getScreenCenter().add(new Vector2(size).scl(-1/2f));
    }

    public static Vector2 getScreenCenterOfImageSize(float width, float height){
        return getScreenCenterOfImageSize(new Vector2(width, height));
    }

    public static Vector2 getScreenCenterOfImageSizeOfActor(Actor actor){
        return getScreenCenterOfImageSize(new Vector2(actor.getWidth(), actor.getHeight()));
    }

    public static Vector2 getScreenCenterOfImageSizeINT(float width, float height){
        Vector2 centr = getScreenCenterOfImageSize(new Vector2(width, height));
        return new Vector2((int) centr.x, (int) centr.y);
    }

    public static Vector2 getScreenCenterOfImageSizeINT(Vector2 size){
        Vector2 centr = getScreenCenterOfImageSize(size);
        return new Vector2((int) centr.x, (int) centr.y);
    }

    public static Vector2 getScreenCenterOfImageSizeOfActorINT(Actor actor){
        return getScreenCenterOfImageSizeINT(new Vector2(actor.getWidth(), actor.getHeight()));
    }

    public static Vector2 getResolutionSize(){
        return EmberCrest.get().settingsWindow.videoSettings.resolution.getSize();
    }

    public static Vector2 getResolutionProportion(){
        return new Vector2(getResolutionSize().x/ VideoSettings.Resolution.FullHD.getWidth(), getResolutionSize().y/ VideoSettings.Resolution.FullHD.getHeight());
    }

    public static Vector2 getScaledSize(Vector2 originalSize){
        Vector2 proportion =  getResolutionProportion();
        return new Vector2(originalSize.x*proportion.x, originalSize.y*proportion.y);
    }

    public static Vector2 getScaledSize(float width, float height){
        return getScaledSize(new Vector2(width, height));
    }

    public static Vector2 getScaledSizeINT(Vector2 originalSize){
        Vector2 scaled = getScaledSize(originalSize);
        return new Vector2((int) scaled.x, (int) scaled.y);
    }

    public static Vector2 getScaledSizeINT(float width, float height){
        return getScaledSizeINT(new Vector2(width, height));
    }
}
