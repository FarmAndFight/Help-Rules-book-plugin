package fr.topeka.HelpRulesBook.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.topeka.HelpRulesBook.Main;


public class CommandNew implements CommandExecutor {

	private Main main;
	
	public CommandNew(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(args.length == 1 && args[0].toUpperCase().equals("RELOAD") && sender.hasPermission("HelpRulesBook.admin")) {
			main.reloadConfig();
			main._book.loadConfig();
			if(main._book.createBook()) {
				sender.sendMessage("§0[§aHelp-Rules-Book§0]§f Plugin reloaded");
				return true;
			}else {
				sender.sendMessage("§0[§aHelp-Rules-Book§0]§f Error during configuration reload");
				return false;
			}
		}else {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				main._book.openBook(player);
				return true;
				}
				sender.sendMessage("§0[§aHelp-Rules-Book§0]§f You need to be a player to view your tutorial");
				return false;
		}
	}

}
