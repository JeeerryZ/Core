package me.jerryz.coreplugin.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.jerryz.coreplugin.Core;


public class StringUtils {

	public static String preffix = ChatColor.BLACK + "[" + ChatColor.GOLD + "Test" + ChatColor.RED + "e" + ChatColor.BLACK
			+ "] ";
	public static String consolePreffix = ChatColor.RED + "[" + ChatColor.GOLD + "CORE" + ChatColor.RED
			+ "] ";

	public static void log(MessageType type, String msg) {
		switch (type) {

		case ERROR:
			Core.getInstance().getServer().getConsoleSender().sendMessage(preffix + ChatColor.RED + msg);
			break;
		case INFO:
			Core.getInstance().getServer().getConsoleSender().sendMessage(preffix + ChatColor.GREEN + msg);
			break;
		case WARN:
			Core.getInstance().getServer().getConsoleSender().sendMessage(preffix + ChatColor.YELLOW + msg);
			break;
		default:
			break;
		}
	}

	public static void sendMessage(Player p, MessageType type, String msg) {
		switch (type) {

		case ERROR:
			p.sendMessage(preffix + ChatColor.RED + msg);
			break;
		case INFO:
			p.sendMessage(preffix + ChatColor.GREEN + msg);
			break;
		case WARN:
			p.sendMessage(preffix + ChatColor.YELLOW + msg);
			break;
		default:
			break;
		}
	}

	public static String sendHelp(Player p){
		return preffix;
	}
	
}
