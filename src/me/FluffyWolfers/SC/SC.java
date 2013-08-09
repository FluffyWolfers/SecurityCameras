package me.FluffyWolfers.SC;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SC extends JavaPlugin{
	
	public static SC s;
	
	public void onEnable(){
		
		Bukkit.getLogger().info("[SecurityCameras] Starting up...");
		
		s = this;
		
	}
	
}
