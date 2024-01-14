package com.embercrest.game.drawing_tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Disableable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Pools;
import com.embercrest.game.EmberCrest;

public class HealthBar extends Widget implements Disableable{
    private TextureRegionDrawable bg = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Ui/GameScreen/Hud/PlayerFrame/statbar100x14.png"))));
    private TextureRegionDrawable knob = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Ui/GameScreen/Hud/PlayerFrame/HealthBar98x10.png"))));
    Texture text = new Texture(Gdx.files.internal("Ui/GameScreen/Hud/PlayerFrame/statbar100x14.png"));
    private com.badlogic.gdx.scenes.scene2d.ui.ProgressBar.ProgressBarStyle style;
    float min, max, stepSize;
    private float value, animateFromValue;
    float position;
    final boolean vertical;
    private float animateDuration, animateTime;
    private Interpolation animateInterpolation = Interpolation.linear, visualInterpolation = Interpolation.linear;
    boolean disabled;
    private boolean round = true, programmaticChangeEvents = true;

    public HealthBar(float max) {
        this(0, max, 0.5f, false, new ProgressBar.ProgressBarStyle());
        getStyle().background = bg;
        getStyle().knobBefore = knob;
        getStyle().knobBefore = knob;
    }

    public HealthBar (float min, float max, float stepSize, boolean vertical, ProgressBar.ProgressBarStyle style) {
        if (min > max) throw new IllegalArgumentException("max must be > min. min,max: " + min + ", " + max);
        if (stepSize <= 0) throw new IllegalArgumentException("stepSize must be > 0: " + stepSize);
        setStyle(style);
        this.min = min;
        this.max = max;
        this.stepSize = stepSize;
        this.vertical = vertical;
        this.value = min;
        setSize(getPrefWidth(), getPrefHeight());
    }

    public void setStyle (com.badlogic.gdx.scenes.scene2d.ui.ProgressBar.ProgressBarStyle style) {
        if (style == null) throw new IllegalArgumentException("style cannot be null.");
        this.style = style;
        invalidateHierarchy();
    }

    /** Returns the progress bar's style. Modifying the returned style may not have an effect until
     * {@link #setStyle(com.badlogic.gdx.scenes.scene2d.ui.ProgressBar.ProgressBarStyle)} is called. */
    public com.badlogic.gdx.scenes.scene2d.ui.ProgressBar.ProgressBarStyle getStyle () {
        return style;
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
        com.badlogic.gdx.scenes.scene2d.ui.ProgressBar.ProgressBarStyle style = this.style;
        boolean disabled = this.disabled;
        Drawable knob = style.knob, currentKnob = getKnobDrawable();
        Drawable bg = getBackgroundDrawable();
        Drawable knobBefore = getKnobBeforeDrawable();
        Drawable knobAfter = getKnobAfterDrawable();

        Color color = getColor();
        float x = getX(), y = getY();
        float width = getWidth(), height = getHeight();
        float knobHeight = knob == null ? 0 : knob.getMinHeight();
        float knobWidth = knob == null ? 0 : knob.getMinWidth();
        float percent = getVisualPercent();

        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);


        float bgLeftWidth = 2, bgRightWidth = 2;

        float total = width - knobWidth;
        float beforeWidth = MathUtils.clamp(total * percent, 0, total);
        position = bgLeftWidth + beforeWidth;

        float knobWidthHalf = knobWidth * 0.5f;
        if (knobBefore != null) {
            drawRound(batch, knobBefore, //
                    x + bgLeftWidth, //
                    y + (height - knobBefore.getMinHeight()) * 0.5f, //
                    beforeWidth + knobWidthHalf, knobBefore.getMinHeight());
        }
        if (knobAfter != null) {
            drawRound(batch, knobAfter, //
                    x + position + knobWidthHalf, //
                    y + (height - knobAfter.getMinHeight()) * 0.5f, //
                    total - (round ? Math.round(beforeWidth - knobWidthHalf) : beforeWidth - knobWidthHalf), knobAfter.getMinHeight());
        }
        if (currentKnob != null) {
            float w = currentKnob.getMinWidth(), h = currentKnob.getMinHeight();
            drawRound(batch, currentKnob, //
                    x + position + (knobWidth - w) * 0.5f, //
                    y + (height - h) * 0.5f, //
                    w, h);
        }

