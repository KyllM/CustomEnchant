package fr.kyll01.customenchant;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    private static final ArrayList<EnchantsType> enchantsList = new ArrayList<>();

    public static EnchantsType telepathy_I = new EnchantsType("telepathy1", "§aTelepathy I", List.of("Déposé se livre sur une Hache, une Pelle ou une Pioche afin de lui appliquer l'enchant","§aTelepathy I"), List.of(ToolsType.SHOVEL,ToolsType.PICKAXE,ToolsType.AXE));
    public static EnchantsType autosmelt_I = new EnchantsType("autosmelt1","§aAuto Smelt I", List.of("Déposé se livre sur une Pioche afin de lui appliquer l'enchant","§aAuto Smelt I"), List.of(ToolsType.PICKAXE));
    public static EnchantsType oxygen_I = new EnchantsType("oxygen1","§3Oxygen I", List.of("Déposé se livre sur une Pelle ou une Pioche afin de lui appliquer l'enchant","§3Oxygen I"), List.of(ToolsType.SHOVEL,ToolsType.PICKAXE));
    public static EnchantsType blocktrack = new EnchantsType("blocktrack1","§cBlockTrack", List.of("Déposé se livre sur une Hache, une Pelle ou une Pioche afin de lui appliquer l'enchant","§cBlockTrack"), List.of(ToolsType.SHOVEL,ToolsType.PICKAXE,ToolsType.AXE));
    public static EnchantsType fishtrack = new EnchantsType("fishtrack1","§cFishTrack", List.of("Déposé se livre sur une Canne à pêche afin de lui appliquer l'enchant","§cFishTrack"), List.of(ToolsType.FISHING_ROD,ToolsType.PICKAXE,ToolsType.AXE));
    public static EnchantsType movetrack = new EnchantsType("movetrack1","§cMoveTrack ", List.of("Déposé se livre sur des Bottes afin de lui appliquer l'enchant","§cMoveTrack"), List.of(ToolsType.BOOTS));
    public static EnchantsType dolphin_I = new EnchantsType("dolphin1","§3Dolphin I", List.of("Déposé se livre sur un Pantalon afin de lui appliquer l'enchant","§3Dolphin I"), List.of(ToolsType.LEGGINGS));
    public static EnchantsType dolphin_II = new EnchantsType("dolphin2","§3Dolphin II", List.of("Déposé se livre sur un Pantalon afin de lui appliquer l'enchant","§3Dolphin II"), List.of(ToolsType.LEGGINGS));
    public static EnchantsType dolphin_III = new EnchantsType("dolphin3","§3Dolphin III", List.of("Déposé se livre sur un Pantalon afin de lui appliquer l'enchant","§3Dolphin III"), List.of(ToolsType.LEGGINGS));
    public static EnchantsType speed_I = new EnchantsType("speed1","§eSpeed I", List.of("Déposé se livre sur des Bottes afin de lui appliquer l'enchant","§eSpeed I"), List.of(ToolsType.BOOTS));
    public static EnchantsType speed_II = new EnchantsType("speed2","§eSpeed II", List.of("Déposé se livre sur des Bottes afin de lui appliquer l'enchant","§eSpeed II"), List.of(ToolsType.BOOTS));
    public static EnchantsType speed_III = new EnchantsType("speed3","§eSpeed III", List.of("Déposé se livre sur des Bottes afin de lui appliquer l'enchant","§eSpeed III"), List.of(ToolsType.BOOTS));
    public static EnchantsType speed_IV = new EnchantsType("speed4","§eSpeed IV", List.of("Déposé se livre sur des Bottes afin de lui appliquer l'enchant","§eSpeed IV"), List.of(ToolsType.BOOTS));
    public static EnchantsType speed_V = new EnchantsType("speed5","§eSpeed V", List.of("Déposé se livre sur des Bottes afin de lui appliquer l'enchant","§eSpeed V"), List.of(ToolsType.BOOTS));
    public static EnchantsType haste_I = new EnchantsType("haste1","§6Haste I", List.of("Déposé se livre sur une Hache, une Pelle ou une Pioche afin de lui appliquer l'enchant","§6Haste I"), List.of(ToolsType.SHOVEL,ToolsType.PICKAXE,ToolsType.AXE));
    public static EnchantsType haste_II = new EnchantsType("haste2","§6Haste II", List.of("Déposé se livre sur une Hache, une Pelle ou une Pioche afin de lui appliquer l'enchant","§6Haste II"), List.of(ToolsType.SHOVEL,ToolsType.PICKAXE,ToolsType.AXE));
    public static EnchantsType haste_III = new EnchantsType("haste3","§6Haste III", List.of("Déposé se livre sur une Hache, une Pelle ou une Pioche afin de lui appliquer l'enchant","§6Haste III"), List.of(ToolsType.SHOVEL,ToolsType.PICKAXE,ToolsType.AXE));
    public static EnchantsType haste_IV = new EnchantsType("haste4","§6Haste IV", List.of("Déposé se livre sur une Hache, une Pelle ou une Pioche afin de lui appliquer l'enchant","§6Haste IV"), List.of(ToolsType.SHOVEL,ToolsType.PICKAXE,ToolsType.AXE));
    public static EnchantsType haste_V = new EnchantsType("haste5","§6Haste V", List.of("Déposé se livre sur une Hache, une Pelle ou une Pioche afin de lui appliquer l'enchant","§6Haste V"), List.of(ToolsType.SHOVEL,ToolsType.PICKAXE,ToolsType.AXE));
    public static EnchantsType bighole_I = new EnchantsType("bighole1","§4Big Hole I", List.of("Déposé se livre sur une Pelle ou une Pioche afin de lui appliquer l'enchant","§4Big Hole I"), List.of(ToolsType.SHOVEL,ToolsType.PICKAXE));
    public static EnchantsType bighole_II = new EnchantsType("bighole2","§4Big Hole II", List.of("Déposé se livre sur une Pelle ou une Pioche afin de lui appliquer l'enchant","§4Big Hole II"), List.of(ToolsType.SHOVEL,ToolsType.PICKAXE));
    public static EnchantsType bighole_III = new EnchantsType("bighole3","§4Big Hole III", List.of("Déposé se livre sur une Pelle ou une Pioche afin de lui appliquer l'enchant","§4Big Hole III"), List.of(ToolsType.SHOVEL,ToolsType.PICKAXE));
    public static EnchantsType autofeed_I = new EnchantsType("autofeed1","§6Auto Feed I", List.of("Déposé se livre sur un Casque afin de lui appliquer l'enchant","§6Auto Feed I"), List.of(ToolsType.HELMET));
    public static EnchantsType autofeed_II = new EnchantsType("autofeed2","§6Auto Feed II", List.of("Déposé se livre sur un Casque afin de lui appliquer l'enchant","§6Auto Feed II"), List.of(ToolsType.HELMET));
    public static EnchantsType autofeed_III = new EnchantsType("autofeed3","§6Auto Feed III", List.of("Déposé se livre sur un Casque afin de lui appliquer l'enchant","§6Auto Feed III"), List.of(ToolsType.HELMET));
    public static EnchantsType stormbreaker_I = new EnchantsType("stormbreaker1","§b§lStormBreaker I", List.of("Déposé se livre sur une Hache afin de lui appliquer l'enchant","§b§lStormBreaker I"), List.of(ToolsType.AXE));
    public static EnchantsType slimeboots_I = new EnchantsType("slimeboots1","§a§lSlime Boots I", List.of("Déposé se livre sur des bottes afin de lui appliquer l'enchant","§a§lSlime Boots I"), List.of(ToolsType.BOOTS));
    public static EnchantsType strangulation_I = new EnchantsType("strangulation1","§cStrangulation I", List.of("Déposé se livre sur un item afin de lui appliquer l'enchant","§cStrangulation I"),List.of(ToolsType.SHOVEL,ToolsType.PICKAXE,ToolsType.AXE));
    public static EnchantsType push_I = new EnchantsType("push1","§cPush I", List.of("Déposé se livre sur un item afin de lui appliquer l'enchant","§cPush I"),List.of(ToolsType.SHOVEL,ToolsType.PICKAXE,ToolsType.AXE));
    public static EnchantsType autofish_I = new EnchantsType("autofish1","§bAutofish I", List.of("Déposé se livre sur une canne à pêche afin de lui appliquer l'enchant","§bAutofish I"),List.of(ToolsType.FISHING_ROD));
    public static EnchantsType veinminer_I = new EnchantsType("veinminer1","§bVeinMiner I", List.of("Déposé se livre sur une pioche afin de lui appliquer l'enchant","§bVein Miner I"),List.of(ToolsType.PICKAXE));
    public static EnchantsType wither_I = new EnchantsType("wither1","§8Wither I", List.of("Déposé se livre sur une armure afin de lui appliquer l'enchant","§8Wither I"),List.of(ToolsType.HELMET,ToolsType.CHESTPLATE,ToolsType.LEGGINGS,ToolsType.BOOTS));
    public static EnchantsType wither_II = new EnchantsType("wither2","§8Wither II", List.of("Déposé se livre sur une armure afin de lui appliquer l'enchant","§8Wither II"),List.of(ToolsType.HELMET,ToolsType.CHESTPLATE,ToolsType.LEGGINGS,ToolsType.BOOTS));
    public static EnchantsType wither_III = new EnchantsType("wither3","§8Wither III", List.of("Déposé se livre sur une armure afin de lui appliquer l'enchant","§8Wither III"),List.of(ToolsType.HELMET,ToolsType.CHESTPLATE,ToolsType.LEGGINGS,ToolsType.BOOTS));
    public static EnchantsType poison_I = new EnchantsType("poison1","§2Poison I", List.of("Déposé se livre sur une armure afin de lui appliquer l'enchant","§2Ppoison I"),List.of(ToolsType.HELMET,ToolsType.CHESTPLATE,ToolsType.LEGGINGS,ToolsType.BOOTS));
    public static EnchantsType poison_II = new EnchantsType("poison2","§2Poison II", List.of("Déposé se livre sur une armure afin de lui appliquer l'enchant","§2Poison II"),List.of(ToolsType.HELMET,ToolsType.CHESTPLATE,ToolsType.LEGGINGS,ToolsType.BOOTS));
    public static EnchantsType poison_III = new EnchantsType("poison3","§2Poison III", List.of("Déposé se livre sur une armure afin de lui appliquer l'enchant","§2Poison III"),List.of(ToolsType.HELMET,ToolsType.CHESTPLATE,ToolsType.LEGGINGS,ToolsType.BOOTS));
    public static EnchantsType fly_I = new EnchantsType("fly1","§bFly I", List.of("Déposé se livre sur des élytres afin de lui appliquer l'enchant","§bFly I"),List.of(ToolsType.ELYTRA));
    public static EnchantsType fly_II = new EnchantsType("fly2","§bFly II", List.of("Déposé se livre sur des élytres afin de lui appliquer l'enchant","§bFly II"),List.of(ToolsType.ELYTRA));
    public static EnchantsType fly_III = new EnchantsType("fly3","§bFly III", List.of("Déposé se livre sur des élytres afin de lui appliquer l'enchant","§bFly III"),List.of(ToolsType.ELYTRA));
    public static EnchantsType vampirism_I = new EnchantsType("vampirism1","§cVampirism I", List.of("Déposé se livre sur une épée afin de lui appliquer l'enchant","§cVampirism I"),List.of(ToolsType.SWORD));
    public static EnchantsType vampirism_II = new EnchantsType("vampirism2","§cVampirism II", List.of("Déposé se livre sur une épée afin de lui appliquer l'enchant","§cVampirism II"),List.of(ToolsType.SWORD));
    public static EnchantsType boyeusedos_I = new EnchantsType("boyeusedos1","§bBoyeuse d'os I", List.of("Déposé se livre sur une épée afin de lui appliquer l'enchant","§bBoyeuse d'os I"),List.of(ToolsType.SWORD));
    public static EnchantsType antiprojectile_I = new EnchantsType("antiprojectile1","§bAnti Projectile I", List.of("Déposé se livre sur une épée afin de lui appliquer l'enchant","§bAnti Projectile I"),List.of(ToolsType.SWORD));

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
        enchantsList.add(bighole_II);
        enchantsList.add(bighole_III);
        enchantsList.add(autofeed_I);
        enchantsList.add(autofeed_II);
        enchantsList.add(autofeed_III);
        enchantsList.add(stormbreaker_I);
        enchantsList.add(slimeboots_I);
        enchantsList.add(strangulation_I);
        enchantsList.add(push_I);
        //à faire
        enchantsList.add(autofish_I);
        enchantsList.add(veinminer_I);
        enchantsList.add(wither_I);         //fait
        enchantsList.add(wither_II);        //fait
        enchantsList.add(wither_III);       //fait
        enchantsList.add(poison_I);         //fait
        enchantsList.add(poison_II);        //fait
        enchantsList.add(poison_III);       //fait
        enchantsList.add(fly_I);
        enchantsList.add(fly_II);
        enchantsList.add(fly_III);
        enchantsList.add(vampirism_I);
        enchantsList.add(vampirism_II);
        enchantsList.add(boyeusedos_I);
        enchantsList.add(antiprojectile_I); //fait
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
                if (lore.startsWith(enchant.displayname())) {
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
