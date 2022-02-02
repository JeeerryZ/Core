package me.jerryz.coreplugin.inventories;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;

import me.jerryz.coreplugin.inventories.AnvilGUI.AnvilClickEvent;
import me.jerryz.coreplugin.utils.ItemStackBuilder;
import me.jerryz.coreplugin.utils.MessageType;
import me.jerryz.coreplugin.utils.StringUtils;

public class AdminInv {
	
	private static ArrayList<Player> inv = new ArrayList<Player>();

	public static InventoryAPI api = new InventoryAPI(ChatColor.GREEN +  (ChatColor.BOLD + "Menu admin:"), 9);

	public static void setup(final Player p) {
		api.clear();
		
		ItemStack a = new ItemStackBuilder(Material.WATCH).setDisplayName(ChatColor.GREEN + "Ficar Invisível").setLores(ChatColor.GRAY + "Status: " + (inv.contains(p) ? (ChatColor.GREEN + (ChatColor.BOLD + "Ativado")) : (ChatColor.RED + (ChatColor.BOLD +"Desativado")))).finish();
		ItemStack b = new ItemStackBuilder(Material.SAPLING).setDisplayName(ChatColor.GREEN + "Camuflar").setLores(ChatColor.GRAY + "Status: " + ChatColor.RED + (ChatColor.BOLD +"#Desativado no momento")).finish();
		ItemStack c = new ItemStackBuilder(Material.STICK).setDisplayName(ChatColor.GREEN + "Teleportar").finish();
		ItemStack d = new ItemStackBuilder(Material.IRON_AXE).setDisplayName(ChatColor.GREEN + "Lista de Reportados").finish();
		ItemStack e = new ItemStackBuilder(Material.DIAMOND).setDisplayName(ChatColor.GREEN + "Minas").finish();
		
		api.setItem(0, a, new Runnable() {
			
			@Override
			public void run() {
				if(inv.contains(p)) {
					inv.remove(p);
					p.closeInventory();
					p.playSound(p.getLocation(), Sound.ANVIL_USE, 10F, 0.5F);
					ItemStack aa = new ItemStackBuilder(Material.WATCH).setDisplayName(ChatColor.GREEN + "Ficar Invisível").setLores(ChatColor.GRAY + "Status: " + ((ChatColor.RED + (ChatColor.BOLD + "Desativado")))).finish();
					api.setItem(0, aa);
					for(Player pp : Bukkit.getOnlinePlayers()) {
						pp.showPlayer(p);
					}
				}else {
					inv.add(p);
					p.closeInventory();
					p.playSound(p.getLocation(), Sound.ANVIL_USE, 10F, 1F);
					ItemStack aa = new ItemStackBuilder(Material.WATCH).setDisplayName(ChatColor.GREEN + "Ficar Invisível").setLores(ChatColor.GRAY + "Status: " + ((ChatColor.GREEN + (ChatColor.BOLD + "Ativado")))).finish();
					api.setItem(0, aa);
					for(Player pp : Bukkit.getOnlinePlayers()) {
						pp.hidePlayer(p);
					}
				}
			}
			
		});

		api.setItem(2, b, new Runnable() {
			public void run() {

			}
			
		});
		
		api.setItem(4, c, new Runnable() {
			@Override
			public void run() {
					p.closeInventory();
					 AnvilGUI gui = new AnvilGUI(p, new AnvilGUI.AnvilClickEventHandler() {
                         
                         @Override
                         public void onAnvilClick(AnvilClickEvent event) {
                                 if(event.getSlot() == AnvilGUI.AnvilSlot.OUTPUT){
                                         event.setWillClose(true);
                                         event.setWillDestroy(true);
                                         p.playSound(p.getLocation(), Sound.ANVIL_USE, 10F, 1F);
                                         Player pp = null;
                                         try {
                                        	 pp = Bukkit.getPlayer(event.getName());
                                        	 StringUtils.sendMessage(p, MessageType.INFO, "Teleportando para " + ChatColor.YELLOW + event.getName());
                                        	 p.teleport(pp.getLocation());
                                         }catch(Exception e) {
                                        	 StringUtils.sendMessage(p, MessageType.ERROR, "Jogador não encontrado.");
                                         }
                                 }else{
                                         event.setWillClose(false);
                                         event.setWillDestroy(false);
                                 }
                         
                         }
                 });
                
                 ItemStack i = new ItemStack(Material.SKULL_ITEM,1,(short)3);
                 SkullMeta im = (SkullMeta)i.getItemMeta();
                 im.setDisplayName("Nome");
                 im.setOwner(p.getName());
                 i.setItemMeta(im);
                
                 gui.setSlot(AnvilGUI.AnvilSlot.INPUT_LEFT, i);
                
                 gui.open();
					}
			
		});
	
		api.setItem(6, d, new Runnable() {
			@Override
			public void run() {
					p.playSound(p.getLocation(), Sound.ANVIL_USE, 10F, 1F);
					p.closeInventory();
					ReportInv.open(p);
				}
		});

		api.setItem(8, e, new Runnable() {
			@Override
			public void run() {
					p.playSound(p.getLocation(), Sound.ANVIL_USE, 10F, 1F);
					p.closeInventory();
					//TODO ABRIR INVENTÁRIO DAS MINAS PRA RESETAR/ARRUMAR EOQ
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
