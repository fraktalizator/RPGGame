package com.embercrest.game.game_tools;

import static com.embercrest.game.ashley.componenets.PositionComponent.GRIDSIZE;

import static java.lang.Math.abs;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.embercrest.game.game.maps.Xentos;

import java.util.ArrayList;
import java.util.HashMap;

public class Pathfinding{
    private final Xentos xentos;
    public Pathfinding(Xentos xentos){
        this.xentos = xentos;

    }

    public boolean isPathValid(Vector2 startNodePos, Vector2 endNodePos, int range){
        HashMap<Vector2, Integer> positionAndMoveCosts = flood(startNodePos, range);
        return positionAndMoveCosts.containsKey(endNodePos);
    }


    public ArrayList<Direction> getPath(Vector2 startNodePos, Vector2 endNodePos){
        ArrayList<Direction> directionsReversed = new ArrayList<>();

        int range = (int)startNodePos.dst(endNodePos);
        HashMap<Vector2, Integer> positionAndMoveCosts = flood(startNodePos, range);
        
        Vector2 currentPosition = new Vector2(endNodePos);

        for(int i=0;i<=range;i++){
            if(positionAndMoveCosts.get(currentPosition) == 0){
                return directionsReversed;
            }
            Vector2 upTilePositon = new Vector2(currentPosition.x+0*GRIDSIZE, currentPosition.y+1*GRIDSIZE);
            Vector2 rightTilePositon = new Vector2(currentPosition.x+1*GRIDSIZE, currentPosition.y+0*GRIDSIZE);
            Vector2 downTilePositon = new Vector2(currentPosition.x+0*GRIDSIZE, currentPosition.y-1*GRIDSIZE);
            Vector2 leftTilePositon = new Vector2(currentPosition.x-1*GRIDSIZE, currentPosition.y+0*GRIDSIZE);
            int upPathCost = positionAndMoveCosts.get(upTilePositon);
            int rightPathCost = positionAndMoveCosts.get(rightTilePositon);
            int downPathCost = positionAndMoveCosts.get(downTilePositon);
            int leftPathCost = positionAndMoveCosts.get(leftTilePositon);

            ArrayList<Integer> aroundCostList = new ArrayList();
            aroundCostList.add(upPathCost);
            aroundCostList.add(rightPathCost);
            aroundCostList.add(downPathCost);
            aroundCostList.add(leftPathCost);
            int minValue = aroundCostList.get(0);
            for(Integer value : aroundCostList) {
                if (value < minValue) {
                    minValue = value;
                }
            }
            if(upPathCost == minValue){
                directionsReversed.add(Direction.Up);
                currentPosition = upTilePositon;
            }else if(rightPathCost == minValue){
                directionsReversed.add(Direction.Right);
                currentPosition = rightTilePositon;
            }else if(downPathCost == minValue){
                directionsReversed.add(Direction.Down);
                currentPosition = downTilePositon;
            }else if(leftPathCost == minValue){
                directionsReversed.add(Direction.Left);
                currentPosition = leftTilePositon;
            }
        }

        ArrayList<Direction> directionsCorrected = new ArrayList<>();
        for(int i = directionsReversed.size() - 1; i >= 0; i--){
            Direction direction = directionsCorrected.get(i);
            if(direction == Direction.Left)directionsCorrected.add(Direction.Right);
            else if(direction == Direction.Right)directionsCorrected.add(Direction.Left);
            else if(direction == Direction.Up)directionsCorrected.add(Direction.Down);
            else if(direction == Direction.Down)directionsCorrected.add(Direction.Up);

        }

        return directionsCorrected;
    }

    // assuming start node is center of positionAndMoveCosts array
    public boolean isPathValid(Vector2 endNodePos, HashMap<Vector2, Integer> positionAndMoveCosts){
        return positionAndMoveCosts.containsKey(endNodePos);
    }


