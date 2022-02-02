package me.jerryz.coreplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.jerryz.coreplugin.utils.MessageType;
import me.jerryz.coreplugin.utils.StringUtils;
import me.jerryz.coreplugin.utils.TitleAPI;

public class SendTitle implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] args) {

		if(args.length > 1) {
			try {
				
				int time = Integer.parseInt(args[0]);
				
				StringBuilder message = new StringBuilder();
				
			    for(int i = 1; i < args.length; i++){
			        message.append(" ").append(args);
			    }
			    
			    for(Player p : Bukkit.getOnlinePlayers()) {
			    	TitleAPI.sendTitle(p, 1, time, 1, message.toString(), "");
			    }
			    
			    StringUtils.sendMessage((Player) arg0, MessageType.INFO, "Título enviado a todos os jogadores.");
				}catch(Exception e) {
					StringUtils.sendMessage((Player) arg0, MessageType.ERROR, "Erro ao enviar. Use /sendtitle <tempo> <mensagem>");
			}
		}else {
			StringUtils.sendMessage((Player) arg0, MessageType.ERROR, "Use /sendtitle <tempo> <mensagem>");
		}
		
		return false;
	}

}
