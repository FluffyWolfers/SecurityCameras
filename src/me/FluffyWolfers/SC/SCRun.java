package me.FluffyWolfers.SC;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.scheduler.BukkitRunnable;

public class SCRun extends BukkitRunnable{
	
	public void run(){

		for(Entity en : Bukkit.getServer().getWorld("world").getEntities()){
			
			if(en.getType().equals(EntityType.BAT)){
				
				Bat bat = (Bat) en;
				
				File dir = new File(SC.s.getDataFolder(), File.separator + "users");
				
				for(File child : dir.listFiles()){
					
					YamlConfiguration yaml = YamlConfiguration.loadConfiguration(child);
					
					if(yaml.isList("cList")){
						
						for(String str : yaml.getStringList("cList")){
							
							if(yaml.getBoolean("cameras." + str + ".enabled")){
								
								if(bat.getCustomName().equalsIgnoreCase(str)){
									
									World world = Bukkit.getWorld(yaml.getString("cameras."+str+".world"));
									String[] coords = yaml.getString("cameras."+str+".coords").split(":");
									
									int x = Integer.parseInt(coords[0]);
									int y = Integer.parseInt(coords[1]);
									int z = Integer.parseInt(coords[2]);
									
									Location loca = new Location(world, x, y, z);
									
									bat.teleport(loca);
									
								}
								
							}
							
						}
						
					}
					
				}
				
			}
			
		}
		
	}

}
