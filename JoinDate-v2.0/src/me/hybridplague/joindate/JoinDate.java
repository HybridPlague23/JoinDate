package me.hybridplague.joindate;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

import me.hybridplague.joindate.command.JoinDateAdmin;
import me.hybridplague.joindate.command.JoinDateCommand;
import me.hybridplague.joindate.data.PlayerFiles;
import me.hybridplague.joindate.events.JoinDateTabCompleter;
import me.hybridplague.joindate.events.JoinListener;

public class JoinDate extends JavaPlugin {

	public PlayerFiles data;
	
	@Override
	public void onEnable() {
		this.getCommand("joindate").setExecutor(new JoinDateCommand());
		this.getCommand("jd").setExecutor(new JoinDateCommand());
		
		this.getCommand("joindateadmin").setExecutor(new JoinDateAdmin());
		this.getCommand("joindateadmin").setTabCompleter(new JoinDateTabCompleter());
		this.getCommand("jda").setExecutor(new JoinDateAdmin());
		this.getCommand("jda").setTabCompleter(new JoinDateTabCompleter());
		
		this.data = new PlayerFiles(this);
		this.getServer().getPluginManager().registerEvents(new JoinListener(this), this);
		
		if (!this.getDataFolder().exists()) {
			this.getDataFolder().mkdir();
		}
		
		File players = new File(this.getDataFolder(), "players");
		if (!players.exists()) {
			players.mkdirs();
		}
		
		super.onEnable();
	}
	
	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		super.onDisable();
	}
	
}
