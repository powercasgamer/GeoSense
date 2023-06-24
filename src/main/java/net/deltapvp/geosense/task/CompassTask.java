package net.deltapvp.geosense.task;

import net.deltapvp.geosense.GeoSense;
import net.deltapvp.geosense.util.PlaceholderUtil;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CompassTask implements Runnable {

    final GeoSense plugin;
    final MiniMessage miniMessage = MiniMessage.miniMessage();

    public CompassTask(final GeoSense plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        for (final Player player : Bukkit.getOnlinePlayers()) {
            if (this.plugin.getConfig().getBoolean("require-permission", false)
                    && !player.hasPermission(this.plugin.getConfig().getString("permission", "geosense.use")))
                continue;
            final ItemStack mainHand = player.getInventory().getItemInMainHand();
            if (mainHand.getType() == Material.COMPASS) {
                final Location location = player.getLocation();
                final int blockX = location.getBlockX();
                final int blockY = location.getBlockY();
                final int blockZ = location.getBlockZ();
                final float yaw = location.getYaw();
                final float pitch = location.getPitch();

                player.sendActionBar(this.miniMessage.deserialize(
                        this.plugin.getConfig().getString("formats.compass", "<x>, <y>, <z>"),
                        Placeholder.unparsed("x", Integer.toString(blockX)),
                        Placeholder.unparsed("y", Integer.toString(blockY)),
                        Placeholder.unparsed("z", Integer.toString(blockZ)),
                        Placeholder.unparsed("yaw", Float.toString(yaw)),
                        Placeholder.unparsed("pitch", Float.toString(pitch)),
                        Placeholder.unparsed("world", location.getWorld().getName()),
                        PlaceholderUtil.miniPlaceholders(player)
                ));
            }
        }
    }
}
