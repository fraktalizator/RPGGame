package com.embercrest.game.ashley.systems;

import static com.embercrest.game.ashley.componenets.PositionComponent.GRIDSIZE;
import static java.lang.Math.abs;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.math.Vector2;
import com.embercrest.game.ashley.actions.Action;
import com.embercrest.game.ashley.componenets.HoverHandlingComponent;
import com.embercrest.game.ashley.componenets.RenderComponent;
import com.embercrest.game.ashley.componenets.TouchHandlingComponent;
import com.embercrest.game.ashley.entities.InteractAbleEntity;
import com.embercrest.game.game.maps.Xentos;
import com.embercrest.game.game_tools.Pathfinding;
import com.embercrest.game.ashley.componenets.MoveComponent;
import com.embercrest.game.ashley.componenets.PathToTileComponent;
import com.embercrest.game.ashley.componenets.PositionComponent;
import com.embercrest.game.ashley.entities.MoveTile;

import java.util.ArrayList;
import java.util.HashMap;

public class MovementSystem extends EntitySystem {
    // select and path display system
    private InteractAbleEntity currentSelectedEntity;
    private final HashMap<Vector2, MoveTile> moveTilesAndPositions = new HashMap<>();
    private final ArrayList<MoveTile> currentSelectedMoveTiles = new ArrayList<>();
    private final Pathfinding pathfinding;

    //move system
    private boolean isEntityMoving;
    private ArrayList<Pathfinding.Direction> currentMovingEntityPath = new ArrayList<>();
    private int movedPixels = 0;
    private Xentos xentos;
    private final int MOVMENT_SPEED = 8;  // ONLY POWERS OF 2 !!!!!!!!

    public MovementSystem(Xentos xentos) {
        this.xentos = xentos;
        this.pathfinding = new Pathfinding(xentos);
    }

    public void SelectEntity(InteractAbleEntity entity) {
        if(isEntityMoving) return;
        if (!entity.equals(currentSelectedEntity) && currentSelectedEntity != null) {
            disposeOldMoveTiles();
        } else if (entity.equals(currentSelectedEntity)) {
            return;
        }
        currentSelectedEntity = entity;


        int range = entity.getComponent(MoveComponent.class).getRange();
        Vector2 position = entity.getComponent(PositionComponent.class).getPosition();
        //MoveComponent.MoveType moveType = entity.getComponent(MoveComponent.class).getType();
        //TODO moveTYPE
        HashMap<Vector2, Integer> positionAndMoveCosts = pathfinding.flood(position, range);

        ArrayList<Vector2> tilesInRangePositions = pathfinding.getTilesInRange(positionAndMoveCosts, range);

        for (Vector2 tilePosition : tilesInRangePositions) {
            if(tilePosition.x == position.x && tilePosition.y == position.y) continue;
            generateMoveTile(tilePosition, positionAndMoveCosts);
        }


        //test
//        for (Vector2 tilePosition : positionAndMoveCosts.keySet()) {
//            if(tilePosition.x == position.x && tilePosition.y == position.y) continue;
//            displayTilesAndCost(tilePosition, positionAndMoveCosts);
//        }
    }

    private void generateMoveTile(Vector2 tilePosition, HashMap<Vector2, Integer> positionAndMoveCosts){
        ArrayList<Pathfinding.Direction> path = pathfinding.getPath(currentSelectedEntity.getComponent(PositionComponent.class).getPosition(), tilePosition, positionAndMoveCosts);
        MoveTile moveTile = new MoveTile(tilePosition, path);
        Action displayPathToAction = (ent) -> {
            pathDisplay(path);
            return true;
        };

        moveTile.getComponent(HoverHandlingComponent.class).setOnHoverAction(displayPathToAction);
        moveTile.getComponent(TouchHandlingComponent.class).setLeftClickAction(moveAction);
        moveTilesAndPositions.put(tilePosition, moveTile);
        //moveTile.add(new DisplayTextComponenet(tilesInRangePossitions.get(tile).toString()));
        super.getEngine().addEntity(moveTile);
    }

    @Override
    public void update(float deltaTime) {
        if(!isEntityMoving)return;

        if(doneMovingOneTile(currentMovingEntityPath.get(0))){
            currentMovingEntityPath.remove(0);

            if(currentMovingEntityPath.size() == 0){
                Vector2 entPos = currentSelectedEntity.getComponent(PositionComponent.class).getPosition();
                xentos.entities.put(entPos, currentSelectedEntity);
                // so that entity foot wont be rendered on top of other ent head

                currentSelectedEntity.setZIndexByPosition();
                getEngine().getSystem(RenderSystem.class).forceSort();

                isEntityMoving = false;
                currentSelectedEntity = null;
            }
        }
    }

//    @Override
//    public void update(float deltaTime) {
//        if (!isEntityMoving) return;
//        if (doneMovingOneTile(currentMovingEntityPath.get(currentMovingDirectionIndex))) {
//            if (currentMovingDirectionIndex >= currentMovingEntityPath.size() - 1) {
//                Vector2 entPos = currentSelectedEntity.getComponent(PositionComponent.class).getPosition();
//                xentos.entities.put(entPos, currentSelectedEntity);
//                // so that entity foot wont be rendered on top of other ent head
//
//                currentSelectedEntity.setZIndexByPosition();
//                getEngine().getSystem(RenderSystem.class).forceSort();
//
//                isEntityMoving = false;
//                currentSelectedEntity = null;
//                currentMovingDirectionIndex = 0;
//            }
//            currentMovingDirectionIndex++;
//        }
//    }

