package me.jerryz.coreplugin.inventories;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Dye;
import org.bukkit.plugin.Plugin;

import me.jerryz.coreplugin.regions.RegionAPI;
import me.jerryz.coreplugin.Core;
import me.jerryz.coreplugin.regions.Region;
import me.jerryz.coreplugin.utils.Config;
import me.jerryz.coreplugin.utils.ItemStackBuilder;
import me.jerryz.coreplugin.warps.WarpAPI;

public class WarpsInv {

	public static InventoryAPI api = new InventoryAPI(ChatColor.YELLOW +  (ChatColor.BOLD + "Warps:"), 9);
	private static Config invs = new Config("plugins/Core", "inventory.yml", Core.getInstance());
	
	public static void setup(final Player p) {
		api.clear();
		
		ItemStack a = new ItemStackBuilder(Material.ENDER_PORTAL_FRAME).setDisplayName(ChatColor.YELLOW + "Spawn").finish();
		ItemStack b = new ItemStackBuilder(Material.EMERALD).setDisplayName(ChatColor.GREEN + "Loja").finish();
		
		Dye dye = new Dye();
		dye.setColor(DyeColor.BLUE);
		ItemStack lapis = dye.toItemStack(1);
		ItemStack c = new ItemStackBuilder(lapis).setDisplayName(ChatColor.WHITE + "Mina: " + ChatColor.BLUE + "Lapis").finish();
		
		ItemStack d = new ItemStackBuilder(Material.GLASS).setDisplayName(ChatColor.WHITE + "Arena: " + ChatColor.AQUA + "FPS").finish();
		ItemStack e = new ItemStackBuilder(Material.DIAMOND_SWORD).setDisplayName(ChatColor.WHITE + "Arena: " + ChatColor.GOLD + "Glad").finish();
		
		api.setItem(0, a, new Runnable() {
			public void run() {
				p.teleport(p.getWorld().getSpawnLocation());
				RegionAPI.setRegion(Region.SPAWN, p.getName());
				p.sendMessage(String.valueOf(RegionAPI.getCurrentRegion(p.getName())));
				invs.saveInventory(p);
				p.getInventory().clear();
				p.getInventory().setHelmet(null);
				p.getInventory().setChestplate(null);
				p.getInventory().setLeggings(null);
				p.getInventory().setBoots(null);
				p.getInventory().setItem(0, new ItemStackBuilder(Material.COMPASS).setDisplayName(ChatColor.BLUE + "WARPS").setLores(ChatColor.GRAY + "Clique para abrir o menu de warps.").finish());
				p.getInventory().setItem(3, new ItemStackBuilder(Material.WATCH).setDisplayName(ChatColor.GOLD + "ESCONDER JOGADORES").setLores(ChatColor.GRAY + "Clique para esconder/mostrar jogadores.").finish());
			}
		});
		api.setItem(2, b, new Runnable() {
			public void run() {
				WarpAPI.teleport(p, "loja");
				RegionAPI.setRegion(Region.LOJA, p.getName());
				p.sendMessage(String.valueOf(RegionAPI.getCurrentRegion(p.getName())));
			}
		});
		api.setItem(4, c, new Runnable() {
			public void run() {
				WarpAPI.teleport(p, "minalapis");
				RegionAPI.setRegion(Region.MINALAPIS, p.getName());
				p.sendMessage(String.valueOf(RegionAPI.getCurrentRegion(p.getName())));
			}
		});
		api.setItem(6, d, new Runnable() {
			public void run() {
				WarpAPI.teleport(p, "arenafps");
				RegionAPI.setRegion(Region.ARENAFPS, p.getName());
				p.sendMessage(String.valueOf(RegionAPI.getCurrentRegion(p.getName())));
			}
		});
		api.setItem(8, e, new Runnable() {
			public void run() {
				WarpAPI.teleport(p, "arenap");
				RegionAPI.setRegion(Region.ARENAGLAD, p.getName());
				p.sendMessage(String.valueOf(RegionAPI.getCurrentRegion(p.getName())));
			}
		});
		
		for(int i = 0; i <= 8; i++) {
			if(api.getInventory().getItem(i) == null) {
				api.setItem(i, new ItemStackBuilder(Material.THIN_GLASS).setDisplayName(" ").done());
			}
		}
		
		
	}
	
	public static void open(final Player p) {

		setup(p);
		api.open(p);

	}

	public static void register(Plugin pl) {
		api.register(pl);
	}
}
