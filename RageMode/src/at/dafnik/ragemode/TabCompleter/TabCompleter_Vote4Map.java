package at.dafnik.ragemode.TabCompleter;

import java.util.List;

import at.dafnik.ragemode.Main.Library;
import at.dafnik.ragemode.Main.Main;
import at.dafnik.ragemode.Main.Main.Status;

public class TabCompleter_Vote4Map extends AbstractTabCompleter{

	@Override
	public List<String> returnList() {
		return Library.mapstovote;
	}
	
	@Override
	public String commandString() {
		return "vote";
	}
	
	@Override
	public int argumentsMaxInt() {
		return 2;
	}
	
	@Override
	public int argumentsMinInt() {
		return 0;
	}
	
	@Override
	public Boolean Status() {
		if(Main.status == Status.LOBBY) return true;
		else return false;
	}
}
