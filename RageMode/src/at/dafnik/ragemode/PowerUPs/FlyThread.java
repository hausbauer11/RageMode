package at.dafnik.ragemode.PowerUPs;

import org.bukkit.entity.Player;

import at.dafnik.ragemode.Main.Main;

public class FlyThread implements Runnable{

	private Player player;
	Thread thread;
	private Boolean running = false;
	private Main plugin;
	
	public FlyThread(Main main, Player player) {
		this.plugin = main;
		this.player = player;
		
		this.thread = new Thread(this);
	}
	
	public void start() {
		running = true;
		if(running) {
			this.thread.start();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void stop() {
		running = false;
		this.thread.stop();
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		while(running) {
			try{
				Thread.sleep(500);
			}catch (InterruptedException e){
				e.printStackTrace();
			}
			
			if(player.isOnGround()) {
				plugin.powerup_flyparticle.remove(player);
				player.getInventory().setChestplate(null);
				this.stop();
			}
		}		
	}
}