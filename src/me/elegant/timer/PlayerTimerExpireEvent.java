package me.elegant.timer;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerTimerExpireEvent extends Event {
	
	private UUID uuid;
	private String timer;
	
	public PlayerTimerExpireEvent(UUID uuid, String timer) {
		this.uuid = uuid;
		this.timer = timer;
	}
	
	public UUID getUUID() {
		return uuid;
	}
	public Player toPlayer() {
		return Bukkit.getPlayer(uuid);
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

