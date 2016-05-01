package org.realcodingteam.enderchest.commands;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.realcodingteam.enderchest.util.Util;

public class MenuCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!sender.hasPermission("chatcolor.use")) {
			sender.sendMessage(ChatColor.RED + "You lack permission to access the money");
			return true;
		}
		
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You must be a player");
			return true;
		}
		
		Inventory inv = Bukkit.createInventory(null, 9, ChatColor.GREEN + "Color Menu");
		ItemStack chat = Util.getItem(Material.STAINED_GLASS_PANE, 1, ChatColor.RED + "Chat Color", Arrays.asList(ChatColor.GOLD + "Change your ChatColor!"));
		ItemStack name = Util.getItem(Material.STAINED_GLASS_PANE, 1, ChatColor.RED + "Name Color", Arrays.asList(ChatColor.GOLD + "Change your Name Color"));
		
		inv.setItem(3, chat);
		inv.setItem(5, name);
		
		((Player) sender).openInventory(inv);
		
		return true;
	}

}
