package me.jerryz.coreplugin.inventories;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;

import me.jerryz.coreplugin.report.Report;
import me.jerryz.coreplugin.report.ReportAPI;
import me.jerryz.coreplugin.utils.ItemStackBuilder;

public class ReportInv {

	private static InventoryAPI api = new InventoryAPI(ChatColor.DARK_BLUE +  (ChatColor.BOLD + "Reportados:"), 54);

	private static void setup(final Player p) {
		api.clear();
		
		for(int i = 0 ; i<ReportAPI.reports.size() ; i++ ) {
			Report r = ReportAPI.reports.get(i);
			ItemStack is = new ItemStack(Material.SKULL_ITEM,1,(short)3);
            SkullMeta im = (SkullMeta)is.getItemMeta();
            im.setDisplayName(ChatColor.WHITE + r.getName());
            im.setOwner(r.getName());
            is.setItemMeta(im);
            api.addItem(is, new Runnable() {

				@SuppressWarnings("deprecation")
				public void run() {
					InventoryAPI temp = new InventoryAPI(ChatColor.GRAY + r.getName() + ChatColor.GRAY + ": " + (r.getPlayer().isOnline() ? (ChatColor.GREEN + (ChatColor.BOLD + "Online")) : (ChatColor.RED + (ChatColor.BOLD + "Offline"))), InventorySize.NINE); 
					
					temp.setItem(2, new ItemStackBuilder(new ItemStack(Material.WOOL, 1, DyeColor.RED.getData())).setDisplayName(ChatColor.RED + (ChatColor.BOLD + "Banir")).finish(), new Runnable() {

						public void run() {
							r.getPlayer().setBanned(true);
							p.closeInventory();
							temp.clear();
							p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 10, 1);
						}
						
					});
					
					temp.setItem(4, new ItemStackBuilder(new ItemStack(Material.WOOL, 1, DyeColor.WHITE.getData())).setDisplayName(ChatColor.GREEN + (ChatColor.BOLD + "Perdoar")).finish(), new Runnable() {

						public void run() {
			  				ReportAPI.delete(r);
							p.closeInventory();
							temp.clear();
							p.playSound(p.getLocation(), Sound.LEVEL_UP, 10, 1);
						}
						
					});
					
					temp.setItem(6, new ItemStackBuilder(new ItemStack(Material.WOOL, 1, DyeColor.BLUE.getData())).setDisplayName(ChatColor.GREEN + (ChatColor.BOLD + "Teleportar")).finish(), new Runnable() {

						public void run() {
							if(r.getPlayer().isOnline()) {
								p.closeInventory();
								p.teleport(r.getPlayer().getLocation());
								temp.clear();
								p.playSound(r.getPlayer().getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
							}
						}
						
					});
					
					for(int i = 0; i <= 8; i++) {
						if(api.getInventory().getItem(i) == null) {
							api.setItem(i, new ItemStackBuilder(Material.THIN_GLASS).setDisplayName(" ").done());
						}
					}
				}

            });
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
	
