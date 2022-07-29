package me.hybridplague.joindate.events;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class JoinDateTabCompleter implements TabCompleter {

	List<String> set = new ArrayList<String>();
	List<String> month = new ArrayList<String>();
	List<Integer> day = new ArrayList<Integer>();
	List<Integer> year = new ArrayList<Integer>();
	
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (set.isEmpty()) {
			set.add("set");
		}
		
		if (month.isEmpty()) {
			for (int i = 1; i < 13; i++) {
				month.add(String.valueOf(i));
			}
			month.add("Jan"); month.add("January");
			month.add("Feb"); month.add("February");
			month.add("Mar"); month.add("March");
			month.add("Apr"); month.add("April");
			month.add("May");
			month.add("Jun"); month.add("June");
			month.add("July"); month.add("July");
			month.add("Aug"); month.add("August");
			month.add("Oct"); month.add("October");
			month.add("Sep"); month.add("September");
			month.add("Nov"); month.add("November");
			month.add("Dec"); month.add("December");
		}
		
		if (day.isEmpty()) {
			for (int i = 1; i < 32; i++) {
				day.add(i);
			}
		}
		
		if (year.isEmpty()) {
			Date date = new Date();
			SimpleDateFormat sd = new SimpleDateFormat("yyyy");
			
			int currentYear = Integer.parseInt(sd.format(date)) + 1;
			for (int i = 2016; i < currentYear; i++) {
				year.add(i);
			}
		}
		
		List<String> result = new ArrayList<String>();
		
		if (args.length == 1) {
			for (String a : set) {
				if (a.toLowerCase().startsWith(args[0].toLowerCase())) {
					result.add(a);
				}
			}
			return result;
		}
		if (args.length == 3) {
			for (String a : month) {
				if (a.toLowerCase().startsWith(args[2].toLowerCase())) {
					result.add(a);
				}
			}
			return result;
		}
		if (args.length == 4) {
			for (Integer a : day) {
				if (a.toString().startsWith(args[3])) {
					result.add(a.toString());
				}
			}
			return result;
		}
		if (args.length == 5) {
			for (Integer a : year) {
				if (a.toString().startsWith(args[4])) {
					result.add(a.toString());
				}
			}
			return result;
		}
		return null;
	}
	
}
