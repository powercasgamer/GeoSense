package net.deltapvp.geosense.task;

import net.deltapvp.geosense.GeoSense;
import net.deltapvp.geosense.util.PlaceholderUtil;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ClockTask implements Runnable {

    final GeoSense plugin;
    final MiniMessage miniMessage = MiniMessage.miniMessage();

    public ClockTask(final GeoSense plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        for (final Player player : Bukkit.getOnlinePlayers()) {
            if (this.plugin.getConfig().getBoolean("require-permission", false)
                    && !player.hasPermission(this.plugin.getConfig().getString("permission", "geosense.use")))
                continue;
            final ItemStack mainHand = player.getInventory().getItemInMainHand();
            if (mainHand.getType() == Material.CLOCK) {
                final Location location = player.getLocation();
                final World world = location.getWorld();

                player.sendActionBar(this.miniMessage.deserialize(
                        this.plugin.getConfig().getString("formats.clock", "<world>, <time>"),
                        Placeholder.unparsed("world", location.getWorld().getName()),
                        Placeholder.unparsed("time", Long.toString(world.getTime())),
                        PlaceholderUtil.miniPlaceholders(player)
                ));
            }
        }
    }
}
