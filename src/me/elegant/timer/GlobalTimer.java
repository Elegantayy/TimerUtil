package me.elegant.timer;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class GlobalTimer {
	
	private HashMap<String, Integer> timers = new HashMap<String, Integer>();
	private ArrayList<String> pausedTimers = new ArrayList<String>();
	
	public int getRemainingInSeconds(String timer) {
		return timers.get(timer);
	}
	public boolean isActive(String timer) {
		return timers.containsKey(timer) && timers.get(timer) > -1;
	}
	public void setTimer(String timer, int seconds) {
		timers.put(timer, seconds);
	}
	public boolean isPaused(String timer) {
		if(pausedTimers.contains(timer)) {
			return true;
		}
		return false;
	}
	public void stopTimer(String timer) {
		if(timers.containsKey(timer)) {
			timers.remove(timer);
			return;
		}else{
			return;
		}
	}
	public void setPaused(String timer, boolean paused) {
		if(paused) {
		pausedTimers.add(timer);
		}else{
			pausedTimers.remove(timer);
		}
	}
	
	public void startTimerRunnable() {
		new BukkitRunnable() {
			public void run() {
				if(timers.keySet().size() > 0) {
					for(String s : timers.keySet()) {
					if(timers.containsKey(s) && !isPaused(s)) {
						timers.put(s, timers.get(s) -1);
						if(timers.get(s) < 0) {
							Bukkit.getPluginManager().callEvent(new GlobalTimerExpireEvent(s));
							timers.remove(s);
						}
					}
					}
				}
				}
		}.runTaskTimer(Main.getInstance(), 20, 20);
	}
}
