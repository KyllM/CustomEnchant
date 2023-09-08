package fr.kyll01.customenchant;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Container;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.*;

public class Events implements Listener {

    @EventHandler
    public void onGuiMove(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack item = event.getCurrentItem();
        if (item == null || item.getType().equals(Material.AIR)) return;
        if (item.getItemMeta() == null) return;
        String itemname = item.getItemMeta().getDisplayName();
        if (player.getOpenInventory().getTitle().equals("§2Liste des enchantements")) {
            event.setCancelled(true);
            if (event.getSlot() <= 53 && event.getSlot() >= 0) {
                Utils.giveBook(player, Utils.getEnchantFromDisplayname(item.getItemMeta().getDisplayName()).name());
            }
        }
    }

    @EventHandler
    public void onBookOnTool(InventoryClickEvent event) {
        ItemStack clickedItem = event.getCurrentItem();
        ItemStack cursorItem = event.getCursor();
        if (clickedItem == null || cursorItem == null) return;
        if (cursorItem.getType() != Material.ENCHANTED_BOOK) return;
        if (!cursorItem.hasItemMeta()) return;
        ItemMeta clickedMeta = clickedItem.getItemMeta();
        ItemMeta cursorMeta = cursorItem.getItemMeta();
        if (clickedMeta == null || cursorMeta == null) return;
        List<String> clickedLore = clickedMeta.getLore();
        String cursorName = cursorMeta.getDisplayName();
        if (clickedLore != null) {

            EnchantsType chaine = Utils.getEnchantFromDisplayname(cursorItem.getItemMeta().getDisplayName());
            System.out.println(cursorItem.getItemMeta().getDisplayName());
            //System.out.println(chaine);
            String chaine2 = chaine.name();

            //System.out.println(chaine2);
            String dernierCaractere = String.valueOf(chaine2.charAt(chaine2.length() - 1));
            //System.out.println(dernierCaractere);
            Integer enchantLevel = Integer.parseInt(dernierCaractere);
            //System.out.println(enchantLevel);

            EnchantsType chaineItem = Utils.getEnchantFromDisplayname(clickedItem.getItemMeta().getDisplayName());
            //System.out.println(cursorItem.toString());
            //System.out.println(chaine);
            String chaine2Item = chaine.name();
            //System.out.println(chaine2);
            String dernierCaractereItem = String.valueOf(chaine2.charAt(chaine2.length() - 1));
            //System.out.println(dernierCaractere);
            Integer enchantLevelItem = Integer.parseInt(dernierCaractere);
            //System.out.println(enchantLevel);


            String ancienEnchant = null;
            String newEnchant = null;
            EnchantsType enchant = null;


            if (enchantLevel == 1) {
                ancienEnchant = chaine.displayname().substring(0, chaine2.length() - 1);
                for (Iterator<String> iterator = clickedLore.iterator(); iterator.hasNext(); ) {
                    String lore2 = iterator.next();
                    if (lore2.startsWith(ancienEnchant)) {
                        EnchantsType enchantAncien = Utils.getEnchantFromDisplayname(lore2);

                        dernierCaractereItem = String.valueOf(enchantAncien.name().charAt(enchantAncien.name().length() - 1));

                        enchantLevelItem = Integer.parseInt(dernierCaractereItem);


                        iterator.remove();
                        enchantLevel = enchantLevelItem + 1;
                        ancienEnchant = chaine2.substring(0, chaine2.length() - 1) + (enchantLevel - 1);

                        enchant = Utils.getEnchantFromName(ancienEnchant);

                        ancienEnchant = chaine2.substring(0, chaine2.length() - 1);
                        newEnchant = ancienEnchant + enchantLevel;
                        enchant = Utils.getEnchantFromName(newEnchant);
                        cursorName = enchant.displayname();
                        if (cursorName == "Null") return;
                    }
                }

                clickedLore.add(cursorName);
                clickedMeta.setLore(clickedLore);
                clickedItem.setItemMeta(clickedMeta);

                cursorItem.setAmount(event.getCursor().getAmount() - 1);
                return;

            } else {
                clickedLore.add(cursorName);
                clickedMeta.setLore(clickedLore);
                clickedItem.setItemMeta(clickedMeta);
            }
        } else {
            clickedMeta.setLore(List.of(cursorName));
            clickedItem.setItemMeta(clickedMeta);
        }
        cursorItem.setAmount(event.getCursor().getAmount() - 1);
    }


