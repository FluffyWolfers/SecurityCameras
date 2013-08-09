package me.FluffyWolfers.SC;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Bat;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class SCCommand implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player){
			
			Player p = (Player) sender;
			String name = p.getName();
			
			if(args.length == 0){
				
				p.sendMessage(SC.getPreifx() + "Version: " + ChatColor.YELLOW + "v" + SC.pdf.getVersion());
				p.sendMessage(SC.getPreifx() + "Creator: " + ChatColor.YELLOW + "FluffyWolfers");
				p.sendMessage(SC.getPreifx() + "Type /sc help");
				
			}else if(args.length > 0){
				
				String command = args[0];
				
				if(command.equalsIgnoreCase("help")){
					
					p.sendMessage(SC.getPreifx() + "/sc help - Displays help menu");
					p.sendMessage(SC.getPreifx() + "/sc create <name> - Creates a security camera where your head is");
					
				}else if(command.equalsIgnoreCase("create")){
					
					if(!(args.length > 1)){
						p.sendMessage(SC.getPreifx() + ChatColor.DARK_RED + "Error! Syntax: /sc create <name>");
					}else{
						
						String cName = args[1];
						
						String setup = "cameras."+cName;
						
						File file = new File(SC.s.getDataFolder(), File.separator + "users" + File.separator + name + ".yml");
						YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
						
						if(!yaml.isList("cList")){
							ArrayList<String> aList = new ArrayList<String>();
							aList.add(cName);
							yaml.set("cList", aList);
							yaml.set(setup+".enabled", true);
							
							Location loc = p.getEyeLocation();
							String world = loc.getWorld().getName();
							String x = String.valueOf(Math.round(loc.getX()));
							String y = String.valueOf(Math.round(loc.getY()));
							String z = String.valueOf(Math.round(loc.getZ()));
							
							String sep = ":";
							
							yaml.set(setup+".world", world);
							yaml.set(setup+".coords", x+sep+y+sep+z);
							try{
								yaml.save(file);
							}catch(Exception e){e.printStackTrace();}
							
							Bat bat = (Bat) loc.getWorld().spawnEntity(loc, EntityType.BAT);
							bat.setCustomName(cName);
							bat.setCustomNameVisible(false);
							
							BukkitTask task = new SCRun().runTaskTimer(SC.s, 0, 1);
						}else{
							List<String> list = yaml.getStringList("cList");
							if(!list.contains(cName)){
								list.add(cName);
								yaml.set("cList", list);
								yaml.set(setup+".enabled", true);
								
								Location loc = p.getEyeLocation();
								String world = loc.getWorld().getName();
								String x = String.valueOf(Math.round(loc.getX()));
								String y = String.valueOf(Math.round(loc.getY()));
								String z = String.valueOf(Math.round(loc.getZ()));
								
								String sep = ":";
								
								yaml.set(setup+".world", world);
								yaml.set(setup+".coords", x+sep+y+sep+z);
								try{
									yaml.save(file);
								}catch(Exception e){e.printStackTrace();}
								
								Bat bat = (Bat) loc.getWorld().spawnEntity(loc, EntityType.BAT);
								bat.setCustomName(cName);
								bat.setCustomNameVisible(false);
								
								BukkitTask task = new SCRun().runTaskTimer(SC.s, 0, 1);
								
							}else{
								
								p.sendMessage(SC.getPreifx() + ChatColor.DARK_RED + "Error! A camera with that name already exists!");
								
							}
						}
						
					}
					
				}
				
			}
			
		}
		
		return false;
	}

}
