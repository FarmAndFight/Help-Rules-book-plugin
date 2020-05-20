package fr.topeka.HelpRulesBook;

import java.util.List;

import org.bukkit.plugin.java.JavaPlugin;

import fr.topeka.HelpRulesBook.commands.CommandNew;

public class Main extends JavaPlugin{

	public String _title, _author;
	public List<String> _pages;
	
	@Override
	public void onEnable() {
		saveDefaultConfig();
		loadConfig();
		getCommand("new").setExecutor(new CommandNew(this));
	}
	
	@Override
	public void onDisable() {
		
	}
	
	
	public void loadConfig() {
		this._title = getConfig().getString("book.title");
		this._author = getConfig().getString("book.author");
		this._pages = getConfig().getStringList("book.pages");
	}
	
}
