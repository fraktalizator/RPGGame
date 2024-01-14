package com.embercrest.game.ashley.componenets;

import static java.lang.Math.abs;
import static java.lang.Math.decrementExact;

import com.badlogic.ashley.core.Component;

public class MoveComponent implements Component {
    private int range;
    private MoveType type;

    public MoveComponent(int range, MoveType type) {
    this.range = range;
    this.type = type;
    }


    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public MoveType getType() {
        return type;
    }

    public void setType(MoveType type) {
        this.type = type;
    }

    public enum MoveType {
        Normal(1),
        Flying(2),
        WaterWalker(3);

        private final int moveTypeId;
        MoveType(int moveTypeId) {
            this.moveTypeId = moveTypeId;
        }

        public int getMoveTypeId() {
            return moveTypeId;
        }
    }

}
