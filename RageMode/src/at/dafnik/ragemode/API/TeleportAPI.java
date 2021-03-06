package at.dafnik.ragemode.API;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import at.dafnik.ragemode.Main.Library;
import at.dafnik.ragemode.Main.Main;

public class TeleportAPI {
	
	public static Location getLobbyLocation() {
		Location location = null;

		String w = Main.getInstance().getConfig().getString("ragemode.lobbyspawn.world");
		double x = Main.getInstance().getConfig().getDouble("ragemode.lobbyspawn.x");
		double y = Main.getInstance().getConfig().getDouble("ragemode.lobbyspawn.y");
		double z = Main.getInstance().getConfig().getDouble("ragemode.lobbyspawn.z");
		double yaw = Main.getInstance().getConfig().getDouble("ragemode.lobbyspawn.yaw");
		double pitch = Main.getInstance().getConfig().getDouble("ragemode.lobbyspawn.pitch");
		
		if(w != null) {
			location = new Location(Bukkit.getWorld(w), x, y, z);
			location.setYaw((float) yaw);
			location.setPitch((float) pitch);
		}

		return location;
	}
	
	public static Location getVillagerShopLocation() {
		Location location = null;

		String w = Main.getInstance().getConfig().getString("ragemode.villagershopspawn.world");
		double x = Main.getInstance().getConfig().getDouble("ragemode.villagershopspawn.x");
		double y = Main.getInstance().getConfig().getDouble("ragemode.villagershopspawn.y");
		double z = Main.getInstance().getConfig().getDouble("ragemode.villagershopspawn.z");
		
		if(w != null) {
			location = new Location(Bukkit.getWorld(w), x, y, z);
		}

		if(location == null) System.out.println(Strings.error_not_existing_villagerspawn);
		return location;
	}
	
	public static Location getRandomMapSpawnLocations() {
		try {
			int spawnnumber = Main.getInstance().getConfig().getInt("ragemode.mapspawn." +  Library.votedmap + ".spawnnumber");
			Random r = new Random();
			int randomzahl = r.nextInt(spawnnumber);
			Location location = null;
			
			for(int i = 0; i < 40; i++) {
				String w = Main.getInstance().getConfig().getString("ragemode.mapspawn." + Library.votedmap + "." + randomzahl + ".world");
				double x = Main.getInstance().getConfig().getDouble("ragemode.mapspawn." + Library.votedmap + "." + randomzahl + ".x");
				double y = Main.getInstance().getConfig().getDouble("ragemode.mapspawn." + Library.votedmap + "." + randomzahl + ".y");
				double z = Main.getInstance().getConfig().getDouble("ragemode.mapspawn." + Library.votedmap + "." + randomzahl + ".z");
				double yaw = Main.getInstance().getConfig().getDouble("ragemode.mapspawn." + Library.votedmap + "." + randomzahl + ".yaw");
				double pitch = Main.getInstance().getConfig().getDouble("ragemode.mapspawn." + Library.votedmap + "." + randomzahl + ".pitch");
				
				if(w != null) {
					location = new Location(Bukkit.getWorld(w), x, y, z);
					location.setYaw((float)yaw);
					location.setPitch((float)pitch);
					break;
				}
			}
			
			return location;
		} catch (IllegalArgumentException e) {
			System.out.println(Strings.error_no_map);
			return getLobbyLocation();
		}
	}
	
	public static Location getMapSpawnLocation(String wantto, String mapname) {
		Location location = null;
		
		for(int i = 0; i < 40; i++) {
			String w = Main.getInstance().getConfig().getString("ragemode.mapspawn." + mapname + "." + wantto + ".world");
			double x = Main.getInstance().getConfig().getDouble("ragemode.mapspawn." + mapname + "." + wantto + ".x");
			double y = Main.getInstance().getConfig().getDouble("ragemode.mapspawn." + mapname + "." + wantto + ".y");
			double z = Main.getInstance().getConfig().getDouble("ragemode.mapspawn." + mapname + "." + wantto + ".z");
			double yaw = Main.getInstance().getConfig().getDouble("ragemode.mapspawn." + mapname + "." + wantto + ".yaw");
			double pitch = Main.getInstance().getConfig().getDouble("ragemode.mapspawn." + mapname + "." + wantto + ".pitch");
			
			if(w != null) {
				location = new Location(Bukkit.getWorld(w), x, y, z);
				location.setYaw((float)yaw);
				location.setPitch((float)pitch);
				break;
			}
		}
		
		return location;
	}
	
	public static Location getMapMiddleLocation() {
		Location location = null;

		String w = Main.getInstance().getConfig().getString("ragemode.mapspawn." + Library.votedmap + ".middlepoint.world");
		double x = Main.getInstance().getConfig().getDouble("ragemode.mapspawn." + Library.votedmap + ".middlepoint.x");
		double y = Main.getInstance().getConfig().getDouble("ragemode.mapspawn." + Library.votedmap + ".middlepoint.y");
		double z = Main.getInstance().getConfig().getDouble("ragemode.mapspawn." + Library.votedmap + ".middlepoint.z");
		
		if(w != null) {
			location = new Location(Bukkit.getWorld(w), x, y, z);
		}

		return location;
	}
	
	public static Location getRandomPowerUPSpawnLocation() {
		try {
			int spawnnumber = Main.getInstance().getConfig().getInt("ragemode.powerupspawn." + Library.votedmap + ".spawnnumber");
			Random r = new Random();
			int randomzahl = r.nextInt(spawnnumber);
			Location location = null;
			
			for(int i = 0; i < 40; i++) {
				String w = Main.getInstance().getConfig().getString("ragemode.powerupspawn." + Library.votedmap + "." + randomzahl + ".world");
				double x = Main.getInstance().getConfig().getDouble("ragemode.powerupspawn." + Library.votedmap + "." + randomzahl + ".x");
				double y = Main.getInstance().getConfig().getDouble("ragemode.powerupspawn." + Library.votedmap + "." + randomzahl + ".y");
				double z = Main.getInstance().getConfig().getDouble("ragemode.powerupspawn." + Library.votedmap + "." + randomzahl + ".z");
				
				if(w != null) {
					location = new Location(Bukkit.getWorld(w), x, y, z);
					if (Main.isDebug) System.out.print(Strings.debug_powerup_spawn_1 + randomzahl + Strings.debug_powerup_spawn_2 + w + ", " + x	+ " ," + y + ", " + z);
					break;
				}
			}
			
			return location;
		} catch (IllegalArgumentException e) {
			System.out.println(Strings.error_no_map);
			return getLobbyLocation();
		}
	}
}
