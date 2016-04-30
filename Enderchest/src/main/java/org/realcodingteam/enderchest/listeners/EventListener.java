package org.realcodingteam.enderchest.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World.Environment;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.realcodingteam.enderchest.util.Util;

public class EventListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		event.getPlayer().getAttribute(Attribute.GENERIC_ATTACK_SPEED).addModifier(new AttributeModifier("generic.attackSpeed", 99999999.0D, AttributeModifier.Operation.ADD_NUMBER));
	}

	@EventHandler
	public void onArrowCraft(PrepareItemCraftEvent event) {
		Material itemToCheck = event.getRecipe().getResult().getType();
		if (itemToCheck == Material.TIPPED_ARROW || itemToCheck == Material.SPECTRAL_ARROW) {
			event.getInventory().setResult(new ItemStack(Material.AIR));
		}
	}

	@EventHandler
	public void onInventoryEvent(InventoryClickEvent event) {
		ItemStack item = event.getCurrentItem();

		if (item == null)
			return;

		if (item.getType() == Material.SPECTRAL_ARROW || item.getType() == Material.TIPPED_ARROW) {
			event.setCurrentItem(new ItemStack(Material.AIR));
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onPlayerPickup(PlayerPickupItemEvent event) {
		Material mat = event.getItem().getItemStack().getType();
		if (mat == Material.SPECTRAL_ARROW || mat == Material.TIPPED_ARROW || mat == Material.BEDROCK) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		
		if (Util.globalMute && !player.hasPermission("enderchest.globalmute")) {
			player.sendMessage(ChatColor.RED + "Chat is muted");
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onBuild(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		
		if (player.getWorld().getEnvironment() == Environment.NETHER) {
			if (player.getLocation().getBlockY() >= 128) {
				if (!player.hasPermission("enderchest.roof")) {
					player.sendMessage(ChatColor.RED + "You can't build here!");
					event.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler
	public void onDMG(EntityDamageByEntityEvent event) {
		if (!(event.getDamager() instanceof Player)) 
			return;
	
		Player damager = (Player) event.getDamager();
		ItemStack axe = damager.getInventory().getItemInMainHand();
		
		if (axe == null || axe.getType() == Material.AIR)
			return;
		
		if (!axe.getType().name().endsWith("_AXE"))
			return;
		
		int bonus = axe.getEnchantmentLevel(Enchantment.DAMAGE_ALL);
		
		switch (axe.getType()) {
			case WOOD_AXE: case GOLD_AXE:
				event.setDamage(3.0 + bonus);
			case STONE_AXE:
				event.setDamage(4.0 + bonus);
			case IRON_AXE:
				event.setDamage(5.0 + bonus);
			case DIAMOND_AXE:
				event.setDamage(6.0 + bonus);
			default:
		}
		
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		Player killer = event.getEntity().getKiller();
		
		if (killer == null) {
			return;
		}
		
		Player killed = event.getEntity();
		
		if (Util.getsHead(killer)) {
			ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
			SkullMeta meta = (SkullMeta) item.getItemMeta();
			
			meta.setOwner(killed.getName());
			meta.setDisplayName(ChatColor.RESET + killed.getName());
			item.setItemMeta(meta);
			event.getDrops().add(item);
		}
	}
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
		Player killer = event.getEntity().getKiller();
		
		if (killer == null)
			return;
		
		ItemStack hand = killer.getInventory().getItemInMainHand();
		
		if (hand == null || hand.getType() == Material.AIR)
			return;
			
		if (!hand.containsEnchantment(Enchantment.LOOT_BONUS_MOBS))
			return;
		
		if (!killer.hasPermission("enderchest.bonusxp"))
			return;
		
		event.setDroppedExp(event.getDroppedExp() * hand.getEnchantmentLevel(Enchantment.LOOT_BONUS_MOBS));
	}
}
