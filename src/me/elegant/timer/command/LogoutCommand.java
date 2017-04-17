package me.elegant.timer.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.elegant.timer.Main;
import net.md_5.bungee.api.ChatColor;

public class LogoutCommand implements CommandExecutor  {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("logout")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "You're not a player!");
				return true;
			}
			Player p = (Player) sender;
			if(Main.getInstance().getPlayerTimerManager().hasTimer(p.getUniqueId(), "logout")) {
				p.sendMessage(ChatColor.RED + "You are already logging out.");
				return true;
			}
			Main.getInstance().getPlayerTimerManager().setTimer(p, "logout", 30);
			p.sendMessage(ChatColor.RED + "Logging out in 30 seconds...");
		}
		return false;
	}

}
