package com.embercrest.game.ashley.componenets;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.embercrest.game.Assets;
import com.embercrest.game.EmberCrest;
import com.embercrest.game.drawing_tools.StatusBar;

public class CombatComponent implements Component {
    // entity status
    private int maxHP, HP, maxResource, resource;
    private ResourceType resourceType = ResourceType.Mana;
    //private ProgressBar resourceBar, healthBar;

    private StatusBar resourceBar, healthBar;

    //entity stats

    // Primary
    private int strengthBase = 0; // damage stat for warriors
    private int agilityBase = 0; // damage stat for rogue like classes
    private int intellectBase = 0; // damage stat for casters
    private int vitalityBase = 0; // health stat

    // Secondary
    private int armorBase = 0; //just armor, reduce physical dmg
    private int resilienceBase = 0; //reduce magic damage taken
    private int critBase = 0; // chance for dealing 1.5times dmg
    private int masteryBase = 0; // class based stat

    // modifiers
    private int blockChanceBase = 0;

    private int strengthEquipment = 0;
    private int agilityEquipment = 0;
    private int intellectEquipment = 0;
    private int vitalityEquipment = 0;

    // Secondary
    private int armorEquipment = 0;
    private int resilienceEquipment = 0;
    private int critEquipment = 0;
    private int masteryEquipment = 0;

    // modifiers
    private int blockChanceEquipment= 0;

    private float strengthPercentageChange = 0;
    private float agilityPercentageChange = 0;
    private float intellectPercentageChange = 0;
    private float vitalityPercentageChange = 0;

    // Secondary
    private float armorPercentageChange = 0;
    private float resiliencePercentageChange = 0;
    private float critPercentageChange = 0;
    private float masteryPercentageChange = 0;

    private float blockChancePercentageChange = 0;

    public CombatComponent(int maxHP, int  HP, int  maxResource, int  resource){
        this.maxHP = maxHP;
        this.HP = HP;
        this.maxResource = maxResource;
        this.resource = resource;
        setUpProgressBars();
    }

//    public CombatComponenet(int maxHP,int  HP,int  maxResource,int  resource, ResourceType resourceType){
//        this.maxHP = maxHP;
//        this.HP = HP;
//        this.maxResource = maxResource;
//        this.resource = resource;
//        this.resourceType = resourceType;
//        setUpProgressBars(maxHP, HP, maxResource, resource);
//    }

    private void setUpProgressBars(){
        Skin skin = Assets.get().getSkin();
        healthBar = new StatusBar(maxHP);
        healthBar.setValue(HP);
        //healthBar.setWidth(64);
        //healthBar.setSize(64, 3);

        resourceBar = new StatusBar(maxResource, new StatusBar.StatusBarStyle(resourceType.i));
        resourceBar.setValue(resource);
        //resourceBar.setWidth(64);
        //resourceBar.setHeight(4);
    }

