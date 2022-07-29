package me.hybridplague.joindate;

import java.io.File;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class JoinDateUtils {

	public static String prefix = "&6&lJoinDate &8⼁ &7";
	
	static JoinDate main = JoinDate.getPlugin(JoinDate.class);

	public static String getJoinDate(CommandSender p, UUID id) {
		String date = "";
		
		File dataFile = new File(main.getDataFolder(), "players/" + id.toString() + ".yml");
		YamlConfiguration playerConfig = new YamlConfiguration();
		
		try {
			
			playerConfig.load(dataFile);
			if (p instanceof Player) {
				if (((Player) p).getUniqueId() == id) {
					date = format(prefix + "&7Your JoinDate is: &e" + playerConfig.getString("date"));
				} else {
					date = format(prefix + "&e" + Bukkit.getOfflinePlayer(id).getName() + "&7's JoinDate is: &e" + playerConfig.getString("date"));
				}
			} 
			
		} catch (Exception ex) {
			
			date = errorPlayerNoFile(p, Bukkit.getOfflinePlayer(id));
		}
		
		return date;
	}
	
	public static void setJoinDate(CommandSender p, UUID id, String date) {
		
		OfflinePlayer op = Bukkit.getOfflinePlayer(id);
		
		File dataFile = new File(main.getDataFolder(), "players/" + id.toString() + ".yml");
		YamlConfiguration playerConfig = new YamlConfiguration();
		
		try {
			
			playerConfig.load(dataFile);
			playerConfig.set("date", date);
			playerConfig.save(dataFile);
			
			p.sendMessage(format(prefix + "&aYou have successfully set &e" + op.getName() + "&a's JoinDate to &e" + date));
			
			return;
			
		} catch (Exception ex) {
			
			/*
			 * not found, so create and set it
			 */
			
			main.data.fileManager(Bukkit.getOfflinePlayer(id));
			
			try {
				
				playerConfig.load(dataFile);
				playerConfig.set("date", date);
				playerConfig.save(dataFile);
				
				p.sendMessage(format(prefix + "&aYou have successfully set &e" + op.getName() + "&a's JoinDate to &e" + date));
				
			} catch (Exception ex2) {
				// this should not trigger an error
				ex2.printStackTrace();
			}
			
		}
		
	}
	
	public static String errorNoFile() {
		
		String a = format("&6• &eThere was an error trying to load your JoinDate data file!");
		String b = format("&6• &ePlease fill out this Google Forms so an Administrator can fix this ASAP! https://forms.gle/kLCM3FLUjXttLZbs8");
		
		return a + "\n" + b;
		
	}
	
	public static String errorNoPerms() {
		return format("&cInsufficient Permission");
	}
	
	public static String errorPlayerNoFile(CommandSender p, OfflinePlayer op) {
		if (p instanceof Player) {
			if (p.getName() == op.getName()) {
				return format(prefix + "&c&lERROR &7Hm.. It seems your data file does not exist. Contact an Administrator to get this fixed!");
			} else {
				return format(prefix + "&c&lERROR &7Hm.. It seems &e" + op.getName() + "&7's data file does not exist. Contact an Administrator to get this fixed!");
			}
		} else {
			return format(prefix + "&c&lERROR &7Hm.. It seems &e" + op.getName() + "&7's data file does not exist. Contact an Administrator to get this fixed!");
		}
		
	}
	
	public static String errorNoArg() {
		return format(prefix + "/jd &c<player>");
	}
	
	public static String errorInvalidAdminArg() {
		return format(prefix + "&cInvalid Argument! &8--> &e/jda set &c<player> <month> <day> <year>");
	}
	
	public static String errorMissingAdminArgs() {
		return format(prefix + "/jda set &c<player> <month> <day> <year>");
	}
	
	public static String errorInvalidYear2(int currentYear) {
		return format(prefix + "&cOh come on, be real, you cannot exceed &e" + String.valueOf(currentYear) + "&c.");
	}
	
	public static String errorInvalidYear1() {
		return format(prefix + "&cImpossible, the server was released in &e2016&c.");
	}
	
	public static String errorInvalidMonth() {
		return format(prefix + "&cSeriously, you cannot start creating months now, time moves slow enough. &e&o(Use 1-12)");
	}
	
	public static String errorInvalidDay() {
		return format(prefix + "&cSeriously, you cannot start creating days now, time moves slow enough. &e&o(Use 1-31)");
	}
	
	public static String errorInvalidPlayer() {
		return format(prefix + "Player not found.");
	}
	
	public static String format(String text) {
		return ChatColor.translateAlternateColorCodes('&', text);
	}
	
	
	public static boolean isNum(String num) {
		try {
			Integer.parseInt(num);
		} catch (Exception e) {
			return false;
		}
		return true;
		
	}
	
}
