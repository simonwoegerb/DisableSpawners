package com.auster.disablespawners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.SpawnerSpawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.sql.BatchUpdateException;
import java.util.ArrayList;
import java.util.Arrays;

public final class DisableSpawners extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this,this);
        if (getConfig().getStringList("worlds").isEmpty()) {
            getConfig().set("worlds", Arrays.asList("world"));
        }
        saveConfig();
    }



    @EventHandler
    public void onSpawn(SpawnerSpawnEvent spawnerSpawnEvent) {
        reloadConfig();
        getConfig().getStringList("worlds").forEach(Bukkit::broadcastMessage);
        if (getConfig().getStringList("worlds").contains(spawnerSpawnEvent.getLocation().getWorld().getName())) {
            spawnerSpawnEvent.setCancelled(true);

        }

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("disablespawnerreload")) {
            if (sender.isOp()) {
                sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.RED + "DS" + ChatColor.GOLD + "]"+  ChatColor.RESET + " Reloaded");

            } else if (sender.hasPermission("disablespawners.reload")) {
                sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.RED + "DS" + ChatColor.GOLD + "]"+  ChatColor.RESET + " Reloaded");

            } else sender.sendMessage(ChatColor.RED + "You don't have permission to execute this command!");
        }

        return true;
    }
}
