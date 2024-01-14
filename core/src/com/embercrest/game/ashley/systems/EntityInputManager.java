package com.embercrest.game.ashley.systems;



import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.embercrest.game.ashley.componenets.HoverHandlingComponent;
import com.embercrest.game.ashley.componenets.TouchHandlingComponent;
import com.embercrest.game.ashley.componenets.RenderComponent;
import com.embercrest.game.game.hud.HUDStage;
import com.embercrest.game.game.hud.inwentory.Inventory;
import com.embercrest.game.game.maps.Xentos;

public class EntityInputManager implements InputProcessor{

    private final Xentos xentos;
    private final OrthographicCamera gameScreenCamera;

    public EntityInputManager(OrthographicCamera gameScreenCamera, Xentos xentos) {
        this.xentos = xentos;
        this.gameScreenCamera = gameScreenCamera;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Vector3 clickPosition3 = gameScreenCamera.unproject(new Vector3(screenX, screenY, 0));

        Vector2 clickedTile = new Vector2(clickPosition3.x- clickPosition3.x%32, clickPosition3.y - clickPosition3.y%32);
        Entity clickedEnt = xentos.entities.get(clickedTile);

        if(clickedEnt == null) return false;
        if(!clickedEnt.getComponent(RenderComponent.class).isVisible()) return false;

        TouchHandlingComponent touch = clickedEnt.getComponent(TouchHandlingComponent.class);
        if(touch == null) return false;

        if(HUDStage.SelectedItem != null){
            HUDStage.SelectedItem.useOnEntity(clickedEnt);
            if(!Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) )HUDStage.SelectedItem.unSelect();
            return true;
        }
        if (button == 0) {
            touch.getLeftClickAction().act(clickedEnt);
            return true;
        } else if (button == 1) {
            touch.getRightClickAction().act(clickedEnt);
            return true;
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        Vector3 clickPosition3 = gameScreenCamera.unproject(new Vector3(screenX, screenY, 0));

        Vector2 clickedTile = new Vector2(clickPosition3.x - clickPosition3.x % 32, clickPosition3.y - clickPosition3.y % 32);
        Entity clickedEnt = xentos.entities.get(clickedTile);

        // Un hover every entity
        for(Entity entity :xentos.entities.values()) {
            if(entity.getComponent(HoverHandlingComponent.class) == null) continue;
            if (entity.getComponent(HoverHandlingComponent.class).isHovered() && !entity.equals(clickedEnt)) {
                entity.getComponent(HoverHandlingComponent.class).getOnHoverOffAction().act(clickedEnt);
                entity.getComponent(HoverHandlingComponent.class).setHovered(false);
            }
        }

        if (clickedEnt == null) return false;
        if (!clickedEnt.getComponent(RenderComponent.class).isVisible()) return false;

        HoverHandlingComponent hovComponent  = clickedEnt.getComponent(HoverHandlingComponent.class);
        if(hovComponent == null) return false;


        if (!clickedEnt.getComponent(HoverHandlingComponent.class).isHovered()) {
            clickedEnt.getComponent(HoverHandlingComponent.class).getOnHoverAction().act(clickedEnt);
            clickedEnt.getComponent(HoverHandlingComponent.class).setHovered(true);
            return true;
        }
        return false;
    }

//    @Override
//    public boolean mouseMoved(int screenX, int screenY) {
//        Vector3 clickPosition3 = gameScreenCamera.unproject(new Vector3(screenX, screenY, 0));
//        Vector2 clickPosition = new Vector2(clickPosition3.x, clickPosition3.y);;
//        for(Entity entity: hoverables){
//            if(!entity.getComponent(RenderComponent.class).isVisible()) continue;
//            PositionComponent positionComponent = entity.getComponent(PositionComponent.class);
//
//            if(!positionComponent.getBounds().contains(clickPosition) && entity.getComponent(HoverHandlingComponent.class).isHovered()) {
//                entity.getComponent(HoverHandlingComponent.class).getOnHoverOffAction().act(entity);
//                entity.getComponent(HoverHandlingComponent.class).setHovered(false);
//            }
//        }
//        for(Entity entity: hoverables){
//            if(!entity.getComponent(RenderComponent.class).isVisible()) continue;
//            PositionComponent positionComponent = entity.getComponent(PositionComponent.class);
//
//            //System.out.println(positionComponent.getPosition()+"  "+entity.getComponent(HoverHandlingComponent.class).isHovered()+""+positionComponent.getBounds().contains(clickPosition));
//            // so that getOnHoverAction acts once, and getOnHoverOffAction acts when not hovered.
//
//            if(positionComponent.getBounds().contains(clickPosition) && !entity.getComponent(HoverHandlingComponent.class).isHovered()){
//                entity.getComponent(HoverHandlingComponent.class).getOnHoverAction().act(entity);
//                entity.getComponent(HoverHandlingComponent.class).setHovered(true);
//                return true;
//            }
//
////            if(positionComponent.getBounds().contains(clickPosition) && !entity.getComponent(HoverHandlingComponent.class).isHovered()) {
////                entity.getComponent(HoverHandlingComponent.class).setHovered(true);
////                entity.getComponent(HoverHandlingComponent.class).getOnHoverAction().act(entity);
////                return true;
////            }else if(!positionComponent.getBounds().contains(clickPosition) && entity.getComponent(HoverHandlingComponent.class).isHovered()){
////                entity.getComponent(HoverHandlingComponent.class).getOnHoverOffAction().act(entity);
////                entity.getComponent(HoverHandlingComponent.class).setHovered(false);
////                return true;
////            }
//        }
//        return false;
//    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
