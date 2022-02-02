package me.jerryz.coreplugin.ranks;

import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.entity.Player;

import me.jerryz.coreplugin.Core;
import me.jerryz.coreplugin.utils.Config;

public class RankAPI {
	
	private static ArrayList<Rank> ranks = new ArrayList<Rank>();
	
	public static void setRank(Player p, RankType rt) {
		if(hasRank(p)) {
			Rank r = getRank(p);
			ranks.remove(r);
			Rank rr = new Rank(p.getName(), rt);
			ranks.add(rr);
		}else {
			Rank r = new Rank(p.getName(), rt);
			ranks.add(r);
		}
	}
	
	public static void setRank(String name, RankType rt) {
		if(hasRank(name)) {
			Rank r = getRank(name);
			ranks.remove(r);
			Rank rr = new Rank(name, rt);
			ranks.add(rr);
		}else {
			Rank r = new Rank(name, rt);
			ranks.add(r);
		}
	}
	
	public static boolean hasRank(Player p) {
		for(Rank r : ranks) {
			if(r.getPlayer().equalsIgnoreCase(p.getName())) {
				return true;
			}else {
				continue;
			}
		}
		return false;
	}
	
	public static boolean hasRank(String name) {
		for(Rank r : ranks) {
			if(r.getPlayer().equalsIgnoreCase(name)) {
				return true;
			}else {
				continue;
			}
		}
		return false;
	}
	
	public static Rank getRank(Player p) {
		for(Rank r : ranks) {
			if(r.getPlayer().equalsIgnoreCase(p.getName())) {
				return r;
			}
		}
		return null;
		
	}
	
	public static Rank getRank(String name) {
		for(Rank r : ranks) {
			if(r.getPlayer().equalsIgnoreCase(name)) {
				return r;
			}
		}
		return null;
		
	}
	
	public static void saveRanks() throws IOException {
		Config config = new Config("plugins/Core", "ranks.yml", Core.getInstance());
		for(Rank r : ranks) {
			config.getConfig().set("Ranks." + r.getPlayer(), r.getRankType().toString());
		}
	}
	
	public static void loadRanks() {
		Config config = new Config("plugins/Core", "ranks", Core.getInstance());
		for(String keys : config.getConfig().getConfigurationSection("Ranks").getKeys(false)) {
			String type = config.getConfig().getString("Ranks." + keys);
			setRank(keys, RankType.valueOf(type));
		}
	}

}
