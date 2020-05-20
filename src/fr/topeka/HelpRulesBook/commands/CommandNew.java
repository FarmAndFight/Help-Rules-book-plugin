package fr.topeka.HelpRulesBook.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

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
			main.loadConfig();
			sender.sendMessage("§0[§aHelp-Rules-Book§0]§f Plugin reloaded");
			return true;
		}else {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				ItemStack book = new ItemStack(Material.WRITTEN_BOOK, 1);
				BookMeta meta = (BookMeta) book.getItemMeta();
				meta.setTitle(main._title);
				meta.setAuthor(main._author);
				for(int i=0;i<main._pages.size();i++) {
					meta.addPage(main._pages.get(i));
				}
				if(book.setItemMeta(meta)) {
					player.openBook(book);
					return true;
				}else {
					player.sendMessage("§0[§aHelp-Rules-Book§0]§f An error occured");
					return false;
				}
			}
			sender.sendMessage("§0[§aHelp-Rules-Book§0]§f You need to be a player to view your tutorial");
			return false;
		}
	}

}
