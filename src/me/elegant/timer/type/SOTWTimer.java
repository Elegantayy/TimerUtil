package me.elegant.timer.type;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import me.elegant.timer.GlobalTimerExpireEvent;
import me.elegant.timer.Main;

public class SOTWTimer implements Listener {
	
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if(e.getEntity() instanceof Player) {
			if(Main.getInstance().getGlobalTimerManager().isActive("sotw")) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onExpire(GlobalTimerExpireEvent e) {
		if(e.getTimerName().equalsIgnoreCase("sotw")) {
			Bukkit.broadcastMessage(ChatColor.RED.toString() + ChatColor.BOLD + "SOTW is over, you can now take and deal damage!");
		}
	}
}
