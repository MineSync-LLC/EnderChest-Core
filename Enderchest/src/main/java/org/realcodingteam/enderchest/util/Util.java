package org.realcodingteam.enderchest.util;

import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.SortedMap;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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

	public static void paginate(Player sender, SortedMap<Integer, String> map, int page, int pageLength) {
		sender.sendMessage(ChatColor.GOLD + "List: Page (" + String.valueOf(page) + " of " + (((map.size() % pageLength) == 0) ? map.size() / pageLength : (map.size() / pageLength) + 1));
		page--;
		
		int i = 0, k = 0;

		for (final Entry<Integer, String> e : map.entrySet()) {
			k++;
			if ((((page * pageLength) + i + 1) == k) && (k != ((page * pageLength) + pageLength + 1))) {
				i++;
				sender.sendMessage(ChatColor.YELLOW + " - " + e.getValue());
			}
		}
	}
	
	public static ItemStack getItem(Material mat, int amount, String name, List<String> lore) {
		ItemStack item = new ItemStack(mat, amount);
		ItemMeta meta = item.getItemMeta();
		
		meta.setDisplayName(name);
		meta.setLore(lore);
		item.setItemMeta(meta);
		
		return item;
	}
}
