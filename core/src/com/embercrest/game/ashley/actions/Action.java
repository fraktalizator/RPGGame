package com.embercrest.game.ashley.actions;

import com.badlogic.ashley.core.Entity;

@FunctionalInterface
public interface Action {
    boolean act(Entity entity);

    Action pass = (entity)->false;
}

