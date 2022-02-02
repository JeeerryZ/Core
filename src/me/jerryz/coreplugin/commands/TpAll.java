package me.jerryz.coreplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.jerryz.coreplugin.utils.MessageType;
import me.jerryz.coreplugin.utils.StringUtils;

public class TpAll implements CommandExecutor{

	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {

		Player p = (Player) arg0;
		StringUtils.sendMessage(p, MessageType.INFO, "Teleportando todos os jogadores.");
		for(Player pp : Bukkit.getOnlinePlayers()) {
			pp.teleport(p);
		}
		
		return false;
	}
	
	

	
}
