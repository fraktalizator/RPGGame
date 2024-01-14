package com.embercrest.game.screens.save_create_screen.class_section;

import java.util.ArrayList;

public class TechClassButtonsContainer extends ClassButtonsContainer{

    public TechClassButtonsContainer() {
        super();
        ArrayList<ClassButton> classes = new ArrayList<>();
        for (TechClasses magicClasses: TechClasses.values()) {
            classes.add(magicClasses.button);
        }
        setUpButtons(classes);
    }


    public enum TechClasses{
        Mech(11),
        Cyborg(12),
        Cyborg1(12),
        Cyborg2(12),
        Cyborg3(12),
        Cyborg4(12),
        Cyborg5(12),
        Cyborg6(12),
        Cyborg7(12),
        Cyborg8(12),
        Cyborg9(12),
        Cyborg10(12),
        Cyborg11(12),
        Cyborg111(12),

        Soldier(13);

        final ClassButton button;
        int ID;

        public ClassButton getButton(){ return button;}
        public int getClassID(){return ID;}
        TechClasses(int ID){
            button = new ClassButton();
            this.ID = ID;
        }
    }
}
