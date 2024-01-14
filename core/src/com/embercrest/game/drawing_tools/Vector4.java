package com.embercrest.game.drawing_tools;

import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Vector4 {
    public float up, left, down, right;

    public Vector4 () {
        this.setZero();
    }
    public Vector4 (float up, float left, float down, float right) {
        this.set(up, left, down, right);
    }

    public Vector4 (final Vector4 vector) {
        this.set(vector);
    }

    public Vector4 set (float up, float left, float down, float right) {
        this.up = up;
        this.left = left;
        this.down = down;
        this.right = right;
        return this;
    }

    public Vector4 set (Vector4 vector4) {
        this.up = vector4.up;
        this.left = vector4.left;
        this.down = vector4.down;
        this.right = vector4.right;
        return this;
    }

    public Vector4 add (float up, float left, float down, float right) {
        return this.set(this.up + up, this.left + left, this.down + down, this.right + right);
    }

    public Vector4 add (Vector4 vector4) {
        return this.set(this.up + vector4.up, this.left + vector4.left, this.down + vector4.down, this.right + vector4.right);
    }

    public Vector4 scl (float number) {
        return this.set(this.up * number, this.left * number, this.down * number, this.right + number);
    }

    public float minNonzero(){
        ArrayList<Float> list = new ArrayList<>();

        list.add(this.up);
        list.add(this.right);
        list.add(this.down);
        list.add(this.left);

        float minValue = list.get(1);

        for(int i=0; i<list.size(); i++) {
            if(list.get(i) == 0) continue;
            else if (list.get(i) < minValue || minValue == 0) minValue = list.get(i);
        }

        return minValue;// == Float.MAX_VALUE ? 0: minValue;
    }

    public Vector4 setZero () {
        this.up = 0;
        this.left = 0;
        this.down = 0;
        this.right = 0;
        return this;
    }

    public void setUp (float value) {
        this.up = value;
    }

    public void setLeft (float value) {
        this.left = value;
    }

    public void setDown (float value) {
        this.down = value;
    }

    public void setRight (float value) {
        this.right = value;
    }

    public String toString () {
        return "(" + up + "," + right + "," + down + "," + left + "," +")";
    }
}
