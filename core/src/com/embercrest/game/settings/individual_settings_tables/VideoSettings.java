package com.embercrest.game.settings.individual_settings_tables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Pools;
import com.embercrest.game.Assets;
import com.embercrest.game.EmberCrest;

import java.util.HashMap;

public class VideoSettings extends Table {
    private final Label label = new Label("Video Settings", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
    private final Skin skin = Assets.get().getSkin();
    private final HashMap<String, String> videoSettingsData;

    private TextButton fullScreenBTN;
    private SelectBox<String> resolutions;

    public Resolution resolution;

    public VideoSettings(HashMap<String, String> videoSettingsData) {
        this.videoSettingsData = videoSettingsData;

        initSettings();

        initData();

        initTable();

        //fire(Pools.obtain(ChangeListener.ChangeEvent.class));
    }

    private void initData() {
        resolution = getResolutionBySize(Integer.parseInt(videoSettingsData.get("ResolutionWidth")), Integer.parseInt(videoSettingsData.get("ResolutionHeight")));

        boolean isFullScreen = Boolean.parseBoolean(videoSettingsData.get("FullScreen"));
        if(isFullScreen){
            fullScreenBTN.setText("Windowed");
            Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
        }else {
            fullScreenBTN.setText("Full Screen");
            Gdx.graphics.setWindowedMode(resolution.width, resolution.height);
        }


//        for (String resItem: resolutions.getItems()){
//            if (resolution.toString().equals(resItem)){
//                resolutions.setSelected(resItem);
//            }
//        }
        resolutions.setSelected(resolution.toString());
    }

    private void initSettings() {
        fullScreenBTN = new TextButton("Full Screen", skin);
        fullScreenBTN.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(Gdx.graphics.isFullscreen()) {
                    Gdx.graphics.setWindowedMode(resolution.width, resolution.height);
                    fullScreenBTN.setText("Full Screen");
                }
                else {
                    Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
                    fullScreenBTN.setText("Windowed");
                }
                fireChangeEvent ();
            }
        });

        resolutions = new SelectBox<>(skin);
        String[] resArray = new String[Resolution.values().length];
        int iterator = 0;
        for(Resolution res: Resolution.values()){
            String temp = res.toString();

            resArray[iterator] = temp;
            iterator++;
        }
        resolutions.setItems(resArray);

        resolutions.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                resolution = getResolutionBySize(resolutions.getSelected());
                Gdx.app.getApplicationListener().resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                //TODO save settings
            }
        });

    }

    private void initTable() {
        setDebug(false);
        add(label).expand().colspan(2);
        row();
        add(fullScreenBTN).expand();
        add(resolutions).expand();
        row();
        add().expand();
        add().expand();
        row();
        add().expand();
        add().expand();
        row();
        add().expand();
        add().expand();
        row();
        add().expand();
        add().expand();
        row();
        add().expand();
        add().expand();
        row();
        add().expand();
        add().expand();
    }

    public HashMap<String, String> GetCurrentVideoSettingsData(){
        HashMap<String, String> videoSettingsData = new HashMap<>();
        videoSettingsData.put("ResolutionWidth", resolution.getWidth()+"");
        videoSettingsData.put("ResolutionHeight", resolution.getHeight()+"");
        System.out.println(fullScreenBTN.getText().toString().equals("Windowed"));
        System.out.println(fullScreenBTN.getText().toString().getClass());
        boolean isFullScreen = fullScreenBTN.getText().toString().equals("Windowed");
        videoSettingsData.put("FullScreen", isFullScreen+"");
        //TODO fullscren data is wrong maybe  changelistener odpala sie szybciej niz fullscreen button click
        return videoSettingsData;
    }

    public enum Resolution {
        FullHD(1920,1080),
        HD(1280,720),
        FourK(1920*2, 1080*2),
        qHD(1440, 900);

        final private int width, height;
        final private Vector2 size;

        public int getWidth(){ return width;}
        public int getHeight() {return height;}
        public Vector2 getSize(){return size;}

        public String toString(){
            return getWidth()+"x"+getHeight();
        }

        Resolution(int width, int height){
            this.width = width;
            this.height = height;
            size = new Vector2(width, height);
        }
    }
    public Resolution getResolutionBySize(int width, int height){
        for (Resolution res: Resolution.values()) {
            if(res.width == width && res.height == height) return res;
        }
        return Resolution.FullHD;
    }

    public Resolution getResolutionBySize(String str){
        int indexOfx = str.indexOf("x");
        int width = Integer.parseInt(str.substring(0, indexOfx));
        int height = Integer.parseInt(str.substring(indexOfx+1, str.length()));
        return getResolutionBySize(width, height);
    }

    public void fireChangeEvent () {
        ChangeListener.ChangeEvent changeEvent = Pools.obtain(ChangeListener.ChangeEvent.class);
        try {
            fire(changeEvent);
        } finally {
            Pools.free(changeEvent);
        }
    }
}
