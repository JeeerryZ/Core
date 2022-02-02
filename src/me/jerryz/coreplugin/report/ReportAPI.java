package me.jerryz.coreplugin.report;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public class ReportAPI {

	public static ArrayList<Report> reports = new ArrayList<Report>();
	
	public static void createReport(Player p, String reason) {
		Report r = new Report(p.getName(), reason, 1);
		reports.add(r);
	}
	
	public static boolean exists(Report r) {
		for(Report rr : reports) {
			if(rr == r) {
				return true;
			}
		}
		return false;
	}
	
	public static Report getReport(Player p) {
		for(Report rr : reports) {
			if(rr.getName() == p.getName()) {
				return rr;
			}
		}
		return null;
	}
	
	public static void delete(Report r) {
		if(exists(r)) {
			reports.remove(r);
		}
	}
	
}
