package com.embercrest.game.ashley.componenets;

import com.badlogic.ashley.core.Component;

public class LevelComponent implements Component {
    private int level = 1;
    private int currentExp = 0;

    public LevelComponent(int level, int currentExp) {
        this.level = level;
        this.currentExp = currentExp;
    }

    public LevelComponent() {
    }

    public enum levelRequirements{
        Level1(100),
        Level2(200),
        Level3(300),
        Level4(500),
        Level5(1000),
        Level6(1200);

        private final int expRequired;

        levelRequirements(int expRequired){
            this.expRequired = expRequired;
        }
    }
}
