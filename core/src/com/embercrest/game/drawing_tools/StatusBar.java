package com.embercrest.game.drawing_tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Disableable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Pools;

public class StatusBar extends Widget{
    float min, max, stepSize;
    private float value, animateFromValue;
    private float animateDuration, animateTime;
    private boolean round = true, programmaticChangeEvents = true;

    private StatusBarStyle statusBarStyle;

    public StatusBar(float max) {
        this(0, max, 0.5f, false, new StatusBarStyle());
    }

    public StatusBar(float max, StatusBarStyle style) {
        this(0, max, 0.5f, false, style);
    }

    public StatusBar (float min, float max, float stepSize, boolean vertical, StatusBarStyle style) {
        if (min > max) throw new IllegalArgumentException("max must be > min. min,max: " + min + ", " + max);
        if (stepSize <= 0) throw new IllegalArgumentException("stepSize must be > 0: " + stepSize);
        setStyle(style);
        this.min = min;
        this.max = max;
        this.stepSize = stepSize;
        this.value = min;
        setSize(statusBarStyle.background.getMinWidth(), statusBarStyle.background.getMinHeight());
        animateTime = 2f;
        animateDuration = 2f;
    }

    public void setStyle (StatusBarStyle statusBarStyle) {
        if (statusBarStyle == null) setStyle(new StatusBarStyle());
        this.statusBarStyle = statusBarStyle;
        invalidateHierarchy();
    }

    public StatusBarStyle getStatusBarStyle() {
        return statusBarStyle;
    }

    public void act (float delta) {
        super.act(delta);
        if (animateTime > 0) {
            animateTime -= delta;
            Stage stage = getStage();
            if (stage != null && stage.getActionsRequestRendering()) Gdx.graphics.requestRendering();
        }
    }

    public void draw (Batch batch, float parentAlpha) {
        Drawable knob = statusBarStyle.knob;
        Drawable bg = statusBarStyle.background;

        Color color = getColor();
        float x = getX(), y = getY();
        float width = getWidth(), height = getHeight();
        float knobHeight = knob.getMinHeight();
        float knobWidth = knob.getMinWidth();
        float percent = getPercent();

        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);


        float bgLeftWidth = 2, bgRightWidth = 2;

        float total = width-bgLeftWidth-bgRightWidth;
        float beforeWidth = MathUtils.clamp(total * percent, 0, total);

        float knobWidthHalf = knobWidth * 0.5f;


        drawRound(batch, knob, //
                x + bgLeftWidth, //
                y + (height - knob.getMinHeight()) * 0.5f, //
                beforeWidth, knob.getMinHeight());

        //Draw bg
        drawRound(batch, bg, x, Math.round(y + (height - bg.getMinHeight()) * 0.5f), width, Math.round(bg.getMinHeight()));
        bgLeftWidth = bg.getLeftWidth();
        bgRightWidth = bg.getRightWidth();
        width -= bgLeftWidth + bgRightWidth;
    }

    private void drawRound (Batch batch, Drawable drawable, float x, float y, float w, float h) {
        if (round) {
            x = Math.round(x);
            y = Math.round(y);
            w = Math.round(w);
            h = Math.round(h);
        }
        drawable.draw(batch, x, y, w, h);
    }

    public float getValue () {
        return value;
    }

    public float getPercent () {
        if (min == max) return 0;
        return (value - min) / (max - min);
    }

    public boolean setValue (float value) {
        value = clamp(round(value));
        float oldValue = this.value;
        if (value == oldValue) return false;
        this.value = value;

        if (programmaticChangeEvents) {
            ChangeListener.ChangeEvent changeEvent = Pools.obtain(ChangeListener.ChangeEvent.class);
            boolean cancelled = fire(changeEvent);
            Pools.free(changeEvent);
            if (cancelled) {
                this.value = oldValue;
                return false;
            }
        }

        if (animateDuration > 0) {
            animateFromValue = oldValue;
            animateTime = animateDuration;
        }
        return true;
    }

    /** Rouinds the value using the progress bar's step size. This can be overridden to customize or disable rounding. */
    protected float round (float value) {
        return Math.round(value / stepSize) * stepSize;
    }

    /** Clamps the value to the progress bar's min/max range. This can be overridden to allow a range different from the progress
     * bar knob's range. */
    protected float clamp (float value) {
        return MathUtils.clamp(value, min, max);
    }

    /** Sets the range of this progress bar. The progress bar's current value is clamped to the range. */
    public void setRange (float min, float max) {
        if (min > max) throw new IllegalArgumentException("min must be <= max: " + min + " <= " + max);
        this.min = min;
        this.max = max;
        if (value < min)
            setValue(min);
        else if (value > max) //
            setValue(max);
    }

    public void setStepSize (float stepSize) {
        if (stepSize <= 0) throw new IllegalArgumentException("steps must be > 0: " + stepSize);
        this.stepSize = stepSize;
    }

    public float getPrefWidth () {
        Drawable  bg = statusBarStyle.background;
        return bg.getMinWidth();
    }

    public float getPrefHeight () {
            Drawable  bg = statusBarStyle.background;
            return bg.getMinHeight();
    }

    public float getMinValue () {
        return this.min;
    }

    public float getMaxValue () {
        return this.max;
    }

    public float getStepSize () {
        return this.stepSize;
    }

    public void setAnimateDuration (float duration) {
        this.animateDuration = duration;
    }


    public void setRound (boolean round) {
        this.round = round;
    }


    public boolean isAnimating () {
        return animateTime > 0;
    }


    public void setProgrammaticChangeEvents (boolean programmaticChangeEvents) {
        this.programmaticChangeEvents = programmaticChangeEvents;
    }

    static public class StatusBarStyle {

        public Drawable background = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Ui/GameScreen/Hud/PlayerFrame/statbar100x14.png"))));
        public Drawable knobHP = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Ui/GameScreen/Hud/PlayerFrame/HealthBar98x10.png"))));

        public Drawable knobENERGY = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Ui/GameScreen/Hud/PlayerFrame/EnergyBar98x10.png"))));

        public Drawable knobDARKENERGY = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Ui/GameScreen/Hud/PlayerFrame/DarkEnergyBar98x10.png"))));
        public Drawable knobMANA = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Ui/GameScreen/Hud/PlayerFrame/ManaBar98x10.png"))));

        public Drawable knob;

        public StatusBarStyle () {
            knob = knobHP;
        }

        public StatusBarStyle (int i) {
            if(i == 0) knob = knobHP;
            else if(i == 1) knob = knobMANA;
            else if(i == 2) knob = knobENERGY;
            else if(i == 3) knob = knobDARKENERGY;
        }

    }
}

