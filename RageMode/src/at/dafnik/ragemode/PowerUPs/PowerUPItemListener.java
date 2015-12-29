package at.dafnik.ragemode.PowerUPs;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import at.dafnik.ragemode.API.Holograms;
import at.dafnik.ragemode.API.Strings;
import at.dafnik.ragemode.Main.Main;
import at.dafnik.ragemode.Main.Main.Status;

public class PowerUPItemListener implements Listener{
	
	private Main plugin;
	
	private String doubleheart = "�4Double Heart";
	private String claymore = "�dClaymore";
	private String mine = "�5Mine";
	private String jump = "�aJump boost";
	private String slowness = "�1Slowness";
	private String blindness = "�1Blindness";
	private String speed = "�aSpeed";
	private int time = 30*20;
	
	public PowerUPItemListener(Main main){
		this.plugin = main;
	}
	
	@EventHandler
	public void DespawnItem(ItemDespawnEvent event) {
		Entity entity = event.getEntity();
		
		if(Main.status == Status.INGAME) {
			if(plugin.powerupentity.contains(entity)) event.setCancelled(true);
			else event.setCancelled(false);
		} else {
			event.setCancelled(false);
		}
	}
	
	@EventHandler
	public void GetItem(PlayerPickupItemEvent event){
		Player player = event.getPlayer();
		Entity entity = event.getItem();
		Item item = event.getItem();
		
		if(Main.status == Status.PRE_LOBBY || Main.status == Status.LOBBY) {
			if(player.hasPermission("ragemode.admin")) event.setCancelled(false);
			else event.setCancelled(true);
		} else {
			if(plugin.powerupentity.contains(entity)) {
				event.setCancelled(true);
				
				Holograms holo = plugin.poweruphashmap.get(Integer.valueOf(item.getItemStack().getItemMeta().getDisplayName()));
				for(Player players : Bukkit.getOnlinePlayers()) holo.destroy(players);
					
				Random random = new Random();
				int randomint = random.nextInt(7);
				if (randomint == 0) {
					ItemStack i = new ItemStack(Material.REDSTONE, 1);
					ItemMeta imd = i.getItemMeta();
					imd.setDisplayName(doubleheart);
					i.setItemMeta(imd);
					player.getInventory().setItem(5, i);
					player.sendMessage(Strings.powerup_get_0 + doubleheart + Strings.powerup_get_1);
					if (Main.isDebug) System.out.println("[Debug]> PowerUP: " + doubleheart + " | Player: " + player.getName());
				} else if (randomint == 1) {
					ItemStack i = new ItemStack(Material.FLOWER_POT_ITEM, 4);
					ItemMeta imd = i.getItemMeta();
					imd.setDisplayName(claymore);
					i.setItemMeta(imd);
					player.getInventory().setItem(7, i);
					player.sendMessage(Strings.powerup_get_0 + claymore + Strings.powerup_get_1);
					if (Main.isDebug) System.out.println("[Debug]> PowerUP: " + claymore + " | Player: " + player.getName());
				} else if (randomint == 2) {
					ItemStack i = new ItemStack(Material.STONE_PLATE, 2);
					ItemMeta imd = i.getItemMeta();
					imd.setDisplayName(mine);
					i.setItemMeta(imd);
					player.getInventory().setItem(6, i);
					player.sendMessage(Strings.powerup_get_0 + mine + Strings.powerup_get_1);
					if (Main.isDebug) System.out.println("[Debug]> PowerUP: " + mine + " | Player: " + player.getName());
				} else if (randomint == 3) {
					player.sendMessage(Strings.powerup_get_0 + jump + Strings.powerup_get_1);
					player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, time, 4));
					if (Main.isDebug) System.out.println("[Debug]> PowerUP: " + jump + " | Player: " + player.getName());
				} else if (randomint == 4) {
					player.sendMessage(Strings.powerup_get_0 + slowness + Strings.powerup_get_1);
					player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, time, 2));
					if (Main.isDebug) System.out.println("[Debug]> PowerUP: " + slowness + " | Player: " + player.getName());
				} else if (randomint == 5) {
					player.sendMessage(Strings.powerup_get_0 + blindness + Strings.powerup_get_1);
					player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, time, 1));
					if (Main.isDebug) System.out.println("[Debug]> PowerUP: " + blindness + " | Player: " + player.getName());
				} else if (randomint == 6) {
					player.sendMessage(Strings.powerup_get_0 + speed + Strings.powerup_get_1);
					plugin.powerupspeedeffect.add(player);
					Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
						@Override
						public void run() {
							player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, (time-1)*20, 3));
						}
					}, 20);
					Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
						@Override
						public void run() {
							plugin.powerupspeedeffect.remove(player);
						}
					}, time);
					if (Main.isDebug) System.out.println("[Debug]> PowerUP: " + blindness + " | Player: " + player.getName());
				} else {
					System.out.println("[RageMode] ERROR: PowerUPItemListener - Randomizer doesn't work!");
				}
				player.playSound(player.getLocation(), Sound.LEVEL_UP, 1000, 1);
					
				entity.remove();
				plugin.powerupentity.remove(entity);
				plugin.poweruphashmap.remove(Integer.valueOf(item.getItemStack().getItemMeta().getDisplayName()));
			} else {
				event.setCancelled(true);
				entity.remove();
			}
		}
	}
}