    public Table getStatsTable(){
        Table table = new Table();
        table.setDebug(false);

        table.add(new Label("Strength = "+strengthBase, Assets.get().getSkin()));
        table.add(new Label(" + "+strengthEquipment+" + ", Assets.get().getSkin()));
        int strBonus = (int) (strengthPercentageChange*(strengthBase+strengthEquipment))/100;
        Color bonusColor = Color.GREEN;
        if(strBonus < 0) bonusColor = Color.RED;
        table.add(new Label(strBonus+" ", new Label.LabelStyle(Assets.get().getSkin().getFont("aleo_regular_small"), bonusColor)));
        int totalStr = strengthBase + strengthEquipment+ strBonus;
        table.add(new Label(" = "+ totalStr, Assets.get().getSkin()));

        table.row();

        table.add(new Label("Agility = "+agilityBase, Assets.get().getSkin()));
        table.add(new Label(" + "+agilityEquipment+" + ", Assets.get().getSkin()));
        int agiBonus = (int) (agilityPercentageChange*(agilityBase+agilityEquipment))/100;
        bonusColor = Color.GREEN;
        if(agiBonus < 0) bonusColor = Color.RED;
        table.add(new Label(agiBonus+" ", new Label.LabelStyle(Assets.get().getSkin().getFont("aleo_regular_small"), bonusColor)));
        int totalAgi = agilityBase + agilityEquipment+ agiBonus;
        table.add(new Label(" = "+ totalAgi, Assets.get().getSkin()));

        table.row();

        table.add(new Label("Intellect = "+intellectBase, Assets.get().getSkin()));
        table.add(new Label(" + "+intellectEquipment+" + ", Assets.get().getSkin()));
        int intBonus = (int) (intellectPercentageChange*(intellectBase+intellectEquipment))/100;
        bonusColor = Color.GREEN;
        if(intBonus < 0) bonusColor = Color.RED;
        table.add(new Label(intBonus+" ", new Label.LabelStyle(Assets.get().getSkin().getFont("aleo_regular_small"), bonusColor)));
        int totalInt = intellectBase + intellectEquipment+ intBonus;
        table.add(new Label(" = "+ totalInt, Assets.get().getSkin()));

        table.row();

        table.add(new Label("Vitality = "+vitalityBase, Assets.get().getSkin()));
        table.add(new Label(" + "+vitalityEquipment+" + ", Assets.get().getSkin()));
        int vitBonus = (int) (vitalityPercentageChange*(vitalityBase+vitalityEquipment))/100;
        bonusColor = Color.GREEN;
        if(vitBonus < 0) bonusColor = Color.RED;
        table.add(new Label(vitBonus+" ", new Label.LabelStyle(Assets.get().getSkin().getFont("aleo_regular_small"), bonusColor)));
        int totalVit = vitalityBase + vitalityEquipment+ vitBonus;
        table.add(new Label(" = "+ totalVit, Assets.get().getSkin()));

        table.row();

        table.add(new Label("Armor = "+armorBase, Assets.get().getSkin()));
        table.add(new Label(" + "+armorEquipment+" + ", Assets.get().getSkin()));
        int armBonus = (int) (armorPercentageChange*(armorBase+armorEquipment))/100;
        bonusColor = Color.GREEN;
        if(armBonus < 0) bonusColor = Color.RED;
        table.add(new Label(armBonus+" ", new Label.LabelStyle(Assets.get().getSkin().getFont("aleo_regular_small"), bonusColor)));
        int totalArm = armorBase + armorEquipment+ armBonus;
        table.add(new Label(" = "+ totalArm, Assets.get().getSkin()));

        table.row();

        table.add(new Label("Resilience = "+resilienceBase, Assets.get().getSkin()));
        table.add(new Label(" + "+resilienceEquipment+" + ", Assets.get().getSkin()));
        int resBonus = (int) (resiliencePercentageChange*(resilienceBase+resilienceEquipment))/100;
        bonusColor = Color.GREEN;
        if(resBonus < 0) bonusColor = Color.RED;
        table.add(new Label(resBonus+" ", new Label.LabelStyle(Assets.get().getSkin().getFont("aleo_regular_small"), bonusColor)));
        int totalRes = resilienceBase + resilienceEquipment+ resBonus;
        table.add(new Label(" = "+ totalRes, Assets.get().getSkin()));

        table.row();

        table.add(new Label("Crit = "+critBase, Assets.get().getSkin()));
        table.add(new Label(" + "+critEquipment+" + ", Assets.get().getSkin()));
        int critBonus = (int) (critPercentageChange*(critBase+critEquipment))/100;
        bonusColor = Color.GREEN;
        if(critBonus < 0) bonusColor = Color.RED;
        table.add(new Label(critBonus+" ", new Label.LabelStyle(Assets.get().getSkin().getFont("aleo_regular_small"), bonusColor)));
        int critTotal = critBase + critEquipment+ critBonus;
        table.add(new Label(" = "+ critTotal, Assets.get().getSkin()));

        table.row();

        table.add(new Label("Mastery = "+masteryBase, Assets.get().getSkin()));
        table.add(new Label(" + "+masteryEquipment+" + ", Assets.get().getSkin()));
        int mastBonus = (int) (masteryPercentageChange*(masteryBase+masteryEquipment))/100;
        bonusColor = Color.GREEN;
        if(mastBonus < 0) bonusColor = Color.RED;
        table.add(new Label(mastBonus+" ", new Label.LabelStyle(Assets.get().getSkin().getFont("aleo_regular_small"), bonusColor)));
        int mastTotal = masteryBase + masteryEquipment+ mastBonus;
        table.add(new Label(" = "+ mastTotal, Assets.get().getSkin()));

        table.row();

        table.add(new Label("Block = "+blockChanceBase, Assets.get().getSkin()));
        table.add(new Label(" + "+blockChanceEquipment+" + ", Assets.get().getSkin()));
        int blockBonus = (int) (blockChancePercentageChange*(blockChanceBase+blockChanceEquipment))/100;
        bonusColor = Color.GREEN;
        if(blockBonus < 0) bonusColor = Color.RED;
        table.add(new Label(blockBonus+" ", new Label.LabelStyle(Assets.get().getSkin().getFont("aleo_regular_small"), bonusColor)));
        int blockTotal = blockChanceBase + blockChanceEquipment+ blockBonus;
        table.add(new Label(" = "+ blockTotal, Assets.get().getSkin()));

        return table;
    }

