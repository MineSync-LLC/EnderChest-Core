package org.realcodingteam.enderchest;

import java.util.UUID;
import java.util.Map.Entry;
import java.util.logging.Level;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.realcodingteam.enderchest.commands.BounceCommand;
import org.realcodingteam.enderchest.commands.ChatColorCommand;
import org.realcodingteam.enderchest.commands.GlobalMuteCommand;
import org.realcodingteam.enderchest.commands.MenuCommand;
import org.realcodingteam.enderchest.commands.NameColorCommand;
import org.realcodingteam.enderchest.commands.SuicideCommand;
import org.realcodingteam.enderchest.listeners.ChatColorListener;

public class Enderchest extends JavaPlugin {

	public static Enderchest instance;
	
	@Override
	public void onEnable() {
		try {
			instance = this;
			
			getCommand("bounce").setExecutor(new BounceCommand());
			getCommand("globalmute").setExecutor(new GlobalMuteCommand());
			getCommand("suicide").setExecutor(new SuicideCommand());
			getCommand("cc").setExecutor(new ChatColorCommand());
			getCommand("nc").setExecutor(new NameColorCommand());
			getCommand("menu").setExecutor(new MenuCommand());
			
			getConfig().options().copyDefaults(true);
			saveConfig();
			
			if (getConfig().getConfigurationSection("colors") == null) {
				getConfig().createSection("colors");
				saveConfig();
			}
			
			for (String id : getConfig().getConfigurationSection("colors").getKeys(false)) {
				ChatColorListener.list.put(UUID.fromString(id), ChatColor.valueOf(getConfig().getString("colors." + id)));
			}
			
		} catch (Throwable t) {
			
			
			getLogger().log(Level.SEVERE, "There was an error initilzing the plugin", t);
			getServer().getPluginManager().disablePlugin(this);
		}
	}
	
	@Override
	public void onDisable() {
		for (Entry<UUID, ChatColor> entry : ChatColorListener.list.entrySet()) {
			getConfig().set("colors." + entry.getKey().toString(), entry.getValue().name());
			saveConfig();
		}
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (label.equalsIgnoreCase("1")) {
			
		} else if (label.equalsIgnoreCase("2")) {
			
		} else if (label.equalsIgnoreCase("3")) {
			
		}
		
		return true;
	}
}
