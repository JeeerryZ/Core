package me.jerryz.coreplugin.report;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Report {

	private String name;
	private String reason;
	private int times;
	
	public Report(String name, String reason, int times) {
		this.name = name;
		this.reason = reason;
		this.times = times;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Player getPlayer() {
		return Bukkit.getPlayer(this.name);
	}
	
	public String getReason() {
		return this.reason;
	}
	
	public void addTime() {
		this.times ++;
	}
	
	public int getTimes() {
		return this.times;
	}
}
