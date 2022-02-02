package me.jerryz.coreplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.jerryz.coreplugin.utils.MessageType;
import me.jerryz.coreplugin.utils.StringUtils;
import me.jerryz.coreplugin.warps.WarpAPI;
import net.md_5.bungee.api.ChatColor;

public class SetWarp implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] args) {
		Player p = (Player) arg0;
		if(!p.isOp()) {
			return false;
		}
		if(args.length == 1) {
			WarpAPI.createWarp(args[0], p.getLocation());
			StringUtils.sendMessage(p, MessageType.INFO, "Warp " + ChatColor.YELLOW + args[0] + ChatColor.GREEN + " criado com sucesso.");
			return false;
		}else {
			StringUtils.sendMessage(p, MessageType.WARN, "Digite /warp <nomeDoWarp> para criar um warp.");
			return false;
		}
	}
	
	

}
