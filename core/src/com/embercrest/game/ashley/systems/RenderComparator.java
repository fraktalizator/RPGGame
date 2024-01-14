package com.embercrest.game.ashley.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.embercrest.game.ashley.componenets.RenderComponent;

import java.util.Comparator;

public class RenderComparator implements Comparator<Entity> {
    private final ComponentMapper<RenderComponent> cmTrans;

    public RenderComparator(){
        cmTrans = ComponentMapper.getFor(RenderComponent.class);
    }

    @Override
    public int compare(Entity entityA, Entity entityB) {
        int A_EntZ = cmTrans.get(entityA).getZindex();
        int B_EntZ = cmTrans.get(entityB).getZindex();
        int res = 0;
        if(A_EntZ > B_EntZ){
            res = 1;
        }else if(A_EntZ < B_EntZ){
            res = -1;
        }
        return res;
    }
}
