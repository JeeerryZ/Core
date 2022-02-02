package me.jerryz.coreplugin.minas;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.jerryz.coreplugin.Core;
import me.jerryz.coreplugin.regions.RegionAPI;
import me.jerryz.coreplugin.regions.Region;
import me.jerryz.coreplugin.warps.WarpAPI;
import net.md_5.bungee.api.ChatColor;

public class MinaAPI {

	private static ArrayList<Mina> minas = new ArrayList<Mina>();
	public static ArrayList<MinaType> willReset = new ArrayList<MinaType>();

	public static void createMina(Location l1, Location l2, MinaType mt) {
		int blocks = 0;
		double x1 = l1.getX();
		double y1 = l1.getY();
		double z1 = l1.getZ();
		double x2 = l2.getX();
		double y2 = l2.getY();
		double z2 = l2.getZ();

		double xMin = Math.min(x1, x2);
		double yMin = Math.min(y1, y2);
		double zMin = Math.min(z1, z2);

		double xMax = Math.max(x1, x2);
		double yMax = Math.max(y1, y2);
		double zMax = Math.max(z1, z2);

		for (int x = (int) xMin; x < xMax; x++) {
			for (int y = (int) yMin; y < yMax; y++) {
				for (int z = (int) zMin; z < zMax; z++) {
					blocks++;
				}
			}
		}

		Mina mina = new Mina(l1, l2, blocks, mt);

		mina.setCurrentBlocks(blocks);

		minas.add(mina);

	}

	public static int getCurrentBlocks(MinaType mt) {
		return getMina(mt).getCurrentBlocks();
	}

	public static void removeBlockFromMina(MinaType mt) {
		int current = getCurrentBlocks(mt);
		getMina(mt).setCurrentBlocks(current - 1);
	}

	public static void setWillReset(MinaType mt, boolean reset) {
		if (reset) {
			willReset.add(mt);
		} else {
			willReset.remove(mt);
		}
	}

	public static boolean willReset(MinaType mt) {
		return willReset.contains(mt);
	}

	public static void resetMina(MinaType mt) {
		getMina(mt).setCurrentBlocks(getMina(mt).getMaxBlocks());

		setWillReset(mt, true);

		Location l1 = getMina(mt).getLocationOne();
		Location l2 = getMina(mt).getLocationTwo();

		switch(mt) {
		
		case LAPIS:
			
			for (Player p : getPlayersInArea(l1, l2)) {
				if (RegionAPI.getPlayersInRegion(Region.MINALAPIS).contains(p)) {
					WarpAPI.teleport(p, "minalapis");
				}

			}

			generateSquareBlocks(l1, l2, 5, Material.LAPIS_ORE);
			
			setWillReset(mt, false);

			sendMessageToMina(MinaType.LAPIS, "");
			sendMessageToMina(MinaType.LAPIS, ChatColor.YELLOW + " >> " + ChatColor.GRAY + "Mina lapis resetada.");
			sendMessageToMina(MinaType.LAPIS, "");
			
			break;
			
		case VIP:
			
			for (Player p : getPlayersInArea(l1, l2)) {
				if (RegionAPI.getPlayersInRegion(Region.MINAVIP).contains(p)) {
					WarpAPI.teleport(p, "minavip");
				}

			}

			generateSquareBlocks(l1, l2, 5, Material.GOLD_ORE);
			
			setWillReset(mt, false);

			sendMessageToMina(MinaType.VIP, "");
			sendMessageToMina(MinaType.VIP, ChatColor.YELLOW + " >> " + ChatColor.GRAY + "Mina vip resetada.");
			sendMessageToMina(MinaType.VIP, "");
			
			break;
		}

	}

	static int delay = 0;

