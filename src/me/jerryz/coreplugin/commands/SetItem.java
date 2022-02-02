package me.jerryz.coreplugin.commands;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class SetItem implements CommandExecutor{

	public static ArrayList<String> editingName = new ArrayList<String>();
	public static ArrayList<String> editingLore = new ArrayList<String>();
	public static ArrayList<String> editingEnchants = new ArrayList<String>();
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
	
		
		if(sender instanceof ConsoleCommandSender) {
			sender.sendMessage("Somente jogadores podem executar esse comando");
			return false;
		}
		
		Player p = (Player) sender;
		
		if(!p.isOp()) {
			return false;
		}
		
		if(p.getItemInHand().getType() == Material.AIR ) {
			p.sendMessage(ChatColor.RED + "Você precisa segurar um item na mão para executar esse comando");
			return false;
		}
		
		p.sendMessage(ChatColor.YELLOW + "Digite o nome do item :");
		editingName.add(p.getName());

		
		return false;
	}
	

	
	
	}
