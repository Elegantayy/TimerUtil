package me.elegant.timer;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class GlobalTimerExpireEvent extends Event {
	
	private String timer;
	
	public GlobalTimerExpireEvent(String timer) {
		this.timer = timer;
	}

	public String getTimerName() {
		return timer;
	}

	private static final HandlerList handlers = new HandlerList();
	
	public HandlerList getHandlers() {
		return handlers;
	}
	public static HandlerList getHandlerList() {
		return handlers;
	}
}

