package me.jerryz.coreplugin.ranks;

public class Rank {
	
	String name;
	RankType rt;
	
	public Rank(String name, RankType rt) {
		this.name = name;
		this.rt = rt;
	}
	
	public String getPlayer() {
		return this.name;
	}
	
	public RankType getRankType() {
		return this.rt;
	}

}