    @EventHandler
    public void onNewBlockBreak(BlockBreakEvent event) {
        ItemStack itemInHand = event.getPlayer().getInventory().getItemInMainHand();
        ItemMeta itemMeta = itemInHand.getItemMeta();
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Collection<ItemStack> drops = block.getDrops(player.getInventory().getItemInMainHand());
        if (!itemInHand.hasItemMeta()) return;
        if (itemInHand.getItemMeta() == null) return;
        // Si l'outil a Telepathy I et AutoSmelt I

        if (Utils.hasEnchant(itemInHand, Utils.telepathy_I) && Utils.hasEnchant(itemInHand, Utils.autosmelt_I)) {
            onTelepathyAndAutoSmelt(event);
        }

        // Si l'outil a Telepathy I
        if (Utils.hasEnchant(itemInHand, Utils.telepathy_I) && !Utils.hasEnchant(itemInHand, Utils.autosmelt_I)) {
            if (event.getPlayer().getGameMode() == GameMode.CREATIVE || event.getPlayer().getGameMode() == GameMode.SPECTATOR)
                return;
            onTelepathy(event, drops);
        }

        // Si l'outil a AutoSmelt
        if (!Utils.hasEnchant(itemInHand, Utils.telepathy_I) && Utils.hasEnchant(itemInHand, Utils.autosmelt_I)) {
            if (event.getPlayer().getGameMode() == GameMode.CREATIVE || event.getPlayer().getGameMode() == GameMode.SPECTATOR)
                return;
            onAutoSmelt(event);
        }

        // Si l'outil a AutoSmelt
        if (Utils.hasEnchant(itemInHand, Utils.oxygen_I)) {
            if (event.getPlayer().getGameMode() == GameMode.CREATIVE || event.getPlayer().getGameMode() == GameMode.SPECTATOR)
                return;
            Block checkWater = event.getPlayer().getEyeLocation().getBlock();
            if (checkWater.getType() == Material.WATER) {


                int playerAir = player.getRemainingAir();
                int playerNewAir = playerAir + 30;
                if (playerNewAir >= 300) {
                    playerNewAir = 300;
                }
                player.setRemainingAir(playerNewAir);

            }
        }
        if (Utils.hasEnchant(itemInHand, Utils.blocktrack)) {
            updateBlockTrackData(itemInHand);
        }


        if (Utils.hasEnchant(itemInHand, Utils.bighole_I)) {
            Location centerLocation = block.getLocation();

            if (itemInHand.getType().toString().endsWith("_SHOVEL")) {
                if (!isBreakableBlockShovel(block.getType())) {
                    return;
                }
            }

            if (itemInHand.getType().toString().endsWith("_PICKAXE")) {
                if (!isBreakableBlockPickaxe(block.getType())) {
                    return;
                }
            }

            // Parcourir chaque bloc dans le carré 3x3 autour du bloc cassé
            for (int xOffset = -1; xOffset <= 1; xOffset++) {
                for (int yOffset = -1; yOffset <= 1; yOffset++) {
                    for (int zOffset = -1; zOffset <= 1; zOffset++) {
                        Block relativeBlock = centerLocation.clone().add(xOffset, yOffset, zOffset).getBlock();
                        Collection<ItemStack> relativeBlockDrops = relativeBlock.getDrops(player.getInventory().getItemInMainHand());

                        if (itemInHand.getType().toString().endsWith("_SHOVEL")) {
                            if (isBreakableBlockShovel(relativeBlock.getType())) {
                                if (Utils.hasEnchant(itemInHand, Utils.telepathy_I)) {
                                    if (event.getPlayer().getInventory().firstEmpty() == -1) {
                                        relativeBlock.breakNaturally(player.getInventory().getItemInMainHand());
                                    }

                                    relativeBlock.setType(Material.AIR);
                                    onTelepathy(event, relativeBlockDrops);

                                } else {
                                    relativeBlock.breakNaturally(player.getInventory().getItemInMainHand());
                                }
                            }
                        }
                        if (itemInHand.getType().toString().endsWith("_PICKAXE")) {
                            if (isBreakableBlockPickaxe(relativeBlock.getType())) {

                                // Si l'outil a AutoSmelt
                                if (!Utils.hasEnchant(itemInHand, Utils.telepathy_I) && Utils.hasEnchant(itemInHand, Utils.autosmelt_I)) {
                                    onAutoSmeltBigHole(event, relativeBlock, relativeBlockDrops);
                                } else if (Utils.hasEnchant(itemInHand, Utils.telepathy_I) && Utils.hasEnchant(itemInHand, Utils.autosmelt_I)) {

                                    if (event.getPlayer().getInventory().firstEmpty() == -1) {
                                        onAutoSmeltBigHole(event, relativeBlock, relativeBlockDrops);
                                    } else {
                                        onTelepathyAndAutoSmelt(event);
                                    }
                                } else if (Utils.hasEnchant(itemInHand, Utils.telepathy_I) && !Utils.hasEnchant(itemInHand, Utils.autosmelt_I)) {

                                    if (event.getPlayer().getInventory().firstEmpty() == -1) {
                                        relativeBlock.breakNaturally(player.getInventory().getItemInMainHand());
                                    }

                                    onTelepathy(event, relativeBlockDrops);

                                }
                                if (Utils.hasEnchant(itemInHand, Utils.blocktrack)) {
                                    updateBlockTrackData(itemInHand);
                                }
                                if (!Utils.hasEnchant(itemInHand, Utils.telepathy_I) && !Utils.hasEnchant(itemInHand, Utils.autosmelt_I)) {
                                    relativeBlock.breakNaturally(player.getInventory().getItemInMainHand());
                                }
                                relativeBlock.setType(Material.AIR);
                            }

                        }
                    }
                }
            }


        }
    }

