package me.jerryz.coreplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.jerryz.coreplugin.inventories.AdminInv;

public class Admin implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		Player p = (Player) arg0;
		if(!p.isOp()) {
			return false;
		}
		AdminInv.open(p);
		return false;
	}

}
