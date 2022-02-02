package me.jerryz.coreplugin.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.jerryz.coreplugin.economy.EconomyAPI;
import me.jerryz.coreplugin.utils.MessageType;
import me.jerryz.coreplugin.utils.StringUtils;

public class Money implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] args) {
		Player p = (Player) arg0;
		if(p.isOp()) {
			if(args.length == 0) {
				StringUtils.sendMessage(p, MessageType.INFO, ChatColor.YELLOW + String.valueOf(EconomyAPI.getEconomy(p.getName()).getMoney()) + ChatColor.GREEN + " Reais");
			}else if(args.length == 1) {
				StringUtils.sendMessage(p, MessageType.WARN, "Digite money <set><add><remove><view> <nomeDoJogador> <quantidade> para mais funções");
			}
			if(args.length >= 2) {
				switch (args[0]) {
				
				case "set":
					EconomyAPI.getEconomy(args[1]).setMoney(Double.parseDouble(args[2]));
					StringUtils.sendMessage(p, MessageType.INFO, "Dinheiro de " + ChatColor.YELLOW + args[1] + ChatColor.GREEN + " mudado para " + ChatColor.YELLOW + args[2]);
					break;
				case "add":
					EconomyAPI.addMoney(args[1], Double.parseDouble(args[2]));
					StringUtils.sendMessage(p, MessageType.INFO, "Foi adicionado " + ChatColor.YELLOW + args[2] + ChatColor.GREEN + " à conta de " + ChatColor.YELLOW + args[1]);
					break;
				case "remove":
					if(EconomyAPI.getEconomy(args[1]).getMoney() < Double.parseDouble(args[2])) {
						StringUtils.sendMessage(p, MessageType.ERROR, "O jogador " + ChatColor.YELLOW + args[1] + ChatColor.GREEN + " não possui dinheiro suficiente.");
						break;
					}else {
						EconomyAPI.removeMoney(args[1], Double.parseDouble(args[2]));
						StringUtils.sendMessage(p, MessageType.INFO, "Foi retirado " + ChatColor.YELLOW + args[2] + ChatColor.GREEN + " à conta de " + ChatColor.YELLOW + args[1]);
						break;
					}
				case "view":
					StringUtils.sendMessage(p, MessageType.INFO, ChatColor.YELLOW + String.valueOf(EconomyAPI.getEconomy(args[1]).getMoney()) + ChatColor.GREEN + " Reais");
					break;
				default:
					StringUtils.sendMessage(p, MessageType.WARN, "Digite money <set><add><remove><view> <nomeDoJogador> <quantidade> para mais funções");
					break;
				}
			}
		}else {
			StringUtils.sendMessage(p, MessageType.INFO, ChatColor.YELLOW + String.valueOf(EconomyAPI.getEconomy(p.getName()).getMoney()) + ChatColor.GREEN + " Reais");
		}
		return false;
	}

}
