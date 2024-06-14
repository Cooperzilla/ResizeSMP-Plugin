package me.cooperzilla.resizesmp.Utils;

import me.cooperzilla.resizesmp.ResizeSMP;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.Date;
import java.util.UUID;

public class Utils {
    public static void decrementLives(Player p) {
        p.setMetadata("lives", new FixedMetadataValue(ResizeSMP.getPl(), p.getMetadata("lives").get(0).asInt() - 1));
    }

    public static void incrementLives(Player p) {
        p.setMetadata("lives", new FixedMetadataValue(ResizeSMP.getPl(), p.getMetadata("lives").get(0).asInt() + 1));
    }

    public static int getLives(Player p) {
        return p.getMetadata("lives").get(0).asInt();
    }

    public static double getLivesDouble(Player p) {
        return p.getMetadata("lives").get(0).asDouble();
    }

    public static void Ban(Player p) {
        p.setMetadata("lives", new FixedMetadataValue(ResizeSMP.getPl(), 0));
        updateNbt(p);
        Bukkit.getBanList(BanList.Type.NAME).addBan(p.getName(), "Death Banned", (Date) null, ResizeSMP.getPl().getName());
        p.kickPlayer("Death Banned");
    }

    public static void updateNbt(Player p) {
        AttributeInstance attr = p.getAttribute(Attribute.GENERIC_SCALE);

        attr.getModifiers().forEach(attr::removeModifier);

        attr.addModifier(new AttributeModifier(
                UUID.fromString("9ea69eae-23ab-4198-b54d-5dd8f419ae5b"),
                "size",
                getLivesDouble(p) * -0.25,
                AttributeModifier.Operation.ADD_NUMBER
        ));
    }
}
