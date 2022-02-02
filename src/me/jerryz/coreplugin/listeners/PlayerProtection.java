package me.jerryz.coreplugin.listeners;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.jerryz.coreplugin.minas.MinaAPI;
import me.jerryz.coreplugin.minas.MinaType;
import me.jerryz.coreplugin.regions.RegionAPI;
import me.jerryz.coreplugin.regions.Region;
import me.jerryz.coreplugin.utils.GlowEnchantment;
import me.jerryz.coreplugin.utils.ItemStackBuilder;
import net.md_5.bungee.api.ChatColor;

public class PlayerProtection implements Listener {
	
	
	
	public void addChave(Player p, int random) {
		ItemStack chave = new ItemStackBuilder(Material.TRIPWIRE_HOOK).setDisplayName(ChatColor.GREEN + "Chave")
				.setLores(ChatColor.GOLD + "Chave necessária para abrir caixas misteriosas.")
				.addEnchantment(new GlowEnchantment(69), 1).finish();
		Random r = new Random();
		int i = r.nextInt(random);
		if (i == 1) {
			p.getInventory().addItem(chave);
			p.sendMessage(ChatColor.GREEN + "Você ganhou uma chave ! use-a para abrir caixas misteriosas !");
			p.playSound(p.getLocation(), Sound.LEVEL_UP, 5, 1);
		}
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		

		if (RegionAPI.getCurrentRegion(e.getPlayer().getName()) != Region.MINALAPIS
				|| RegionAPI.getCurrentRegion(e.getPlayer().getName()) != Region.MINAVIP) {
			if (!e.getPlayer().isOp()) {
				e.setCancelled(true);
			}
		}
		
		
		
		if (RegionAPI.getCurrentRegion(e.getPlayer().getName()) == Region.MINALAPIS
				&& e.getBlock().getType() == Material.LAPIS_ORE) {

			MinaAPI.removeBlockFromMina(MinaType.LAPIS);

			if (MinaAPI.getMina(MinaType.LAPIS).getCurrentBlocks() <= 9850 && !MinaAPI.willReset(MinaType.LAPIS)) {
				MinaAPI.resetMina(MinaType.LAPIS, 60);
				return;
			}

			addChave(e.getPlayer(), 150);
			
		}
		
		
		if (RegionAPI.getCurrentRegion(e.getPlayer().getName()) == Region.MINAVIP
				&& e.getBlock().getType() == Material.GOLD_ORE) {

			MinaAPI.removeBlockFromMina(MinaType.VIP);

			if (MinaAPI.getMina(MinaType.VIP).getCurrentBlocks() <= 9850 && !MinaAPI.willReset(MinaType.VIP)) {

				MinaAPI.resetMina(MinaType.VIP, 60);
				return;
			}
			
			addChave(e.getPlayer(), 100);

		}

	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		if (!e.getPlayer().isOp()) {
			e.setBuild(true);
		}
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if (e.getAction() == Action.PHYSICAL && !e.getPlayer().isOp()) {
			if (e.getClickedBlock().getType() == Material.STONE_BUTTON
					|| e.getClickedBlock().getType() == Material.WOOD_BUTTON
					|| e.getClickedBlock().getType() == Material.LEVER
					|| e.getClickedBlock().getType() == Material.FURNACE
					|| e.getClickedBlock().getType() == Material.BURNING_FURNACE) {
				e.setCancelled(true);
			}
		}
	}

}
