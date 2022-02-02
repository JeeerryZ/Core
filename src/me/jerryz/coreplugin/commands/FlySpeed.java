package me.jerryz.coreplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.jerryz.coreplugin.utils.MessageType;
import me.jerryz.coreplugin.utils.StringUtils;
import net.md_5.bungee.api.ChatColor;

public class FlySpeed implements CommandExecutor{

	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
			
		Player p = (Player) arg0;
		p.setPlayerListName(ChatColor.RED + p.getDisplayName());
		p.sendMessage(String.valueOf(p.getLocation().getPitch()));
		p.sendMessage(String.valueOf(p.getLocation().getYaw()));
		if(arg3.length == 1) {
			p.setFlySpeed(Float.parseFloat(arg3[0]));
			StringUtils.sendMessage(p, MessageType.INFO, "Velocidade de voo alterada para " + ChatColor.YELLOW + arg3[0]);
		}
		
		return false;
	}
	
	

}
