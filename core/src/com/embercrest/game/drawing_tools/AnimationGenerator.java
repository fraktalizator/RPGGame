package com.embercrest.game.drawing_tools;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

public class AnimationGenerator {
    public static ArrayList<Animation<TextureRegion>> setUpAnimation(Texture animationTexture) {
        ArrayList<Animation<TextureRegion>> animation = new ArrayList<>(5);
        TextureRegion[][] tmp = TextureRegion.split(animationTexture, 32, 48);
        TextureRegion[] walkUpFrames = new TextureRegion[4];
        TextureRegion[] walkDownFrames = new TextureRegion[4];
        TextureRegion[] walkRightFrames = new TextureRegion[4];
        TextureRegion[] walkLeftFrames = new TextureRegion[4];

        for (int i = 0; i < 4; i++) {
            walkUpFrames[i] = tmp[3][i];
            walkRightFrames[i] = tmp[2][i];
            walkDownFrames[i] = tmp[0][i];
            walkLeftFrames[i] = tmp[1][i];
        }

        animation.add(new Animation<>(0.125f, walkUpFrames));
        animation.add(new Animation<>(0.125f, walkRightFrames));
        animation.add(new Animation<>(0.125f, walkDownFrames));
        animation.add(new Animation<>(0.125f, walkLeftFrames));
        animation.add(new Animation<>(0.125f, walkDownFrames));
        return animation;
    }
}
