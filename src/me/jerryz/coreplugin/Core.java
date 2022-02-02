package me.jerryz.coreplugin;

import java.io.IOException;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import me.jerryz.coreplugin.commands.Admin;
import me.jerryz.coreplugin.commands.FlySpeed;
import me.jerryz.coreplugin.commands.Limpar;
import me.jerryz.coreplugin.commands.MineReset;
import me.jerryz.coreplugin.commands.Money;
import me.jerryz.coreplugin.commands.MoneyTop;
import me.jerryz.coreplugin.commands.SendTitle;
import me.jerryz.coreplugin.commands.SetItem;
import me.jerryz.coreplugin.commands.SetSpawn;
import me.jerryz.coreplugin.commands.SetWarp;
import me.jerryz.coreplugin.commands.Spawn;
import me.jerryz.coreplugin.commands.TpAll;
import me.jerryz.coreplugin.commands.Warn;
import me.jerryz.coreplugin.commands.Warps;
import me.jerryz.coreplugin.economy.EconomyAPI;
import me.jerryz.coreplugin.inventories.AdminInv;
import me.jerryz.coreplugin.inventories.ReportInv;
import me.jerryz.coreplugin.inventories.WarpsInv;
import me.jerryz.coreplugin.listeners.PlayerChat;
import me.jerryz.coreplugin.listeners.PlayerInteract;
import me.jerryz.coreplugin.listeners.PlayerInv;
import me.jerryz.coreplugin.listeners.PlayerJoin;
import me.jerryz.coreplugin.listeners.PlayerMove;
import me.jerryz.coreplugin.listeners.PlayerProtection;
import me.jerryz.coreplugin.listeners.WorldListener;
import me.jerryz.coreplugin.minas.MinaAPI;
import me.jerryz.coreplugin.minas.MinaType;
import me.jerryz.coreplugin.ranks.RankAPI;
import me.jerryz.coreplugin.regions.RegionAPI;
import me.jerryz.coreplugin.utils.StringUtils;
import me.jerryz.coreplugin.warps.WarpAPI;

public class Core extends JavaPlugin {

	public HashMap<String, Integer> warn = new HashMap<String, Integer>();

	public static Core getInstance() {
		return (Core) Bukkit.getPluginManager().getPlugin("Core");
	}

	public void onEnable() {

		getServer().getConsoleSender()
				.sendMessage(StringUtils.consolePreffix + ChatColor.YELLOW + "Registrando eventos");

		getServer().getPluginManager().registerEvents(new PlayerInteract(), this);
		getServer().getPluginManager().registerEvents(new PlayerInv(), this);
		getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
		getServer().getPluginManager().registerEvents(new WorldListener(), this);
		getServer().getPluginManager().registerEvents(new PlayerMove(), this);
		getServer().getPluginManager().registerEvents(new PlayerChat(), this);
		getServer().getPluginManager().registerEvents(new PlayerProtection(), this);
		getServer().getPluginManager().registerEvents(new PlayerMove(), this);
		AdminInv.register(this);
		ReportInv.register(this);
		WarpsInv.register(this);

		getServer().getConsoleSender()
				.sendMessage(StringUtils.consolePreffix + ChatColor.GREEN + "Eventos registrados com sucesso");

		try {
			RankAPI.loadRanks();
		} catch (Exception e) {
			getServer().getConsoleSender()
					.sendMessage(StringUtils.consolePreffix + ChatColor.RED + "Nenhum rank foi encontrado");
		}
		try {
			EconomyAPI.loadEconomies();
		} catch (Exception e) {
			getServer().getConsoleSender()
					.sendMessage(StringUtils.consolePreffix + ChatColor.RED + "Nenhuma economia foi encontrada");
		}
		try {
			RegionAPI.loadRegions(true);
		} catch (Exception e) {
			getServer().getConsoleSender()
					.sendMessage(StringUtils.consolePreffix + ChatColor.RED + "Nenhuma regiao foi encontrada");
		}
		try {
			WarpAPI.loadWarps();
		} catch (Exception e) {
			getServer().getConsoleSender()
					.sendMessage(StringUtils.consolePreffix + ChatColor.RED + "Nenhum warp foi encontrado");
		}

		getServer().getConsoleSender()
				.sendMessage(StringUtils.consolePreffix + ChatColor.YELLOW + "Registrando comandos");

		getCommand("moneytop").setExecutor(new MoneyTop());
		getCommand("warn").setExecutor(new Warn());
		getCommand("spawn").setExecutor(new Spawn());
		getCommand("setspawn").setExecutor(new SetSpawn());
		getCommand("money").setExecutor(new Money());
		getCommand("limpar").setExecutor(new Limpar());
		getCommand("admin").setExecutor(new Admin());
		getCommand("setwarp").setExecutor(new SetWarp());
		getCommand("warps").setExecutor(new Warps());
		getCommand("flyspeed").setExecutor(new FlySpeed());
		getCommand("tpall").setExecutor(new TpAll());
		getCommand("minereset").setExecutor(new MineReset());
		getCommand("sendtitle").setExecutor(new SendTitle());
		getCommand("setitem").setExecutor(new SetItem());

		getServer().getConsoleSender()
				.sendMessage(StringUtils.consolePreffix + ChatColor.GREEN + "Comandos registrados com sucesso");

		getServer().getConsoleSender().sendMessage(StringUtils.consolePreffix + ChatColor.YELLOW + "Registrando minas");

		Location l1 = new Location(Bukkit.getWorld("world"), 452, 1, -1);
		Location l2 = new Location(Bukkit.getWorld("world"), 430, 19, -26);

		Location l3 = new Location(Bukkit.getWorld("world"), 488, 1, -1);
		Location l4 = new Location(Bukkit.getWorld("world"), 466, 19, -26);

		MinaAPI.createMina(l1, l2, MinaType.LAPIS);
		MinaAPI.createMina(l3, l4, MinaType.VIP);

		getServer().getConsoleSender()
				.sendMessage(StringUtils.consolePreffix + ChatColor.GREEN + "Minas registradas com sucesso");

	}

	public void onDisable() {

		try {
			RankAPI.saveRanks();
			EconomyAPI.saveEconomies();
			WarpAPI.saveWarps();
			RegionAPI.saveRegions(true);
		} catch (IOException e) {
			getServer().getConsoleSender()
					.sendMessage(StringUtils.consolePreffix + ChatColor.RED + "Erro ao salvar informações do server");
		}

	}

}
