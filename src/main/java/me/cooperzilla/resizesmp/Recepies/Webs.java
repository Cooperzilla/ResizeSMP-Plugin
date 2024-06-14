package me.cooperzilla.resizesmp.Recepies;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class Webs {
    public void register() {
        ShapedRecipe webs = new ShapedRecipe(new NamespacedKey("resize", "webs"), new ItemStack(Material.COBWEB));
        webs.shape(" S ", "SSS"," S ");
        webs.setIngredient('S', Material.STRING);

        Bukkit.addRecipe(webs);
    }
}
