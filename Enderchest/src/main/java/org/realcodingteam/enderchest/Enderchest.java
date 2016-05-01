package org.realcodingteam.enderchest;

import java.io.File;
import java.util.Collections;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;
import java.util.logging.Level;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
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
	public static SortedMap<Integer, String> costs;
	
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
			
			loadCosts();
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
	
	private void loadCosts() {
		costs = new TreeMap<>(Collections.reverseOrder());
		
		File ess = new File(new File("").getParent() + "/Essentials/worth.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(ess);
		int counter = 0;
		
		for (String s : config.getConfigurationSection("worth").getKeys(true)) {
			costs.put(counter++, s + ": " + config.getDouble("worth." + s));
		}
	}
}
