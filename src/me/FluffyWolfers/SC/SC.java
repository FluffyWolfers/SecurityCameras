package me.FluffyWolfers.SC;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class SC extends JavaPlugin{
	
	public static SC s;
	public static PluginDescriptionFile pdf;
	
	public void onEnable(){

		s = this;
		pdf = this.getDescription();
		
		Bukkit.getLogger().info("[SecurityCameras v" + pdf.getVersion() + "] Starting up...");
		
		this.getCommand("securitycamera").setExecutor(new SCCommand());
		this.getCommand("securitycameras").setExecutor(new SCCommand());
		this.getCommand("security").setExecutor(new SCCommand());
		this.getCommand("camera").setExecutor(new SCCommand());
		this.getCommand("cameras").setExecutor(new SCCommand());
		this.getCommand("sc").setExecutor(new SCCommand());
		
		Bukkit.getPluginManager().registerEvents(new SCJoinListener(), this);
		
		this.changeBatNoise();
		
	}
	
	public void changeBatNoise(){
		
		
		
	}
	
	public static String getPreifx(){
		return ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + "SecurityCameras" + ChatColor.DARK_AQUA + "] " + ChatColor.DARK_PURPLE;
	}
	
	public static String getLogPrefix(){
		return "[" + pdf.getName() + " v" + pdf.getVersion() + "]";
	}
	
}
