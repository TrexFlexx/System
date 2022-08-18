package de.trexflexx.listener;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class AutoSmelt implements Listener {

    @EventHandler
    public void onBreak(BlockDropItemEvent e) {
        if (e.getPlayer().getInventory().getItemInMainHand().getItemMeta() != null && e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLore() != null &&
                e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLore().contains("§6» §5AutoSmelt")) {

            Iterator<Recipe> recipes = Bukkit.recipeIterator();

            while (recipes.hasNext()) {
                Recipe recipe = recipes.next();

                if (recipe instanceof FurnaceRecipe) {
                    FurnaceRecipe furnaceRecipe = (FurnaceRecipe) recipe;

                    for (int i = 0; i < e.getItems().size(); i++) {
                        ItemStack drop = e.getItems().get(i).getItemStack();

                        if (drop.getType() != Material.GOLD_ORE && drop.getType() != Material.IRON_ORE)
                            return;

                        if (furnaceRecipe.getInputChoice().test(drop)) {
                            ItemStack newdrop = furnaceRecipe.getResult();
                            newdrop.setAmount(drop.getAmount());
                            e.getItems().remove(e.getItems().get(i));
                            e.getPlayer().getInventory().addItem(newdrop);
                        }
                    }
                }
            }
        }
    }
}
