package com.embercrest.game.drawing_tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class CharacterTextureGeneratorFromParts {

    private final ArrayList<Texture> M_bodyArray = new ArrayList<>(4);
    private final ArrayList<ArrayList<Texture>> M_hairArray = new ArrayList<>(2);
    private final ArrayList<Integer> M_hairColorArray = new ArrayList<>();
    private final ArrayList<Texture> M_clothArray = new ArrayList<>();
    private final ArrayList<Texture> M_eyeColorArray = new ArrayList<>();
    private final ArrayList<Texture> M_legsArray = new ArrayList<>();
    private final ArrayList<Texture> M_bootsArray = new ArrayList<>();


    private final ArrayList<Texture> F_bodyArray = new ArrayList<>(4);
    private final ArrayList<ArrayList<Texture>> F_hairArray = new ArrayList<>(2);
    private final ArrayList<Integer> F_hairColorArray = new ArrayList<>();
    private final ArrayList<Texture> F_clothArray = new ArrayList<>();
    private final ArrayList<Texture> F_eyeColorArray = new ArrayList<>();
    private final ArrayList<Texture> F_legsArray = new ArrayList<>();
    private final ArrayList<Texture> F_bootsArray = new ArrayList<>();

    public CharacterTextureGeneratorFromParts(){

        // TODO create version that uses assets, and generates animation instead of texture;

        F_bodyArray.add(new Texture(Gdx.files.internal("Actors/Characters/body/basicfemale.png")));
        F_bodyArray.add(new Texture(Gdx.files.internal("Actors/Characters/body/basicfemaleb.png")));
        F_bodyArray.add(new Texture(Gdx.files.internal("Actors/Characters/body/basicfemaled.png")));
        F_bodyArray.add(new Texture(Gdx.files.internal("Actors/Characters/body/basicfemalev.png")));

        F_clothArray.add(new Texture(Gdx.files.internal("Actors/Characters/dress/dress11.png")));
        F_clothArray.add(new Texture(Gdx.files.internal("Actors/Characters/dress/dress22.png")));

        F_hairArray.add(new ArrayList<>(1));
       F_hairArray.get(0).add(new Texture(Gdx.files.internal("Actors/Characters/hairs/brownhairA2.png")));    ///// hair nr 1 color 1
        F_hairArray.get(0).add(new Texture(Gdx.files.internal("Actors/Characters/hairs/grayhairB2.png")));    ///////  hair nr 2 color 1
        F_hairArray.get(0).add(new Texture(Gdx.files.internal("Actors/Characters/hairs/brownhairA2.png")));   ///////////// hair nr 3 color 1
        F_hairColorArray.add(1);     /// number of colors of hair nr 1
        F_hairArray.add(new ArrayList<>(1));
        F_hairArray.get(1).add(new Texture(Gdx.files.internal("Actors/Characters/hairs/grayhairB2.png"))); /// hair nr 1 color 2
        F_hairArray.get(1).add(new Texture(Gdx.files.internal("Actors/Characters/hairs/brownhairA2.png"))); /// hair nr 2 color 2
        F_hairArray.get(1).add(new Texture(Gdx.files.internal("Actors/Characters/hairs/grayhairB2.png"))); /// hair nr 3  color 2
        F_hairColorArray.add(2);

    }


    public Texture GenFromPartsId(int bodyId, int hairId, int hairColorId, int clothId, int eyeColorId, int legsId, int bootsId, boolean isFemale){
        Texture bodyTexture;
        Texture hairTexture;
        Texture clothTexture;
        //Texture eyeColorTexture;
        //Texture legsTexture;
        //Texture bootsTexture;
        Integer hairColor;
        if(isFemale){
            bodyTexture = F_bodyArray.get(bodyId);
            hairTexture = F_hairArray.get(hairColorId).get(hairId);
            clothTexture = F_clothArray.get(clothId);
//            eyeColorTexture = F_bodyArray.get(eyeColorId);
//            legsTexture = F_bodyArray.get(legsId);
//            bootsTexture = F_bootsArray.get(bootsId);
            hairColor = F_hairColorArray.get(hairColorId);
        }else {
            bodyTexture = M_bodyArray.get(bodyId);
            hairTexture = M_hairArray.get(hairColorId).get(hairId);
            clothTexture = M_clothArray.get(clothId);
//            eyeColorTexture = M_bodyArray.get(eyeColorId);
//            legsTexture = M_bodyArray.get(legsId);
//            bootsTexture = M_bootsArray.get(bootsId);
            hairColor = M_hairColorArray.get(hairColorId);
        }

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
        CHAR_PIXMAP.drawPixmap(hairPixmap, 0, 0);
        CHAR_PIXMAP.drawPixmap(clothPixmap, 0, 0);
//        CHAR_PIXMAP.drawPixmap(eyePixmap, 0, 0);
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

    public void dispose(){
        for(Texture texture: M_bodyArray) texture.dispose();
        for(ArrayList<Texture> M_hairColorArray: M_hairArray) for(Texture texture: M_hairColorArray) texture.dispose();
        for(Texture texture: M_clothArray) texture.dispose();
        for(Texture texture: M_eyeColorArray) texture.dispose();
        for(Texture texture: M_legsArray) texture.dispose();
        for(Texture texture: M_bootsArray) texture.dispose();

        for(Texture texture: F_bodyArray) texture.dispose();
        for(ArrayList<Texture> F_hairColorArray: F_hairArray) for(Texture texture: F_hairColorArray) texture.dispose();
        for(Texture texture: F_clothArray) texture.dispose();
        for(Texture texture: F_eyeColorArray) texture.dispose();
        for(Texture texture: F_legsArray) texture.dispose();
        for(Texture texture: F_bootsArray) texture.dispose();
    }

    public ArrayList<Texture> getM_BodyArray() {
        return M_bodyArray;
    }

    public ArrayList<ArrayList<Texture>> getM_HairArray() {
        return M_hairArray;
    }

    public ArrayList<Integer> getM_HairColorArray() {
        return M_hairColorArray;
    }

    public ArrayList<Texture> getM_ClothArray() {
        return M_clothArray;
    }

    public ArrayList<Texture> getM_EyeColorArray() {
        return M_eyeColorArray;
    }

    public ArrayList<Texture> getM_LegsArray() {
        return M_legsArray;
    }

    public ArrayList<Texture> getM_BootsArray() {
        return M_bootsArray;
    }

    ///////////////////////////////////////////////////////////////////////////

    public ArrayList<Texture> getF_BodyArray() {
        return F_bodyArray;
    }

    public ArrayList<ArrayList<Texture>> getF_HairArray() {
        return F_hairArray;
    }

    public ArrayList<Integer> getF_HairColorArray() {
        return F_hairColorArray;
    }

    public ArrayList<Texture> getF_ClothArray() {
        return F_clothArray;
    }

    public ArrayList<Texture> getF_EyeColorArray() {
        return F_eyeColorArray;
    }

    public ArrayList<Texture> getF_LegsArray() {
        return F_legsArray;
    }

    public ArrayList<Texture> getF_BootsArray() {
        return F_bootsArray;
    }
}
