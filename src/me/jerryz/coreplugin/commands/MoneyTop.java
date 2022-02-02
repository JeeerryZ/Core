package me.jerryz.coreplugin.commands;

import java.util.ArrayList;
import java.util.Collections;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.jerryz.coreplugin.economy.Economy;
import me.jerryz.coreplugin.economy.EconomyAPI;
import me.jerryz.coreplugin.utils.MessageType;
import me.jerryz.coreplugin.utils.StringUtils;

public class MoneyTop implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player p = (Player) sender;
		if(!p.isOp()) {
			return false;
		}
		StringUtils.sendMessage(p, MessageType.INFO, "Mostrando os top 3 com mais dinheiro:");
		ArrayList<Double> top = new ArrayList<Double>();
		for(Economy e : EconomyAPI.contas) {
			top.add(e.getMoney());
		}
		Collections.sort(top);
		Collections.reverse(top);
		int i = 0;
			for(Economy e : EconomyAPI.contas) {
				if(i==3) {
					i=0;
					break;
				}else {
					if(top.get(i) == e.getMoney()) {
						p.sendMessage(ChatColor.YELLOW + e.getPlayer() + ChatColor.RESET + " : " + ChatColor.GREEN + e.getMoney());
						i++;
					}
				}
			}	
		return false;
	}
	
	
}
