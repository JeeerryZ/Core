package me.jerryz.coreplugin.minas;

import org.bukkit.Location;
import org.bukkit.World;
@SuppressWarnings("unused")
public class Mina {

	
	private Location l1;
	private Location l2;
	private int blocks;
	private int currentBlocks;
	private MinaType mt;

	public Mina(Location l1, Location l2, int blocks, MinaType mt) {
		this.l1 = l1;
		this.l2 = l2;
		this.blocks = blocks;
		this.mt = mt;
	}
	
	public void setBlocks(int id) {
		this.blocks = id;
	}
	
	public int getMaxBlocks() {
		return this.blocks;
	}
	
	public int getCurrentBlocks() {
		return this.currentBlocks;
	}
	
	public void setCurrentBlocks(int i) {
		this.currentBlocks = i;
	}

	public void setLocationOne(Location l1) {
		this.l1 = l1;
	}
	
	public void setLocationTwo(Location l2) {
		this.l2 = l2;
	}
	
	public Location getLocationOne() {
		return this.l1;
	}
	
	public Location getLocationTwo() {
		return this.l2;
	}
	
	public MinaType getMinaType() {
		return this.mt;
	}
	
	public World getWorld() {
		return this.l1.getWorld();
	}
	
}
