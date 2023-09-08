package fr.kyll01.customenchant;

import org.bukkit.Material;

public enum ToolsType {

    // Outils:
    SWORD("_SWORD"),
    PICKAXE("_PICKAXE"),
    AXE("_AXE"),
    SHOVEL("_SHOVEL"),
    HOE("_HOE"),
    FISHING_ROD("FISHING_ROD"),

    // Armure:
    HELMET("_HELMET"),
    CHESTPLATE("_CHESTPLATE"),
    LEGGINGS("LEGGINGS"),
    BOOTS("_BOOTS");

    public final String label;

    ToolsType(String label){
        this.label = label;
    }
}
