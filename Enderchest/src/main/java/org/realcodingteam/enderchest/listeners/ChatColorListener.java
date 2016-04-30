package org.realcodingteam.enderchest.listeners;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.realcodingteam.enderchest.Enderchest;

public class ChatColorListener implements Listener {

	public static Enderchest plugin;
	
	public ChatColorListener(Enderchest instance) {
		plugin = instance;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	public static HashMap<UUID, ChatColor> list = new HashMap<UUID, ChatColor>();
	public static HashMap<UUID, ChatColor> namecolor = new HashMap<UUID, ChatColor>();
	
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		
		if (!(event.getWhoClicked() instanceof Player)) {
			return;
		}
		
		if (event.getCurrentItem() == null) 
			return;
		
		if (!event.getCurrentItem().hasItemMeta()) 
			return;
		
		Player player = (Player) event.getWhoClicked();
		if (ChatColor.stripColor(event.getInventory().getName()).equals("Pick your ChatColor")) {
			event.setResult(Result.DENY);
			
			switch (ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName())) {
				case "No Permission":
					event.getWhoClicked().sendMessage(ChatColor.RED + "You lack permission");
					event.getWhoClicked().closeInventory();
					break;
				case "Light Green":
					event.getWhoClicked().sendMessage(ChatColor.GOLD + "Your chatcolor has been set to " + ChatColor.GREEN + "this");
					event.getWhoClicked().closeInventory();
					list.put(event.getWhoClicked().getUniqueId(), ChatColor.GREEN);
					break;
				case "Light Blue":
					event.getWhoClicked().sendMessage(ChatColor.GOLD + "Your chatcolor has been set to " + ChatColor.AQUA + "this");
					event.getWhoClicked().closeInventory();
					list.put(event.getWhoClicked().getUniqueId(), ChatColor.AQUA);
					break;
				case "Light Red":
					event.getWhoClicked().sendMessage(ChatColor.GOLD + "Your chatcolor has been set to " + ChatColor.RED + "this");
					event.getWhoClicked().closeInventory();
					list.put(event.getWhoClicked().getUniqueId(), ChatColor.RED);
					break;
				case "Light Purple":
					event.getWhoClicked().sendMessage(ChatColor.GOLD + "Your chatcolor has been set to " + ChatColor.LIGHT_PURPLE + "this");
					event.getWhoClicked().closeInventory();
					list.put(event.getWhoClicked().getUniqueId(), ChatColor.LIGHT_PURPLE);
					break;
				case "Yellow":
					event.getWhoClicked().sendMessage(ChatColor.GOLD + "Your chatcolor has been set to " + ChatColor.YELLOW + "this");
					event.getWhoClicked().closeInventory();
					list.put(event.getWhoClicked().getUniqueId(), ChatColor.YELLOW);
					break;
				case "White":
					event.getWhoClicked().sendMessage(ChatColor.GOLD + "Your chatcolor has been set to " + ChatColor.WHITE + "this");
					event.getWhoClicked().closeInventory();
					list.put(event.getWhoClicked().getUniqueId(), ChatColor.WHITE);
					break;
				case "Black":
					event.getWhoClicked().sendMessage(ChatColor.GOLD + "Your chatcolor has been set to " + ChatColor.BLACK + "this");
					event.getWhoClicked().closeInventory();
					list.put(event.getWhoClicked().getUniqueId(), ChatColor.BLACK);
					break;
				case "Dark Blue":
					event.getWhoClicked().sendMessage(ChatColor.GOLD + "Your chatcolor has been set to " + ChatColor.DARK_BLUE + "this");
					event.getWhoClicked().closeInventory();
					list.put(event.getWhoClicked().getUniqueId(), ChatColor.DARK_BLUE);
					break;
				case "Dark Green":
					event.getWhoClicked().sendMessage(ChatColor.GOLD + "Your chatcolor has been set to " + ChatColor.DARK_GREEN + "this");
					event.getWhoClicked().closeInventory();
					list.put(event.getWhoClicked().getUniqueId(), ChatColor.DARK_GREEN);
					break;
				case "Dark Aqua (Cyan)":
					event.getWhoClicked().sendMessage(ChatColor.GOLD + "Your chatcolor has been set to " + ChatColor.DARK_AQUA + "this");
					event.getWhoClicked().closeInventory();
					list.put(event.getWhoClicked().getUniqueId(), ChatColor.DARK_AQUA);
					break;
				case "Dark Red":
					event.getWhoClicked().sendMessage(ChatColor.GOLD + "Your chatcolor has been set to " + ChatColor.DARK_RED + "this");
					event.getWhoClicked().closeInventory();
					list.put(event.getWhoClicked().getUniqueId(), ChatColor.DARK_RED);
					break;
				case "Purple":
					event.getWhoClicked().sendMessage(ChatColor.GOLD + "Your chatcolor has been set to " + ChatColor.DARK_PURPLE + "this");
					event.getWhoClicked().closeInventory();
					list.put(event.getWhoClicked().getUniqueId(), ChatColor.DARK_PURPLE);
					break;
				case "Gold (Orange)":
					event.getWhoClicked().sendMessage(ChatColor.GOLD + "Your chatcolor has been set to " + ChatColor.GOLD + "this");
					event.getWhoClicked().closeInventory();
					list.put(event.getWhoClicked().getUniqueId(), ChatColor.GOLD);
					break;
				case "Gray":
					event.getWhoClicked().sendMessage(ChatColor.GOLD + "Your chatcolor has been set to " + ChatColor.GRAY + "this");
					event.getWhoClicked().closeInventory();
					list.put(event.getWhoClicked().getUniqueId(), ChatColor.GRAY);
					break;
				case "Dark Gray":
					event.getWhoClicked().sendMessage(ChatColor.GOLD + "Your chatcolor has been set to " + ChatColor.DARK_GRAY + "this");
					event.getWhoClicked().closeInventory();
					list.put(event.getWhoClicked().getUniqueId(), ChatColor.DARK_GRAY);
					break;
				case "Blue":
					event.getWhoClicked().sendMessage(ChatColor.GOLD + "Your chatcolor has been set to " + ChatColor.BLUE + "this");
					event.getWhoClicked().closeInventory();
					list.put(event.getWhoClicked().getUniqueId(), ChatColor.BLUE);
					break;
			}
		} else if (ChatColor.stripColor(event.getInventory().getName()).equals("Pick your Name Color")) {
			event.setResult(Result.DENY);
			String pre;
			if (player.hasPermission("namecolor.owner")) {
			    pre = "&4Owner";
			   } else if (player.hasPermission("namecolor.admin")) {
			    pre = "&4Admin";
			   } else if (player.hasPermission("namecolor.dev")) {
			    pre = "&4Dev";
			   } else if (player.hasPermission("namecolor.builder")) {
			    pre = "&bBuilder";
			   } else if (player.hasPermission("namecolor.modplus")) {
				   pre = "&bStaff";
			   } else if (player.hasPermission("namecolor.mod")) {
			    pre = "&6Staff";
			   } else if (player.hasPermission("namecolor.trial")) {
			    pre = "&dTrial";
			   } else {
			    pre = "";
			   }

			
			switch (ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName())) {
			case "No Permission":
				event.getWhoClicked().sendMessage(ChatColor.RED + "You lack permission");
				event.getWhoClicked().closeInventory();
				break;
			case "Light Green":
				event.getWhoClicked().sendMessage(ChatColor.GOLD + "Your Name Color has been set to " + ChatColor.GREEN + "this");
				event.getWhoClicked().closeInventory();
				String lgreen = (pre.equals("") ? pre + "&a" : pre + "&a ");
				pre = "pex user " + event.getWhoClicked().getName() + " prefix \"" + lgreen + "\""; 
				// pex user flash1110 prefix "" &a
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), pre);
				break;
			case "Light Blue":
				event.getWhoClicked().sendMessage(ChatColor.GOLD + "Your Name Color has been set to " + ChatColor.AQUA + "this");
				event.getWhoClicked().closeInventory();
				String lblue = (pre.equals("") ? pre + "&b" : pre + "&b ");
				pre = "pex user " + event.getWhoClicked().getName() + " prefix \"" + lblue + "\""; 
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), pre);
				break;
			case "Light Red":
				event.getWhoClicked().sendMessage(ChatColor.GOLD + "Your Name Color has been set to " + ChatColor.RED + "this");
				event.getWhoClicked().closeInventory();
				String lred = (pre.equals("") ? pre + "&c" : pre + "&c ");
				pre = "pex user " + event.getWhoClicked().getName() + " prefix \"" + lred + "\""; 
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), pre);
				break;
			case "Light Purple":
				event.getWhoClicked().sendMessage(ChatColor.GOLD + "Your Name Color has been set to " + ChatColor.LIGHT_PURPLE + "this");
				event.getWhoClicked().closeInventory();
				String lpurp = (pre.equals("") ? pre + "&d" : pre + "&d ");
				pre = "pex user " + event.getWhoClicked().getName() + " prefix \"" + lpurp + "\""; 
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), pre);
				break;
			case "Yellow":
				event.getWhoClicked().sendMessage(ChatColor.GOLD + "Your Name Color has been set to " + ChatColor.YELLOW + "this");
				event.getWhoClicked().closeInventory();
				String lyel = (pre.equals("") ? pre + "&e" : pre + "&e ");
				pre = "pex user " + event.getWhoClicked().getName() + " prefix \"" + lyel + "\""; 
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), pre);
				break;
			case "White":
				event.getWhoClicked().sendMessage(ChatColor.GOLD + "Your Name Color has been set to " + ChatColor.WHITE + "this");
				event.getWhoClicked().closeInventory();
				String white = (pre.equals("") ? pre + "&f" : pre + "&f ");
				pre = "pex user " + event.getWhoClicked().getName() + " prefix \"" + white + "\""; 
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), pre);
				break;
			case "Black":
				event.getWhoClicked().sendMessage(ChatColor.GOLD + "Your Name Color has been set to " + ChatColor.BLACK + "this");
				event.getWhoClicked().closeInventory();
				String bl = (pre.equals("") ? pre + "&0" : pre + "&0 ");
				pre = "pex user " + event.getWhoClicked().getName() + " prefix \"" + bl + "\""; 
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), pre);
				break;
			case "Dark Blue":
				event.getWhoClicked().sendMessage(ChatColor.GOLD + "Your Name Color has been set to " + ChatColor.DARK_BLUE + "this");
				event.getWhoClicked().closeInventory();
				String blue = (pre.equals("") ? pre + "&1" : pre + "&1 ");
				pre = "pex user " + event.getWhoClicked().getName() + " prefix \"" + blue + "\""; 
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), pre);
				break;
			case "Dark Green":
				event.getWhoClicked().sendMessage(ChatColor.GOLD + "Your Name Color has been set to " + ChatColor.DARK_GREEN + "this");
				event.getWhoClicked().closeInventory();
				String gre = (pre.equals("") ? pre + "&2" : pre + "&2 ");
				pre = "pex user " + event.getWhoClicked().getName() + " prefix \"" + gre + "\""; 
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), pre);
				break;
			case "Dark Aqua (Cyan)":
				event.getWhoClicked().sendMessage(ChatColor.GOLD + "Your Name Color has been set to " + ChatColor.DARK_AQUA + "this");
				event.getWhoClicked().closeInventory();
				String cyan = (pre.equals("") ? pre + "&3" : pre + "&3 ");
				pre = "pex user " + event.getWhoClicked().getName() + " prefix \"" + cyan + "\""; 
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), pre);
				break;
			case "Dark Red":
				event.getWhoClicked().sendMessage(ChatColor.GOLD + "Your Name Color has been set to " + ChatColor.DARK_RED + "this");
				event.getWhoClicked().closeInventory();
				String red = (pre.equals("") ? pre + "&4" : pre + "&4 ");
				pre = "pex user " + event.getWhoClicked().getName() + " prefix \"" + red + "\""; 
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), pre);
				break;
			case "Purple":
				event.getWhoClicked().sendMessage(ChatColor.GOLD + "Your Name Color has been set to " + ChatColor.DARK_PURPLE + "this");
				event.getWhoClicked().closeInventory();
				String pur = (pre.equals("") ? pre + "&5" : pre + "&5 ");
				pre = "pex user " + event.getWhoClicked().getName() + " prefix \"" + pur + "\""; 
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), pre);
				break;
			case "Gold (Orange)":
				event.getWhoClicked().sendMessage(ChatColor.GOLD + "Your Name Color has been set to " + ChatColor.GOLD + "this");
				event.getWhoClicked().closeInventory();
				String gold = (pre.equals("") ? pre + "&6" : pre + "&6 ");
				pre = "pex user " + event.getWhoClicked().getName() + " prefix \"" + gold + "\""; 
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), pre);
				break;
			case "Gray":
				event.getWhoClicked().sendMessage(ChatColor.GOLD + "Your Name Color has been set to " + ChatColor.GRAY + "this");
				event.getWhoClicked().closeInventory();
				String gra = (pre.equals("") ? pre + "&7" : pre + "&7 ");
				pre = "pex user " + event.getWhoClicked().getName() + " prefix \"" + gra + "\""; 
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), pre);
				break;
			case "Dark Gray":
				event.getWhoClicked().sendMessage(ChatColor.GOLD + "Your Name Color has been set to " + ChatColor.DARK_GRAY + "this");
				event.getWhoClicked().closeInventory();
				String dg = (pre.equals("") ? pre + "&8" : pre + "&8 ");
				pre = "pex user " + event.getWhoClicked().getName() + " prefix \"" + dg + "\""; 
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), pre);
				break;
			case "Blue":
				event.getWhoClicked().sendMessage(ChatColor.GOLD + "Your Name Color has been set to " + ChatColor.BLUE + "this");
				event.getWhoClicked().closeInventory();
				String blu = (pre.equals("") ? pre + "&9" : pre + "&9 ");
				pre = "pex user " + event.getWhoClicked().getName() + " prefix \"" + blu + "\""; 
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), pre);
				break;
			
			}
		} else if (ChatColor.stripColor(event.getInventory().getName()).equals("Color Menu")) {
			event.setResult(Result.DENY);
				
			switch (ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName())) {
			case "Chat Color":
				event.getWhoClicked().closeInventory();
				Bukkit.dispatchCommand((Player) event.getWhoClicked(), "cc");
			    break;
			case "Name Color":
				event.getWhoClicked().closeInventory();
				Bukkit.dispatchCommand((Player) event.getWhoClicked(), "nc");
		    	break;
			default:
				event.getWhoClicked().closeInventory();
		    	break;
			}
		}
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		
		if (player.hasPermission("chatcolor.use")) {
			if (list.containsKey(player.getUniqueId())) {
				event.setMessage(list.get(player.getUniqueId()) + event.getMessage());
			}
		}
	}
}
