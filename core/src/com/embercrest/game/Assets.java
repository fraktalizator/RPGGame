package com.embercrest.game;


import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Assets {
    public final AssetManager assetManager = new AssetManager();
    private final AssetDescriptor<Skin> skinAssetDescriptor;

    private static Assets instance;

    public static Assets get(){
        if( instance == null) throw new IllegalStateException("You need to ini assets first");
        return instance;
    }

    public static void init(String skinPath){
        if(instance != null) throw new IllegalStateException("Assets are already initialized");
        instance = new Assets(skinPath);
    }

    private Assets(String skinPath) {
        assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));

        skinAssetDescriptor = new AssetDescriptor<>(skinPath, Skin.class);
        assetManager.load(skinAssetDescriptor);

        for (PreGameTextureAssets preGameTextureAssets: PreGameTextureAssets.values()){
            assetManager.load(preGameTextureAssets.assetDescriptor);
        }

        for (FemaleBodyTextureAssets femaleBodyTextureAssets: FemaleBodyTextureAssets.values()){
            assetManager.load(femaleBodyTextureAssets.assetDescriptor);
        }

        for (FemaleHairTextureAssets femaleHairTextureAssets: FemaleHairTextureAssets.values()){
            assetManager.load(femaleHairTextureAssets.assetDescriptor);
        }

        for (FemaleClothTextureAssets femaleClothTextureAssets: FemaleClothTextureAssets.values()){
            assetManager.load(femaleClothTextureAssets.assetDescriptor);
        }
    }

    public boolean update(){
        return assetManager.update();
    }

    public void loadGameAssets(){
        for (TextureAssets textureAssets: TextureAssets.values()){
            assetManager.load(textureAssets.assetDescriptor);
        }

        for (MapAssets mapAssets: MapAssets.values()){
            assetManager.load(mapAssets.assetDescriptor);
        }

    }
    public Skin getSkin(){
        return assetManager.get(skinAssetDescriptor);
    }


    public Texture generateCharacterTexture(FemaleBodyTextureAssets femaleBodyTextureAssets,FemaleHairTextureAssets femaleHairTextureAssets, FemaleClothTextureAssets femaleClothTextureAssets){

        Texture bodyTexture = femaleBodyTextureAssets.getTexture();
        Texture hairTexture = femaleHairTextureAssets.getTexture();
        Texture clothTexture = femaleClothTextureAssets.getTexture();
        //Texture eyeColorTexture = bodyPartTexturesAssets[3].getTexture();
        //Texture legsTextur e= bodyPartTexturesAssets[4].getTexture();
        //Texture bootsTextur e= bodyPartTexturesAssets[5].getTexture();

        bodyTexture.getTextureData().prepare();
        hairTexture.getTextureData().prepare();
        clothTexture.getTextureData().prepare();
//        eyeColorTexture.getTextureData().prepare();
//        legsTexture.getTextureData().prepare();
//        bootsTexture.getTextureData().prepare();

        Pixmap hairPixmap = hairTexture.getTextureData().consumePixmap();
        Pixmap clothPixmap = clothTexture.getTextureData().consumePixmap();
//        Pixmap eyePixmap = eyeColorTexture.getTextureData().consumePixmap();
//        Pixmap legsPixmap = legsTexture.getTextureData().consumePixmap();
//        Pixmap bootsPixmap = bootsTexture.getTextureData().consumePixmap();

        Pixmap CHAR_PIXMAP = bodyTexture.getTextureData().consumePixmap();
//        CHAR_PIXMAP.drawPixmap(eyePixmap, 0, 0);
        CHAR_PIXMAP.drawPixmap(hairPixmap, 0, 0);
        CHAR_PIXMAP.drawPixmap(clothPixmap, 0, 0);
//        CHAR_PIXMAP.drawPixmap(legsPixmap, 0, 0);
//        CHAR_PIXMAP.drawPixmap(bootsPixmap, 0, 0);

        Texture CHAR_TEXTURE = new Texture(CHAR_PIXMAP);
        hairPixmap.dispose();
        clothPixmap.dispose();
//        eyePixmap.dispose();
//        legsPixmap.dispose();
//        bootsPixmap.dispose();
        CHAR_PIXMAP.dispose();

        return CHAR_TEXTURE;
    }

    public enum PreGameTextureAssets{
        MenuBG("Ui/MenuScreen/BG.jpg"),

        SettingsWindowBG("Ui/SettingsWindow/SettingBackround.png"),
        SettingsButtonUp("Ui/SettingsWindow/ButtonSetting.png"),
        SettingsButtonDown("Ui/SettingsWindow/ButtonSettingsPress.png"),
        SettingsButtonChecked("Ui/SettingsWindow/ButtonSettingsPress.png"),

        SaveSelectWindowBG("Ui/SaveScreen/saveButtons/selectlevelbackground.png"),
        SaveButton("Ui/SaveScreen/saveButtons/save.png"),
        SaveButtonSelected("Ui/SaveScreen/saveButtons/saveclick.png"),

        SaveCreateWindowBG("Ui/CharacterCreateScreen/CreatorBackground.png"),
        CharacterPreviewBG("Ui/CharacterCreateScreen/CharacterPreviewBackground.png"),
        CharacterPreviewBGBorder("Ui/CharacterCreateScreen/CharacterPreviewBackgroundBorder.png"),
        ClassButton("Ui/CharacterCreateScreen/SelectClass.png"),
        ClassButtonSelected("Ui/CharacterCreateScreen/SelectClassClick146x207.png"),

        CustomizeOptionBackButton("Ui/CharacterCreateScreen/ArrowButton.png"),
        CustomizeOptionBackButtonSelected("Ui/CharacterCreateScreen/ArrowButtonClick.png"),

        CustomizeOptionNextButton("Ui/CharacterCreateScreen/ArrowButtonL.png"),

        CustomizeOptionNextButtonSelected("Ui/CharacterCreateScreen/ArrowButtonClickL.png"),

        CustomizeOptionLabelBackground("Ui/CharacterCreateScreen/StyleText.png");

        private final String path;
        private final AssetDescriptor<Texture> assetDescriptor;

        public Texture getTexture(){
            return Assets.get().assetManager.get(assetDescriptor);
        }

        PreGameTextureAssets(String path){
            this.path = path;
            assetDescriptor = new AssetDescriptor<Texture>(path, Texture.class);
        }
    }

    public enum TextureAssets{
        PlayerMovementAnimation("Actors/Characters/player.png"),
        Stairs("Actors/Interactable/stairs.png"),
        InventorySlot("Ui/GameScreen/Hud/Inveontry/eqgrid.png"),
        BadApple("Items/Consumable/bad_apple.png"),
        MoveTile("Textures/MoveTiles/moveTile.png"),
        MoveTileSelected("Textures/MoveTiles/moveTileSelected.png"),
        HeavyArm("Spells/Warrior/Talents/heavyArm.png"),
        HerbPouch("Spells/Warrior/Talents/heavyArm.png"),
        InventoryBG("Ui/GameScreen/Hud/Inveontry/frameeqFilled.png");

        private final String path;
        private final AssetDescriptor<Texture> assetDescriptor;

        public Texture getTexture(){
            return Assets.get().assetManager.get(assetDescriptor);
        }

        TextureAssets(String path){
            this.path = path;
            assetDescriptor = new AssetDescriptor<Texture>(path, Texture.class);
        }
    }

    public enum MapAssets{
        TutorialIsland("Textures/tutorialZone/tut2.tmx");

        private final String path;
        private final AssetDescriptor<TiledMap> assetDescriptor;

        public TiledMap getMap(){
            return Assets.get().assetManager.get(assetDescriptor);
        }

        MapAssets(String path){
            this.path = path;
            assetDescriptor = new AssetDescriptor<TiledMap>(path, TiledMap.class);
        }
    }



    public enum FemaleBodyTextureAssets {
        FemaleBody1("Actors/Characters/body/basicfemale.png"),
        FemaleBody2("Actors/Characters/body/basicfemaleb.png"),
        FemaleBody3("Actors/Characters/body/basicfemaled.png"),
        FemaleBody4("Actors/Characters/body/basicfemalev.png");

        private final String path;
        private final AssetDescriptor<Texture> assetDescriptor;

        public Texture getTexture(){
            return Assets.get().assetManager.get(assetDescriptor);
        }

        FemaleBodyTextureAssets(String path){
            this.path = path;
            assetDescriptor = new AssetDescriptor<>(path, Texture.class);
        }

    }

    public enum FemaleHairTextureAssets {
        FemaleHair1Color1("Actors/Characters/hairs/brownhairA2.png"),
        FemaleHair1Color2("Actors/Characters/hairs/grayhairB2.png"),
        FemaleHair2Color1("Actors/Characters/hairs/grayhairB2.png"),
        FemaleHair2Color2("Actors/Characters/hairs/brownhairA2.png"),
        FemaleHair3Color1("Actors/Characters/hairs/brownhairA2.png"),
        FemaleHair3Color2("Actors/Characters/hairs/grayhairB2.png");

        private final String path;
        private final AssetDescriptor<Texture> assetDescriptor;

        public Texture getTexture(){
            return Assets.get().assetManager.get(assetDescriptor);
        }

        FemaleHairTextureAssets(String path){
            this.path = path;
            assetDescriptor = new AssetDescriptor<>(path, Texture.class);
        }

    }

    public enum FemaleClothTextureAssets {
        FemaleDress1("Actors/Characters/dress/dress11.png"),
        FemaleDress2("Actors/Characters/dress/dress22.png");

        private final String path;
        private final AssetDescriptor<Texture> assetDescriptor;

        public Texture getTexture(){
            return Assets.get().assetManager.get(assetDescriptor);
        }

        FemaleClothTextureAssets(String path){
            this.path = path;
            assetDescriptor = new AssetDescriptor<>(path, Texture.class);
        }

    }

    public void dispose() {
        assetManager.dispose();
    }
}
