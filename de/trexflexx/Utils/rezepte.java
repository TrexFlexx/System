package de.trexflexx.Utils;

import de.trexflexx.main.ItemBuilder;
import de.trexflexx.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class rezepte {


    public static void MagmaPickaxe(){

        ItemStack pick = new ItemBuilder(Material.DIAMOND_PICKAXE, 1).setLore(" ").addLoreLine("§6» §5AutoSmelt").addLoreLine("§6» §5AutoCollect").setName("§6§lMagmaPicke").toItemStack();

        ItemMeta pickm = pick.getItemMeta();


        pickm.setUnbreakable(true);

        pick.setItemMeta(pickm);


        ShapedRecipe recipe = new ShapedRecipe(pick);

        recipe.shape("BDB", "ASA", "ASA");
        recipe.setIngredient('A', Material.AIR);
        recipe.setIngredient('B', Material.BEACON);
        recipe.setIngredient('D', Material.DRAGON_EGG);
        recipe.setIngredient('S', Material.STICK);

        Bukkit.addRecipe(recipe);
    }
}
