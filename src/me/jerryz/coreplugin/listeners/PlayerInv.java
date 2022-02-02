package me.jerryz.coreplugin.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerInv implements Listener{
	
	@EventHandler
	public void onPlayerDrop(PlayerDropItemEvent e) {
		if(e.getPlayer().isOp()) {
			return;
		}
		if(e.getItemDrop().getItemStack().getType() == Material.GOLDEN_APPLE) e.setCancelled(true);
		if(e.getItemDrop().getItemStack().getType() == Material.WATCH) e.setCancelled(true);
	}

	@EventHandler
	public void onPlayerClick(InventoryClickEvent e) {
		Player p = null;
		if(e.getWhoClicked() instanceof Player) {
			p = (Player) e.getWhoClicked();
		}
		if(!p.isOp()) {
			e.setCancelled(true);
		}
	}
	
}
