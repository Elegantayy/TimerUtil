package me.elegant.timer.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.elegant.timer.Main;
import net.minecraft.util.com.google.common.primitives.Ints;
import net.minecraft.util.org.apache.commons.lang3.time.DurationFormatUtils;

public class SOTWCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("sotw")) {
			if(!sender.hasPermission("timers.sotw")) {
				sender.sendMessage(ChatColor.RED + "You don't have permission.");
				return true;
			}
			if(args.length == 0) {
				showHelp(sender);
				return true;
			}
			if(args.length == 1) {
				if(args[0].equalsIgnoreCase("start")) {
					sender.sendMessage(ChatColor.RED + "Usage: /sotw start <duration>");
					return true;
				}
				else if(args[0].equalsIgnoreCase("set")) {
					sender.sendMessage(ChatColor.RED + "Usage: /sotw set <duration>");
					return true;
			}
				else if(args[0].equalsIgnoreCase("pause")) {
					if(!Main.getInstance().getGlobalTimerManager().isActive("sotw")) {
						sender.sendMessage(ChatColor.RED + "This timer is not active!");
						return true;
					}
					if(!Main.getInstance().getGlobalTimerManager().isPaused("sotw")) {
						Main.getInstance().getGlobalTimerManager().setPaused("sotw", true);
						Bukkit.broadcastMessage(ChatColor.YELLOW + "SOTW has been paused.");
						return true;
					}else{
						Main.getInstance().getGlobalTimerManager().setPaused("sotw", false);
						Bukkit.broadcastMessage(ChatColor.YELLOW + "SOTW has been resumed.");
						return true;
					}
				}
				else if(args[0].equalsIgnoreCase("stop")) {
					if(!Main.getInstance().getGlobalTimerManager().isActive("sotw")) {
						sender.sendMessage(ChatColor.RED + "This timer is not active!");
						return true;
					}
					else{
						Main.getInstance().getGlobalTimerManager().stopTimer("sotw");
						Bukkit.broadcastMessage(ChatColor.RED + "SOTW has been cancelled!");
						return true;
					}
				}else{
					showHelp(sender);
				}
		}
			if(args.length == 2) {
				if(args[0].equalsIgnoreCase("start")) {
					if(Main.getInstance().getGlobalTimerManager().isActive("sotw")) {
						sender.sendMessage(ChatColor.RED + "This timer is already active!");
						return true;
					}
					if(Ints.tryParse(args[1]) == null || Ints.tryParse(args[1]) < 0) {
						sender.sendMessage(ChatColor.RED + "Invalid duration.");
						return true;
					}
					int i = Integer.parseInt(args[1]);
					Main.getInstance().getGlobalTimerManager().setTimer("sotw", i * 60);
					Bukkit.broadcastMessage(ChatColor.GREEN + "SOTW has started for " + DurationFormatUtils.formatDurationWords((i *60) *1000, true, true) + ".");
				}
				else{
					showHelp(sender);
				}
			}
		}
		return false;
	}
	public void showHelp(CommandSender sender) {
		sender.sendMessage(ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "SOTW Help" + ChatColor.GRAY + ":");
		sender.sendMessage(ChatColor.YELLOW + "  /sotw start <duration> " + ChatColor.GRAY + "-" + ChatColor.WHITE + " Start the SOTW timer.");
		sender.sendMessage(ChatColor.YELLOW + "  /sotw stop " + ChatColor.GRAY + "-" + ChatColor.WHITE + " Cancel the SOTW timer.");
		sender.sendMessage(ChatColor.YELLOW + "  /sotw pause " + ChatColor.GRAY + "-" + ChatColor.WHITE + " Pause the SOTW timer.");
	}
}
