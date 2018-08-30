package net.pl3x.bukkit.ncp;

import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class NoCreativePhantoms extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        try {
            Class.forName("com.destroystokyo.paper.PaperConfig");
        } catch (ClassNotFoundException e) {
            getLogger().severe("###########################################");
            getLogger().severe("#                                         #");
            getLogger().severe("#          Server is unsupported          #");
            getLogger().severe("#   Please install Paper server v1.13.1   #");
            getLogger().severe("#     Plugin will now disable itself      #");
            getLogger().severe("#                                         #");
            getLogger().severe("###########################################");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        try {
            Class.forName("com.destroystokyo.paper.event.entity.PhantomPreSpawnEvent");
        } catch (ClassNotFoundException e) {
            getLogger().severe("###########################################");
            getLogger().severe("#                                         #");
            getLogger().severe("#           Server is outdated            #");
            getLogger().severe("#  Please update to latest Paper version  #");
            getLogger().severe("#     Plugin will now disable itself      #");
            getLogger().severe("#                                         #");
            getLogger().severe("###########################################");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        getServer().getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onPhantomPreSpawn(com.destroystokyo.paper.event.entity.PhantomPreSpawnEvent event) {
                Entity spawningEntity = event.getSpawningEntity();
                if (spawningEntity instanceof Player && ((Player) spawningEntity).getGameMode() == GameMode.CREATIVE) {
                    event.setCancelled(true);
                    event.setShouldAbortSpawn(true);
                }
            }
        }, this);
    }
}
