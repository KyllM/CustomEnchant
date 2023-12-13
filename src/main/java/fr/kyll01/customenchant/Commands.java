package fr.kyll01.customenchant;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class Commands implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        int DataTrack;
        if (sender instanceof Player p) {
            if (label.equalsIgnoreCase("celist")) {

                Inventory listMenu = Bukkit.createInventory(null, 54, "§2Liste des enchantements");
                int i = 0;
                for (EnchantsType enchant : Utils.getEnchantsList()) {
                    ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
                    ItemMeta itemMeta = item.getItemMeta();
                    assert itemMeta != null;
                    itemMeta.setDisplayName(enchant.displayname());
                    itemMeta.setLore(enchant.lore());
                    item.setItemMeta(itemMeta);
                    listMenu.setItem(i, item);
                    i++;
                }
                p.openInventory(listMenu);

            }
            if (label.equalsIgnoreCase("cegive")) {
                if (args.length == 2) {
                    String nomLivre = args[1];
                    Utils.giveBook(p, nomLivre);
                }

            }
            else if (label.equalsIgnoreCase("ce")) {
                if (args.length == 2) {
                    String value = args[1];
                    switch (args[0].toLowerCase()) {
                        case "give":
                            Utils.giveBook(p, value);
                            break;
                        case "remove":
                            if (Utils.getEnchantsList().contains(Utils.getEnchantFromName(value))) {
                                EnchantsType enchant = Utils.getEnchantFromName(value);
                                ItemStack item = p.getInventory().getItemInMainHand();
                                if (item.getItemMeta() != null && item.getItemMeta().hasLore()) {
                                    ItemMeta itemMeta = item.getItemMeta();
                                    List<String> loreList = item.getItemMeta().getLore();
                                    if (loreList == null || loreList.isEmpty()) return false;
                                    loreList.removeIf(line -> line.contains(enchant.displayname()));
                                    itemMeta.setLore(loreList);
                                    item.setItemMeta(itemMeta);
                                }
                            }
                        case "setblocktrack":
                            DataTrack = 0;
                            if (sender != null) {
                                ItemStack item = p.getInventory().getItemInMainHand();
                                ItemMeta itemMeta = item.getItemMeta();
                                assert itemMeta != null;
                                List<String> lore = itemMeta.getLore(); // Obtient la liste du lore
                                DataTrack = Integer.parseInt(args[1]);
                                if (lore != null) {
                                    String walkedBlocksLinePrefix = "§cBlockTrack:";
                                    for (int i = 0; i < lore.size(); i++) {
                                        String loreLine = lore.get(i);
                                        // Crée un itérateur pour parcourir la liste
                                        // Supprime l'élément contenant le mot "telepathy"
                                        if (loreLine.startsWith(walkedBlocksLinePrefix)) {
                                            String walkedBlocksValue = loreLine.replace(walkedBlocksLinePrefix, "");
                                            lore.set(i, walkedBlocksLinePrefix + DataTrack);
                                            itemMeta.setLore(lore); // Met à jour le lore de l'item avec la liste modifiée
                                            item.setItemMeta(itemMeta); // Met à jour les métadonnées de l'item
                                            //System.out.println(walkedBlocks);
                                        }
                                    }
                                }
                            }

                                case "setfishtrack":
                                    break;
                                case "setmovetrack":

                                    if (sender != null) {
                                        ItemStack item = p.getInventory().getItemInMainHand();
                                        ItemMeta itemMeta = item.getItemMeta();
                                        assert itemMeta != null;
                                        List<String> lore = itemMeta.getLore(); // Obtient la liste du lore
                                        DataTrack = Integer.parseInt(args[2]);
                                        if (lore != null) {
                                            String walkedBlocksLinePrefix = "§cMoveTrack : ";
                                            for (int i = 0; i < lore.size(); i++) {
                                                String loreLine = lore.get(i);
                                                // Crée un itérateur pour parcourir la liste
                                                // Supprime l'élément contenant le mot "telepathy"
                                                if (loreLine.startsWith(walkedBlocksLinePrefix)) {
                                                    String walkedBlocksValue = loreLine.replace(walkedBlocksLinePrefix, "");
                                                    lore.set(i, walkedBlocksLinePrefix + DataTrack);
                                                    itemMeta.setLore(lore); // Met à jour le lore de l'item avec la liste modifiée
                                                    item.setItemMeta(itemMeta); // Met à jour les métadonnées de l'item
                                                    //System.out.println(walkedBlocks);
                                                }
                                            }
                                        }
                                    }

                                default:
                                    helpMsg(p);

                            }
                    } else{
                        p.sendMessage("Message help");
                    }
                }
            }
            /*Player player = (Player) sender;
            if (label.equalsIgnoreCase("cegui")) {
                String testGive = args[0];
                String testEnchantName = args[1];

                if (testEnchantName.equals("fishtrack")) {
                    if (sender != null) {
                        ItemStack item = player.getInventory().getItemInMainHand();
                        ItemMeta itemMeta = item.getItemMeta();
                        assert itemMeta != null;
                        List<String> lore = itemMeta.getLore(); // Obtient la liste du lore
                        DataTrack = Integer.parseInt(args[2]);
                        if (lore != null) {
                            String walkedBlocksLinePrefix = "\u00A7cFishTrack : ";
                            for (int i = 0; i < lore.size(); i++) {
                                String loreLine = lore.get(i);
                                // Crée un itérateur pour parcourir la liste
                                // Supprime l'élément contenant le mot "telepathy"
                                if (loreLine.startsWith(walkedBlocksLinePrefix)) {
                                    String walkedBlocksValue = loreLine.replace(walkedBlocksLinePrefix, "");
                                    lore.set(i, walkedBlocksLinePrefix + DataTrack);
                                    itemMeta.setLore(lore); // Met à jour le lore de l'item avec la liste modifiée
                                    item.setItemMeta(itemMeta); // Met à jour les métadonnées de l'item
                                    //System.out.println(walkedBlocks);
                                }
                            }
                        }
                    }
                }
            }
            if (testEnchantName.equals("movetrack")) {
            }*/
        return false;
    }


    public void helpMsg(Player p) {
        p.sendMessage("Commandes:");
        p.sendMessage("§e/ce give §7| xxx");
        p.sendMessage("§e/ce remove §7| xxx");
        p.sendMessage("§e/ce setblocktrack §7| xxx");
        p.sendMessage("§e/ce setfishtrack §7| xxx");
        p.sendMessage("§e/ce setmovetrack §7| xxx");
    }

}
