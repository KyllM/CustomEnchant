package fr.kyll01.customenchant;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    private static final ArrayList<EnchantsType> enchantsList = new ArrayList<>();

    public static EnchantsType telepathy_I = new EnchantsType("telepathy1", "§aTelepathy I", List.of("Tligne1","Tligne2"), List.of(ToolsType.SHOVEL,ToolsType.PICKAXE,ToolsType.AXE));
    public static EnchantsType autosmelt_I = new EnchantsType("autosmelt1","§aAuto Smelt I", List.of("Aligne1","ligne2"), List.of(ToolsType.PICKAXE));
    public static EnchantsType oxygen_I = new EnchantsType("oxygen1","§3Oxygen I", List.of("Aligne1","Aligne2"), List.of(ToolsType.SHOVEL,ToolsType.PICKAXE));
    public static EnchantsType blocktrack = new EnchantsType("blocktrack1","§cBlockTrack ", List.of("Aligne1","Aligne2"), List.of(ToolsType.SHOVEL,ToolsType.PICKAXE,ToolsType.AXE));
    public static EnchantsType fishtrack = new EnchantsType("fishtrack1","§cFishTrack", List.of("Aligne1","Aligne2"), List.of(ToolsType.FISHING_ROD,ToolsType.PICKAXE,ToolsType.AXE));
    public static EnchantsType movetrack = new EnchantsType("movetrack1","§cMoveTrack ", List.of("Aligne1","Aligne2"), List.of(ToolsType.BOOTS));
    public static EnchantsType dolphin_I = new EnchantsType("dolphin1","§3Dolphin I", List.of("Aligne1","Aligne2"), List.of(ToolsType.LEGGINGS));
    public static EnchantsType dolphin_II = new EnchantsType("dolphin2","§3Dolphin II", List.of("Aligne1","Aligne2"), List.of(ToolsType.LEGGINGS));
    public static EnchantsType dolphin_III = new EnchantsType("dolphin3","§3Dolphin III", List.of("Aligne1","Aligne2"), List.of(ToolsType.LEGGINGS));
    public static EnchantsType speed_I = new EnchantsType("speed1","§eSpeed I", List.of("Aligne1","Aligne2"), List.of(ToolsType.BOOTS));
    public static EnchantsType speed_II = new EnchantsType("speed2","§eSpeed II", List.of("Aligne1","Aligne2"), List.of(ToolsType.BOOTS));
    public static EnchantsType speed_III = new EnchantsType("speed3","§eSpeed III", List.of("Aligne1","Aligne2"), List.of(ToolsType.BOOTS));
    public static EnchantsType speed_IV = new EnchantsType("speed4","§eSpeed IV", List.of("Aligne1","Aligne2"), List.of(ToolsType.BOOTS));
    public static EnchantsType speed_V = new EnchantsType("speed5","§eSpeed V", List.of("Aligne1","Aligne2"), List.of(ToolsType.BOOTS));
    public static EnchantsType haste_I = new EnchantsType("haste1","§6Haste I", List.of("Aligne1","Aligne2"), List.of(ToolsType.SHOVEL,ToolsType.PICKAXE,ToolsType.AXE));
    public static EnchantsType haste_II = new EnchantsType("haste2","§6Haste II", List.of("Aligne1","Aligne2"), List.of(ToolsType.SHOVEL,ToolsType.PICKAXE,ToolsType.AXE));
    public static EnchantsType haste_III = new EnchantsType("haste3","§6Haste III", List.of("Aligne1","Aligne2"), List.of(ToolsType.SHOVEL,ToolsType.PICKAXE,ToolsType.AXE));
    public static EnchantsType haste_IV = new EnchantsType("haste4","§6Haste IV", List.of("Aligne1","Aligne2"), List.of(ToolsType.SHOVEL,ToolsType.PICKAXE,ToolsType.AXE));
    public static EnchantsType haste_V = new EnchantsType("haste5","§6Haste V", List.of("Aligne1","Aligne2"), List.of(ToolsType.SHOVEL,ToolsType.PICKAXE,ToolsType.AXE));
    public static EnchantsType bighole_I = new EnchantsType("bighole1","§4Big Hole I", List.of("Aligne1","Aligne2"), List.of(ToolsType.SHOVEL,ToolsType.PICKAXE));
    public static EnchantsType autofeed_I = new EnchantsType("autofeed1","§6Auto Feed I", List.of("Aligne1","Aligne2"), List.of(ToolsType.HELMET));
    public static EnchantsType autofeed_II = new EnchantsType("autofeed2","§6Auto Feed II", List.of("Aligne1","Aligne2"), List.of(ToolsType.HELMET));
    public static EnchantsType autofeed_III = new EnchantsType("autofeed3","§6Auto Feed III", List.of("Aligne1","Aligne2"), List.of(ToolsType.HELMET));
    public static EnchantsType stormbreaker_I = new EnchantsType("stormbreaker1","§b§lStormBreaker I", List.of("Déposé se livre sur une Hache afin de lui appliquer l'enchant","§b§lStormBreaker I"), List.of(ToolsType.AXE));
    public static EnchantsType slimeboots_I = new EnchantsType("slimeboots1","§a§lSlime Boots I", List.of("Déposé se livre sur une Hache afin de lui appliquer l'enchant","§b§lStormBreaker I"), List.of(ToolsType.BOOTS));

    public static void initEnchants(){
        enchantsList.add(telepathy_I);
        enchantsList.add(autosmelt_I);
        enchantsList.add(oxygen_I);
        enchantsList.add(blocktrack);
        enchantsList.add(fishtrack);
        enchantsList.add(movetrack);
        enchantsList.add(dolphin_I);
        enchantsList.add(dolphin_II);
        enchantsList.add(dolphin_III);
        enchantsList.add(speed_I);
        enchantsList.add(speed_II);
        enchantsList.add(speed_III);
        enchantsList.add(speed_IV);
        enchantsList.add(speed_V);
        enchantsList.add(haste_I);
        enchantsList.add(haste_II);
        enchantsList.add(haste_III);
        enchantsList.add(haste_IV);
        enchantsList.add(haste_V);
        enchantsList.add(bighole_I);
        enchantsList.add(autofeed_I);
        enchantsList.add(autofeed_II);
        enchantsList.add(autofeed_III);
        enchantsList.add(stormbreaker_I);
        enchantsList.add(slimeboots_I);
    }

    public static boolean isEnchantable(ItemStack item, EnchantsType enchant){
        for(ToolsType toolsType : enchant.tools()){
            if(item.getType().toString().endsWith(toolsType.label)){
                return true;
            }
        }
        return false;
    }

    public static ArrayList<EnchantsType> getEnchantsList(){
        return enchantsList;
    }

    public static EnchantsType getEnchantFromName(String enchant){
        for(EnchantsType enchantsType : enchantsList){
            if(enchantsType.name().equals(enchant)){
                return enchantsType;
            }
        }
        return new EnchantsType("Null","Null",List.of(), List.of());
    }

    public static EnchantsType getEnchantFromDisplayname(String enchant){
        for(EnchantsType enchantsType : enchantsList){
            if(enchantsType.displayname().equals(enchant)){
                return enchantsType;
            }
        }
        return new EnchantsType("Null","Null",List.of(), List.of());
    }

    public static boolean hasEnchant(ItemStack item, String enchant){
        if(item == null || item.getItemMeta() == null || item.getItemMeta().getLore() == null) return false;
        return item.getItemMeta().getLore().contains(enchant);
    }

    public static boolean hasEnchant(ItemStack item, EnchantsType enchant) {
        if (item == null || item.getItemMeta() == null || item.getItemMeta().getLore() == null) return false;
        List<String> itemLore = item.getItemMeta().getLore();
        if (itemLore != null) {
            for (String lore : itemLore) {
                if (lore.equals(enchant.displayname())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void giveBook(Player p, String value){
        if (Utils.getEnchantsList().contains(Utils.getEnchantFromName(value))) {
            EnchantsType enchant = Utils.getEnchantFromName(value);
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta itemMeta = item.getItemMeta();
            assert itemMeta != null;
            itemMeta.setDisplayName(enchant.displayname());
            itemMeta.setLore(enchant.lore());
            item.setItemMeta(itemMeta);
            p.getInventory().addItem(item);
        } else {
            p.sendMessage("§cErreur: cet enchantement n'existe pas.");
        }
    }

}