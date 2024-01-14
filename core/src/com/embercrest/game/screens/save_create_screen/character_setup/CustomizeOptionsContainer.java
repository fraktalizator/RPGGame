package com.embercrest.game.screens.save_create_screen.character_setup;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import com.embercrest.game.Assets;
import com.embercrest.game.drawing_tools.ScreenUtils;
import com.embercrest.game.screens.save_create_screen.character_setup.customize_options.BodyCustomizeOption;
import com.embercrest.game.screens.save_create_screen.character_setup.customize_options.ClothCustomizeOption;
import com.embercrest.game.screens.save_create_screen.character_setup.customize_options.HairColorCustomizeOption;
import com.embercrest.game.screens.save_create_screen.character_setup.customize_options.HairCustomizeOption;
import com.embercrest.game.screens.save_create_screen.character_setup.customize_options.SexCustomizeOption;

import java.util.ArrayList;

public class CustomizeOptionsContainer extends ScrollPane {

    public final static int WIDTH = 474;
    //public final static int HEIGHT = 452;
    public final static int HEIGHT = 438;

    private final ArrayList<CustomizeOption> options = new ArrayList<>();

    private final Table mainTable = new Table();

    private SexCustomizeOption sexCustomizeOption;
    private HairCustomizeOption hairCustomizeOption;
    private HairColorCustomizeOption hairColorCustomizeOption;
    private BodyCustomizeOption bodyCustomizeOption;
    private ClothCustomizeOption clothCustomizeOption;

    public CustomizeOptionsContainer() {
        super(null);
        setTouchable(Touchable.enabled);
        mainTable.setDebug(false);
        setFadeScrollBars(false);
        initOptions();
        setTableSize();
        setActor(mainTable);
        setDebug(false);
        resize();
        addListener(new InputListener(){
            @Override
            public boolean mouseMoved(InputEvent event, float x, float y) {
                if(!hasScrollFocus())sendScrollFocusRequest();
                return super.mouseMoved(event, x, y);
            }
        });
    }

    private void initOptions() {
        addSpacingToMainTable();

        sexCustomizeOption = new SexCustomizeOption();
        options.add(sexCustomizeOption);
        mainTable.add(sexCustomizeOption).height(sexCustomizeOption.getHeight()).width(sexCustomizeOption.getWidth());
        addSpacingToMainTable();

        hairCustomizeOption = new HairCustomizeOption();
        options.add(hairCustomizeOption);
        mainTable.add(hairCustomizeOption).height(hairCustomizeOption.getHeight()).width(hairCustomizeOption.getWidth());
        addSpacingToMainTable();

        hairColorCustomizeOption = new HairColorCustomizeOption();
        options.add(hairColorCustomizeOption);
        mainTable.add(hairColorCustomizeOption).height(hairColorCustomizeOption.getHeight()).width(hairColorCustomizeOption.getWidth());
        addSpacingToMainTable();

        bodyCustomizeOption = new BodyCustomizeOption();
        options.add(bodyCustomizeOption);
        mainTable.add(bodyCustomizeOption).height(bodyCustomizeOption.getHeight()).width(bodyCustomizeOption.getWidth());
        addSpacingToMainTable();

        clothCustomizeOption = new ClothCustomizeOption();
        options.add(clothCustomizeOption);
        mainTable.add(clothCustomizeOption).height(clothCustomizeOption.getHeight()).width(clothCustomizeOption.getWidth());
        addSpacingToMainTable();
    }

    private void addSpacingToMainTable(){
        int spacingHeight = 30;
        Vector2 scaledSize = ScreenUtils.getScaledSize(spacingHeight, spacingHeight);
        mainTable.row();
        //mainTable.add().width(scaledSize.x).height(scaledSize.y);
        mainTable.add().width(spacingHeight).height(spacingHeight);
        mainTable.row();
    }

    public void setTableSize(){
        int spacingHeight = 30;
        Vector2 scaledSpacingSize = ScreenUtils.getScaledSizeINT(spacingHeight, spacingHeight);

        int tableHeight = (int) (options.size()*(options.get(0).getHeight()+scaledSpacingSize.y));
        Vector2 scaledSize = ScreenUtils.getScaledSizeINT(WIDTH, tableHeight);
        setWidth(scaledSize.x);
        setHeight(scaledSize.y);
    }

    public Integer[] getInformation(){
        int sStyle = sexCustomizeOption.getCurrentStyle();
        int hStyle = hairCustomizeOption.getCurrentStyle();
        int hColorStyle = hairColorCustomizeOption.getCurrentStyle();
        int bStyle = bodyCustomizeOption.getCurrentStyle();
        int cStyle = clothCustomizeOption.getCurrentStyle();
        return new Integer[] {sStyle, hStyle, hColorStyle, bStyle, cStyle};
    }

    public void resize(){
        Vector2 scaledSize = ScreenUtils.getScaledSizeINT(WIDTH, HEIGHT);
        setWidth(scaledSize.x);
        setHeight(scaledSize.y);
        setBounds(getX(), getY(), getWidth(), getHeight());
    }

    private void sendScrollFocusRequest(){
        FocusListener.FocusEvent focusEvent = new FocusListener.FocusEvent();
        focusEvent.setRelatedActor(this);
        focusEvent.setType(FocusListener.FocusEvent.Type.scroll);
        fire(focusEvent);
    }


    enum CustomizeOptions{
        Sex("Male", 2),
        Hairs("Hair style:", Assets.FemaleHairTextureAssets.values().length),
        HairColor("Hair color: ", 2),
        Body("Body style:", Assets.FemaleBodyTextureAssets.values().length),
        Cloth("Cloth: ", 2),
        EyeColor("Eye color: ", 2);

        final String optionName;
        final int max;
        CustomizeOptions(String optionName, int max){
            this.optionName = optionName;
            this.max = max;
        }
    }

}
