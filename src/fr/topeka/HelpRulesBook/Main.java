package fr.topeka.HelpRulesBook;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.topeka.HelpRulesBook.commands.CommandNew;
import fr.topeka.HelpRulesBook.listener.PlayerJoinListener;

public class Main extends JavaPlugin{

	public Book _book;
	
	@Override
	public void onEnable() {
		saveDefaultConfig();
		_book = new Book(this);
		_book.loadConfig();
		if(!_book.createBook()) {
			getServer().getPluginManager().disablePlugin(this);
		}
		getCommand("new").setExecutor(new CommandNew(this));
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new PlayerJoinListener(this), this);
	}
	
	@Override
	public void onDisable() {
		
	}
	
}
