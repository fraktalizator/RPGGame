package com.embercrest.game.ashley.componenets;

import com.badlogic.ashley.core.Component;
import com.embercrest.game.game_tools.Pathfinding;

import java.util.ArrayList;

public class PathToTileComponent implements Component {
    ArrayList<Pathfinding.Direction> pathToTile;

    public PathToTileComponent(ArrayList<Pathfinding.Direction> pathToTile) {
        this.pathToTile = pathToTile;
    }

    public ArrayList<Pathfinding.Direction> getPathToTile() {
        return pathToTile;
    }
}
