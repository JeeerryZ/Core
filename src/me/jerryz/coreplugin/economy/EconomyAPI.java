package me.jerryz.coreplugin.economy;

import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.entity.Player;

import me.jerryz.coreplugin.Core;
import me.jerryz.coreplugin.utils.Config;

public class EconomyAPI {
	
	public static ArrayList<Economy> contas = new ArrayList<Economy>();
	private static Config config = new Config("plugins/Core", "economy.yml", Core.getInstance());
	
	public static void createEconomy(Player p) {
		Economy e = new Economy(0, p.getName());
		contas.add(e);
	}
	
	public static void createEconomy(String name) {
		Economy e = new Economy(0, name);
		contas.add(e);
	}
	
	public static Economy getEconomy(Player p) {
		for(Economy e : contas) {
			if(e.getPlayer().equalsIgnoreCase(p.getName())){
				return e;
			}
		}
		return null;
	}
	
	public static Economy getEconomy(String name) {
		for(Economy e : contas) {
			if(e.getPlayer().equalsIgnoreCase(name)){
				return e;
			}
		}
		return null;
	}
	
	public static void addMoney(Player p, double money) {
		double moneyBefore = getEconomy(p).getMoney();
		getEconomy(p).setMoney(moneyBefore + money);
	}
	
	public static void addMoney(String name, double money) {
		double moneyBefore = getEconomy(name).getMoney();
		getEconomy(name).setMoney(moneyBefore + money);
	}
	
	public static void removeMoney(Player p, double money) {
		double moneyBefore = getEconomy(p).getMoney();
		getEconomy(p).setMoney(moneyBefore - money);
	}
	
	public static void removeMoney(String name, double money) {
		double moneyBefore = getEconomy(name).getMoney();
		getEconomy(name).setMoney(moneyBefore - money);
	}
	
	public static boolean hasEconomy(Player p) {
		for(Economy e : contas) {
			if(e.getPlayer().equalsIgnoreCase(p.getName())) {
				return true;
			}else {
				continue;
			}
		}
		return false;
	}
	
	public static boolean hasEconomy(String name) {
		for(Economy e : contas) {
			if(e.getPlayer().equalsIgnoreCase(name)) {
				return true;
			}else {
				continue;
			}
		}
		return false;
	}

	public static void saveEconomies() throws IOException {
		for(Economy e : contas) {
			config.getConfig().set("Economy." + e.getPlayer(), e.getMoney());
		}
	}
	
	public static void loadEconomies() {
		for(String keys : config.getConfig().getConfigurationSection("Economy").getKeys(false)) {
			double money = config.getConfig().getDouble("Economy." + keys);
			Economy e = new Economy(money, keys);
			contas.add(e);
		}
	}
	
}
