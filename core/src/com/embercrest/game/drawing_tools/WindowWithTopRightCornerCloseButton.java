package com.embercrest.game.drawing_tools;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Window which features the close button in top right corner (button moved outside of the window bounds).
 *
 * @author serhiy
 */
public class WindowWithTopRightCornerCloseButton extends Window {
    private final Texture texture = new Texture(Gdx.files.internal(("Ui/GameScreen/Hud/Inveontry/exitbuttoneq.png")));
    public WindowWithTopRightCornerCloseButton(String title, Skin skin) {
        super(title, skin);
        getTitleLabel().setAlignment(1);

        final Button closeButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(texture)));
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setVisible(false);
            }
        });
        getTitleTable().add(closeButton).size(closeButton.getWidth(), closeButton.getHeight()).padRight(8).padTop(8+closeButton.getHeight());

        setClip(false);
        setTransform(true);
    }

    public void dispose() {
        texture.dispose();
    }
}