    public ArrayList<Direction> getPath(Vector2 startNodePos, Vector2 endNodePos, HashMap<Vector2, Integer> positionAndMoveCosts){
        ArrayList<Direction> directionsReversed = new ArrayList<>();
        int range = (int)startNodePos.dst(endNodePos);
        Vector2 currentPosition = new Vector2(endNodePos);

        for(int i=0;i<=range;i++){
            if(positionAndMoveCosts.get(currentPosition) == 0){
                return reverseDirections(directionsReversed);
            }
            Vector2 upTilePositon = new Vector2(currentPosition.x+0*GRIDSIZE, currentPosition.y+1*GRIDSIZE);
            Vector2 rightTilePositon = new Vector2(currentPosition.x+1*GRIDSIZE, currentPosition.y+0*GRIDSIZE);
            Vector2 downTilePositon = new Vector2(currentPosition.x+0*GRIDSIZE, currentPosition.y-1*GRIDSIZE);
            Vector2 leftTilePositon = new Vector2(currentPosition.x-1*GRIDSIZE, currentPosition.y+0*GRIDSIZE);

            int upPathCost = positionAndMoveCosts.get(upTilePositon) == null ? 999 : positionAndMoveCosts.get(upTilePositon);
            int rightPathCost = positionAndMoveCosts.get(rightTilePositon) == null ? 999 : positionAndMoveCosts.get(rightTilePositon);
            int downPathCost = positionAndMoveCosts.get(downTilePositon) == null ? 999 : positionAndMoveCosts.get(downTilePositon);
            int leftPathCost = positionAndMoveCosts.get(leftTilePositon) == null ? 999 : positionAndMoveCosts.get(leftTilePositon);

            ArrayList<Integer> aroundCostList = new ArrayList();
            aroundCostList.add(upPathCost);
            aroundCostList.add(rightPathCost);
            aroundCostList.add(downPathCost);
            aroundCostList.add(leftPathCost);
            int minValue = aroundCostList.get(0);
            for(Integer value : aroundCostList) {
                if (value < minValue) {
                    minValue = value;
                }
            }
            if(upPathCost == minValue){
                directionsReversed.add(Direction.Up);
                currentPosition = upTilePositon;
            }else if(rightPathCost == minValue){
                directionsReversed.add(Direction.Right);
                currentPosition = rightTilePositon;
            }else if(downPathCost == minValue){
                directionsReversed.add(Direction.Down);
                currentPosition = downTilePositon;
            }else if(leftPathCost == minValue){
                directionsReversed.add(Direction.Left);
                currentPosition = leftTilePositon;
            }
        }

        return reverseDirections(directionsReversed);
    }

    private ArrayList<Direction> reverseDirections(ArrayList<Direction> directions) {
        ArrayList<Direction> directionsReversed = new ArrayList<Direction>();
        for(int i = directions.size() - 1; i >= 0; i--){
            Direction direction = directions.get(i);
            if(direction == Direction.Left)directionsReversed.add(Direction.Right);
            else if(direction == Direction.Right)directionsReversed.add(Direction.Left);
            else if(direction == Direction.Up)directionsReversed.add(Direction.Down);
            else if(direction == Direction.Down)directionsReversed.add(Direction.Up);

        }
        return directionsReversed;
    }


    public ArrayList<Vector2> getTilesInRange(Vector2 position, int range){
        ArrayList<Vector2> tilePositions = new ArrayList<>();
        HashMap<Vector2, Integer> positionAndMoveCosts = flood(position, range);

        for(Vector2 pos:positionAndMoveCosts.keySet()) {
            if (positionAndMoveCosts.get(pos) <= range) tilePositions.add(pos);
        }

        return tilePositions;
    }

    public ArrayList<Vector2> getTilesInRange(HashMap<Vector2, Integer> positionAndMoveCosts, int range){
        ArrayList<Vector2> tilePositions = new ArrayList<>();

        for(Vector2 pos:positionAndMoveCosts.keySet()) {
            if (positionAndMoveCosts.get(pos) <= range) tilePositions.add(pos);
        }

        return tilePositions;
    }

    public HashMap<Vector2, Integer> flood(Vector2 position, int range){
        HashMap<Vector2, Integer> positionAndMoveCosts = hashMapInit(range, position);
        ArrayList<Vector2> justComputedTiles = new ArrayList<>();
        ArrayList<Vector2> justComputedTilesTMP;
        justComputedTiles.add(position);

        for (int n = 0; n < range;n++) {
            justComputedTilesTMP = new ArrayList<>();
            for(Vector2 tile : justComputedTiles) {
                int currentCost = positionAndMoveCosts.get(tile);

                Vector2 upTilePositon = new Vector2(tile.x+0*GRIDSIZE, tile.y+1*GRIDSIZE);
                int upTileCost = getTileCost(upTilePositon);

                Vector2 rightTilePositon = new Vector2(tile.x+1*GRIDSIZE, tile.y+0*GRIDSIZE);
                int rightTileCost = getTileCost(rightTilePositon);

                Vector2 downTilePositon = new Vector2(tile.x+0*GRIDSIZE, tile.y-1*GRIDSIZE);
                int downTileCost = getTileCost(downTilePositon);

                Vector2 leftTilePositon = new Vector2(tile.x-1*GRIDSIZE, tile.y+0*GRIDSIZE);
                int leftTileCost = getTileCost(leftTilePositon);


                if(upTileCost+currentCost< positionAndMoveCosts.get(upTilePositon)) {
                    positionAndMoveCosts.put(upTilePositon, upTileCost + currentCost);
                    justComputedTilesTMP.add(upTilePositon);
                }

                if(rightTileCost+currentCost< positionAndMoveCosts.get(rightTilePositon)) {
                    positionAndMoveCosts.put(rightTilePositon, rightTileCost + currentCost);
                    justComputedTilesTMP.add(rightTilePositon);
                }


                if(downTileCost+currentCost< positionAndMoveCosts.get(downTilePositon)) {
                    positionAndMoveCosts.put(downTilePositon, downTileCost + currentCost);
                    justComputedTilesTMP.add(downTilePositon);
                }

                if(leftTileCost+currentCost< positionAndMoveCosts.get(leftTilePositon)) {
                    positionAndMoveCosts.put(leftTilePositon, leftTileCost + currentCost);
                    justComputedTilesTMP.add(leftTilePositon);
                }
            }
            justComputedTiles.clear();
            justComputedTiles.addAll(justComputedTilesTMP);
            justComputedTilesTMP.clear();
        }
        return positionAndMoveCosts;
    }

