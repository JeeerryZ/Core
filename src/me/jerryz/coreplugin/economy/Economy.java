package me.jerryz.coreplugin.economy;

public class Economy {

	private double money;
	private String name;
	
	public Economy(double money, String name) {
		this.money = money;
		this.name = name;
	}
	
	public String getPlayer() {
		return this.name;
	}
	
	public double getMoney() {
		return this.money;
	}
	
	public void setMoney(double money) {
		this.money = money;
	}
	
}
