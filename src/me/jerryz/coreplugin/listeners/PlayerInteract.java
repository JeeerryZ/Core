package me.jerryz.coreplugin.listeners;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.jerryz.coreplugin.inventories.WarpsInv;
import me.jerryz.coreplugin.utils.Cooldown;
import me.jerryz.coreplugin.utils.ItemStackBuilder;
import me.jerryz.coreplugin.utils.MessageType;
import me.jerryz.coreplugin.utils.StringUtils;

public class PlayerInteract implements Listener {

	private ArrayList<Player> hiding = new ArrayList<Player>();

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		
		if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (e.getMaterial() == Material.COMPASS && e.getItem().hasItemMeta()) {
				if (e.getItem().getItemMeta().getDisplayName().contains("WARPS")) {
					WarpsInv.open(e.getPlayer());
					e.setCancelled(true);
				}
			}
			if (e.getMaterial() == Material.WATCH && e.getItem().hasItemMeta()) {
				if (e.getItem().getItemMeta().getDisplayName().contains("JOGADORES")) {
					Cooldown c = new Cooldown(p.getUniqueId(), "hs", 2);
					if (Cooldown.isInCooldown(p.getUniqueId(), "hs")) {
						StringUtils.sendMessage(p, MessageType.ERROR, "Espere para usar novamente.");
						e.setCancelled(true);
						return;
					} else {

						if (hiding.contains(p)) {
							hiding.remove(p);
							c.start();
							p.getInventory().remove(3);
							p.getInventory().setItem(3, new ItemStackBuilder(Material.WATCH)
									.setDisplayName(ChatColor.GOLD + "ESCONDER JOGADORES")
									.setLores(ChatColor.GRAY + "Clique para esconder/mostrar jogadores.").finish());
							StringUtils.sendMessage(p, MessageType.INFO, "Mostrando jogadores");

							for (Player pp : Bukkit.getOnlinePlayers()) {
								p.showPlayer(pp);
							}
						} else {
							hiding.add(p);
							c.start();
							p.getInventory().remove(3);
							p.getInventory().setItem(3, new ItemStackBuilder(Material.WATCH)
									.setDisplayName(ChatColor.GOLD + "MOSTRAR JOGADORES")
									.setLores(ChatColor.GRAY + "Clique para esconder/mostrar jogadores.").finish());
							StringUtils.sendMessage(p, MessageType.INFO, "Escondendo jogadores");
							for (Player pp : Bukkit.getOnlinePlayers()) {
								p.hidePlayer(pp);
							}

						}
					}
				}
			}
		}
	}

	public ItemStack setName(ItemStack is, String name) {
		ItemMeta m = is.getItemMeta();
		m.setDisplayName(name);
		is.setItemMeta(m);
		return is;
	}

}
