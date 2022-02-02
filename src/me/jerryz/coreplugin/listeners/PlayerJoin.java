package me.jerryz.coreplugin.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.jerryz.coreplugin.economy.EconomyAPI;
import me.jerryz.coreplugin.ranks.RankAPI;
import me.jerryz.coreplugin.ranks.RankType;
import me.jerryz.coreplugin.regions.Region;
import me.jerryz.coreplugin.regions.RegionAPI;
import me.jerryz.coreplugin.utils.ItemStackBuilder;
import net.md_5.bungee.api.ChatColor;

public class PlayerJoin implements Listener {

	private String adminTag = "&4&kkkk&0[&e&lDiretor&0]&4&kkkk";
	private String diamanteTag = "&2[&3Diamante&2]";
	private String bronzeTag = "&3[&6Bronze&3]";
	private String mestreTag = "&2[&5Mestre&2]";
	private String ouroTag = "&1[&6Ouro&1]";
	private String platinaTag = "&5[&7Platina&5]";
	private String prataTag = "&2[&9Prata&2]";
	private String membroTag = "&1[&8Membro&1]";

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {

		if (e.getPlayer().isOp()) {
			if (RankAPI.hasRank(e.getPlayer().getName()) == false) {
				RankAPI.setRank(e.getPlayer(), RankType.ADMIN);
			}
		} else {
			if (RankAPI.hasRank(e.getPlayer().getName()) == false) {
				RankAPI.setRank(e.getPlayer(), RankType.MEMBRO);
			}
		}

		e.getPlayer().getInventory().clear();
		e.getPlayer().getInventory().setItem(0,
				new ItemStackBuilder(Material.COMPASS).setDisplayName(ChatColor.BLUE + "WARPS")
						.setLores(ChatColor.GRAY + "Clique para abrir o menu de warps.").finish());
		e.getPlayer().getInventory().setItem(3,
				new ItemStackBuilder(Material.WATCH).setDisplayName(ChatColor.GOLD + "ESCONDER JOGADORES")
						.setLores(ChatColor.GRAY + "Clique para esconder/mostrar jogadores.").finish());

		if (RankAPI.hasRank(e.getPlayer().getName())) {
			switch (RankAPI.getRank(e.getPlayer()).getRankType()) {
			case ADMIN:
				e.getPlayer().setDisplayName(ChatColor.translateAlternateColorCodes('&', adminTag) + ChatColor.RED + " "
						+ e.getPlayer().getDisplayName() + ChatColor.RESET);
				e.getPlayer().setCustomName(ChatColor.translateAlternateColorCodes('&', adminTag) + ChatColor.RED + " "
						+ e.getPlayer().getDisplayName() + ChatColor.RESET);
				e.getPlayer().setPlayerListName(ChatColor.translateAlternateColorCodes('&', adminTag) + ChatColor.RED + " "
						+ e.getPlayer().getDisplayName() + ChatColor.RESET);
				break;
			case BRONZE:
				e.getPlayer().setDisplayName(ChatColor.translateAlternateColorCodes('&', bronzeTag) + ChatColor.YELLOW
						+ " " + e.getPlayer().getDisplayName() + ChatColor.RESET);
				break;
			case DIAMANTE:
				e.getPlayer().setDisplayName(ChatColor.translateAlternateColorCodes('&', diamanteTag) + ChatColor.YELLOW
						+ " " + e.getPlayer().getDisplayName() + ChatColor.RESET);
				break;
			case MEMBRO:
				e.getPlayer().setDisplayName(ChatColor.translateAlternateColorCodes('&', membroTag) + ChatColor.YELLOW
						+ " " + e.getPlayer().getDisplayName() + ChatColor.RESET);
				break;
			case MESTRE:
				e.getPlayer().setDisplayName(ChatColor.translateAlternateColorCodes('&', mestreTag) + ChatColor.YELLOW
						+ " " + e.getPlayer().getDisplayName() + ChatColor.RESET);
				break;
			case OURO:
				e.getPlayer().setDisplayName(ChatColor.translateAlternateColorCodes('&', ouroTag) + ChatColor.YELLOW
						+ " " + e.getPlayer().getDisplayName() + ChatColor.RESET);
				break;
			case PLATINA:
				e.getPlayer().setDisplayName(ChatColor.translateAlternateColorCodes('&', platinaTag) + ChatColor.YELLOW
						+ " " + e.getPlayer().getDisplayName() + ChatColor.RESET);
				break;
			case PRATA:
				e.getPlayer().setDisplayName(ChatColor.translateAlternateColorCodes('&', prataTag) + ChatColor.YELLOW
						+ " " + e.getPlayer().getDisplayName() + ChatColor.RESET);
				break;
			default:
				break;
			}
		}

		if (EconomyAPI.hasEconomy(e.getPlayer().getName()) == false) {
			EconomyAPI.createEconomy(e.getPlayer().getName());
		}

		RegionAPI.setRegion(Region.SPAWN, e.getPlayer().getName());
		e.getPlayer().sendMessage(String.valueOf(RegionAPI.getCurrentRegion(e.getPlayer().getName())));
		e.getPlayer().teleport(e.getPlayer().getWorld().getSpawnLocation());
		


	}
}