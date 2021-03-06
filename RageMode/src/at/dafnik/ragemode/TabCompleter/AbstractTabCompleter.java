package at.dafnik.ragemode.TabCompleter;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import at.dafnik.ragemode.API.Strings;

public abstract class AbstractTabCompleter implements TabCompleter{

	public String commandString() {
		return null;
	}
	
	public int argumentsMaxInt() {
		return 0;
	}
	
	public int argumentsMinInt() {
		return 0;
	}
	
	public List<String> returnList() {
		return null;
	}
	
	public Boolean Status() {
		return true;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] arguments) {
		if(command.getName().equalsIgnoreCase(commandString()) && arguments.length > argumentsMinInt() && arguments.length < argumentsMaxInt()) {
			if(sender instanceof Player) {
				if(Status()) {
					List<String> list = returnList();
					if(list == null) {
						list = new ArrayList<>();
					}
					
					return list;
				}
			} else {
				System.out.println(Strings.error_only_player_use);
				return null;
			}
		}
		return null;
	}
}
