package com.embercrest.game.ashley.componenets;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class PositionComponent implements Component {
    private float x, y, width, height;
    private Rectangle bounds;
    public final static int GRIDSIZE = 32;
    public PositionComponent(float x, float y, float width, float height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        bounds = new Rectangle(x, y, width, height);

    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public Vector2 getPosition() {
        return new Vector2(x, y);
    }

    public void setX(float x) {
        this.x = x;
        bounds = new Rectangle(x, y, width, height);
    }

    public void setY(float y) {
        this.y = y;
        bounds = new Rectangle(x, y, width, height);
    }

    public void setWidth(float width) {
        this.width = width;
        bounds = new Rectangle(x, y, width, height);
    }

    public void setHeight(float height) {
        this.height = height;
        bounds = new Rectangle(x, y, width, height);
    }

    public void setPosition(Vector2 position) {
        this.x = position.x;
        this.y = position.y;
        bounds = new Rectangle(x, y, width, height);
    }

}