        if (bg != null) {
            drawRound(batch, bg, x, Math.round(y + (height - bg.getMinHeight()) * 0.5f), width, Math.round(bg.getMinHeight()));
            bgLeftWidth = bg.getLeftWidth();
            bgRightWidth = bg.getRightWidth();
            width -= bgLeftWidth + bgRightWidth;
        }
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

    /** If {@link #setAnimateDuration(float) animating} the progress bar value, this returns the value current displayed. */
    public float getVisualValue () {
        if (animateTime > 0) return animateInterpolation.apply(animateFromValue, value, 1 - animateTime / animateDuration);
        return value;
    }

    /** Sets the visual value equal to the actual value. This can be used to set the value without animating. */
    public void updateVisualValue () {
        animateTime = 0;
    }

    public float getPercent () {
        if (min == max) return 0;
        return (value - min) / (max - min);
    }

    public float getVisualPercent () {
        if (min == max) return 0;
        return visualInterpolation.apply((getVisualValue() - min) / (max - min));
    }

    protected @Null Drawable getBackgroundDrawable () {
        if (disabled && style.disabledBackground != null) return style.disabledBackground;
        return style.background;
    }

    protected @Null Drawable getKnobDrawable () {
        if (disabled && style.disabledKnob != null) return style.disabledKnob;
        return style.knob;
    }

    protected Drawable getKnobBeforeDrawable () {
        if (disabled && style.disabledKnobBefore != null) return style.disabledKnobBefore;
        return style.knobBefore;
    }

    protected Drawable getKnobAfterDrawable () {
        if (disabled && style.disabledKnobAfter != null) return style.disabledKnobAfter;
        return style.knobAfter;
    }

    /** Returns progress bar visual position within the range (as it was last calculated in {@link #draw(Batch, float)}). */
    protected float getKnobPosition () {
        return this.position;
    }

    /** Sets the progress bar position, rounded to the nearest step size and clamped to the minimum and maximum values.
     * {@link #clamp(float)} can be overridden to allow values outside of the progress bar's min/max range.
     * @return false if the value was not changed because the progress bar already had the value or it was canceled by a
     *         listener. */
    public boolean setValue (float value) {
        value = clamp(round(value));
        float oldValue = this.value;
        if (value == oldValue) return false;
        float oldVisualValue = getVisualValue();
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
            animateFromValue = oldVisualValue;
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
        if (vertical) {
            Drawable knob = style.knob, bg = getBackgroundDrawable();
            return Math.max(knob == null ? 0 : knob.getMinWidth(), bg == null ? 0 : bg.getMinWidth());
        } else
            return 140;
    }

    public float getPrefHeight () {
        if (vertical)
            return 140;
        else {
            Drawable knob = style.knob, bg = getBackgroundDrawable();
            return Math.max(knob == null ? 0 : knob.getMinHeight(), bg == null ? 0 : bg.getMinHeight());
        }
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

    /** If > 0, changes to the progress bar value via {@link #setValue(float)} will happen over this duration in seconds. */
    public void setAnimateDuration (float duration) {
        this.animateDuration = duration;
    }

    /** Sets the interpolation to use for {@link #setAnimateDuration(float)}. */
    public void setAnimateInterpolation (Interpolation animateInterpolation) {
        if (animateInterpolation == null) throw new IllegalArgumentException("animateInterpolation cannot be null.");
        this.animateInterpolation = animateInterpolation;
    }

    /** Sets the interpolation to use for display. */
    public void setVisualInterpolation (Interpolation interpolation) {
        this.visualInterpolation = interpolation;
    }

    /** If true (the default), inner Drawable positions and sizes are rounded to integers. */
    public void setRound (boolean round) {
        this.round = round;
    }

    public void setDisabled (boolean disabled) {
        this.disabled = disabled;
    }

    public boolean isAnimating () {
        return animateTime > 0;
    }

    public boolean isDisabled () {
        return disabled;
    }

    /** True if the progress bar is vertical, false if it is horizontal. **/
    public boolean isVertical () {
        return vertical;
    }

    /** If false, {@link #setValue(float)} will not fire {@link ChangeListener.ChangeEvent}. The event will only be fired when the user changes
     * the slider. */
    public void setProgrammaticChangeEvents (boolean programmaticChangeEvents) {
        this.programmaticChangeEvents = programmaticChangeEvents;
    }
}

