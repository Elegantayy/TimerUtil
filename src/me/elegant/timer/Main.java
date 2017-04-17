package me.elegant.timer;

import org.bukkit.plugin.java.JavaPlugin;

import me.elegant.timer.command.LogoutCommand;
import me.elegant.timer.command.SOTWCommand;
import me.elegant.timer.type.LogoutTimer;
import me.elegant.timer.type.SOTWTimer;

public class Main extends JavaPlugin {
	
	public static Main instance;
	private PlayerTimer playerTimer;
	private GlobalTimer globalTimer;
	
	@Override
	public void onEnable() {
		instance = this;
		playerTimer = new PlayerTimer();
		globalTimer = new GlobalTimer();
		getCommand("logout").setExecutor(new LogoutCommand());
		getCommand("sotw").setExecutor(new SOTWCommand());
		getServer().getPluginManager().registerEvents(new LogoutTimer(), this);
		getServer().getPluginManager().registerEvents(new SOTWTimer(), this);
		getPlayerTimerManager().startTimerRunnable();
		getGlobalTimerManager().startTimerRunnable();
	}
	public static Main getInstance() {
		return instance;
	}
	public PlayerTimer getPlayerTimerManager() {
		return playerTimer;
	}
	public GlobalTimer getGlobalTimerManager() {
		return globalTimer;
	}
}
