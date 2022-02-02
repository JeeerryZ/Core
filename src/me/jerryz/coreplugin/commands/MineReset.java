package me.jerryz.coreplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.jerryz.coreplugin.minas.MinaAPI;
import me.jerryz.coreplugin.minas.MinaType;

public class MineReset implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player p = (Player) sender;
		if(p.isOp()) {
			if(args.length == 1) {
				switch (args[0]) {
				case "lapis":
					MinaAPI.resetMina(MinaType.LAPIS);
				case "vip":
					MinaAPI.resetMina(MinaType.VIP);
				}
				
			}
			if(args.length == 2) {
				switch (args[0]) {
				case "lapis":
					MinaAPI.resetMina(MinaType.LAPIS, Integer.valueOf(args[1]));
				case "vip":
					MinaAPI.resetMina(MinaType.VIP, Integer.valueOf(args[1]));
				}
			}
		}
		return false;
	}
	
	

}
