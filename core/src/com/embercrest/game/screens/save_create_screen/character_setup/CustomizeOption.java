package com.embercrest.game.screens.save_create_screen.character_setup;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Pools;
import com.embercrest.game.Assets;
import com.embercrest.game.drawing_tools.ScreenUtils;
import com.embercrest.game.drawing_tools.TextureActor;

public class CustomizeOption extends Table {

    public final static int SPACING_WIDTH = 12;
    public final static int WIDTH = 196 + 2*SPACING_WIDTH + 2*26;
    public final static int HEIGHT = 34;

    private final Texture backButtonTexture = Assets.PreGameTextureAssets.CustomizeOptionBackButton.getTexture();
    private final Texture backButtonSelectedTexture  = Assets.PreGameTextureAssets.CustomizeOptionBackButtonSelected.getTexture();
    private final Texture nextButtonTexture  = Assets.PreGameTextureAssets.CustomizeOptionNextButton.getTexture();
    private final Texture nextButtonSelectedTexture  = Assets.PreGameTextureAssets.CustomizeOptionNextButtonSelected.getTexture();
    private final Texture labelBGTexture = Assets.PreGameTextureAssets.CustomizeOptionLabelBackground.getTexture();

    private ImageButton backButton, nextButton;

    protected final Label currentSelectedLabel = new Label("hairssss: 44/15", Assets.get().getSkin());
    TextureActor styleTextArea;
    private int currentStyle = 0;
    private Vector2 labelCenterInt;
    protected final String optionName;
    protected final int max;

    public CustomizeOption(String optionName, int max) {
        super();
        this.optionName = optionName;
        this.max = max;
        setUpContent();
        resize();
        changeStyleId(currentStyle);
    }

    private void setUpContent() {

        backButton = new ImageButton(new TextureRegionDrawable(backButtonTexture), new TextureRegionDrawable(backButtonSelectedTexture)){
            @Override
            public boolean fire(Event event) {
                return false;
            }
        };
        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //changeStyleId((currentStyle-2+max)%max+1);
                changeStyleId((currentStyle-1+max)%max);

            }
        });

        nextButton = new ImageButton(new TextureRegionDrawable(nextButtonTexture), new TextureRegionDrawable(nextButtonSelectedTexture)){
            @Override
            public boolean fire(Event event) {
                return false;
            }
        };
        nextButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //changeStyleId((currentStyle+max)%max+1);
                changeStyleId((currentStyle+max+1)%max);

            }
        });

        currentSelectedLabel.setText(optionName+" "+currentStyle+" / "+max);

        labelCenterInt = new Vector2((int)(labelBGTexture.getWidth()/2f-currentSelectedLabel.getWidth()/2f), (int)(labelBGTexture.getHeight()/2f-currentSelectedLabel.getHeight()/2f));

        styleTextArea = new TextureActor(labelBGTexture){
            @Override
            public void draw(Batch batch, float parentAlpha) {
                super.draw(batch, parentAlpha);
                currentSelectedLabel.setPosition(labelCenterInt.x+getX(), labelCenterInt.y+getY());
                currentSelectedLabel.draw(batch, parentAlpha);
            }
        };

        Vector2 scaledBackButton = ScreenUtils.getScaledSizeINT(backButton.getWidth(), backButton.getHeight());

        Vector2 scaledSpace = ScreenUtils.getScaledSizeINT(SPACING_WIDTH, SPACING_WIDTH);

        add(backButton);
        add().width(12).height(1);
        add(styleTextArea).width(labelBGTexture.getWidth()).height(labelBGTexture.getHeight());
        add().width(12).height(1);
        add(nextButton);
    }

    public void changeStyleId(int newStyleId){
        currentStyle = newStyleId;
        setCustomLabel();
        centerLabel();
        fireChangeEvent();
    }

    public void setCustomLabel(){
        currentSelectedLabel.setText(optionName+" "+currentStyle+" / "+max);
    }

    public void centerLabel(){
        labelCenterInt = new Vector2((int)(labelBGTexture.getWidth()/2f-currentSelectedLabel.getWidth()/2f), (int)(labelBGTexture.getHeight()/2f-currentSelectedLabel.getHeight()/2f));
    }

    public int getCurrentStyle(){return currentStyle;}

//    private void setUpContent() {
//
//        backButton = new ImageButton(new TextureRegionDrawable(backButtonTexture), new TextureRegionDrawable(backButtonSelectedTexture));
//        backButton.addListener(new ClickListener(){
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                currentStyle = (currentStyle-1+max)%max;
//                currentSelectedLabel.setText(optionName+" "+currentStyle+" / "+max);
//            }
//        });
//
//        nextButton = new ImageButton(new TextureRegionDrawable(nextButtonTexture), new TextureRegionDrawable(nextButtonSelectedTexture));
//        nextButton.addListener(new ClickListener(){
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                currentStyle = (currentStyle+1+max)%max;
//                currentSelectedLabel.setText(optionName+" "+currentStyle+" / "+max);
//
//            }
//        });
//
//        currentSelectedLabel.setText(optionName+" "+currentStyle+" / "+max);
//
//        TextureActor label = new TextureActor(labelBGTexture){
//            @Override
//            public void draw(Batch batch, float parentAlpha) {
//                super.draw(batch, parentAlpha);
//                currentSelectedLabel.setPosition(getX()+30, getY()+10);
//                currentSelectedLabel.draw(batch, parentAlpha);
//            }
//        };
//
//        Vector2 scaledBackButton = ScreenUtils.getScaledSizeINT(backButton.getWidth(), backButton.getHeight());
//
//        Vector2 scaledSpace = ScreenUtils.getScaledSizeINT(SPACING_WIDTH, SPACING_WIDTH);
//
//        Vector2 scaledLabelSize = ScreenUtils.getScaledSizeINT(label.getWidth(), label.getHeight());
//
//        add(backButton).width(scaledBackButton.x).height(scaledBackButton.y);
//
//        add().width(scaledSpace.x).height(scaledSpace.y);
//
//        add(label).width(scaledLabelSize.x).height(scaledLabelSize.y);
//
//        add().width(scaledSpace.x).height(scaledSpace.y);
//
//        add(nextButton).width(scaledBackButton.x).height(scaledBackButton.y);
//    }

    public void resize(){
        Vector2 scaledSize = ScreenUtils.getScaledSize(WIDTH, HEIGHT);
        setWidth(scaledSize.x);
        setHeight(scaledSize.y);
        setBounds(getX(), getY(), getWidth(), getHeight());
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
