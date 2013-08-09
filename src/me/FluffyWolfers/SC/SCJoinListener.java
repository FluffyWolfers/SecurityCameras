package me.FluffyWolfers.SC;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class SCJoinListener implements Listener{

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onJoin(PlayerJoinEvent e){
		
		Player p = e.getPlayer();
		String name = p.getName();
		
		File file = new File(SC.s.getDataFolder(), File.separator + "users" + File.separator + name + ".yml");
		YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
		
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdirs();
			Bukkit.getLogger().info(SC.getLogPrefix() + "Created users dir");
		}
		if(!file.exists()){
			try{
				file.createNewFile();
				yaml.save(file);
				Bukkit.getLogger().info(SC.getLogPrefix() + "Created " + name + ".yml");
			}catch(Exception ex){ex.printStackTrace();}
		}
		
	}

}
