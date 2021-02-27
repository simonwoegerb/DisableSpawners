package com.auster.disablespawners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.SpawnerSpawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;

public final class DisableSpawners extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this,this);
        if (getConfig().getStringList("worlds").isEmpty()) {
            getConfig().set("worlds", Collections.singletonList("world"));
        }
        saveConfig();
    }
    @EventHandler
    public void onSpawn(SpawnerSpawnEvent spawnerSpawnEvent) {
        reloadConfig();
        if (getConfig().getStringList("worlds").contains(spawnerSpawnEvent.getLocation().getWorld().getName())) {
            spawnerSpawnEvent.setCancelled(true);
        }
    }
}
