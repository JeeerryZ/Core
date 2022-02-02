package me.jerryz.coreplugin.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WorldListener implements Listener{
	
	@EventHandler
	public void onRain(WeatherChangeEvent e) {
		e.setCancelled(e.toWeatherState());
	}

}