    public boolean dealDamage(int dmg){
        if(HP-dmg<=0) return false;
        HP -= dmg;
        // ToDO refresh bars
        return true;
    }
    public void heal(int heal){
        HP = Math.min(HP+heal, maxHP);
    }

    public boolean useResource(int amount){
        if(resource-amount <0) return false;
        resource -= amount;
        return true;
    }
    public void regenerateResource(int amount){
        resource = Math.min(resource+amount, maxResource);
    }

    public
    enum ResourceType {
        Mana(1),
        Energy(2),
        DarkEnergy(3);

        private final int i;
        ResourceType(int i) {
            this.i = i;
        }
        public int getI() {return i;}
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getMaxResource() {
        return maxResource;
    }

    public void setMaxResource(int maxResource) {
        this.maxResource = maxResource;
    }

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
        setUpProgressBars();
    }

    public StatusBar getResourceBar() {
        return resourceBar;
    }

//    public void setResourceBar(ProgressBar resourceBar) {
//        this.resourceBar = resourceBar;
//    }

    public StatusBar getHealthBar() {
        return healthBar;
    }
//
//    public void setHealthBar(ProgressBar healthBar) {
//        this.healthBar = healthBar;
//    }

    public int getStrengthBase() {
        return strengthBase;
    }

    public void setStrengthBase(int strengthBase) {
        this.strengthBase = strengthBase;
    }

    public int getAgilityBase() {
        return agilityBase;
    }

    public void setAgilityBase(int agilityBase) {
        this.agilityBase = agilityBase;
    }

    public int getIntellectBase() {
        return intellectBase;
    }

    public void setIntellectBase(int intellectBase) {
        this.intellectBase = intellectBase;
    }

    public int getVitalityBase() {
        return vitalityBase;
    }

    public void setVitalityBase(int vitalityBase) {
        this.vitalityBase = vitalityBase;
    }

    public int getMasteryBase() {
        return masteryBase;
    }

    public void setMasteryBase(int masteryBase) {
        this.masteryBase = masteryBase;
    }

    public int getArmorBase() {
        return armorBase;
    }

    public void setArmorBase(int armorBase) {
        this.armorBase = armorBase;
    }

    public int getResilienceBase() {
        return resilienceBase;
    }

    public void setResilienceBase(int resilienceBase) {
        this.resilienceBase = resilienceBase;
    }

    public int getCritBase() {
        return critBase;
    }

    public void setCritBase(int critBase) {
        this.critBase = critBase;
    }

