package net.deltapvp.geosense;

import net.deltapvp.geosense.task.ClockTask;
import net.deltapvp.geosense.task.CompassTask;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class GeoSense extends JavaPlugin {

    @Override
    public void onLoad() {
        getComponentLogger().info(MiniMessage.miniMessage().deserialize("""
                <rainbow>

                ░██████╗░███████╗░█████╗░░██████╗███████╗███╗░░██╗░██████╗███████╗
                ██╔════╝░██╔════╝██╔══██╗██╔════╝██╔════╝████╗░██║██╔════╝██╔════╝
                ██║░░██╗░█████╗░░██║░░██║╚█████╗░█████╗░░██╔██╗██║╚█████╗░█████╗░░
                ██║░░╚██╗██╔══╝░░██║░░██║░╚═══██╗██╔══╝░░██║╚████║░╚═══██╗██╔══╝░░
                ╚██████╔╝███████╗╚█████╔╝██████╔╝███████╗██║░╚███║██████╔╝███████╗
                ░╚═════╝░╚══════╝░╚════╝░╚═════╝░╚══════╝╚═╝░░╚══╝╚═════╝░╚══════╝

                ██████╗░██╗░░░██╗
                ██╔══██╗╚██╗░██╔╝
                ██████╦╝░╚████╔╝░
                ██╔══██╗░░╚██╔╝░░
                ██████╦╝░░░██║░░░
                ╚═════╝░░░░╚═╝░░░

                ██████╗░░█████╗░░██╗░░░░░░░██╗███████╗██████╗░░█████╗░░█████╗░░██████╗░██████╗░░█████╗░███╗░░░███╗███████╗██████╗░
                ██╔══██╗██╔══██╗░██║░░██╗░░██║██╔════╝██╔══██╗██╔══██╗██╔══██╗██╔════╝██╔════╝░██╔══██╗████╗░████║██╔════╝██╔══██╗
                ██████╔╝██║░░██║░╚██╗████╗██╔╝█████╗░░██████╔╝██║░░╚═╝███████║╚█████╗░██║░░██╗░███████║██╔████╔██║█████╗░░██████╔╝
                ██╔═══╝░██║░░██║░░████╔═████║░██╔══╝░░██╔══██╗██║░░██╗██╔══██║░╚═══██╗██║░░╚██╗██╔══██║██║╚██╔╝██║██╔══╝░░██╔══██╗
                ██║░░░░░╚█████╔╝░░╚██╔╝░╚██╔╝░███████╗██║░░██║╚█████╔╝██║░░██║██████╔╝╚██████╔╝██║░░██║██║░╚═╝░██║███████╗██║░░██║
                ╚═╝░░░░░░╚════╝░░░░╚═╝░░░╚═╝░░╚══════╝╚═╝░░╚═╝░╚════╝░╚═╝░░╚═╝╚═════╝░░╚═════╝░╚═╝░░╚═╝╚═╝░░░░░╚═╝╚══════╝╚═╝░░╚═╝

                ██╗░░░██╗███████╗██████╗░░██████╗██╗░█████╗░███╗░░██╗
                ██║░░░██║██╔════╝██╔══██╗██╔════╝██║██╔══██╗████╗░██║
                ╚██╗░██╔╝█████╗░░██████╔╝╚█████╗░██║██║░░██║██╔██╗██║
                ░╚████╔╝░██╔══╝░░██╔══██╗░╚═══██╗██║██║░░██║██║╚████║
                ░░╚██╔╝░░███████╗██║░░██║██████╔╝██║╚█████╔╝██║░╚███║
                ░░░╚═╝░░░╚══════╝╚═╝░░╚═╝╚═════╝░╚═╝░╚════╝░╚═╝░░╚══╝

                ░█████╗░░░░░█████╗░░░░██████╗░
                ██╔══██╗░░░██╔══██╗░░░╚════██╗
                ██║░░██║░░░██║░░██║░░░░░███╔═╝
                ██║░░██║░░░██║░░██║░░░██╔══╝░░
                ╚█████╔╝██╗╚█████╔╝██╗███████╗
                ░╚════╝░╚═╝░╚════╝░╚═╝╚══════╝

                """));
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        new Metrics(this, 18865);
        Bukkit.getCommandMap().register("geosense", new Command("geosense") {
            @Override
            public boolean execute(@NotNull final CommandSender sender, @NotNull final String commandLabel, @NotNull final String[] args) {
                if (!sender.hasPermission("geosense.admin")) return false;
                sender.sendRichMessage("<rainbow>Reloaded config!");
                GeoSense.this.reloadConfig();
                return false;
            }
        });
        Bukkit.getScheduler().runTaskTimerAsynchronously(this,
                new CompassTask(this),
                0L,
                1L
        );
        Bukkit.getScheduler().runTaskTimerAsynchronously(this,
                new ClockTask(this),
                0L,
                1L
        );
    }

    @Override
    public void onDisable() {
        getComponentLogger().info(MiniMessage.miniMessage().deserialize("<rainbow>GeoSense is disabled =("));
    }
}