    public boolean isBreakableBlockShovel(Material material) {
        return (material.toString() == "DIRT"
                || material.toString() == "GRAVEL"
                || material.toString() == "SOUL_SAND"
                || material.toString() == "SOUL_SOIL"
                || material.toString() == "GRASS_BLOCK"
                || material.toString() == "MYCELIUM"
                || material.toString() == "PODZOL"
                || material.toString() == "CLAY"
                || material.toString() == "SAND");
    }

    public boolean isBreakableBlockPickaxe(Material material) {
        return (material.toString() == "STONE"
                || material.toString() == "COBBLESTONE"
                || material.toString() == "DEEPSLATE"
                || material.toString() == "GRANITE"
                || material.toString() == "ANDESITE"
                || material.toString() == "DIORITE"
                || material.toString() == "CALCITE"
                || material.toString() == "TUFF"
                || material.toString() == "DRIPSTONE_BLOCK"
                || material.toString() == "PRISMARINE"
                || material.toString() == "MAGMA_BLOCK"
                || material.toString() == "OBSIDIAN"
                || material.toString() == "NETHERRACK"
                || material.toString() == "BASALT"
                || material.toString() == "END_STONE"
                || material.toString() == "STONE_BRICKS"
                || material.toString() == "SANDSTONE"
                || material.toString() == "PRISMARINE_BRICKS"
                || material.toString() == "DARK_PRISMARINE"
                || material.toString() == "SMOOTH_STONE");
    }