    public float getBlockChanceBase() {
        return blockChanceBase;
    }

    public void setBlockChanceBase(int blockChanceBase) {
        this.blockChanceBase = blockChanceBase;
    }

    public int getStrengthEquipment() {
        return strengthEquipment;
    }

    public void setStrengthEquipment(int strengthEquipment) {
        this.strengthEquipment = strengthEquipment;
    }

    public int getAgilityEquipment() {
        return agilityEquipment;
    }

    public void setAgilityEquipment(int agilityEquipment) {
        this.agilityEquipment = agilityEquipment;
    }

    public int getIntellectEquipment() {
        return intellectEquipment;
    }

    public void setIntellectEquipment(int intellectEquipment) {
        this.intellectEquipment = intellectEquipment;
    }

    public int getVitalityEquipment() {
        return vitalityEquipment;
    }

    public void setVitalityEquipment(int vitalityEquipment) {
        this.vitalityEquipment = vitalityEquipment;
    }

    public int getMasteryEquipment() {
        return masteryEquipment;
    }

    public void setMasteryEquipment(int masteryEquipment) {
        this.masteryEquipment = masteryEquipment;
    }

    public int getArmorEquipment() {
        return armorEquipment;
    }

    public void setArmorEquipment(int armorEquipment) {
        this.armorEquipment = armorEquipment;
    }

    public int getResilienceEquipment() {
        return resilienceEquipment;
    }

    public void setResilienceEquipment(int resilienceEquipment) {
        this.resilienceEquipment = resilienceEquipment;
    }

    public int getCritEquipment() {
        return critEquipment;
    }

    public void setCritEquipment(int critEquipment) {
        this.critEquipment = critEquipment;
    }

    public float getBlockChanceEquipment() {
        return blockChanceEquipment;
    }

    public void setBlockChanceEquipment(int blockChanceEquipment) {
        this.blockChanceEquipment = blockChanceEquipment;
    }

    public float getStrengthPercentageChange() {
        return strengthPercentageChange;
    }

    public void setStrengthPercentageChange(float strengthPercentageChange) {
        this.strengthPercentageChange = strengthPercentageChange;
    }

    public float getAgilityPercentageChange() {
        return agilityPercentageChange;
    }

    public void setAgilityPercentageChange(float agilityPercentageChange) {
        this.agilityPercentageChange = agilityPercentageChange;
    }

    public float getIntellectPercentageChange() {
        return intellectPercentageChange;
    }

    public void setIntellectPercentageChange(float intellectPercentageChange) {
        this.intellectPercentageChange = intellectPercentageChange;
    }

    public float getVitalityPercentageChange() {
        return vitalityPercentageChange;
    }

    public void setVitalityPercentageChange(float vitalityPercentageChange) {
        this.vitalityPercentageChange = vitalityPercentageChange;
    }

    public float getMasteryPercentageChange() {
        return masteryPercentageChange;
    }

    public void setMasteryPercentageChange(float masteryPercentageChange) {
        this.masteryPercentageChange = masteryPercentageChange;
    }

    public float getArmorPercentageChange() {
        return armorPercentageChange;
    }

    public void setArmorPercentageChange(float armorPercentageChange) {
        this.armorPercentageChange = armorPercentageChange;
    }

    public float getResiliencePercentageChange() {
        return resiliencePercentageChange;
    }

    public void setResiliencePercentageChange(float resiliencePercentageChange) {
        this.resiliencePercentageChange = resiliencePercentageChange;
    }

    public float getCritPercentageChange() {
        return critPercentageChange;
    }

    public void setCritPercentageChange(float critPercentageChange) {
        this.critPercentageChange = critPercentageChange;
    }

    public float getBlockChancePercentageChange() {
        return blockChancePercentageChange;
    }

    public void setBlockChancePercentageChange(float blockChancePercentageChange) {
        this.blockChancePercentageChange = blockChancePercentageChange;
    }
}
