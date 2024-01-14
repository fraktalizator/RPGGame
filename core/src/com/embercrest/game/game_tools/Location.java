package com.embercrest.game.game_tools;

import com.badlogic.gdx.math.Vector2;

public enum Location {
    TutorialIsland("Tutorial Island", new Vector2(0,0));
    
    
    String name;
    Vector2 chunk;

    Location(String name, Vector2 chunk){
        this.name = name;
        this.chunk = chunk;
    }

    public String getName() {
        return name;
    }

    public Vector2 getChunk() {
        return chunk;
    }
}
//TODO get chunk pos by player pos
