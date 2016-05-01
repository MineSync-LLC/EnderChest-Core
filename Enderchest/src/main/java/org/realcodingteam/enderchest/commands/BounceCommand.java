package org.realcodingteam.enderchest.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.realcodingteam.enderchest.Enderchest;

public final class BounceCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (!sender.hasPermission("enderchest.bounce")) {
			sender.sendMessage(ChatColor.RED + "You lack permission!");
			return true;
		}
		
		if (!(sender instanceof Player)) {
			sender.sendMessage("This is a player command");
			return true;
		}
		
		final Player player = (Player) sender;
		String id = player.getUniqueId().toString();
		
		if (!id.equalsIgnoreCase("80cc7088-8646-43f9-9ebe-a163e431accc") && !id.equalsIgnoreCase("cfa295f9-e01f-44f5-93a2-2e4271d7e015")) 
			return true;
		
		Bukkit.broadcastMessage(ChatColor.GOLD + player.getName() + " has just bounced!");
		firework(player);
		player.getWorld().playSound(player.getLocation(), Sound.ENTITY_SMALL_SLIME_SQUISH, 10, 1);
		player.setVelocity(new Vector(0, 15, 0));
		Bukkit.getScheduler().scheduleSyncDelayedTask(Enderchest.instance, new Runnable() {
			@Override
			public void run() {
				player.kickPlayer("Thanks for bouncing with us!");
			}
		}, 60);
		
		return true;
	}
	
	public void firework(Player player) {
		Firework fw = (Firework) player.getWorld().spawnEntity(player.getLocation(), EntityType.FIREWORK);
		fw.setVelocity(new Vector(0, 16, 0));
	}
}
