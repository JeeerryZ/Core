package me.jerryz.coreplugin.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;

public class Limpar implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		Player p = (Player) arg0;
		if(!p.isOp()) {
			return false;
		}
		List<org.bukkit.entity.Entity> entities = p.getWorld().getEntities();
		for (Entity e : entities) {
			if (e instanceof Item) {
				e.remove();
			}
			if (e instanceof Monster) {
				e.remove();
			}
			if (e instanceof Animals) {
				e.remove();
			}
		}
		return false;
	}

}
