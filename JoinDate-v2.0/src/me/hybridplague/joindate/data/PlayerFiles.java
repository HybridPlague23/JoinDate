package me.hybridplague.joindate.data;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.OfflinePlayer;

import me.hybridplague.joindate.JoinDate;
import me.hybridplague.joindate.JoinDateUtils;

public class PlayerFiles {

	JoinDate main;
	public PlayerFiles(JoinDate main) {
		this.main = main;
	}
	
	private Logger log = Logger.getLogger("JoinDate");
	
	public void fileManager(final OfflinePlayer p) {
		
		File playerData = new File(main.getDataFolder(), "players/" + p.getUniqueId().toString() + ".yml");
		
		if (!playerData.exists()) {
			try {
				
				log.info(JoinDateUtils.format("&aPlayer File created for &e" + p.getName() + "&a."));
				playerData.createNewFile();
				
			} catch (Exception ex) {
				
				log.info(JoinDateUtils.format("&cPlayer File could not be created for &e" + p.getName() + "&a."));
				ex.printStackTrace();
				
				return;
			}
		}
	}
}
