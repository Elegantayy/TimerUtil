package me.elegant.timer.type;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.elegant.timer.Main;
import me.elegant.timer.PlayerTimerExpireEvent;

public class LogoutTimer implements Listener {
	
	@EventHandler
	public void onExpire(PlayerTimerExpireEvent e) {
		Player p = e.toPlayer();
		if(p != null && e.getTimerName().equalsIgnoreCase("logout")) {
			p.kickPlayer(ChatColor.GREEN + "You have been safely logged out.");
		}
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Location f = e.getFrom();
		Location t = e.getTo();
		Player p = e.getPlayer();
		if(f.getBlockX() != t.getBlockX() && f.getBlockX() != t.getBlockZ()) {
			if(Main.getInstance().getPlayerTimerManager().hasTimer(p.getUniqueId(), "logout")) {
				p.sendMessage(ChatColor.RED + "Logout cancelled!");
				Main.getInstance().getPlayerTimerManager().stopTimer(p, "logout");
			}
		}
	}
}
