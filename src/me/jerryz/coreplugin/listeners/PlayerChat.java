package me.jerryz.coreplugin.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;

import me.jerryz.coreplugin.commands.SetItem;
import me.jerryz.coreplugin.utils.GlowEnchantment;
import me.jerryz.coreplugin.utils.ItemStackBuilder;
import net.md_5.bungee.api.ChatColor;

public class PlayerChat implements Listener {

	
	//SET ITEM COMMAND EVENT
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		ItemStackBuilder is = new ItemStackBuilder(e.getPlayer().getItemInHand());

		if (SetItem.editingName.contains(p.getName())) {
			SetItem.editingName.remove(p.getName());
			SetItem.editingLore.add(p.getName());
			is = new ItemStackBuilder(p.getItemInHand())
					.setDisplayName(ChatColor.translateAlternateColorCodes('&', e.getMessage()));
			p.sendMessage(ChatColor.YELLOW + "Digite a descrição do item :");
			e.setCancelled(true);
			return;
		}
		if (SetItem.editingLore.contains(p.getName())) {
			SetItem.editingLore.remove(p.getName());
			SetItem.editingEnchants.add(p.getName());
			String[] msgLore = ChatColor.translateAlternateColorCodes('&', e.getMessage()).split(",");
			is.setLores(msgLore);
			p.sendMessage(ChatColor.YELLOW + "Digite se o item terá efeito de encantamento <sim/nao> :");
			e.setCancelled(true);
			return;
		}
		if (SetItem.editingEnchants.contains(p.getName())) {
			if (e.getMessage().equalsIgnoreCase("sim") || e.getMessage().equalsIgnoreCase("nao")) {
				if(e.getMessage().equalsIgnoreCase("sim")) is.addEnchantment(new GlowEnchantment(69), 1);
				if(e.getMessage().equalsIgnoreCase("nao")) is.removeEnchantments();
				SetItem.editingEnchants.remove(p.getName());
				p.sendMessage(ChatColor.GREEN + "Item finalizado");
				p.setItemInHand(new ItemStack(Material.AIR));
				p.setItemInHand(is.finish());
				e.setCancelled(true);
				return;
			} else {
				p.sendMessage(ChatColor.RED + "Digite apenas sim ou nao");
				e.setCancelled(true);
				return;
			}

		}

		
		e.setFormat(e.getPlayer().getDisplayName() + ChatColor.GRAY + ": " + ChatColor.RESET + e.getMessage());
		
		
		
		
		
		
	}

}
