package org.realcodingteam.enderchest.util;

import java.util.Random;

import org.bukkit.entity.Player;

public class Util {

	public static boolean globalMute = false;
	
	public static boolean getsHead(Player player) {
		double chance = 0.0;
		
		if (player.hasPermission("enderchest.drops.end"))
			return true;
		else if (player.hasPermission("enderchest.drops.mythic"))
			chance = 0.75;
		else if (player.hasPermission("enderchest.drops.emperor"))
			chance = 0.50;
		else if (player.hasPermission("enderchest.drops.king"))
			chance = 0.40;
		else if (player.hasPermission("enderchest.drops.lord"))
			chance = 0.30;
		else if (player.hasPermission("enderchest.drops.general"))
			chance = 0.20;
		else if (player.hasPermission("enderchest.drops.knight"))
			chance = 0.40;
		else if (player.hasPermission("enderchest.drops.soldier"))
			chance = 0.40;
		
		if (chance >= new Random().nextDouble()) 
			return true;
		
		return false;
	}
}
