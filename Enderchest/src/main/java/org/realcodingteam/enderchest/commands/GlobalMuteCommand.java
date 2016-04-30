package org.realcodingteam.enderchest.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.realcodingteam.enderchest.util.Util;

public class GlobalMuteCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (!sender.hasPermission("enderchest.globalmute")) {
			sender.sendMessage(ChatColor.RED + "You lack permission.");
			return true;
		}
		
		Util.globalMute = !Util.globalMute; 
		Bukkit.broadcastMessage(ChatColor.GREEN + "Chat is " + (!Util.globalMute? "no longer muted" : "now muted") + "!");
		
		return true;
	}
}