    public HashMap<Vector2, Integer> floodIgnoreTerrainCostOLD(Vector2 position, int range){
        HashMap<Vector2, Integer> positionAndMoveCosts = hashMapInit(range, position);
        ArrayList<Vector2> justComputedTiles = new ArrayList<>();
        ArrayList<Vector2> justComputedTilesTMP;
        justComputedTiles.add(position);

        for (int n = 0; n < range;n++) {
            justComputedTilesTMP = new ArrayList<>();
            for(Vector2 tile : justComputedTiles) {
                int currentCost = positionAndMoveCosts.get(tile);

                Vector2 upTilePositon = new Vector2(tile.x+0*GRIDSIZE, tile.y+1*GRIDSIZE);
                int upTileCost = getTileCost(upTilePositon) < 90 ? 1: getTileCost(upTilePositon);

                Vector2 rightTilePositon = new Vector2(tile.x+1*GRIDSIZE, tile.y+0*GRIDSIZE);
                int rightTileCost = getTileCost(rightTilePositon) < 90 ? 1: getTileCost(rightTilePositon);

                Vector2 downTilePositon = new Vector2(tile.x+0*GRIDSIZE, tile.y-1*GRIDSIZE);
                int downTileCost = getTileCost(downTilePositon) < 90 ? 1: getTileCost(downTilePositon);

                Vector2 leftTilePositon = new Vector2(tile.x-1*GRIDSIZE, tile.y+0*GRIDSIZE);
                int leftTileCost = getTileCost(leftTilePositon) < 90 ? 1: getTileCost(leftTilePositon);


                if(upTileCost+currentCost< positionAndMoveCosts.get(upTilePositon)) {
                    positionAndMoveCosts.put(upTilePositon, upTileCost + currentCost);
                    justComputedTilesTMP.add(upTilePositon);
                }

                if(rightTileCost+currentCost< positionAndMoveCosts.get(rightTilePositon)) {
                    positionAndMoveCosts.put(rightTilePositon, rightTileCost + currentCost);
                    justComputedTilesTMP.add(rightTilePositon);
                }


                if(downTileCost+currentCost< positionAndMoveCosts.get(downTilePositon)) {
                    positionAndMoveCosts.put(downTilePositon, downTileCost + currentCost);
                    justComputedTilesTMP.add(downTilePositon);
                }

                if(leftTileCost+currentCost< positionAndMoveCosts.get(leftTilePositon)) {
                    positionAndMoveCosts.put(leftTilePositon, leftTileCost + currentCost);
                    justComputedTilesTMP.add(leftTilePositon);
                }
            }
            justComputedTiles.clear();
            justComputedTiles.addAll(justComputedTilesTMP);
            justComputedTilesTMP.clear();
        }
        positionAndMoveCosts.put(position, 0);
        return positionAndMoveCosts;
    }


