package me.cooperzilla.resizesmp.Recepies;

import me.cooperzilla.resizesmp.ResizeSMP;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Collections;

public class Revive {
    public void register() {
        ItemStack totem = new ItemStack(Material.TOTEM_OF_UNDYING);
        ItemMeta meta = totem.getItemMeta();

        meta.setDisplayName("Revival Totem");
        meta.setLore(Collections.singletonList("Run /revive <name>"));

        var container = meta.getPersistentDataContainer();
        container.set(
                NamespacedKey.fromString("%s:revive".formatted(ResizeSMP.getPl().getName())),
                PersistentDataType.STRING,
                "Some Value"
        );

        totem.setItemMeta(meta);

        ShapedRecipe revive = new ShapedRecipe(new NamespacedKey("resize", "revive"), totem);
        revive.shape("DND", "NTN","DND");
        revive.setIngredient('T', Material.TOTEM_OF_UNDYING);
        revive.setIngredient('N', Material.NETHERITE_SCRAP);
        revive.setIngredient('D', Material.DIAMOND_BLOCK);

        Bukkit.addRecipe(revive);
    }
}
