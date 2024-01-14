package com.embercrest.game.game.equipment;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.embercrest.game.game.Items.ItemSlot;
import com.embercrest.game.game.abilities_Sellbook.SpellSlot;
import com.embercrest.game.game.entities.PartyCharacter;

import java.util.ArrayList;

public class EquipmentManager extends Table {
    private final ArrayList<ItemSlot> itemSlots = new ArrayList<>();

    public EquipmentManager(PartyCharacter partyCharacter) {
        setUpSlots();
        setDebug(false);
    }

    private void setUpSlots() {
        ItemSlot headSlot = new ItemSlot();
        headSlot.setSlotType(ItemSlot.SlotType.Head);
        itemSlots.add(headSlot);

        ItemSlot neckSlot = new ItemSlot();
        neckSlot.setSlotType(ItemSlot.SlotType.Neck);
        itemSlots.add(neckSlot);

        ItemSlot chestSlot = new ItemSlot();
        chestSlot.setSlotType(ItemSlot.SlotType.Chest);
        itemSlots.add(chestSlot);

        ItemSlot legsSlot = new ItemSlot();
        legsSlot.setSlotType(ItemSlot.SlotType.Legs);
        itemSlots.add(legsSlot);

        ItemSlot bootsSlot = new ItemSlot();
        bootsSlot.setSlotType(ItemSlot.SlotType.Boots);
        itemSlots.add(bootsSlot);

        ItemSlot mainHandSlot = new ItemSlot();
        mainHandSlot.setSlotType(ItemSlot.SlotType.MainHand);
        itemSlots.add(mainHandSlot);

        ItemSlot offHandSlot = new ItemSlot();
        offHandSlot.setSlotType(ItemSlot.SlotType.Offhand);
        itemSlots.add(offHandSlot);

        ItemSlot ringSlot1 = new ItemSlot();
        ringSlot1.setSlotType(ItemSlot.SlotType.Ring);
        itemSlots.add(ringSlot1);

        ItemSlot ringSlot2 = new ItemSlot();
        ringSlot2.setSlotType(ItemSlot.SlotType.Ring);
        itemSlots.add(ringSlot2);

        add(new Actor());
        add(headSlot).pad(10f);
        add(new Actor());

        row();

        add(new Actor());
        add(neckSlot).pad(10f);
        add(new Actor());

        row();

        add(ringSlot1).pad(10f);
        add(chestSlot).pad(10f);
        add(ringSlot2).pad(10f);

        row();

        add(new Actor());
        add(legsSlot).pad(10f);
        add(new Actor());

        row();

        add(mainHandSlot).pad(10f);
        add(bootsSlot).pad(10f);
        add(offHandSlot).pad(10f);
    }
}
