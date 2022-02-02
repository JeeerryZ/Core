package me.jerryz.coreplugin.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.jerryz.coreplugin.regions.RegionAPI;
import me.jerryz.coreplugin.regions.Region;

public class PlayerMove implements Listener {

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		if (e.getTo().subtract(0, 2, 0).getBlock().getType() == Material.EMERALD_BLOCK
				&& e.getTo().getBlockX() == 457) {
			Location l1 = new Location(e.getPlayer().getWorld(), 463, 1, 4, -90, 0);
			RegionAPI.setRegion(Region.MINAVIP, e.getPlayer().getName());
			e.getPlayer().sendMessage(String.valueOf(RegionAPI.getCurrentRegion(e.getPlayer().getName())));
			e.getPlayer().teleport(l1);
			e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENDERMAN_TELEPORT, 5, 1);
			return;
		}
		if (e.getTo().subtract(0, 0, 0).getBlock().getType() == Material.EMERALD_BLOCK
				&& e.getTo().getBlockX() == 460) {
			Location l1 = new Location(e.getPlayer().getWorld(), 455, 1, 5, 90, 0);
			RegionAPI.setRegion(Region.MINALAPIS, e.getPlayer().getName());
			e.getPlayer().sendMessage(String.valueOf(RegionAPI.getCurrentRegion(e.getPlayer().getName())));
			e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENDERMAN_TELEPORT, 5, 1);
			e.getPlayer().teleport(l1);
			return;
		}
	}

}
