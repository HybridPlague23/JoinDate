package me.hybridplague.joindate.command;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.hybridplague.joindate.JoinDateUtils;

public class JoinDateCommand implements CommandExecutor {

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		/*if (!(sender instanceof Player))
			return false;
		Player p = (Player) sender;*/
		
		if (args.length == 0) {
			sender.sendMessage(JoinDateUtils.errorNoArg());
			return true;
		} else {
			OfflinePlayer op = Bukkit.getOfflinePlayer(args[0]);
			UUID id = op.getUniqueId();
			
			if (!op.hasPlayedBefore() && !op.isOnline()) {
				sender.sendMessage(JoinDateUtils.errorInvalidPlayer());
				return true;
			}
			
			sender.sendMessage(JoinDateUtils.getJoinDate(sender, id));
		}
		

		return true;
	}
	
}
