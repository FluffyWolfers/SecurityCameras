package me.FluffyWolfers.SC;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SCCommand implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player){
			
			Player p = (Player) sender;
			
			if(args.length == 0){
				
				p.sendMessage(SC.getPreifx() + "");
				
			}
			
		}
		
		return false;
	}

}
