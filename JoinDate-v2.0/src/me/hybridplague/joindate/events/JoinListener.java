package me.hybridplague.joindate.events;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.hybridplague.joindate.JoinDate;
import me.hybridplague.joindate.JoinDateUtils;
import net.md_5.bungee.api.ChatColor;

public class JoinListener implements Listener {

	JoinDate main;
	public JoinListener(JoinDate main) {
		this.main = main;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		
		File dataFile = new File(main.getDataFolder(), "players/" + p.getUniqueId().toString() + ".yml");
		YamlConfiguration playerConfig = new YamlConfiguration();
		
		Date date = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("MMM dd, yyyy");
		
		if (!p.hasPlayedBefore()) {
			main.data.fileManager(p);
			
			try {
				playerConfig.load(dataFile);
				playerConfig.set("date", sd.format(date));
				playerConfig.save(dataFile);
			} catch (Exception ex) {}
			return;
		} else {
			/*
			 * player has joined before, check if file exists
			 * if not, notify the player that they do not have new joindate data
			 * contact an administrator to have it set
			 * 
			 * can still use /oldjoindate /ojd to check their date
			 * if it is incorrect, notify an admin that it is incorrect, they can find it from there
			 * 
			 */
			try {
				/*
				 * Successfully loaded file
				 */
				playerConfig.load(dataFile);
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully loaded your file!"));
			} catch (Exception ex) {
				/*
				 * Failed to find file
				 */
				Bukkit.getScheduler().runTaskLater(main, new Runnable() {
					public void run() {
						p.sendMessage(JoinDateUtils.errorNoFile());
					}
				}, 60);
				return;
			}
			
		}
	}
	
	
	
}
