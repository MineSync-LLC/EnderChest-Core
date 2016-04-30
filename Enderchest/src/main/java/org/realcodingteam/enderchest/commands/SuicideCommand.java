package org.realcodingteam.enderchest.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.realcodingteam.enderchest.Enderchest;

public class SuicideCommand implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (!sender.hasPermission("enderchest.suicide")) {
			sender.sendMessage(ChatColor.RED + "You lack permission!");
			return true;
		}
		
		if (!(sender instanceof Player)) {
			return true;
		}
		
		if (args.length != 0) {
			sender.sendMessage(ChatColor.RED + "Hey, this command would've killed you! Don't do it!");
			return true;
		}
		
		Player player = (Player) sender;
		EntityDamageEvent ede = new EntityDamageEvent(player, EntityDamageEvent.DamageCause.SUICIDE, Short.MAX_VALUE);
		
		Enderchest.instance.getServer().getPluginManager().callEvent(ede);
		player.damage(Short.MAX_VALUE);
		player.setHealth(0.0);
		player.sendMessage(ChatColor.GOLD + "Goodbye cruel world!");
		Bukkit.broadcastMessage(player.getCustomName() + ChatColor.GOLD + " has committed suicide");
		
		return false;
	}

	
}