    public void onAutoSmeltBigHole(BlockBreakEvent event, Block relativeBlock, Collection<ItemStack> relativeBlockDrops) {

        Player player = event.getPlayer();
        if (event.getBlock().getState() instanceof Container) return;
        if (relativeBlockDrops.isEmpty()) return;
        //event.setDropItems(false);
        Iterator<Recipe> recipes = Bukkit.recipeIterator();
        while (recipes.hasNext()) {
            Recipe recipe = recipes.next();
            if (recipe instanceof FurnaceRecipe) {

                FurnaceRecipe furnacerecipe = (FurnaceRecipe) recipe;

                for (int i = 0; i < relativeBlock.getDrops().size(); i++) {
                    ItemStack drop = relativeBlock.getDrops().iterator().next();
                    if (!isAutoSmeltable(drop.getType())) {
                        relativeBlock.breakNaturally(player.getInventory().getItemInMainHand());
                    }
                    if (furnacerecipe.getInputChoice().test(drop)) {
                        ItemStack newdrop = furnacerecipe.getResult();
                        newdrop.setAmount(drop.getAmount());
                        relativeBlock.getDrops().remove(relativeBlock.getDrops().iterator().next());
                        event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), newdrop);
                    }
                }
            }
        }
    }

    public void onAutoSmelt(BlockBreakEvent event) {
        if (event.getPlayer().getInventory().firstEmpty() == -1) return;
        if (event.getBlock().getState() instanceof Container) return;
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Collection<ItemStack> drops = block.getDrops(player.getInventory().getItemInMainHand());
        if (drops.isEmpty()) return;
        event.setDropItems(false);
        Iterator<Recipe> recipes = Bukkit.recipeIterator();
        while (recipes.hasNext()) {
            Recipe recipe = recipes.next();

            if (recipe instanceof FurnaceRecipe) {

                FurnaceRecipe furnacerecipe = (FurnaceRecipe) recipe;

                for (int i = 0; i < block.getDrops().size(); i++) {
                    ItemStack drop = block.getDrops().iterator().next();
                    if (!isAutoSmeltable(drop.getType())) return;
                    if (furnacerecipe.getInputChoice().test(drop)) {
                        ItemStack newdrop = furnacerecipe.getResult();
                        newdrop.setAmount(drop.getAmount());
                        block.getDrops().remove(block.getDrops().iterator().next());
                        event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), newdrop);
                    }
                }
            }
        }
    }

    public void onTelepathyAndAutoSmelt(BlockBreakEvent event) {

        if (event.getPlayer().getInventory().firstEmpty() == -1) return;
        if (event.getBlock().getState() instanceof Container) return;
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Collection<ItemStack> drops = block.getDrops(player.getInventory().getItemInMainHand());
        if (drops.isEmpty()) return;
        Iterator<Recipe> recipes = Bukkit.recipeIterator();
        event.setDropItems(false);
        ItemStack dropblock = block.getDrops().iterator().next();
        if (!isAutoSmeltable(dropblock.getType())) {
            System.out.println("Auto Smeltable false");
            onTelepathy(event, drops);
        } else {
            while (recipes.hasNext()) {
                Recipe recipe = recipes.next();

                if (recipe instanceof FurnaceRecipe) {

                    FurnaceRecipe furnacerecipe = (FurnaceRecipe) recipe;

                    for (int i = 0; i < block.getDrops().size(); i++) {
                        ItemStack drop = block.getDrops().iterator().next();
                        if (furnacerecipe.getInputChoice().test(drop)) {
                            System.out.println("Auto Smeltable true");
                            ItemStack newdrop = furnacerecipe.getResult();
                            newdrop.setAmount(drop.getAmount());
                            block.getDrops().remove(block.getDrops().iterator().next());
                            if (drops.isEmpty()) return;
                            player.getInventory().addItem(newdrop);
                        }
                    }
                }
            }
        }
    }

    public void onTelepathy(BlockBreakEvent event, Collection<ItemStack> drops) {
        if (event.getPlayer().getInventory().firstEmpty() == -1) return;
        if (event.getBlock().getState() instanceof Container) return;
        event.setDropItems(false);
        Player player = event.getPlayer();
        Block block = event.getBlock();
        if (drops.isEmpty()) return;
        for (ItemStack drop : drops) {
            player.getInventory().addItem(drop);
        }
    }

    public void updateBlockTrackData(ItemStack item) {
        ItemMeta itemMeta = item.getItemMeta();

        assert itemMeta != null;
        if (!itemMeta.hasLore()) {
            itemMeta.setLore(new ArrayList<>());
        }

        List<String> lore = itemMeta.getLore();
        String blockTrackLinePrefix = "§cBlockTrack: ";
        int blockTrackCount = 0;
        for (int i = 0; i < Objects.requireNonNull(lore).size(); i++) {
            String loreLine = lore.get(i);
            if (loreLine.startsWith("§cBlockTrack ")) {
                // Ajoute 1 au compteur de blocs cassés
                blockTrackCount++;
                System.out.println(blockTrackCount);
                // Met à jour la ligne du lore avec le nouveau compteur
                lore.set(i, blockTrackLinePrefix + blockTrackCount);
                itemMeta.setLore(lore);
                item.setItemMeta(itemMeta);

            }
            if (loreLine.startsWith("§cBlockTrack: ")) {
                String blockTrackValue = loreLine.substring(blockTrackLinePrefix.length()).trim();

                System.out.println(blockTrackValue);

                try {
                    blockTrackCount = Integer.parseInt(blockTrackValue);
                    System.out.println(blockTrackCount);
                } catch (NumberFormatException ignored) {
                }

                // Ajoute 1 au compteur de blocs cassés
                blockTrackCount++;
                // Met à jour la ligne du lore avec le nouveau compteur
                lore.set(i, blockTrackLinePrefix + blockTrackCount);
                itemMeta.setLore(lore);
                item.setItemMeta(itemMeta);
            }

        }
    }

    @EventHandler
    public void onPlayerFish(PlayerFishEvent event) {
        if (event.getState() == PlayerFishEvent.State.CAUGHT_FISH) {
            ItemStack fishingRod = event.getPlayer().getInventory().getItemInMainHand();
            if (Utils.hasEnchant(fishingRod, Utils.fishtrack)) {
                // Récupère l'item de l'outil de pêche du joueur

                // Appelle la fonction pour mettre à jour le lore de l'outil de pêche
                updateFishTrackData(fishingRod, event);
            }
        }
    }

    public void updateFishTrackData(ItemStack fishingRod, PlayerFishEvent event) {
        if (event.getCaught() instanceof Item) {
            Item caughtItem = (Item) event.getCaught();
            Material itemMaterial = caughtItem.getItemStack().getType();

            if (itemMaterial == Material.COD || itemMaterial == Material.SALMON || itemMaterial == Material.TROPICAL_FISH) {
                ItemMeta rodMeta = fishingRod.getItemMeta();

                assert rodMeta != null;
                if (!rodMeta.hasLore()) {
                    rodMeta.setLore(new ArrayList<>());
                }

                List<String> lore = rodMeta.getLore();
                String fishTrackLinePrefix = "§cFishTrack : ";

                for (int i = 0; i < Objects.requireNonNull(lore).size(); i++) {
                    String loreLine = lore.get(i);

                    if (loreLine.startsWith(fishTrackLinePrefix)) {
                        String fishTrackValue = loreLine.substring(fishTrackLinePrefix.length()).trim();
                        int fishTrackCount = 0;

                        try {
                            fishTrackCount = Integer.parseInt(fishTrackValue);
                        } catch (NumberFormatException ignored) {
                        }

                        // Ajoute 1 au compteur de poissons pêchés
                        fishTrackCount++;

                        // Met à jour la ligne du lore avec le nouveau compteur
                        lore.set(i, fishTrackLinePrefix + fishTrackCount);
                        rodMeta.setLore(lore);
                        fishingRod.setItemMeta(rodMeta);

                        return;
                    }
                }
            }
        }
    }

    private int calculateWalkedBlocks(int startX, int startZ, int endX, int endZ) {
        int deltaX = Math.abs(endX - startX);
        int deltaZ = Math.abs(endZ - startZ);

        // Utilisez une formule appropriée pour calculer le nombre de blocs parcourus (par exemple, la distance euclidienne)
        // Ici, nous utilisons la distance euclidienne simple en additionnant les deltas des axes x, y et z
        int walkedBlocks = deltaX + deltaZ;
        //System.out.println("Walked blocks: " + walkedBlocks);

        return walkedBlocks;
    }

    public boolean isAutoSmeltable(Material material) {
        return (material.toString() == "RAW_IRON"
                || material.toString() == "RAW_COPPER"
                || material.toString() == "RAW_GOLD"
                || material.toString() == "COBBLESTONE"
                || material.toString() == "COBBLED_DEEPSLATE"
                || material.toString() == "NETHERRACK");
    }


    @EventHandler
    public void onHandChange(PlayerInteractEvent event) {

        final Player player = event.getPlayer();
        ItemStack itemInHand = event.getPlayer().getInventory().getItemInMainHand();
        if (!itemInHand.hasItemMeta()) return;
        if (itemInHand.getItemMeta() == null) return;
        if (Utils.hasEnchant(itemInHand, Utils.haste_I)) {
            PotionEffectType effect = PotionEffectType.FAST_DIGGING;
            player.addPotionEffect(effect.createEffect(200, 1));
        }
        if (Utils.hasEnchant(itemInHand, Utils.haste_II)) {
            PotionEffectType effect = PotionEffectType.FAST_DIGGING;
            player.addPotionEffect(effect.createEffect(200, 2));
        }
        if (Utils.hasEnchant(itemInHand, Utils.haste_III)) {
            PotionEffectType effect = PotionEffectType.FAST_DIGGING;
            player.addPotionEffect(effect.createEffect(200, 3));
        }
        if (Utils.hasEnchant(itemInHand, Utils.haste_IV)) {
            PotionEffectType effect = PotionEffectType.FAST_DIGGING;
            player.addPotionEffect(effect.createEffect(200, 4));
        }
        if (Utils.hasEnchant(itemInHand, Utils.haste_V)) {
            PotionEffectType effect = PotionEffectType.FAST_DIGGING;
            player.addPotionEffect(effect.createEffect(200, 5));
        }
        if (Utils.hasEnchant(itemInHand, Utils.stormbreaker_I)) {
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                Location targetLocation = player.getTargetBlock(null, 100).getLocation();
                targetLocation.getWorld().strikeLightning(targetLocation);
            }
        }
    }


    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        final Player p = e.getPlayer();
        ItemStack itemBoots = e.getPlayer().getInventory().getBoots();
        ItemStack itemLeggings = e.getPlayer().getInventory().getLeggings();
        ItemStack itemHelmet = e.getPlayer().getInventory().getHelmet();
        if (itemLeggings != null) {
            if (p != null) {
                if (itemLeggings == null) return;
                if (itemLeggings.hasItemMeta() && !(itemLeggings.getItemMeta() == null)) {
                    if (Utils.hasEnchant(itemLeggings, Utils.dolphin_I)) {
                        PotionEffectType effect = PotionEffectType.DOLPHINS_GRACE;
                        p.addPotionEffect(effect.createEffect(200, 0));
                    }
                    if (Utils.hasEnchant(itemLeggings, Utils.dolphin_II)) {
                        PotionEffectType effect = PotionEffectType.DOLPHINS_GRACE;
                        p.addPotionEffect(effect.createEffect(200, 1));
                    }
                    if (Utils.hasEnchant(itemLeggings, Utils.dolphin_III)) {
                        PotionEffectType effect = PotionEffectType.DOLPHINS_GRACE;
                        p.addPotionEffect(effect.createEffect(200, 2));
                    }
                }
            }
        }
        if (itemHelmet != null) {
            if (p != null) {
                if (itemHelmet == null) return;
                if (itemHelmet.hasItemMeta() && !(itemHelmet.getItemMeta() == null)) {
                    if (Utils.hasEnchant(itemHelmet, Utils.autofeed_I)) {
                        PotionEffectType effect = PotionEffectType.SATURATION;
                        p.addPotionEffect(effect.createEffect(200, 0));
                    }
                    if (Utils.hasEnchant(itemHelmet, Utils.autofeed_II)) {
                        PotionEffectType effect = PotionEffectType.SATURATION;
                        p.addPotionEffect(effect.createEffect(200, 1));
                    }
                    if (Utils.hasEnchant(itemHelmet, Utils.autofeed_III)) {
                        PotionEffectType effect = PotionEffectType.SATURATION;
                        p.addPotionEffect(effect.createEffect(200, 2));
                    }
                }
            }
        }
        if (itemBoots != null) {
            if (p != null) {
                if (itemBoots == null) return;
                if (itemBoots.hasItemMeta() && !(itemBoots.getItemMeta() == null)) {
                    if (Utils.hasEnchant(itemBoots, Utils.speed_I)) {
                        PotionEffectType effect = PotionEffectType.SPEED;
                        p.addPotionEffect(effect.createEffect(200, 1));
                    }
                    if (Utils.hasEnchant(itemBoots, Utils.speed_II)) {
                        PotionEffectType effect = PotionEffectType.SPEED;
                        p.addPotionEffect(effect.createEffect(200, 2));
                    }
                    if (Utils.hasEnchant(itemBoots, Utils.speed_III)) {
                        PotionEffectType effect = PotionEffectType.SPEED;
                        p.addPotionEffect(effect.createEffect(200, 3));
                    }
                    if (Utils.hasEnchant(itemBoots, Utils.speed_IV)) {
                        PotionEffectType effect = PotionEffectType.SPEED;
                        p.addPotionEffect(effect.createEffect(200, 4));
                    }
                    if (Utils.hasEnchant(itemBoots, Utils.speed_V)) {
                        PotionEffectType effect = PotionEffectType.SPEED;
                        p.addPotionEffect(effect.createEffect(200, 5));
                    }

                    // Vérifie si les données des blocs parcourus existent déjà dans les données de l'item
                    ItemMeta bootsMeta = itemBoots.getItemMeta();
                    if (bootsMeta.hasLore()) {
                        List<String> lore = bootsMeta.getLore();
                        String walkedBlocksLinePrefix = "§cMoveTrack: ";
                        String walkedBlocksValue = "";
                        for (int i = 0; i < lore.size(); i++) {
                            String loreLine = lore.get(i);

                            if (loreLine.startsWith(walkedBlocksLinePrefix) || loreLine.startsWith("§cMoveTrack")) {
                                // Met à jour la ligne du lore avec le nouveau nombre de blocs parcourus
                                int walkedBlocks;
                                if (loreLine.equals("§cMoveTrack ")) {
                                    walkedBlocksValue = "";
                                } else {
                                    walkedBlocksValue = loreLine.substring(walkedBlocksLinePrefix.length()).trim();
                                }
                                if (!walkedBlocksValue.isEmpty()) {
                                    walkedBlocks = Integer.parseInt(walkedBlocksValue);
                                } else {
                                    walkedBlocks = 0; // Valeur par défaut si la chaîne est vide
                                }
                                walkedBlocks = walkedBlocks + calculateWalkedBlocks(
                                        e.getFrom().getBlockX(), e.getFrom().getBlockZ(),
                                        e.getTo().getBlockX(), e.getTo().getBlockZ()
                                );
                                lore.set(i, walkedBlocksLinePrefix + walkedBlocks);

                                bootsMeta.setLore(lore);
                                itemBoots.setItemMeta(bootsMeta);
                                // Après avoir modifié le lore de l'item
                                //System.out.println(walkedBlocks);
                            }
                        }
                    }
                    if (Utils.hasEnchant(itemBoots, Utils.slimeboots_I)) {
                        System.out.println("aaa");
                        if (p.isOnGround()) {
                            System.out.println(p.getFallDistance());
                            if (p.getFallDistance() > 0) {
                                if(p.getFallDistance() == 1){
                                    reboundStrength = 5.0;
                                }
                                System.out.println("hhh");
                                e.setCancelled(true); // Annule la chute pour éviter les dégâts de chute
                                System.out.println("ggg");
                                System.out.println(p.getFallDistance());
                                System.out.println("bbb");
                                e.setCancelled(true); // Annule la chute pour éviter les dégâts de chute
                                System.out.println("ccc");
                                Vector playerVelocity = p.getVelocity();
                                System.out.println("ddd");
                                playerVelocity.setY(p.getFallDistance() * reboundStrength);
                                System.out.println("eee");
                                p.setVelocity(playerVelocity);
                                System.out.println("fff");
                                reboundStrength *= 0.8; // Diminue la force de rebond à chaque chute
                            }

                        }
                    }
                }
            }
        }
    }


    private double reboundStrength = 1.0; // Force de rebond initial

    @EventHandler
    public void onEntityDamageByPlayer(EntityDamageByEntityEvent event) {
        if (event.getCause() == EntityDamageEvent.DamageCause.LIGHTNING && event.getEntity() instanceof Player) {
            event.setCancelled(true);
        }
        if (event.getDamager() instanceof Player && event.getEntity() instanceof LivingEntity) {
            Player player = (Player) event.getDamager();
            LivingEntity entity = (LivingEntity) event.getEntity();
            ItemStack itemInHand = player.getInventory().getItemInMainHand();
            if (Utils.hasEnchant(itemInHand, Utils.stormbreaker_I)) {
                summonLightning(entity);
            }
        }
    }

    public void summonLightning(Entity entity) {
        Location entityLocation = entity.getLocation();
        entity.getWorld().strikeLightning(entityLocation);
    }

    @EventHandler
    public void onPlayerFall(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            ItemStack itemBoots = player.getInventory().getBoots();
            if (event.getCause() == EntityDamageEvent.DamageCause.FALL && Utils.hasEnchant(itemBoots, Utils.slimeboots_I)) {
                event.setCancelled(true);
            }
        }
    }

    public boolean isOnGround(Location loc) {
        if (loc.clone().subtract(0, 1, 0).getBlock().getType() == Material.AIR) return false;
        if (Math.floor(loc.getY()) != loc.getY()) return false;
        return true;
    }



}

