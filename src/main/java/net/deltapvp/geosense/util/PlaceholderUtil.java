package net.deltapvp.geosense.util;

import io.github.miniplaceholders.api.MiniPlaceholders;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

public class PlaceholderUtil {

    public static TagResolver miniPlaceholders(@Nullable final Player player) {
        if (Bukkit.getPluginManager().isPluginEnabled("MiniPlaceholders")) {
            if (player != null) {
                return MiniPlaceholders.getAudienceGlobalPlaceholders(player);
            }
            return MiniPlaceholders.getGlobalPlaceholders();
        } else {
            return TagResolver.empty();
        }
    }
}
