package com.embercrest.game.screens.save_create_screen.class_section;

import java.util.ArrayList;

public class MagicClassButtonsContainer extends ClassButtonsContainer{

    public MagicClassButtonsContainer() {
        super();
        ArrayList<ClassButton> classes = new ArrayList<>();
        for (MagicClasses magicClasses: MagicClasses.values()) {
            classes.add(magicClasses.button);
        }
        setUpButtons(classes);

    }

    public enum MagicClasses{
        Mage(1),
        Witch(2),
        Witch1(2),
        Witch2(2),
        Witch3(2),
        Witch4(2),
        Witch5(2),
        Witch6(2),
        Witch7(2),
        Slayer(3);

        final ClassButton button;
        int ID=0;

        public ClassButton getButton(){ return button;}
        public int getClassID(){return ID;}
        MagicClasses(int ID){
            button = new ClassButton();
            this.ID = ID;
        }
    }
}
