package me.jerryz.coreplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.jerryz.coreplugin.Core;
import me.jerryz.coreplugin.utils.MessageType;
import me.jerryz.coreplugin.utils.StringUtils;

public class Warn implements CommandExecutor{
	
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player p = (Player) sender;
		if(!p.isOp()) {
			return false;
		}
		if(args.length == 0) {
			StringUtils.sendMessage(p, MessageType.WARN, "Digite /warn <nomeDoJogador> para avisá-lo.");
			return false;
		}
		
		if(args.length == 1) {
			Player pp = Bukkit.getPlayer(args[0]);
			
			if(pp == null) {
				StringUtils.sendMessage(p, MessageType.ERROR, "O jogador " + ChatColor.YELLOW + args[0] + ChatColor.RED + " não foi encontrado.");
				return false;
			}
			try{
			if(!Core.getInstance().warn.containsKey(args[0])) {
				Core.getInstance().warn.put(args[0], 1);
				StringUtils.sendMessage(p, MessageType.INFO, ChatColor.GREEN + args[0] + ChatColor.YELLOW + " foi avisado, na próxima ele será kickado do servidor.");
				StringUtils.sendMessage(pp, MessageType.ERROR, "Você levou uma advertência, na próxima será kickado");
				return false;
			}
			if(Core.getInstance().warn.get(args[0]) == 1) {
				Core.getInstance().warn.remove(args[0]);
				Core.getInstance().warn.put(args[0], 2);
				p.kickPlayer(args[0]);
				StringUtils.sendMessage(p, MessageType.INFO, ChatColor.GREEN + args[0] + ChatColor.YELLOW + " foi kickado, na próxima ele será banido do servidor.");
				return false;
			}
			if(Core.getInstance().warn.get(args[0]) == 2) {
				Core.getInstance().warn.remove(args[0]);
				Core.getInstance().warn.put(args[0], 3);
				pp.setBanned(true);
				StringUtils.sendMessage(p, MessageType.INFO, ChatColor.GREEN + args[0] + ChatColor.YELLOW + " foi banido.");
				return false;
			}
		}catch(Exception e) {
			StringUtils.sendMessage(p, MessageType.ERROR, "O jogador " + ChatColor.YELLOW + args[0] + ChatColor.RED + " não foi encontrado.");
	}
	}
		return false;
	}
	
}
