package com.embercrest.game.screens.save_create_screen.class_section;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import com.embercrest.game.drawing_tools.ScreenUtils;

import java.util.ArrayList;

public class ClassButtonsContainer extends ScrollPane {
    public static final int WIDTH = 314;
    public static final int HEIGHT = 882;

    public static final int TOP_SPACER_HEIGHT = 21;
    public static final int TOP_SPACER_WIDTH = 315;

    public static final int LEFT_SPACER_WIDTH = 8;

    public static final int MIDDLE_SPACER_WIDTH = 6;

    public static final int RIGHT_SPACER_WIDTH = 8;

    private final ArrayList<ClassButton> classButtons = new ArrayList<>();

    private final Table mainTable = new Table();

    public ClassButtonsContainer() {
        super(null);
        setTouchable(Touchable.enabled);
    }

    public void setUpButtons(ArrayList<ClassButton> inputButtons) {
        resize();
        mainTable.left().top();
        addTopSpacer();
        addLeftSpacer();

        for (ClassButton classButton: inputButtons) {
            classButton.resize();
            addClassButton(classButton);
        }

        if (classButtons.size()%2 == 1){
            mainTable.row();
            addTopSpacer();
        }

        setTableSize();

        setActor(mainTable);

        setScrollingDisabled(true, false);


        addListener(new InputListener(){
            @Override
            public boolean mouseMoved(InputEvent event, float x, float y) {
                if(!hasScrollFocus())sendScrollFocusRequest();
                return super.mouseMoved(event, x, y);
            }
        });

    }

    private void addClassButton(ClassButton classButton){
        mainTable.add(classButton).height(classButton.getHeight())
                .width(classButton.getWidth());

        classButtons.add(classButton);

        if (classButtons.size()%2 == 1) addMiddleSpacer();
        else {
            addLeftSpacer();
            mainTable.row();
            addTopSpacer();
            addLeftSpacer();
        }
    }

    private void addTopSpacer(){
        Vector2 scaledSize = ScreenUtils.getScaledSizeINT(TOP_SPACER_WIDTH, TOP_SPACER_HEIGHT);
        mainTable.add()
                .width(scaledSize.x)
                .height(scaledSize.y)
                .colspan(5);
        mainTable.row();
    }

    private void addLeftSpacer(){
        Vector2 scaledSize = ScreenUtils.getScaledSizeINT(LEFT_SPACER_WIDTH, LEFT_SPACER_WIDTH);
        mainTable.add()
                .height(scaledSize.x)
                .width(scaledSize.y);
    }

    private void addMiddleSpacer(){
        Vector2 scaledSize = ScreenUtils.getScaledSizeINT(MIDDLE_SPACER_WIDTH, MIDDLE_SPACER_WIDTH);
        mainTable.add()
                .height(scaledSize.x)
                .width(scaledSize.y);
    }


    private void setTableSize(){
        Vector2 resProp = ScreenUtils.getResolutionProportion();
        if(classButtons.isEmpty()){
            mainTable.setWidth(getWidth());
            mainTable.setHeight(getHeight());
            return;
        };

        for (ClassButton classBTN: classButtons) {
            classBTN.resize();
        }


        mainTable.setWidth(getWidth());

        int height = (int) (TOP_SPACER_HEIGHT*resProp.x) * 2;
        height += Math.ceil(classButtons.size()/2f)*classButtons.get(0).getHeight();
        mainTable.setHeight(height);

        mainTable.setBounds(mainTable.getX(), mainTable.getY(), mainTable.getWidth(), mainTable.getHeight());
    }

    public void resize(){
        Vector2 scaledSize = ScreenUtils.getScaledSizeINT(WIDTH, HEIGHT);

        setWidth(scaledSize.x);
        setHeight(scaledSize.y);

        setBounds(getX(), getY(), getWidth(), getHeight());

        setTableSize();
    }

    private void sendScrollFocusRequest(){
        FocusListener.FocusEvent focusEvent = new FocusListener.FocusEvent();
        focusEvent.setRelatedActor(this);
        focusEvent.setType(FocusListener.FocusEvent.Type.scroll);
        fire(focusEvent);
    }

}