    public ArrayList<Vector2> getTilesAround(int range, Vector2 position){
        ArrayList<Vector2> tilePositions = new ArrayList<>();
        Vector2 iterateDirection = new Vector2(1*GRIDSIZE, -1*GRIDSIZE);
        Vector2 currentCalculatedTilePosition = new Vector2(position.x, range*GRIDSIZE+position.y);
        for(int i=0;i<=4*range;i++){
            tilePositions.add(new Vector2(currentCalculatedTilePosition));
            if(currentCalculatedTilePosition.equals(new Vector2(range*GRIDSIZE+position.x, position.y))) iterateDirection = new Vector2(-1*GRIDSIZE, -1*GRIDSIZE);
            if(currentCalculatedTilePosition.equals(new Vector2(position.x, -range*GRIDSIZE+position.y))) iterateDirection = new Vector2(-1*GRIDSIZE, 1*GRIDSIZE);
            if(currentCalculatedTilePosition.equals(new Vector2(-range*GRIDSIZE+position.x, position.y))) iterateDirection = new Vector2(1*GRIDSIZE, 1*GRIDSIZE);
            currentCalculatedTilePosition.add(iterateDirection);

        }
        return tilePositions;
    }

    private HashMap<Vector2, Integer> getTilesCircle(Vector2 position, int range){
        HashMap<Vector2, Integer> positionAndMoveCosts = new HashMap<>();

        for(int xCord=range; xCord> -range-1; xCord--) {
            for(int yCord=range; yCord> -range-1; yCord--) {
                if(abs(xCord)+abs(yCord)<= range){
                    Vector2 tilepos = new Vector2(position.x+xCord* GRIDSIZE, position.y+yCord* GRIDSIZE);
                    positionAndMoveCosts.put(tilepos, 0);
                }
            }
        }

        return positionAndMoveCosts;
    }

    private int getTileCost(Vector2 position){
        TiledMapTileLayer.Cell cell = ( (TiledMapTileLayer) xentos.getTiledMap().getLayers().get(0)).getCell((int)position.x/GRIDSIZE, (int)position.y/GRIDSIZE);
        if(cell == null) return 9999;
        if(xentos.entities.get(position) != null) return xentos.entities.get(position).getMoveCost();
        return Integer.parseInt(cell.getTile().getProperties().get("moveCost").toString());
    }

    private HashMap<Vector2, Integer> hashMapInit(int range, Vector2 position){
        HashMap<Vector2, Integer> positionAndMoveCosts = new HashMap<>();
        for(int xCord=range; xCord> -range-1; xCord--) {
            for(int yCord=range; yCord> -range-1; yCord--) {
                if(abs(xCord)+abs(yCord)<= range){
                    Vector2 tilepos = new Vector2(position.x+xCord* GRIDSIZE, position.y+yCord* GRIDSIZE);
                    positionAndMoveCosts.put(tilepos, range+1);
                }
            }
        }
        positionAndMoveCosts.put(position, 0);
        return positionAndMoveCosts;
    }

    public enum Direction {

        Up("u", 0),
        Right("r", 1),
        Down("d", 2),
        Left("l", 3);

