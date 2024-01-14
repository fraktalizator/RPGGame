package com.embercrest.game.ashley.componenets;

import com.badlogic.ashley.core.Component;
import com.embercrest.game.game.equipment.EquipmentManager;

public class EquipmentComponent implements Component {
    private EquipmentManager equipmentManager;

    public EquipmentComponent(EquipmentManager equipmentManager) {
        this.equipmentManager = equipmentManager;
    }

    public EquipmentManager getEquipmentManager() {
        return equipmentManager;
    }

    public void setEquipmentManager(EquipmentManager equipmentManager) {
        this.equipmentManager = equipmentManager;
    }
}
