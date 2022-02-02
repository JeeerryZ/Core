package me.jerryz.coreplugin.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.jerryz.coreplugin.utils.MessageType;
import me.jerryz.coreplugin.utils.StringUtils;

public class SetSpawn implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String label, String[] arg3) {
		Player p = (Player) arg0;
		Location l = p.getLocation();
		if(!p.isOp()) {
			return false;
		}
			p.getWorld().setSpawnLocation((int) l.getX(), (int) l.getY(), (int) l.getZ());
			StringUtils.sendMessage(p, MessageType.INFO, "Spawn configurado com sucesso.");
		return false;
	}

}