    private boolean doneMovingOneTile(Pathfinding.Direction direction) {
        Vector2 currentSelectedEntityPosition = currentSelectedEntity.getComponent(PositionComponent.class).getPosition();
        currentSelectedEntity.getComponent(PositionComponent.class).setPosition(currentSelectedEntityPosition.add(new Vector2(direction.targetTilePos).scl(MOVMENT_SPEED)));
        currentSelectedEntity.getComponent(RenderComponent.class).setPosFrame(direction.posFrame);
        movedPixels = movedPixels+MOVMENT_SPEED;
        if(movedPixels >= 32){
            movedPixels = 0;
            return true;
        }
        return false;
    }


    public void pathDisplay(ArrayList<Pathfinding.Direction> directions){
        Vector2 pos = new Vector2(currentSelectedEntity.getComponent(PositionComponent.class).getPosition());
        for (int i = 0; i < currentSelectedMoveTiles.size(); i++) {
            currentSelectedMoveTiles.get(i).unSelect();
        }
        for (Pathfinding.Direction direction : directions){
            pos.add(new Vector2(direction.targetTilePos).scl(GRIDSIZE));
            moveTilesAndPositions.get(pos).select();
            currentSelectedMoveTiles.add(moveTilesAndPositions.get(pos));
        }

    }

    private void resetMoveTiles(){
        for(MoveTile moveTile :moveTilesAndPositions.values()){
            moveTile.unSelect();
        }
    }


    private void initMoveEntity(MoveTile targetMoveTile){
        isEntityMoving = true;
        currentMovingEntityPath = targetMoveTile.getComponent(PathToTileComponent.class).getPathToTile();
    }

    public void disposeOldMoveTiles() {
        if(currentSelectedEntity == null) return;
        getEngine().removeAllEntities(Family.all(PathToTileComponent.class).get());
    }

    public Entity getCurrentSelectedEntity() {
        return currentSelectedEntity;
    }

    Action moveAction = (ent) -> {
        xentos.entities.remove(currentSelectedEntity.getComponent(PositionComponent.class).getPosition());
        initMoveEntity((MoveTile) ent);
        disposeOldMoveTiles();
        return true;
    };

    //-------------------------------------------------------------------------------------------------------
    //--------------------------------------- FOR PATHFINDING TESTING ---------------------------------------
    //-------------------------------------------------------------------------------------------------------

//    private void displayTilesAndCost(Vector2 tilePosition, HashMap<Vector2, Integer> positionAndMoveCosts){
//        MoveTile moveTile = new MoveTile(tilePosition, null);
//
//        moveTile.getComponent(TouchHandlingComponent.class).setRightClickAction(new Action() {
//            @Override
//            public boolean act(Entity entity) {
//                entity.getComponent(PositionComponent.class).setPosition(tilePosition);
//                return true;
//            }
//        });
//        moveTilesAndPositions.put(tilePosition, moveTile);
//        moveTile.add(new DisplayTextComponenet(positionAndMoveCosts.get(tilePosition).toString()));
//        super.getEngine().addEntity(moveTile);
//    }

    //--------------------------------------- FOR PATHFINDING TESTING ---------------------------------------

//    public void SelectEntity(InteractableEntity entity) {
//        if(isEntityMoving) return;
//        if (!entity.equals(currentSelectedEntity) && currentSelectedEntity != null) {
//            disposeOldMoveTiles();
//        } else if (entity.equals(currentSelectedEntity)) {
//            return;
//        }
//        currentSelectedEntity = entity;
//
//
//        int range = entity.getComponent(MoveComponent.class).getRange();
//        Vector2 position = entity.getComponent(PositionComponent.class).getPosition();
//        HashMap<Vector2, Integer> positionAndMoveCosts = pathfinding.flood(position, range);
//
//        ArrayList<Vector2> tilesInRangePositions = pathfinding.getTilesInRange(positionAndMoveCosts, range);
//
//        for (Vector2 tilePosition : positionAndMoveCosts.keySet()) {
//            if(tilePosition.x == position.x && tilePosition.y == position.y) continue;
//            displayTilesAndCost(tilePosition, positionAndMoveCosts);
//        }
//    }
}