	public static void resetMina(MinaType mt, int delayInSeconds) {

		switch (mt) {
		
		case LAPIS:
			
			getMina(MinaType.LAPIS).setCurrentBlocks(getMina(MinaType.LAPIS).getMaxBlocks());

			Location l1 = getMina(MinaType.LAPIS).getLocationOne();
			Location l2 = getMina(MinaType.LAPIS).getLocationTwo();

			delay = delayInSeconds;
			
			setWillReset(MinaType.LAPIS, true);
			
			new BukkitRunnable() {

				@Override
				public void run() {
					
					if (delay == 0) {
						
						for (Player p : getPlayersInArea(l1, l2)) {
							if (RegionAPI.getPlayersInRegion(Region.MINALAPIS).contains(p)) {
								WarpAPI.teleport(p, "minalapis");
							}
						}
						generateSquareBlocks(l1, l2, 5, Material.LAPIS_ORE);
						
						setWillReset(MinaType.LAPIS, false);

						sendMessageToMina(MinaType.LAPIS, "");
						sendMessageToMina(MinaType.LAPIS,ChatColor.YELLOW + " >> " + ChatColor.DARK_GRAY + "Mina lapis resetada.");
						sendMessageToMina(MinaType.LAPIS, "");
						
						delay = 0;
						
						cancel();
						
					} else {
						if (delay == 30) {
							
							sendMessageToMina(MinaType.LAPIS, "");
							sendMessageToMina(MinaType.LAPIS, ChatColor.YELLOW + " >> " + ChatColor.DARK_GRAY + "Mina lapis resetando em " + ChatColor.YELLOW + "30" + ChatColor.DARK_GRAY + " segundos.");
							sendMessageToMina(MinaType.LAPIS, "");
						}
						if (delay == 60) {
							
							sendMessageToMina(MinaType.LAPIS, "");
							sendMessageToMina(MinaType.LAPIS, ChatColor.YELLOW + " >> " + ChatColor.DARK_GRAY + "Mina lapis resetando em " + ChatColor.YELLOW + "1" + ChatColor.DARK_GRAY + " Minuto.");
							sendMessageToMina(MinaType.LAPIS, "");
						}
						if (delay == 10) {
							
							sendMessageToMina(MinaType.LAPIS, "");
							sendMessageToMina(MinaType.LAPIS, ChatColor.YELLOW + " >> " + ChatColor.DARK_GRAY + "Mina lapis resetando em " + ChatColor.YELLOW + "10" + ChatColor.DARK_GRAY + " segundos.");
							sendMessageToMina(MinaType.LAPIS, "");
						}
						
						delay--;
						
					}
				}
			}.runTaskTimer(Core.getInstance(), 0, 20);
			
			break;
			
		case VIP:

			getMina(MinaType.VIP).setCurrentBlocks(getMina(MinaType.VIP).getMaxBlocks());

			Location l3 = getMina(MinaType.VIP).getLocationOne();
			Location l4 = getMina(MinaType.VIP).getLocationTwo();

			delay = delayInSeconds;
			
			setWillReset(MinaType.VIP, true);
			
			new BukkitRunnable() {

				@Override
				public void run() {

					if (delay == 0) {
						
						for (Player p : getPlayersInArea(l3, l4)) {
							if (RegionAPI.getPlayersInRegion(Region.MINAVIP).contains(p)) {
								WarpAPI.teleport(p, "minavip");
							}
						}
						
						generateSquareBlocks(l3, l4, 5, Material.GOLD_ORE);
						
						setWillReset(MinaType.VIP, false);

						sendMessageToMina(MinaType.VIP, "");
						sendMessageToMina(MinaType.VIP,ChatColor.YELLOW + " >> " + ChatColor.GRAY + "Mina vip resetada.");
						sendMessageToMina(MinaType.VIP, "");
						
						delay = 0;
						
						cancel();
						
					} else {
						if (delay == 30) {
							
							sendMessageToMina(MinaType.VIP, "");
							sendMessageToMina(MinaType.VIP, ChatColor.YELLOW + " >> " + ChatColor.GRAY + "Mina vip resetando em " + ChatColor.YELLOW + "30" + ChatColor.GRAY + " segundos.");
							sendMessageToMina(MinaType.VIP, "");
							
						}
						if (delay == 60) {
							
							sendMessageToMina(MinaType.VIP, "");
							sendMessageToMina(MinaType.VIP, ChatColor.YELLOW + " >> " + ChatColor.GRAY + "Mina vip resetando em " + ChatColor.YELLOW + "1" + ChatColor.GRAY + " Minuto.");
							sendMessageToMina(MinaType.VIP, "");
						
						}
						if (delay == 10) {
							
							sendMessageToMina(MinaType.VIP, "");
							sendMessageToMina(MinaType.VIP, ChatColor.YELLOW + " >> " + ChatColor.GRAY + "Mina vip resetando em " + ChatColor.YELLOW + "10" + ChatColor.GRAY + " segundos.");
							sendMessageToMina(MinaType.VIP, "");
							
						}
						
						delay--;
						
					}
				}
			}.runTaskTimer(Core.getInstance(), 0, 20);
			
			break;
			
		}
	}

