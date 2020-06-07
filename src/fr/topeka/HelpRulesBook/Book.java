package fr.topeka.HelpRulesBook;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class Book {

	private ItemStack book;
	private Main main;
	public String _title, _author;
	public List<String> _pages;
	
	public Book(Main main) {
		this.main = main;
	}
	
	public boolean createBook() {
		book = new ItemStack(Material.WRITTEN_BOOK, 1);
		BookMeta meta = (BookMeta) book.getItemMeta();
		meta.setTitle(_title);
		meta.setAuthor(_author);
		int lastCaracter = 0;
		BaseComponent[] page = new ComponentBuilder().create();
		List<String> subPage = new ArrayList<String>();
		for (String rawPage: _pages){
			if (rawPage.contains("{") && rawPage.contains("}")) {
				while (rawPage.length() != 0) {
					if (!rawPage.substring(rawPage.indexOf("{", lastCaracter)-1, rawPage.indexOf("{", lastCaracter)+1).contains("\\")) {
						subPage.add(rawPage.substring(0, rawPage.indexOf("{", lastCaracter)));
						rawPage = rawPage.substring(rawPage.indexOf("{", lastCaracter), rawPage.length());
						while (!rawPage.substring(rawPage.indexOf("}", lastCaracter)-1, rawPage.indexOf("}", lastCaracter)+1).contains("\\")) {
							lastCaracter = rawPage.indexOf("}", lastCaracter)+1;
						}
						subPage.add(rawPage.substring(0, rawPage.indexOf("}", lastCaracter)));
						rawPage = rawPage.substring(rawPage.indexOf("}", lastCaracter), rawPage.length());
					}
					else if (rawPage.substring(rawPage.indexOf("{", lastCaracter)-1, rawPage.indexOf("{", lastCaracter)+1).contains("\\")) {
						lastCaracter = rawPage.indexOf("{", lastCaracter);
					}
					else {
						subPage.add(rawPage.replace("&", "§"));
						rawPage = "";
					}
				}
				for (int i = subPage.size()-1; i != 0; i--) {
					if (!subPage.get(i).startsWith("{")) {
						page = new ComponentBuilder(subPage.get(i)).append(page).create();
					}
					else if (subPage.get(i).startsWith("{")) {
						TextComponent eventBuilder = new TextComponent(subPage.get(i).split(",")[0].substring(1));
						if (!subPage.get(i).split(",")[1].equals("")) {
							eventBuilder.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(subPage.get(i).split(",")[1]).create()));
						}
						if(subPage.get(i).split(",")[2].startsWith("true")) {
							if (subPage.get(i).split(",")[2].startsWith("CHANGE_PAGE", 5)) {
								eventBuilder.setClickEvent(new ClickEvent(ClickEvent.Action.CHANGE_PAGE, subPage.get(i).split(",")[2].substring(subPage.get(i).split(",")[2].indexOf(".") + 1, subPage.get(i).split(",")[2].length() - 2)));
							}
							else if (subPage.get(i).split(",")[2].startsWith("COPY_TO_CLIPBOARD", 5)) {
								eventBuilder.setClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, subPage.get(i).split(",")[2].substring(subPage.get(i).split(",")[2].indexOf(".") + 1, subPage.get(i).split(",")[2].length() - 2)));
							}
							else if (subPage.get(i).split(",")[2].startsWith("OPEN_URL", 5)) {
								eventBuilder.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, subPage.get(i).split(",")[2].substring(subPage.get(i).split(",")[2].indexOf(".") + 1, subPage.get(i).split(",")[2].length() - 2)));
							}
							else if (subPage.get(i).split(",")[2].startsWith("RUN_COMMAND", 5)) {
								eventBuilder.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, subPage.get(i).split(",")[2].substring(subPage.get(i).split(",")[2].indexOf(".") + 1, subPage.get(i).split(",")[2].length() - 2)));
							}
							else if (subPage.get(i).split(",")[2].startsWith("SUGGEST_COMMAND", 5)) {
								eventBuilder.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, subPage.get(i).split(",")[2].substring(subPage.get(i).split(",")[2].indexOf(".") + 1, subPage.get(i).split(",")[2].length() - 2)));
							}
						}
						page = new ComponentBuilder(eventBuilder).append(page).create();
					}
				}
			}
			meta.spigot().addPage(page);
		}
		if(book.setItemMeta(meta)) {
			return true;
		}
		return false;
	}
	
	public void loadConfig() {
		_title = main.getConfig().getString("book.title");
		_author = main.getConfig().getString("book.author");
		_pages = main.getConfig().getStringList("book.pages");
	}
	
	public void openBook(Player player) {
		player.openBook(book);
	}
}
