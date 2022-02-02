package me.jerryz.coreplugin.regions;

import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.jerryz.coreplugin.Core;
import me.jerryz.coreplugin.utils.Config;

public class RegionAPI {

	public static ArrayList<String> spawn = new ArrayList<String>();
	public static ArrayList<String> minalapis = new ArrayList<String>();
	public static ArrayList<String> minavip = new ArrayList<String>();
	public static ArrayList<String> loja = new ArrayList<String>();
	public static ArrayList<String> arenafps = new ArrayList<String>();
	public static ArrayList<String> arenaglad = new ArrayList<String>();
	
	private static Config config= new Config("plugins/Core/", "region.yml", Core.getInstance());

	public static Region getCurrentRegion(String name) {
		if (spawn.contains(name)) {
			return Region.SPAWN;
		}
		if (minalapis.contains(name)) {
			return Region.MINALAPIS;
		}
		if (loja.contains(name)) {
			return Region.LOJA;
		}
		if (arenafps.contains(name)) {
			return Region.ARENAFPS;
		}
		if (arenaglad.contains(name)) {
			return Region.ARENAGLAD;
		}
		if (minavip.contains(name)) {
			return Region.MINAVIP;
		}
		return null;
	}

	public static ArrayList<String> getRegion(Region r) {
		switch (r) {

		case ARENAFPS:
			return arenafps;
		case ARENAGLAD:
			return arenaglad;
		case LOJA:
			return loja;
		case MINALAPIS:
			return minalapis;
		case SPAWN:
			return spawn;
		case MINAVIP:
			return minavip;
		default:
			return spawn;
		}

	}

	public static void removeFromRegion(String name) {
		getRegion(getCurrentRegion(name)).remove(name);
	}

	public static void setRegion(Region r, String name) {
		if(getCurrentRegion(name) != null) {
			removeFromRegion(name);
		}
		switch (r) {

		case ARENAFPS:
			arenafps.add(name);
			break;
		case ARENAGLAD:
			arenaglad.add(name);
			break;
		case LOJA:
			loja.add(name);
			break;
		case MINALAPIS:
			minalapis.add(name);
			break;
		case SPAWN:
			spawn.add(name);
			break;
		case MINAVIP:
			minavip.add(name);
			break;
		}
	}

	public static ArrayList<Player> getPlayersInRegion(Region r) {
		ArrayList<Player> players = new ArrayList<Player>();
		for (String str : getRegion(r)) {
			players.add(Bukkit.getPlayer(str));
		}
		return players;
	}
	
	public static void clearRegions() {
		arenafps.clear();
		arenaglad.clear();
		spawn.clear();
		minalapis.clear();
		loja.clear();
		minavip.clear();
	}
	
	public static void saveRegions(boolean clean) throws IOException {
		
			if(clean) clearRegions();
			
			for(String str : arenafps) {
				config.getConfig().set("Region." + str, "ArenaFps");
			}
			for(String str : arenaglad) {
				config.getConfig().set("Region." + str, "ArenaGlad");
			}
			for(String str : spawn) {
				config.getConfig().set("Region." + str, "Spawn");
			}
			for(String str : minalapis) {
				config.getConfig().set("Region." + str, "MinaLapis");
			}
			for(String str : loja) {
				config.getConfig().set("Region." + str, "Loja");
			}
			for(String str : minavip) {
				config.getConfig().set("Region." + str, "MinaVip");
			}
			
			config.saveConfig();
			
		
	}
	
	public static void loadRegions(boolean clean) {
		
		if(clean) clearRegions();
		
		for(String str : config.getConfig().getConfigurationSection("Region").getKeys(false)) {
			switch (String.valueOf(config.getConfig().get("Region." + str))) {
			case "ArenaFps":
				setRegion(Region.ARENAFPS, str);
			case "ArenaGlad":
				setRegion(Region.ARENAGLAD, str);
			case "Spawn":
				setRegion(Region.SPAWN, str);
			case "MinaLapis":
				setRegion(Region.MINALAPIS, str);
			case "Loja":
				setRegion(Region.LOJA, str);
			case "MinaVip":
				setRegion(Region.MINAVIP, str);
			}
		}
		
	
		
		
	}
}
