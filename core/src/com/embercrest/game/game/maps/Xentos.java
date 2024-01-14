package com.embercrest.game.game.maps;

import static com.embercrest.game.ashley.componenets.PositionComponent.GRIDSIZE;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.embercrest.game.ashley.actions.Action;
import com.embercrest.game.ashley.componenets.CombatComponent;
import com.embercrest.game.ashley.componenets.HoverHandlingComponent;
import com.embercrest.game.ashley.componenets.MoveComponent;
import com.embercrest.game.ashley.componenets.NameComponent;
import com.embercrest.game.ashley.componenets.PositionComponent;
import com.embercrest.game.ashley.entities.InteractAbleEntity;
import com.embercrest.game.game.CharacterClass;
import com.embercrest.game.game.entities.PartyCharacter;
import com.embercrest.game.game.entities.Xynia;
import com.embercrest.game.Assets;

import java.util.ArrayList;
import java.util.HashMap;

public class Xentos implements EntityListener {
    private final TiledMap tiledMap;
    private final Vector2 chunk;
    public OrthogonalTiledMapRenderer mapRenderer;

    public HashMap<Vector2, InteractAbleEntity> entities = new HashMap<>();
    public ArrayList<PartyCharacter> party = new ArrayList<>(5);

    //loaded in loadScreen and is passed to the gameScreen
    private final Engine engine = new Engine();