	public static ArrayList<Player> getPlayersInArea(Location l1, Location l2) {

		double x1 = l1.getX();
		double y1 = l1.getY();
		double z1 = l1.getZ();
		double x2 = l2.getX();
		double y2 = l2.getY();
		double z2 = l2.getZ();

		double xMin = Math.min(x1, x2);
		double yMin = Math.min(y1, y2);
		double zMin = Math.min(z1, z2);

		double xMax = Math.max(x1, x2);
		double yMax = Math.max(y1, y2);
		double zMax = Math.max(z1, z2);

		ArrayList<Player> players = new ArrayList<Player>();

		for (int x = (int) xMin; x < xMax; x++) {
			for (int y = (int) yMin; y < yMax; y++) {
				for (int z = (int) zMin; z < zMax; z++) {
					for (Player p : Bukkit.getOnlinePlayers()) {
						if (p.getLocation().getBlockX() == x && p.getLocation().getBlockY() == y
								&& p.getLocation().getBlockZ() == z) {
							players.add(p);
						}
					}
				}
			}
		}
		return players;
	}

	public static Mina getMina(MinaType mt) {
		for (Mina mina : minas) {
			if (mina.getMinaType() == mt) {
				return mina;
			}
		}
		return null;
	}

	public static void sendMessageToMina(MinaType mt, String msg) {
		switch (mt) {

		case LAPIS:
			for (Player p : RegionAPI.getPlayersInRegion(Region.MINALAPIS)) {
				p.sendMessage(msg);
			}
			break;
		case VIP:
			for (Player p : RegionAPI.getPlayersInRegion(Region.MINAVIP)) {
				if(p != null)
				p.sendMessage(msg);
			}
			break;
		}
	}

	public static ArrayList<Location> getArea(Location l1, Location l2){
		double x1 = l1.getX();
		double y1 = l1.getY();
		double z1 = l1.getZ();
		double x2 = l2.getX();
		double y2 = l2.getY();
		double z2 = l2.getZ();

		double xMin = Math.min(x1, x2);
		double yMin = Math.min(y1, y2);
		double zMin = Math.min(z1, z2);

		double xMax = Math.max(x1, x2);
		double yMax = Math.max(y1, y2);
		double zMax = Math.max(z1, z2);

		ArrayList<Location> locs = new ArrayList<Location>();

		for (int x = (int) xMin; x < xMax; x++) {
			for (int y = (int) yMin; y < yMax; y++) {
				for (int z = (int) zMin; z < zMax; z++) {
					Location l = new Location(l1.getWorld(), x, y, z);
					locs.add(l);
				}
			}
		}
		return locs;
	}
	
