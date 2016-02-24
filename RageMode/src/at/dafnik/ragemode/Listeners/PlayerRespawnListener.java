package at.dafnik.ragemode.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import at.dafnik.ragemode.API.Holograms;
import at.dafnik.ragemode.API.Manager;
import at.dafnik.ragemode.API.Strings;
import at.dafnik.ragemode.API.TeleportAPI;
import at.dafnik.ragemode.API.Title;
import at.dafnik.ragemode.Items.Items;
import at.dafnik.ragemode.Main.Main;

public class PlayerRespawnListener implements Listener{
	
	private Main plugin;
	
	public PlayerRespawnListener(Main main){
		this.plugin = main;
	}
	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent event){
		Player player = event.getPlayer();
		
		//Tp to Map
		event.setRespawnLocation(new TeleportAPI(plugin).getRandomMapSpawnLocations());
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable(){
			 public void run(){
				 if(plugin.ingameplayer.contains(player)) {
					 player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 100, 10));
					 player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 10));
				 }
		     }
		 }, 1);
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable(){
			 public void run(){
		         for(Holograms holo : plugin.poweruplist) {
		        	 holo.display(player);
		         }
		     }
		 }, 20);
		
		if(plugin.spectatorlist.contains(player)) {
			player.setGameMode(GameMode.SPECTATOR);
			
		} else if(plugin.ingameplayer.contains(player)){
			plugin.respawnsafe.add(player);
			if(plugin.powerupspeedeffect.contains(player)) plugin.powerupspeedeffect.remove(player);
			if(plugin.powerupdoublejump.contains(player)) plugin.powerupdoublejump.remove(player);
			
			player.removePotionEffect(PotionEffectType.REGENERATION);
			player.getInventory().setHelmet(null);		
			player.setGameMode(GameMode.SURVIVAL);
			 
			 Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable(){
				 public void run(){	
					 player.removePotionEffect(PotionEffectType.INVISIBILITY);
					 player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
					 plugin.respawnsafe.remove(player);
					 Items.givePlayerItems(player);	
					 Manager.HelmetManagerMethode(player);
			     }
			 }, 60);
		} else System.out.println(Strings.error_not_authenticated_player);
		
		player.getInventory().clear();
		player.setFoodLevel(21);
		player.setVelocity(new Vector(0, 0, 0));
		player.setMaxHealth(20);
		Title.sendTabList(player, "§bRageMode");
	}
}
