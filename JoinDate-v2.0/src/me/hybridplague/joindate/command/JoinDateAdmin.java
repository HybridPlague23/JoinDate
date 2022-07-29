package me.hybridplague.joindate.command;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.hybridplague.joindate.JoinDate;
import me.hybridplague.joindate.JoinDateUtils;

public class JoinDateAdmin implements CommandExecutor {

	JoinDate main = JoinDate.getPlugin(JoinDate.class);
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		/*if (!(sender instanceof Player))
			return false;
		Player p = (Player) sender;*/

		if (sender instanceof Player) {
			if (!sender.hasPermission("joindate.admin")) {
				sender.sendMessage(JoinDateUtils.errorNoPerms());
				return true;
			}
		}
		
		if (args.length < 5) {
			sender.sendMessage(JoinDateUtils.errorMissingAdminArgs());
			return true;
		} else {
			
			if (args[0].equalsIgnoreCase("set")) {
				
				for (int i = 3; i < 5; i++) {
					if (!JoinDateUtils.isNum(args[i])) {
						/*
						 * one of the args is NaN
						 */
						sender.sendMessage(JoinDateUtils.errorInvalidAdminArg());
						return true;
					}
				}
				OfflinePlayer op = Bukkit.getOfflinePlayer(args[1]);
				if (!op.hasPlayedBefore() && !op.isOnline()) {
					sender.sendMessage(JoinDateUtils.errorInvalidPlayer());
					return true;
				} else {
					
					UUID id = op.getUniqueId();
					
					//int month = Integer.parseInt(args[2]);
					
					String month = String.valueOf(args[2].toLowerCase());
					
					int day = Integer.parseInt(args[3]);
					int year = Integer.parseInt(args[4]);
					
					String m = "";
					
					switch(month.toLowerCase()) {
					case "1", "jan", "january":
						m = "Jan";
						break;
					case "2", "feb", "february":
						m = "Feb";
						break;
					case "3", "mar", "march":
						m = "Mar";
						break;
					case "4", "apr", "april":
						m = "Apr";
						break;
					case "5", "may":
						m = "May";
						break;
					case "6", "jun", "june":
						m = "Jun";
						break;
					case "7", "jul", "july":
						m = "Jul";
						break;
					case "8", "aug", "august":
						m = "Aug";
						break;
					case "9", "sep", "september":
						m = "Sep";
						break;
					case "10", "oct", "october":
						m = "Oct";
						break;
					case "11", "nov", "november":
						m = "Nov";
						break;
					case "12", "dec", "december":
						m = "Dec";
						break;
					default:
						sender.sendMessage(JoinDateUtils.errorInvalidMonth());
						return true;
					}
					
					if (day > 31 || day < 1) {
						sender.sendMessage(JoinDateUtils.errorInvalidDay());
						return true;
					}
					
					if (year < 2016) {
						sender.sendMessage(JoinDateUtils.errorInvalidYear1());
						return true;
					}
					
					Date date = new Date();
					SimpleDateFormat sd = new SimpleDateFormat("yyyy");
					
					int currentYear = Integer.parseInt(sd.format(date));
					if (year > currentYear) {
						sender.sendMessage(JoinDateUtils.errorInvalidYear2(currentYear));
						return true;
					}
					
					String d = m + " " + day + ", " + year;
					JoinDateUtils.setJoinDate(sender, id, d);
					return true;
				}
				
				
			}
			
		}
		
		
		return true;
	}
	
	
}