	public static void generateSquareBlocks(Location l1, Location l2, long delayPerBlock, Material materialType) {

		double x1 = l1.getX();
		double y1 = l1.getY();
		double z1 = l1.getZ();
		double x2 = l2.getX();
		double y2 = l2.getY();
		double z2 = l2.getZ();

		double xMin = Math.min(x1, x2);
		double yMin = Math.min(y1, y2);
		double zMin = Math.min(z1, z2);

		double xMax = Math.max(x1, x2);
		double yMax = Math.max(y1, y2);
		double zMax = Math.max(z1, z2);

		ArrayList<Location> locs1 = new ArrayList<Location>();
		ArrayList<Location> locs2 = new ArrayList<Location>();
		ArrayList<Location> locs3 = new ArrayList<Location>();
		ArrayList<Location> locs4 = new ArrayList<Location>();
		ArrayList<Location> locs5 = new ArrayList<Location>();
		ArrayList<Location> locs6 = new ArrayList<Location>();
		ArrayList<Location> locs7 = new ArrayList<Location>();
		ArrayList<Location> locs8 = new ArrayList<Location>();
		ArrayList<Location> locs9 = new ArrayList<Location>();
		ArrayList<Location> locs10 = new ArrayList<Location>();
		ArrayList<Location> locs11 = new ArrayList<Location>();
		ArrayList<Location> locs12 = new ArrayList<Location>();
		ArrayList<Location> locs13 = new ArrayList<Location>();
		ArrayList<Location> locs14 = new ArrayList<Location>();
		ArrayList<Location> locs15 = new ArrayList<Location>();
		ArrayList<Location> locs16 = new ArrayList<Location>();
		ArrayList<Location> locs17 = new ArrayList<Location>();
		ArrayList<Location> locs18 = new ArrayList<Location>();
		ArrayList<Location> locs19 = new ArrayList<Location>();
		ArrayList<Location> locs20 = new ArrayList<Location>();

		for (int x = (int) xMin; x < xMax; x++) {
			for (int z = (int) zMin; z < zMax; z++) {
				for (int y = (int) yMin; y < yMax; y++) {
					Location cube = new Location(l1.getWorld(), x, y, z);
					cube.getBlock().setType(Material.AIR);
					switch (cube.getBlockY()) {
					case 1:
						locs1.add(cube);
					case 2:
						locs2.add(cube);
					case 3:
						locs3.add(cube);
					case 4:
						locs4.add(cube);
					case 5:
						locs5.add(cube);
					case 6:
						locs6.add(cube);
					case 7:
						locs7.add(cube);
					case 8:
						locs8.add(cube);
					case 9:
						locs9.add(cube);
					case 10:
						locs10.add(cube);
					case 11:
						locs11.add(cube);
					case 12:
						locs12.add(cube);
					case 13:
						locs13.add(cube);
					case 14:
						locs14.add(cube);
					case 15:
						locs15.add(cube);
					case 16:
						locs16.add(cube);
					case 17:
						locs17.add(cube);
					case 18:
						locs18.add(cube);
					case 19:
						locs19.add(cube);
					case 20:
						locs20.add(cube);

					}
				}
			}
		}
//18
		new BukkitRunnable() {

			int height = 0;

			public void run() {
				if (height < 20) {
					height = height + 1;
					for(Player p : RegionAPI.getPlayersInRegion(Region.MINALAPIS)) {
						p.playSound(l1, Sound.ANVIL_USE, 0.5F, 1);
					}
					switch (height) {
					case 1:
						for (Location l : locs1) {
							l.getBlock().setType(materialType);
						}
						break;
					case 2:
						for (Location l : locs2) {
							l.getBlock().setType(materialType);
						}
						break;
					case 3:
						for (Location l : locs3) {
							l.getBlock().setType(materialType);
						}
						break;
					case 4:
						for (Location l : locs4) {
							l.getBlock().setType(materialType);
						}
						break;
					case 5:
						for (Location l : locs5) {
							l.getBlock().setType(materialType);
						}
						break;
					case 6:
						for (Location l : locs6) {
							l.getBlock().setType(materialType);
						}
						break;
					case 7:
						for (Location l : locs7) {
							l.getBlock().setType(materialType);
						}
						break;
					case 8:
						for (Location l : locs8) {
							l.getBlock().setType(materialType);
						}
						break;
					case 9:
						for (Location l : locs9) {
							l.getBlock().setType(materialType);
						}
						break;
					case 10:
						for (Location l : locs10) {
							l.getBlock().setType(materialType);
						}
						break;
					case 11:
						for (Location l : locs11) {
							l.getBlock().setType(materialType);
						}
						break;
					case 12:
						for (Location l : locs12) {
							l.getBlock().setType(materialType);
						}
						break;
					case 13:
						for (Location l : locs13) {
							l.getBlock().setType(materialType);
						}
						break;
					case 14:
						for (Location l : locs14) {
							l.getBlock().setType(materialType);
						}
						break;
					case 15:
						for (Location l : locs15) {
							l.getBlock().setType(materialType);
						}
						break;
					case 16:
						for (Location l : locs16) {
							l.getBlock().setType(materialType);
						}
						break;
					case 17:
						for (Location l : locs17) {
							l.getBlock().setType(materialType);
						}
						break;
					case 18:
						for (Location l : locs18) {
							l.getBlock().setType(materialType);
						}
						break;
					case 19:
						for (Location l : locs19) {
							l.getBlock().setType(materialType);
						}
						break;
					case 20:
						for (Location l : locs20) {
							l.getBlock().setType(materialType);
						}
						break;
					}
				} else {
					cancel();
				}

			}
		}.runTaskTimer(Core.getInstance(), 0, delayPerBlock);
	}

}
