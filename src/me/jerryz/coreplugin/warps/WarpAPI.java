package me.jerryz.coreplugin.warps;

import java.io.IOException;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.jerryz.coreplugin.regions.RegionAPI;
import me.jerryz.coreplugin.Core;
import me.jerryz.coreplugin.regions.Region;
import me.jerryz.coreplugin.utils.Config;

public class WarpAPI {

	public static HashMap<String, Location> warps = new HashMap<String, Location>();
	private static Config config = new Config("plugins/Core", "warps.yml", Core.getInstance());
	private static Config invs = new Config("plugins/Core", "inventory.yml", Core.getInstance());

	public static void createWarp(String name, Location l) {
		warps.put(name, l);
		try {
			saveWarps();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void deleteWarp(String name) {
		warps.remove(name);
	}

	public static Location getWarpLocation(String name) {
		return warps.get(name);
	}

	public static void teleport(Player p, String name) {
		if (RegionAPI.getCurrentRegion(p.getName()) == Region.SPAWN) {
			p.getInventory().clear();
			p.teleport(getWarpLocation(name));
			try {
				invs.loadInventory(p);
			}catch(Exception e) {
				e.printStackTrace();
			}
			return;
		}else {
			p.teleport(getWarpLocation(name));
			try {
				invs.saveInventory(p);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		}

	public static void saveWarps() throws IOException {
		for (String keys : warps.keySet()) {
			config.setLocation("Warps." + keys, warps.get(keys));
			config.saveConfig();
		}
	}

	public static void loadWarps() throws Exception {
		for (String keys : config.getConfig().getConfigurationSection("Warps").getKeys(false)) {
			Location l = config.getLocation("Warps." + keys);
			warps.put(keys, l);
		}
	}
}