    public Xentos(Vector2 chunk) {
        this.chunk = chunk;
        //tiledMap = new TmxMapLoader().load("Textures/tutorialZone/tutorial.tmx");
        //tiledMap = new TmxMapLoader().load("Textures/tutorialZone/untitled.tmx");


        tiledMap = Assets.MapAssets.TutorialIsland.getMap();
        //tiledMap = new TmxMapLoader().load("Textures/tutorialZone/tut2.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        engine.addEntityListener(this);

    }
    public boolean initNPC() throws Exception {
        Action hovered = (ent) -> {System.out.println("Hovered");
            return true;};
        Action hoveredOff = (ent) -> {System.out.println("Hovered OFFFFFFFF");
            return true;};
        PartyCharacter partyCharacter = new PartyCharacter(new Vector2(16* GRIDSIZE, 7* GRIDSIZE), Assets.TextureAssets.PlayerMovementAnimation.getTexture(), CharacterClass.PlayerClass.Slayer);
        partyCharacter.add(new MoveComponent(17, MoveComponent.MoveType.Normal));
        partyCharacter.add(new NameComponent("tomek"));
        partyCharacter.getComponent(CombatComponent.class).setHP(100);
        partyCharacter.getComponent(CombatComponent.class).setAgilityBase(100);
        partyCharacter.getComponent(CombatComponent.class).setResourceType(CombatComponent.ResourceType.Energy);
        //character.add(new HoverHandlingComponent(hovered, hoveredOff));
        engine.addEntity(partyCharacter);
//        ((TiledMapTileLayer) tiledMap.getLayers().get(0)).getCell((int) char1Pos.x/GRIDSIZE, (int) char1Pos.y/GRIDSIZE). getProperties().put("moveCost", 99);

        PartyCharacter partyCharacter2 = new PartyCharacter(new Vector2(24* GRIDSIZE, 6* GRIDSIZE), Assets.TextureAssets.PlayerMovementAnimation.getTexture(), CharacterClass.PlayerClass.Warrior);
        partyCharacter2.add(new MoveComponent(4, MoveComponent.MoveType.Normal));
        //character2.add(new DisplayTextComponenet("ASDADASD"));
        partyCharacter2.add(new NameComponent("tomek2"));
        partyCharacter2.getComponent(CombatComponent.class).setHP(1);
        partyCharacter2.getComponent(CombatComponent.class).setResourceType(CombatComponent.ResourceType.DarkEnergy);
        //character2.add(new HoverHandlingComponent(hovered, hoveredOff));
        engine.addEntity(partyCharacter2);

        Xynia xynia = new Xynia(new Vector2(22, 11).scl(GRIDSIZE));

        PartyCharacter partyCharacter4 = new PartyCharacter(new Vector2(24* GRIDSIZE, 8* GRIDSIZE), Assets.TextureAssets.PlayerMovementAnimation.getTexture(), CharacterClass.PlayerClass.Warrior);
        partyCharacter4.add(new MoveComponent(4, MoveComponent.MoveType.Normal));
        //character4.add(new DisplayTextComponenet("ASDADASD"));
        partyCharacter4.add(new NameComponent("tomek4"));
        partyCharacter4.getComponent(CombatComponent.class).setHP(0);
        //character4.add(new HoverHandlingComponent(hovered, hoveredOff));
        engine.addEntity(partyCharacter4);

        PartyCharacter partyCharacter5 = new PartyCharacter(new Vector2(24* GRIDSIZE, 9* GRIDSIZE), Assets.TextureAssets.PlayerMovementAnimation.getTexture(), CharacterClass.PlayerClass.Warrior);
        partyCharacter5.add(new MoveComponent(4, MoveComponent.MoveType.Normal));
        //character5.add(new DisplayTextComponenet("ASDADASD"));
        partyCharacter5.add(new NameComponent("tomek5"));
        //character5.add(new HoverHandlingComponent(hovered, hoveredOff));
        engine.addEntity(partyCharacter5);

        PartyCharacter partyCharacter6 = new PartyCharacter(new Vector2(24* GRIDSIZE, 10* GRIDSIZE),Assets.TextureAssets.PlayerMovementAnimation.getTexture(), CharacterClass.PlayerClass.Warrior);
        partyCharacter6.add(new MoveComponent(4, MoveComponent.MoveType.Normal));
        //character6.add(new DisplayTextComponenet("ASDADASD"));
        partyCharacter6.add(new NameComponent("tomek6"));
        //partyCharacter6.add(new HoverHandlingComponent(hovered, hoveredOff));
        engine.addEntity(partyCharacter6);
        party.add(partyCharacter);
        party.add(partyCharacter2);
        party.add(xynia);
        party.add(partyCharacter4);
        party.add(partyCharacter5);
//        for (int j = 0; j < 50; j++) {
//            for (int i = 0; i < 50; i++) {
//                Character character10 = new Character(new Vector2(i * GRIDSIZE, j * GRIDSIZE), assets.player_movment_animation);
//                character10.add(new MoveComponent(17, MoveComponent.MoveType.Normal));
//                character10.add(new NameComponenet("tomek"+i));
//                //character.add(new HoverHandlingComponent(hovered, hoveredOff));
//                engine.addEntity(character10);
//            }
//        }
        return true;
    }
    public boolean initDoors(){ return false; }
    public boolean initGatherables(){ return false; }

    public int getTileCost(Vector2 position){
        TiledMapTileLayer.Cell cell = ( (TiledMapTileLayer) getTiledMap().getLayers().get(0)).getCell((int)position.x/GRIDSIZE, (int)position.y/GRIDSIZE);
        if(cell == null) return 9999;
        return Integer.parseInt(cell.getTile().getProperties().get("moveCost").toString());
    }

    public ArrayList<PartyCharacter> getParty(){
        return party;
    }

    public TiledMap getTiledMap(){
        return tiledMap;
    }

    public void dispose() {
        tiledMap.dispose();
        mapRenderer.dispose();
    }

    public Engine getEngine() {
        return engine;
    }

    @Override
    public void entityAdded(Entity entity) {
        if(entity instanceof InteractAbleEntity){
            Vector2 entPos = entity.getComponent(PositionComponent.class).getPosition();
            entities.put(entPos, (InteractAbleEntity)entity);
        }
    }

    @Override
    public void entityRemoved(Entity entity) {
        if(entity instanceof InteractAbleEntity){
            Vector2 entPos = entity.getComponent(PositionComponent.class).getPosition();
            entities.remove(entPos);
        }
    }
}