        Direction(String direction, int posFrame) {
            this.posFrame = posFrame;
            switch (direction) {
                case "u":
                    targetTilePos = new Vector2(0, 1);
                    break;
                case "l":
                    targetTilePos = new Vector2(-1, 0);
                    break;
                case "d":
                    targetTilePos = new Vector2(0, -1);
                    break;
                case "r":
                    targetTilePos = new Vector2(1, 0);
                    break;

            }
        }
        public int posFrame;
        public Vector2 targetTilePos= new Vector2(0, 0);
    }

//    private ArrayList<Directions> getDirectionsToCalculate(int n, Vector2 position, Vector2 tile){
//        ArrayList<Directions> directionsToCalculate = new ArrayList<>(4);
//        if( n == 0){
//            directionsToCalculate.add(Directions.North);
//            directionsToCalculate.add(Directions.East);
//            directionsToCalculate.add(Directions.South);
//            directionsToCalculate.add(Directions.West);
//        }
//        else if(tile.equals(new Vector2(n*GRIDSIZE+position.x, 0*GRIDSIZE+position.y))){
//            directionsToCalculate.add(Directions.North);
//            directionsToCalculate.add(Directions.South);
//            directionsToCalculate.add(Directions.East);
//        }
//        else if(tile.equals(new Vector2(0*GRIDSIZE+position.x, -n*GRIDSIZE+position.y))){
//            directionsToCalculate.add(Directions.East);
//            directionsToCalculate.add(Directions.South);
//            directionsToCalculate.add(Directions.West);
//        }
//        else if(tile.equals(new Vector2(-n*GRIDSIZE+position.x, 0*GRIDSIZE+position.y))){
//            directionsToCalculate.add(Directions.North);
//            directionsToCalculate.add(Directions.South);
//            directionsToCalculate.add(Directions.West);
//        }
//        else if(tile.equals(new Vector2(0*GRIDSIZE+position.x, n*GRIDSIZE+position.y))){
//            directionsToCalculate.add(Directions.North);
//            directionsToCalculate.add(Directions.East);
//            directionsToCalculate.add(Directions.West);
//        }
//        else if(tile.x > 0*GRIDSIZE+position.x && tile.y > 0*GRIDSIZE+position.y){
//            directionsToCalculate.add(Directions.North);
//            directionsToCalculate.add(Directions.East);
//            directionsToCalculate.add(Directions.South);
//        }
//        else if(tile.x > 0*GRIDSIZE+position.x && tile.y < 0*GRIDSIZE+position.y){
//            directionsToCalculate.add(Directions.East);
//            directionsToCalculate.add(Directions.South);
//            directionsToCalculate.add(Directions.West);
//        }
//        else if(tile.x < 0*GRIDSIZE+position.x && tile.y < 0*GRIDSIZE+position.y){
//            directionsToCalculate.add(Directions.South);
//            directionsToCalculate.add(Directions.West);
//            directionsToCalculate.add(Directions.East);
//        }
//        else if(tile.x < 0*GRIDSIZE+position.x && tile.y > 0*GRIDSIZE+position.y){
//            directionsToCalculate.add(Directions.North);
//            directionsToCalculate.add(Directions.West);
//            directionsToCalculate.add(Directions.East);
//        }
//        return directionsToCalculate;
//    }

//    public HashMap<Vector2, Integer> floodIsrakaaasdasdasdadgnoreTerrainCost(Vector2 position, int range){
//        HashMap<Vector2, Integer> positionAndMoveCosts = hashMapInit(range, position);
//
//        for (int n = 0; n < range;n++) {
//            ArrayList<Vector2> tilesInRange = getTilesAround(n, position);
//            for(Vector2 tile : tilesInRange) {
//                int currentCost = positionAndMoveCosts.get(tile);
//
//                Vector2 upTilePositon = new Vector2(tile.x+0*GRIDSIZE, tile.y+1*GRIDSIZE);
//                int upTileCost = getTileCost(upTilePositon) < 90 ? 1: getTileCost(upTilePositon);
//
//                Vector2 rightTilePositon = new Vector2(tile.x+1*GRIDSIZE, tile.y+0*GRIDSIZE);
//                int rightTileCost = getTileCost(rightTilePositon) < 90 ? 1: getTileCost(rightTilePositon);
//
//                Vector2 downTilePositon = new Vector2(tile.x+0*GRIDSIZE, tile.y-1*GRIDSIZE);
//                int downTileCost = getTileCost(downTilePositon) < 90 ? 1: getTileCost(downTilePositon);
//
//                Vector2 leftTilePositon = new Vector2(tile.x-1*GRIDSIZE, tile.y+0*GRIDSIZE);
//                int leftTileCost = getTileCost(leftTilePositon) < 90 ? 1: getTileCost(leftTilePositon);
//
//
//                if(upTileCost+currentCost< positionAndMoveCosts.get(upTilePositon) || positionAndMoveCosts.get(upTilePositon) == 0)
//                    positionAndMoveCosts.put(upTilePositon, upTileCost+currentCost);
//
//                if(rightTileCost+currentCost< positionAndMoveCosts.get(rightTilePositon) || positionAndMoveCosts.get(rightTilePositon) == 0)
//                    positionAndMoveCosts.put(rightTilePositon, rightTileCost+currentCost);
//
//
//                if(downTileCost+currentCost< positionAndMoveCosts.get(downTilePositon) || positionAndMoveCosts.get(downTilePositon) == 0)
//                    positionAndMoveCosts.put(downTilePositon, downTileCost+currentCost);
//
//                if(leftTileCost+currentCost< positionAndMoveCosts.get(leftTilePositon) || positionAndMoveCosts.get(leftTilePositon) == 0)
//                    positionAndMoveCosts.put(leftTilePositon, leftTileCost+currentCost);
//            }
//        }
//        positionAndMoveCosts.put(position, 0);
//        return positionAndMoveCosts;
//    }

    //    public enum Directions{
//
//        North("N"),
//        East("E"),
//        South("S"),
//        West("W");
//
//        Directions(String direction) {
//            switch (direction) {
//                case "N":
//                    targetTilePos = new Vector2(0, 1*GRIDSIZE);
//                    break;
//                case "E":
//                    targetTilePos = new Vector2(-1*GRIDSIZE, 0);
//                    break;
//                case "S":
//                    targetTilePos = new Vector2(0, -1*GRIDSIZE);
//                    break;
//                case "W":
//                    targetTilePos = new Vector2(1*GRIDSIZE, 0);
//                    break;
//
//            }
//        }
//        Vector2 targetTilePos= new Vector2(0, 0);
//    }
}
