package me.elegant.timer;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.scheduler.BukkitRunnable;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class PlayerTimer {

	private Table<UUID, String, Integer> timers = HashBasedTable.create();
	private Table<UUID, String, Boolean> pausedTimers = HashBasedTable.create();

	public int getRemainingInSeconds(OfflinePlayer p, String timer) {
		return timers.get(p.getUniqueId(), timer);
	}

	public int getRemainingInSeconds(UUID u, String timer) {
		return timers.get(u, timer);
	}

	public boolean hasTimer(UUID u, String timer) {
		return timers.contains(u, timer) && timers.get(u, timer) > -1;
	}

	public void setTimer(OfflinePlayer p, String timer, int seconds) {
		timers.put(p.getUniqueId(), timer, seconds);
	}

	public boolean isPaused(OfflinePlayer p, String timer) {
		if(pausedTimers.contains(p.getUniqueId(), timer)) {
			return true;
		}
		return false;
	}

	public boolean isPaused(UUID u, String timer) {
		if(pausedTimers.contains(u, timer)) {
			return true;
		}
		return false;
	}

	public void stopTimer(OfflinePlayer p, String timer) {
		if(timers.contains(p.getUniqueId(), timer)) {
			timers.remove(p.getUniqueId(), timer);
			return;
		}else{
			return;
		}
	}

	public void setPaused(OfflinePlayer p, String timer, boolean paused) {
		if(paused) {
			pausedTimers.put(p.getUniqueId(), timer, paused);
		}else{
			pausedTimers.remove(p.getUniqueId(), timer);
		}
	}

	public void startTimerRunnable() {
		new BukkitRunnable() {
			public void run() {
				if(timers.rowKeySet().size() > 0) {
				for(UUID u : timers.rowKeySet()) {
					if(timers.columnKeySet().size() > 0) {
					for(String s : timers.columnKeySet()) {
						if (timers.contains(u, s) && !isPaused(u, s)) {
							timers.put(u, s, timers.get(u, s) - 1);
							if (timers.get(u, s) < 0) {
								Bukkit.getPluginManager().callEvent(new PlayerTimerExpireEvent(u, s));
								timers.remove(u, s);
							}
						}
					}
					}
				}
				}
			}
		}.runTaskTimer(Main.getInstance(), 20, 20);
	}
}
