package me.jerryz.coreplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.jerryz.coreplugin.inventories.WarpsInv;
import me.jerryz.coreplugin.warps.WarpAPI;
import net.md_5.bungee.api.ChatColor;

public class Warps implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] args) {
		Player p = (Player) arg0;
			if(args.length == 0) {
				WarpsInv.open(p);
			}else {
				if(args[0].equalsIgnoreCase("list")) {
					StringBuilder sb = new StringBuilder(ChatColor.AQUA + "Warps: ");
					for(String key : WarpAPI.warps.keySet()) {
						sb.append(ChatColor.YELLOW + key + ChatColor.WHITE + ", ");
					}
					p.sendMessage(sb.toString());
				}
			}
			return false;
		}
	}
	
